#!/usr/bin/env python3
"""Interactive Qwen3-TTS Gradio browser app.

Runs Qwen3-TTS 1.7B on Linux with an NVIDIA GPU or on an Apple Silicon Mac;
see qwen3_backend.py. Each mode uses its own model, which downloads on first use:

- Voice cloning (Base model): speaks in the voice of a reference recording.
  With the transcript of the recording, the clone is closer to the original;
  without it, only the voice's overall character is copied
- Preset voice (CustomVoice model): one of 9 built-in speakers; the
  instruction steers emotion and style, e.g. "Calm, even audiobook narration"
- Voice design (VoiceDesign model): the instruction describes a new voice,
  e.g. "A deep, calm male voice"

Settings:
- Precision: bf16 is full precision; 8, 6, 5, and 4-bit are smaller and
  faster, and only available on the Mac
- Language: auto detects the language from the text
- Temperature: randomness of the delivery; lower is steadier, higher livelier
- Top-k and top-p: how many candidate sounds the model may pick from
- Repetition penalty: discourages repeating the same sounds; on the Mac, voice
  cloning with a transcript uses at least 1.5
- Max tokens: limit per chunk of text; 12.5 tokens are one second of audio
- Max characters: the text is spoken in chunks of at most this size, split
  by chunking.py; a chunk never crosses a blank line
- Below the output, every chunk has its own player with a download button;
  one zip holds all chunk WAV files and chunks.txt with the chunk texts

See:
- https://github.com/QwenLM/Qwen3-TTS
- https://github.com/Blaizzy/mlx-audio
"""

import io
import shutil
import tempfile
import time
import zipfile
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

SAMPLE_TEXT = (
    "Wild jays flick past six quiet brown dogs on the foggy lawn. "
    "Jumping frogs vex bold quick wren by the pond's edge."
)


def generate(
    mode: str,
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
) -> tuple[tuple[int, np.ndarray], str, list[tuple[str, int, np.ndarray]], str]:
    """Speak the text; return the joined audio, an info line, the chunks for
    the chunk players, and the path of the chunk zip.
    """
    if seed != 0:
        qwen3_backend.seed(int(seed))

    start = time.monotonic()
    chunks, sample_rate = qwen3_backend.generate_chunks(
        MODES[mode],
        text,
        language,
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
    )
    processing_seconds = time.monotonic() - start

    audio = np.concatenate([chunk_audio for _, chunk_audio in chunks])
    audio_seconds = len(audio) / sample_rate
    info = (
        f"Audio: {audio_seconds:.1f} s in {len(chunks)} chunks, processing: "
        f"{processing_seconds:.1f} s (including model loading), real-time factor: "
        f"{audio_seconds / processing_seconds:.2f}x"
    )
    return (
        (sample_rate, audio),
        info,
        [(chunk_text, sample_rate, chunk_audio) for chunk_text, chunk_audio in chunks],
        zip_chunks(chunks, sample_rate),
    )


def zip_chunks(chunks: list[tuple[str, np.ndarray]], sample_rate: int) -> str:
    """Write the chunks as 0001.wav, 0002.wav, ... plus chunks.txt, with the
    text of each chunk on its own line, into a zip file and return its path.
    """
    zip_path = Path(tempfile.mkdtemp(prefix="qwen3-chunks-")) / "chunks.zip"
    with zipfile.ZipFile(zip_path, "w") as archive:
        for index, (_, chunk_audio) in enumerate(chunks, start=1):
            wav = io.BytesIO()
            sf.write(wav, chunk_audio, sample_rate, format="WAV")
            archive.writestr(f"{index:04d}.wav", wav.getvalue())
        archive.writestr("chunks.txt", "\n".join(text for text, _ in chunks))
    return str(zip_path)


def save_voice(
    name: str, reference_audio: str | None, reference_text: str
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
        "Preset voice": "Style instruction (optional), e.g. Calm, even audiobook narration",
        "Voice design": "Voice description (required), e.g. A deep, calm male voice with a slight rasp",
    }
    return (
        MODE_DESCRIPTIONS[mode],
        gr.Group(visible=mode == "Voice cloning"),
        gr.Dropdown(visible=mode == "Preset voice"),
        gr.Textbox(label=instruct_labels[mode], interactive=mode != "Voice cloning"),
    )


