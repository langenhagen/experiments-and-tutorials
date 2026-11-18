#!/usr/bin/env python3
"""Interactive Qwen3-TTS Gradio browser app.

Runs Qwen3-TTS (1.7B or the smaller 0.6B) on Linux with an NVIDIA GPU or on an
Apple Silicon Mac; see qwen3_backend.py. Each mode uses its own model, which
downloads on first use:

- Voice cloning (Base model): speaks in the voice of a reference recording.
  With the transcript of the recording, the clone is closer to the original;
  without it, only the voice's overall character is copied
- Preset voice (CustomVoice model): one of 9 built-in speakers; the
  instruction steers emotion and style, e.g. "Calm, even audiobook narration"
- Voice design (VoiceDesign model): the instruction describes a new voice,
  e.g. "A deep, calm male voice"

Settings:
- Model size: 0.6B is smaller and faster than 1.7B, but has no Voice design
- Precision: bf16 is full precision; 8, 6, 5, and 4 are the bits of smaller,
  faster versions, only available on the Mac
- Language: auto detects the language from the text
- Temperature: randomness of the delivery; lower is steadier, higher livelier
- Top-k and top-p: how many candidate sounds the model may pick from
- Repetition penalty: discourages repeating the same sounds; on the Mac, voice
  cloning with a transcript uses at least 1.5
- Max tokens: limit per chunk of text; 12.5 tokens are one second of audio
- Max characters: the text is spoken in chunks of at most this size, split
  by chunking.py; a chunk never crosses a blank line
- The live player, a separate page at /live, plays the speech while the model
  generates it: in pieces of about 2 s on the Mac, whole chunks on Linux
- Below the output, every chunk has its own player with a download button;
  one link downloads a zip of all chunk WAV files and chunks.txt with the
  chunk texts, another opens chunks.txt in a new browser tab

See:
- https://github.com/QwenLM/Qwen3-TTS
- https://github.com/Blaizzy/mlx-audio
"""

import io
import json
import shutil
import tempfile
import time
import zipfile
from collections.abc import Iterator  # noqa: TC003 - Gradio needs it at runtime
from pathlib import Path

import gradio as gr
import numpy as np
import soundfile as sf

import chunking
import qwen3_backend

MODES = {
    "Voice cloning": "Base",
    "Preset voice": "CustomVoice",
    "Voice design": "VoiceDesign",
}

MODE_DESCRIPTIONS = {
    "Voice cloning": "Speaks in **your voice**, or anyone's: upload or record a "
    "reference recording below, ideally with its transcript.",
    "Preset voice": "Speaks with one of **9 built-in speakers**; an optional "
    "instruction steers the style, e.g. *Calm, even audiobook narration*.",
    "Voice design": "Speaks with a **new voice you describe in words**, e.g. "
    "*A deep, calm male voice with a slight rasp*.",
}

# Colored frames around the groups of inputs; the borders work in light and dark mode.
CSS = """
.frame { border: 2px solid; border-radius: 10px; padding: 10px; }
.frame-input { border-color: #3b82f6; }
.frame-chunking { border-color: #10b981; }
.frame-voice { border-color: #a855f7; }
.frame-sampling { border-color: #f59e0b; }
"""

# Generated chunk files, served to the browser for the chunk text link.
CHUNKS_DIR = Path(tempfile.gettempdir()) / "qwen3-tts-hello-chunks"

# The number of the newest run. Each click on Generate counts it up; a running
# generation stops after its current chunk when it sees a newer number.
LATEST_RUN = [0]

# The current run's streamed audio pieces, read by the live player page.
LIVE_STATE = CHUNKS_DIR / "live.json"

