# Qwen3 TTS Hello

Showcase Alibaba's [Qwen3-TTS](https://github.com/QwenLM/Qwen3-TTS) 1.7B for
text-to-speech in 10 languages, including English and German: voice cloning,
9 preset speakers with style instructions, and voice design from a description.

Runs on Linux with an NVIDIA GPU via `qwen-tts`, and on Apple Silicon via
[mlx-audio](https://github.com/Blaizzy/mlx-audio). `qwen3_backend.py` picks the
library by platform.

The model is really good, much slower than kokoro though.
However, the Qwen3 works with chunks and can stream, meaning it can output parts
before the whole generation is done, which makes it real-time capable.

## Setup

```bash
uv sync
```

The first run of each model downloads its weights from Hugging Face.

## Usage

```bash
# Interactive Gradio webapp
uv run gradio-qwen3-tts-hello.py                           # browser app on http://127.0.0.1:7860
uv run gradio gradio-qwen3-tts-hello.py                    # same but with hot reloading
GRADIO_SERVER_PORT=1234 uv run gradio-qwen3-tts-hello.py   # http://127.0.0.1:1234
uv run main.py                          # non-interactive one line with speaker Aiden to output.wav

# Proper CLI tool
uv run qwen3-speak.py --help
uv run qwen3-speak.py text.txt --voice andreas-de   # saved voice clone to text.mp3
uv run qwen3-speak.py text.txt --speaker ryan --instruct "Calm audiobook narration"
uv run qwen3-speak.py text.txt --voice andreas-de --precision 4bit  # smaller model, Mac only
uv run qwen3-speak.py text.txt --voice-design "A deep, calm male voice"

uv run chunking.py --help
uv run chunking.py text.txt     # chunk given text, outputs to stdout

./run-time-benchmark.sh         # time Kokoro and every Qwen3 precision on text.txt

# untested / dirty helpers

uv run create-audiobook-from-textfiles.py --help
uv run create-audiobook-from-textfiles.py --only-chapters 1  # test run, preset speaker Ryan
uv run create-audiobook-from-textfiles.py --language german --voice andreas-de  # saved voice clone

./ctts.sh --voice andreas-de notes.md   # Transform readable text, then convert to audio
./friendlify.sh book.txt                # Make book.txt text-to-speech - friendly
```

- The text is spoken in chunks of at most `--max-chars` characters (default
  500, a slider in the Gradio app), split by `chunking.py`: line breaks within
  a paragraph become spaces, and each chunk ends after the last sentence end
  that fits (`. `, `? `, `! `, also before closing quotes), else after the
  last `; `, else after the last `, `, else at the last space; a chunk never
  crosses a blank line. Short sentences are packed together.
- `chunking.py` also runs alone and prints the chunks, one per line with a
  blank line between paragraphs.
- The Gradio app's live player, a separate page at
  http://127.0.0.1:7860/live, plays the speech while the model generates it:
  in pieces of about 2 s on the Mac, whole chunks on Linux.
- The Gradio app lists every chunk below the output with its own player and
  download button. One link downloads all chunks as a zip of WAV files plus
  `chunks.txt`, which has the text of each chunk on its own line; another
  opens `chunks.txt` in a new browser tab.
- The Gradio app's "Save voice" button stores a voice clone in the git-ignored
  `voices/NAME/` folder; `--voice NAME` uses it.
- `qwen3-speak.py` has the same settings as the Gradio app.
- The voice option picks the model: `--voice` and `--ref-audio` use Base,
  `--speaker` uses CustomVoice, `--voice-design` uses VoiceDesign.
  `--precision` picks its size (see Models).
- `ctts.sh` lets Claude rewrite the text as prose with one sentence per line,
  then passes all other options to `qwen3-speak.py`.
- `run-time-benchmark.sh` runs Kokoro and Qwen3 voice cloning (4bit up to bf16)
  three times each and writes the seconds per run and the mean to
  `benchmark-results.txt`; the audio lands in `benchmark-kokoro-N.wav` and
  `benchmark-qwen3-PRECISION-N.wav`.
- `friendlify.sh` lets Claude clean a text, for example one copied from a PDF,
  without rewriting it: it joins broken lines, writes all-caps words, acronyms,
  and abbreviations as spoken, removes Markdown, and puts one sentence per
  line. Everything else stays verbatim.

## Audiobooks

```bash
uv run create-audiobook-from-textfiles.py --help
uv run create-audiobook-from-textfiles.py --only-chapters 1          # test run, preset speaker Ryan
uv run create-audiobook-from-textfiles.py --language german --voice andreas-de  # saved voice clone
```

The script reads `text/chapter_NN.txt`, splits each chapter with `chunking.py`
into chunks of at most `--max-chars` characters (default 500),
speaks every chunk, and stitches them into one WAV per chapter in
`audiobook/`. The chunks stay in `audio/chapter_NN/`; a rerun skips existing
chunks, so an interrupted run continues where it stopped. It warns about
chunks that sound too fast or too slow for their word count.

The voice comes from `--speaker` with optional `--instruct`, `--voice NAME`,
`--ref-audio` with optional `--ref-text`, or `--voice-design`. `--temperature`
(default 0.9) sets the randomness of the delivery.

Guesstimated good values for audiobooks:

┌────────────────────┬───────────────────────────────────────────────────────────────────────────────────────────────┐
│      Setting       │                                        Recommendation                                         │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Mode               │ Voice cloning with a transcript, or Preset voice (Ryan) with "Calm, even audiobook narration" │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Precision          │ bf16, or 8bit if bf16 is too slow                                                             │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Language           │ not `auto`, set it, for example to `German`                                                   │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Max characters     │ 200 - 300 to avoid runaway effects (speakers become faster or crazy)                          │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Temperature        │ 0.7 to 0.8                                                                                    │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Top-k, top-p       │ Leave at 50 and 1.0                                                                           │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Repetition penalty │ Leave at 1.05 (implicit 1.5)                                                                  │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Max tokens         │ 2.667 times the max characters --> ~600 for 200 characters, ~800 for 300 characters           │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Seed               │ Set it                                                                                        │
└────────────────────┴───────────────────────────────────────────────────────────────────────────────────────────────┘


## Temperature

`--temperature` (default 0.9) sets how adventurous the model is when picking
the next bit of sound.

- Below about 0.6 the delivery is steadier but flatter, which gets tiring over
  hours of audio. A chunk can also run away: it never ends and fills the time
  up to the max tokens limit with silence.
- Above about 1.0 it sounds livelier, but slips, odd stress, and
  mispronunciations become more likely.
- For audiobooks, 0.7 to 0.8 is a good range. The same narrator across
  thousands of chunks matters more than liveliness per chunk, and one bad
  chunk is easy to miss.

## Voice Cloning Transcript

A transcript is the exact words spoken in the reference recording. It is
optional (`--ref-text`, or `transcript.txt` of a saved voice) and changes how
the voice is cloned:

- With a transcript, the model gets the recording as audio plus its words and
  continues it as one utterance. It picks up rhythm, pacing, accent, and
  delivery as well as the timbre.
- Without a transcript, only a speaker embedding is taken from the recording:
  a fixed summary of the voice's overall character. It sounds like a similar
  voice, not like you speaking.
- With a transcript, the whole recording goes into the model's context before
  every chunk, which costs time and memory; a longer recording costs more.
- A longer recording is not better. It costs more time and memory (6 minutes are
  about 4500 positions in the context, 20 seconds about 250), a long reference
  audio makes the output degenerate. Use a clean clip of up to about 30 seconds.
- On the Mac, `mlx-audio` raises the repetition penalty to at least 1.5 in
  this mode to keep the output from looping.
- A wrong transcript misleads the model about which sounds are which words.

## Models

```
┌───────────────┬─────────────┬────────────────────────────────────────────────────┐
│ Mode          │ Model       │ Voice comes from                                   │
├───────────────┼─────────────┼────────────────────────────────────────────────────┤
│ Voice cloning │ Base        │ A reference recording, ideally with its transcript │
├───────────────┼─────────────┼────────────────────────────────────────────────────┤
│ Preset voice  │ CustomVoice │ 9 preset speakers, steered by a style instruction  │
├───────────────┼─────────────┼────────────────────────────────────────────────────┤
│ Voice design  │ VoiceDesign │ A description of the voice in words                │
└───────────────┴─────────────┴────────────────────────────────────────────────────┘
```

On the Mac, `--precision` also offers 8, 6, 5, and 4-bit versions.
Linux uses full precision only.

RAM of the `mlx-community` models used on the Mac:

```
┌─────────────┬───────────────────┬───────────────────┬───────────────────┐
│             │      Base RAM     │  CustomVoice RAM  │  VoiceDesign RAM  │
│             ├─────────┬─────────┼─────────┬─────────┼─────────┬─────────┤
│ --precision │   idle  │   peak  │   idle  │   peak  │   idle  │   peak  │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ bf16        │ 4.61 GB │ 8.40 GB │ 4.58 GB │ 9.09 GB │ 4.58 GB │ 8.38 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 8bit        │ 3.17 GB │ 6.79 GB │ 3.14 GB │ 6.83 GB │ 3.14 GB │ 7.40 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 6bit        │ 2.78 GB │ 6.72 GB │ 2.76 GB │ 7.88 GB │ 2.76 GB │ 6.94 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 5bit        │ 2.59 GB │ 6.42 GB │ 2.57 GB │ 6.64 GB │ 2.57 GB │ 6.68 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 4bit        │ 2.40 GB │ 6.41 GB │ 2.37 GB │ 6.50 GB │ 2.37 GB │ 6.70 GB │
└─────────────┴─────────┴─────────┴─────────┴─────────┴─────────┴─────────┘
```

RAM idle is the memory MLX holds right after loading a model.  
RAM peak is the highest memory MLX held while the model also spoke one short line.
Peaks vary by up to about 1 GB between runs.  
The peaks were measured on one text of 151 characters (two sentences), which
came out as 8 to 17 seconds of audio. Generation memory grows with the length
of the audio, so a longer chunk (`--max-chars`) peaks higher than the table
shows.

For comparison: Kokoro 2.00 GB RAM idle; 5.71 GB at peak.