def main() -> None:
    """Build and launch the Gradio app."""
    with gr.Blocks(title="Qwen3-TTS Hello") as demo:
        with gr.Row():
            with gr.Column():
                mode = gr.Radio(
                    choices=list(MODES),
                    value="Voice cloning",
                    label="Mode",
                    info="Where the voice comes from",
                )
                mode_description = gr.Markdown(MODE_DESCRIPTIONS["Voice cloning"])
                precision = gr.Dropdown(
                    choices=qwen3_backend.PRECISIONS,
                    value=qwen3_backend.PRECISIONS[0],
                    label="Precision (bf16 is full precision; lower bits are smaller and faster, Mac only)",
                )
                text = gr.Textbox(
                    value=SAMPLE_TEXT,
                    label="Text to synthesize (spoken in chunks, see max characters)",
                    max_lines=20,
                )
                max_chars = gr.Slider(
                    50,
                    1000,
                    step=10,
                    value=chunking.DEFAULT_MAX_CHARS,
                    label=f"Max characters per chunk (default {chunking.DEFAULT_MAX_CHARS})",
                    info="The text is spoken chunk by chunk. Line breaks within a paragraph "
                    "become spaces, and each chunk ends at the last sentence end, comma, or "
                    "semicolon that fits; a chunk never crosses a blank line. Longer chunks "
                    "flow more naturally, "
                    "but can drift or repeat, need more memory, and cost more to redo.",
                )
                max_tokens = gr.Slider(
                    100,
                    8192,
                    step=1,
                    value=2048,
                    label="Max tokens per chunk (default 2048)",
                    info="The model produces audio in small pieces called tokens, 12.5 per "
                    "second. This caps how long one chunk of text may become and stops "
                    "runaway generation; 2048 tokens are about 2.7 minutes. Too low "
                    "cuts the speech off: a chunk needs roughly 1 token per character, "
                    "more for slow speech. A cap of 2.5 to 3 tokens per character of "
                    "max characters is safe: 1400 for 500 characters, 800 for 300.",
                )
                language = gr.Dropdown(
                    choices=qwen3_backend.LANGUAGES, value="auto", label="Language"
                )
                with gr.Group(visible=True) as cloning_group:
                    reference_audio = gr.Audio(
                        sources=["upload", "microphone"],
                        type="filepath",
                        label="Reference audio to clone (a few seconds to about 30 s of clean speech)",
                    )
                    gr.Markdown(
                        "Longer is not better. With a transcript, the whole recording goes "
                        "into the model's context before every chunk, which costs time and "
                        "memory, and long recordings raise the risk that the output "
                        "degenerates. Pick a clean clip of up to about 30 seconds."
                    )
                    reference_text = gr.Textbox(
                        label="Transcript of the reference audio (optional)",
                        info="With the exact words of the recording, the model continues "
                        "it as one utterance and also picks up your rhythm, pacing and "
                        "accent. Without it, only the overall voice character is copied. "
                        "The recording then goes into the model's context before every "
                        "chunk, which costs time and memory. A wrong transcript misleads "
                        "the model.",
                    )
                    with gr.Row():
                        voice_name = gr.Textbox(
                            label="Voice name", placeholder="e.g. andreas-de", scale=3
                        )
                        save_button = gr.Button("Save voice", scale=1)
                    with gr.Row():
                        saved_voice = gr.Dropdown(
                            choices=qwen3_backend.saved_voice_names(),
                            value=None,
                            label="Saved voices (in the voices/ folder)",
                            scale=3,
                        )
                        load_button = gr.Button("Load voice", scale=1)
                speaker = gr.Dropdown(
                    choices=list(qwen3_backend.SPEAKERS.items()),
                    value="ryan",
                    label="Speaker",
                    visible=False,
                )
                instruct = gr.Textbox(
                    label="Instruction (not used for voice cloning)", interactive=False
                )
                with gr.Group():
                    temperature = gr.Slider(
                        0.1,
                        1.5,
                        step=0.05,
                        value=0.9,
                        label="Temperature (default 0.9)",
                        info="How adventurous the model is when picking the next bit of sound. "
                        "Below about 0.6 the delivery is steadier but flatter, which gets tiring "
                        "over long audio, and a chunk can run away: it never ends and fills "
                        "the time up to the max tokens limit with silence. Above about 1.0 it "
                        "sounds livelier, but slips, odd stress and mispronunciations become "
                        "likelier. For audiobooks, 0.7 to 0.8 is a good range: a consistent "
                        "narrator across many chunks matters more than liveliness, and one "
                        "bad chunk is easy to miss.",
                    )
                    top_k = gr.Slider(
                        1,
                        200,
                        step=1,
                        value=50,
                        label="Top-k (default 50)",
                        info="The model only picks among this many most likely next sounds. "
                        "Lower sticks to the most expected sounds: fewer slips, but a flatter, "
                        "more predictable delivery. Higher also lets in less expected sounds: "
                        "a livelier intonation, but more slips.",
                    )
                    top_p = gr.Slider(
                        0.1,
                        1.0,
                        step=0.01,
                        value=1.0,
                        label="Top-p (default 1.0)",
                        info="The model only picks among the most likely next sounds that "
                        "together make up this share of the probability. 1.0 means no limit; "
                        "0.8 drops the least likely sounds that make up the last 20 percent. "
                        "Lower sticks to the most expected sounds: fewer slips, but a flatter, "
                        "more predictable delivery. 1.0 also lets in unexpected sounds: a "
                        "livelier intonation, but more slips.",
                    )
                    repetition_penalty = gr.Slider(
                        1.0,
                        2.0,
                        step=0.01,
                        value=1.05,
                        label="Repetition penalty (default 1.05)",
                        info="Makes the model less likely to repeat the same sounds, which "
                        "helps against stuttering and loops. Too high can sound unnatural. "
                        "On the Mac, voice cloning with a transcript uses at least 1.5.",
                    )
                seed = gr.Number(
                    value=0,
                    label="Random seed (0 for random)",
                    info="The same seed with the same settings and text gives the same "
                    "audio, which helps to compare settings.",
                )
                run_button = gr.Button("Generate", variant="primary")

            with gr.Column():
                audio_output = gr.Audio(label="Output Audio")
                info_output = gr.Textbox(label="Generation Info", interactive=False)
                chunks_zip = gr.File(label="All chunks (WAV files and chunks.txt)")
                chunks_state = gr.State([])

                @gr.render(inputs=chunks_state)
                def show_chunks(chunks: list[tuple[str, int, np.ndarray]]) -> None:
                    for index, (chunk_text, sample_rate, chunk_audio) in enumerate(
                        chunks, start=1
                    ):
                        gr.Markdown(f"{index}. {chunk_text}")
                        gr.Audio(
                            (sample_rate, chunk_audio),
                            label=f"Chunk {index}",
                            buttons=["download"],
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
        load_button.click(
            fn=load_voice,
            inputs=saved_voice,
            outputs=[reference_audio, reference_text],
        )
        run_button.click(
            fn=generate,
            inputs=[
                mode,
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
            outputs=[audio_output, info_output, chunks_state, chunks_zip],
        )

    demo.launch()


if __name__ == "__main__":
    main()