# The live player page, a second page of the app at /live. Its JavaScript reads
# LIVE_STATE every half second and plays the pieces back to back on one timeline
# with the Web Audio API, so there are no gaps between them. Pause suspends the
# audio, Stop goes back to the start, and the slider seeks within the audio
# received so far. A new run, seen as a new run name, clears the timeline.
LIVE_HTML = """
<h2>Qwen3-TTS live player</h2>
<p>Plays the speech of the Gradio app while it is being generated.</p>
<p>
  <button id="start">Start listening</button>
  <button id="pause" disabled>Pause</button>
  <button id="stop" disabled>Stop</button>
</p>
<p>
  <input id="seek" type="range" min="0" max="0" step="0.1" value="0"
    style="width: 100%" disabled>
  <span id="time">0.0 / 0.0 s</span>
</p>
<p id="status"></p>
"""
LIVE_JS = """
const stateUrl = "STATE_URL";
const status = element.querySelector("#status");
const pauseButton = element.querySelector("#pause");
const stopButton = element.querySelector("#stop");
const seek = element.querySelector("#seek");
const time = element.querySelector("#time");
let context = null;
let run = null;
let buffers = [];  // the decoded pieces of the current run, in order
let starts = [];  // where each piece starts on the run's timeline, in seconds
let total = 0;  // length of all pieces so far, in seconds
let sources = [];  // the scheduled playback of the pieces
let offset = 0;  // timeline position at the audio context time `anchor`
let anchor = 0;
let dragging = false;

const position = () =>
    Math.min(total, Math.max(0, offset + context.currentTime - anchor));

function stopSources() {
    sources.forEach((source) => source.stop());
    sources = [];
}

function schedule(index) {
    const source = context.createBufferSource();
    source.buffer = buffers[index];
    source.connect(context.destination);
    const when = anchor + starts[index] - offset;
    source.start(Math.max(when, anchor), Math.max(0, offset - starts[index]));
    sources.push(source);
}

function playFrom(from) {
    stopSources();
    offset = from;
    anchor = context.currentTime + 0.05;
    buffers.forEach((buffer, index) => {
        if (starts[index] + buffer.duration > from) {
            schedule(index);
        }
    });
}

async function poll() {
    try {
        const response = await fetch(stateUrl, { cache: "no-store" });
        const state = await response.json();
        if (state.run !== run) {
            stopSources();
            run = state.run;
            buffers = [];
            starts = [];
            total = 0;
            offset = 0;
            anchor = context.currentTime;
        }
        while (buffers.length < state.pieces.length) {
            const url = state.pieces[buffers.length];
            const data = await (await fetch(url)).arrayBuffer();
            const index = buffers.length;
            buffers.push(await context.decodeAudioData(data));
            starts.push(total);
            total += buffers[index].duration;
            // If playback already ran out of audio, continue at the new piece.
            if (anchor + starts[index] - offset < context.currentTime + 0.02) {
                playFrom(starts[index]);
            } else {
                schedule(index);
            }
        }
        status.textContent = `${buffers.length} pieces received`;
    } catch (error) {
        status.textContent = "Waiting for a generation ...";
    }
    setTimeout(poll, 500);
}

function showPosition() {
    seek.max = total.toFixed(1);
    if (!dragging) {
        seek.value = position().toFixed(1);
    }
    time.textContent = `${position().toFixed(1)} / ${total.toFixed(1)} s`;
    requestAnimationFrame(showPosition);
}

element.querySelector("#start").onclick = (event) => {
    context = new AudioContext();
    event.target.disabled = true;
    pauseButton.disabled = false;
    stopButton.disabled = false;
    seek.disabled = false;
    poll();
    showPosition();
};

pauseButton.onclick = async () => {
    if (context.state === "running") {
        await context.suspend();
        pauseButton.textContent = "Play";
    } else {
        await context.resume();
        pauseButton.textContent = "Pause";
    }
};

stopButton.onclick = async () => {
    await context.suspend();
    pauseButton.textContent = "Play";
    playFrom(0);
};

seek.oninput = () => {
    dragging = true;
};

seek.onchange = () => {
    dragging = false;
    playFrom(Number(seek.value));
};
""".replace("STATE_URL", f"/gradio_api/file={LIVE_STATE}")

SAMPLE_TEXT = (
    "Wild jays flick past six quiet brown dogs on the foggy lawn. "
    "Jumping frogs vex bold quick wren by the pond's edge."
)


def save_chunks(
    chunks: list[tuple[str, np.ndarray]],
    sample_rate: int,
) -> tuple[Path, Path]:
    """Write chunks.txt, with the text of each chunk on its own line, and
    chunks.zip, with 0001.wav, 0002.wav, ... plus chunks.txt, into a new
    folder in CHUNKS_DIR and return both paths.
    """
    CHUNKS_DIR.mkdir(exist_ok=True)
    folder = Path(tempfile.mkdtemp(dir=CHUNKS_DIR))
    text_path = folder / "chunks.txt"
    text_path.write_text("\n".join(text for text, _ in chunks) + "\n")
    zip_path = folder / "chunks.zip"
    with zipfile.ZipFile(zip_path, "w") as archive:
        for index, (_, chunk_audio) in enumerate(chunks, start=1):
            wav = io.BytesIO()
            sf.write(wav, chunk_audio, sample_rate, format="WAV")
            archive.writestr(f"{index:04d}.wav", wav.getvalue())
        archive.write(text_path, text_path.name)
    return zip_path, text_path


def finish(
    chunks: list[tuple[str, int, np.ndarray]],
    sample_rate: int,
    start: float,
) -> tuple[np.ndarray, str, str]:
    """Join the chunk audio, save the chunk files, and return the audio, an info
    line, and the links to the chunk zip and chunks.txt.
    """
    processing_seconds = time.monotonic() - start
    audio = np.concatenate([chunk_audio for _, _, chunk_audio in chunks])
    audio_seconds = len(audio) / sample_rate
    info = (
        f"Audio: {audio_seconds:.1f} s in {len(chunks)} chunks, processing: "
        f"{processing_seconds:.1f} s (including model loading), real-time factor: "
        f"{audio_seconds / processing_seconds:.2f}x"
    )
    zip_path, text_path = save_chunks(
        [(chunk_text, chunk_audio) for chunk_text, _, chunk_audio in chunks],
        sample_rate,
    )
    links = (
        f'<a href="/gradio_api/file={zip_path}" download>'
        "Download all chunks (WAV files and chunks.txt)</a><br>"
        f'<a href="/gradio_api/file={text_path}" target="_blank">'
        "Open the chunk texts in a new tab</a>"
    )
    return audio, info, links


def clear_outputs() -> tuple[None, str, list, str]:
    """Start a new run: count up LATEST_RUN and empty the output audio, the info
    line, the chunk players, and the links.
    """
    LATEST_RUN[0] += 1
    return None, "", [], ""


def generate(
    mode: str,
    model_size: str,
    precision: str,
    text: str,
    language: str,
    reference_audio: str | None,
    reference_text: str,
    speaker: str,
    instruct: str,
    temperature: float,
    top_k: float,
    top_p: float,
    repetition_penalty: float,
    max_tokens: float,
    max_chars: float,
    seed: float,
) -> Iterator[tuple[object, ...]]:
    """Speak the text. While the model generates, save each streamed piece of
    audio and list it in LIVE_STATE for the live player page. After each chunk,
    yield a progress line and the chunks so far for the chunk players. With the
    last chunk, yield the joined audio, an info line, and links to the chunk zip
    and chunks.txt instead of the progress line. Stops after the current chunk
    when a newer run has started.
    """
    if mode == "Voice design" and model_size == "0.6B":
        raise gr.Error("Voice design has no 0.6B model. Pick model size 1.7B.")

    if seed != 0:
        qwen3_backend.seed(int(seed))

    run = LATEST_RUN[0]
    CHUNKS_DIR.mkdir(exist_ok=True)
    run_dir = Path(tempfile.mkdtemp(dir=CHUNKS_DIR))
    pieces: list[str] = []
    LIVE_STATE.write_text(json.dumps({"run": run_dir.name, "pieces": pieces}))

    def stream_piece(piece: np.ndarray, sample_rate: int) -> None:
        """Save one streamed piece of audio and add it to LIVE_STATE."""
        if run != LATEST_RUN[0]:
            return
        path = run_dir / f"piece-{len(pieces) + 1:05d}.wav"
        sf.write(path, piece, sample_rate)
        pieces.append(f"/gradio_api/file={path}")
        LIVE_STATE.write_text(json.dumps({"run": run_dir.name, "pieces": pieces}))

    total = len(chunking.split_chunks(text, int(max_chars)))
    start = time.monotonic()
    chunks = []
    for chunk_text, chunk_audio, sample_rate in qwen3_backend.iter_chunks(
        MODES[mode],
        text,
        language,
        model_size=model_size,
        precision=precision,
        speaker=speaker,
        instruct=instruct.strip() or None,
        ref_audio=reference_audio,
        ref_text=reference_text.strip() or None,
        temperature=temperature,
        top_k=int(top_k),
        top_p=top_p,
        repetition_penalty=repetition_penalty,
        max_new_tokens=int(max_tokens),
        max_chars=int(max_chars),
        on_audio=stream_piece,
    ):
        if run != LATEST_RUN[0]:
            return
        chunks.append((chunk_text, sample_rate, chunk_audio))
        if len(chunks) < total:
            progress = (
                f"Chunk {len(chunks)} of {total} done after "
                f"{time.monotonic() - start:.0f} s (including model loading)"
            )
            yield None, progress, chunks.copy(), ""
        else:
            audio, info, links = finish(chunks, sample_rate, start)
            yield (sample_rate, audio), info, chunks, links


def save_voice(
    name: str,
    reference_audio: str | None,
    reference_text: str,
) -> gr.Dropdown:
    """Store the reference audio and transcript in voices/NAME/."""
    name = name.strip()
    if not name or reference_audio is None:
        raise gr.Error("Saving a voice needs a name and a reference audio.")
    voice_dir = qwen3_backend.VOICES_DIR / name
    voice_dir.mkdir(parents=True, exist_ok=True)
    for old_reference in voice_dir.glob("reference.*"):
        old_reference.unlink()
    shutil.copy(reference_audio, voice_dir / f"reference{Path(reference_audio).suffix}")
    (voice_dir / "transcript.txt").write_text(reference_text.strip())
    gr.Info(f"Saved voice {name} to {voice_dir}")
    return gr.Dropdown(choices=qwen3_backend.saved_voice_names(), value=name)


def load_voice(name: str | None) -> tuple[str, str]:
    """Return the reference audio path and transcript of a saved voice."""
    if not name:
        raise gr.Error("Pick a saved voice first.")
    reference, transcript = qwen3_backend.load_saved_voice(name)
    return reference, transcript or ""


def change_mode(mode: str) -> tuple[str, gr.Group, gr.Dropdown, gr.Textbox]:
    """Show the inputs that the selected mode uses."""
    instruct_labels = {
        "Voice cloning": "Instruction (not used for voice cloning)",
        "Preset voice": "Style instruction (optional), e.g. Calm, even audiobook "
        "narration",
        "Voice design": "Voice description (required), e.g. A deep, calm male voice "
        "with a slight rasp",
    }
    return (
        MODE_DESCRIPTIONS[mode],
        gr.Group(visible=mode == "Voice cloning"),
        gr.Dropdown(visible=mode == "Preset voice"),
        gr.Textbox(label=instruct_labels[mode], interactive=mode != "Voice cloning"),
    )


def build_demo() -> gr.Blocks:
    """Build the Gradio app."""
    with gr.Blocks(title="Qwen3-TTS Hello") as blocks:
        with gr.Row():
            with gr.Column():
                with gr.Column(elem_classes=["frame", "frame-input"]):
                    mode = gr.Radio(
                        choices=list(MODES),
                        value="Voice cloning",
                        label="Mode",
                        info="Where the voice comes from",
                    )
                    mode_description = gr.Markdown(MODE_DESCRIPTIONS["Voice cloning"])
                    model_size = gr.Dropdown(
                        choices=qwen3_backend.MODEL_SIZES,
                        value=qwen3_backend.MODEL_SIZES[0],
                        label="Model size (0.6B is smaller and faster than 1.7B, "
                        "but has no Voice design)",
                    )
                    precision = gr.Dropdown(
                        choices=qwen3_backend.PRECISIONS,
                        value=qwen3_backend.PRECISIONS[0],
                        label="Precision (bf16 is full precision; 8, 6, 5, and 4 "
                        "are the bits of smaller, faster versions, Mac only)",
                    )
                    text = gr.Textbox(
                        value=SAMPLE_TEXT,
                        label="Text to synthesize (spoken in chunks, see max "
                        "characters)",
                        max_lines=20,
                    )
                    language = gr.Dropdown(
                        choices=qwen3_backend.LANGUAGES,
                        value="auto",
                        label="Language",
                    )
                with gr.Column(elem_classes=["frame", "frame-chunking"]):
                    max_chars = gr.Slider(
                        50,
                        1000,
                        step=10,
                        value=chunking.DEFAULT_MAX_CHARS,
                        label="Max characters per chunk (default "
                        f"{chunking.DEFAULT_MAX_CHARS})",
                        info="The text is spoken chunk by chunk. Line breaks within "
                        "a paragraph become spaces, and each chunk ends after the "
                        "last sentence end that fits, else after a semicolon or "
                        "comma; a chunk never crosses a blank line. Longer chunks "
                        "flow more naturally, but can drift or repeat, need more "
                        "memory, and cost more to redo.",
                    )
                    max_tokens = gr.Slider(
                        100,
                        8192,
                        step=1,
                        value=2048,
                        label="Max tokens per chunk (default 2048)",
                        info="The model produces audio in small pieces called "
                        "tokens, 12.5 per second. This caps how long one chunk of "
                        "text may become and stops runaway generation; 2048 tokens "
                        "are about 2.7 minutes. Too low cuts the speech off: a chunk "
                        "needs roughly 1 token per character, more for slow speech. "
                        "A cap of 2.5 to 3 tokens per character of max characters is "
                        "safe: 1400 for 500 characters, 800 for 300.",
                    )
                with gr.Column(elem_classes=["frame", "frame-voice"]):
                    with gr.Group(visible=True) as cloning_group:
                        reference_audio = gr.Audio(
                            sources=["upload", "microphone"],
                            type="filepath",
                            label="Reference audio to clone (a few seconds to about "
                            "30 s of clean speech)",
                        )
                        gr.Markdown(
                            "Longer is not better. With a transcript, the whole "
                            "recording goes into the model's context before every "
                            "chunk, which costs time and memory, and long recordings "
                            "raise the risk that the output degenerates. Pick a "
                            "clean clip of up to about 30 seconds.",
                        )
                        reference_text = gr.Textbox(
                            label="Transcript of the reference audio (optional)",
                            info="With the exact words of the recording, the model "
                            "continues it as one utterance and also picks up your "
                            "rhythm, pacing and accent. Without it, only the overall "
                            "voice character is copied. The recording then goes into "
                            "the model's context before every chunk, which costs "
                            "time and memory. A wrong transcript misleads the model.",
                        )
                        with gr.Row():
                            voice_name = gr.Textbox(
                                label="Voice name",
                                placeholder="e.g. andreas-de",
                                scale=3,
                            )
                            save_button = gr.Button("Save voice", scale=1)
                        saved_voice = gr.Dropdown(
                            choices=qwen3_backend.saved_voice_names(),
                            value=None,
                            label="Saved voices (in the voices/ folder)",
                            info="Picking a voice loads its recording and transcript.",
                        )
                    speaker = gr.Dropdown(
                        choices=list(qwen3_backend.SPEAKERS.items()),
                        value="ryan",
                        label="Speaker",
                        visible=False,
                    )
                    instruct = gr.Textbox(
                        label="Instruction (not used for voice cloning)",
                        interactive=False,
                    )
                with gr.Column(elem_classes=["frame", "frame-sampling"]):
                    with gr.Group():
                        temperature = gr.Slider(
                            0.1,
                            2.0,
                            step=0.05,
                            value=0.9,
                            label="Temperature (default 0.9)",
                            info="How adventurous the model is when picking the next "
                            "bit of sound. Below about 0.6 the delivery is steadier "
                            "but flatter, which gets tiring over long audio, and a "
                            "chunk can run away: it never ends and fills the time up "
                            "to the max tokens limit with silence. Above about 1.0 "
                            "it sounds livelier, but slips, odd stress and "
                            "mispronunciations become likelier. For audiobooks, 0.7 "
                            "to 0.8 is a good range: a consistent narrator across "
                            "many chunks matters more than liveliness, and one bad "
                            "chunk is easy to miss.",
                        )
                        top_k = gr.Slider(
                            1,
                            200,
                            step=1,
                            value=50,
                            label="Top-k (default 50)",
                            info="The model only picks among this many most likely "
                            "next sounds. Lower sticks to the most expected sounds: "
                            "fewer slips, but a flatter, more predictable delivery. "
                            "Higher also lets in less expected sounds: a livelier "
                            "intonation, but more slips.",
                        )
                        top_p = gr.Slider(
                            0.1,
                            1.0,
                            step=0.01,
                            value=1.0,
                            label="Top-p (default 1.0)",
                            info="The model only picks among the most likely next "
                            "sounds that together make up this share of the "
                            "probability. 1.0 means no limit; 0.8 drops the least "
                            "likely sounds that make up the last 20 percent. Lower "
                            "sticks to the most expected sounds: fewer slips, but a "
                            "flatter, more predictable delivery. 1.0 also lets in "
                            "unexpected sounds: a livelier intonation, but more "
                            "slips.",
                        )
                        repetition_penalty = gr.Slider(
                            1.0,
                            2.0,
                            step=0.01,
                            value=1.05,
                            label="Repetition penalty (default 1.05)",
                            info="Makes the model less likely to repeat the same "
                            "sounds, which helps against stuttering and loops. Too "
                            "high can sound unnatural. On the Mac, voice cloning "
                            "with a transcript uses at least 1.5.",
                        )
                    seed = gr.Number(
                        value=0,
                        label="Random seed (0 for random)",
                        info="The same seed with the same settings and text gives "
                        "the same audio, which helps to compare settings.",
                    )
                run_button = gr.Button("Generate", variant="primary")

            with gr.Column():
                gr.HTML(
                    '<a href="/live" target="_blank">Open the live player in a new '
                    "tab</a>: it plays the speech while it is being generated.",
                )
                audio_output = gr.Audio(label="Output Audio")
                info_output = gr.Textbox(label="Generation Info", interactive=False)
                chunks_links = gr.HTML()
                chunks_state = gr.State([])

                @gr.render(inputs=chunks_state)
                def show_chunks(chunks: list[tuple[str, int, np.ndarray]]) -> None:
                    for index, (chunk_text, sample_rate, chunk_audio) in enumerate(
                        chunks,
                        start=1,
                    ):
                        # Keys keep finished chunks' widgets when new ones arrive.
                        gr.Markdown(f"{index}. {chunk_text}", key=f"text-{index}")
                        gr.Audio(
                            (sample_rate, chunk_audio),
                            label=f"Chunk {index}",
                            buttons=["download"],
                            key=f"audio-{index}",
                        )

        mode.change(
            fn=change_mode,
            inputs=mode,
            outputs=[mode_description, cloning_group, speaker, instruct],
        )
        save_button.click(
            fn=save_voice,
            inputs=[voice_name, reference_audio, reference_text],
            outputs=saved_voice,
        )
        # .input fires only when the user picks a voice, not after "Save voice".
        saved_voice.input(
            fn=load_voice,
            inputs=saved_voice,
            outputs=[reference_audio, reference_text],
        )
        outputs = [audio_output, info_output, chunks_state, chunks_links]
        run_button.click(fn=clear_outputs, outputs=outputs).then(
            fn=generate,
            inputs=[
                mode,
                model_size,
                precision,
                text,
                language,
                reference_audio,
                reference_text,
                speaker,
                instruct,
                temperature,
                top_k,
                top_p,
                repetition_penalty,
                max_tokens,
                max_chars,
                seed,
            ],
            outputs=outputs,
            # Queue the new generation even while the old one is still stopping.
            trigger_mode="multiple",
        )

    with blocks.route("Live player", "/live", show_in_navbar=False):
        gr.HTML(LIVE_HTML, js_on_load=LIVE_JS)

    return blocks


# At module level, so `gradio gradio-qwen3-tts-hello.py` can reload it on save.
demo = build_demo()

if __name__ == "__main__":
    demo.launch(allowed_paths=[str(CHUNKS_DIR)], css=CSS)
