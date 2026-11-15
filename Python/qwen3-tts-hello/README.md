# Qwen3 TTS Hello

Showcase Alibaba's [Qwen3-TTS](https://github.com/QwenLM/Qwen3-TTS) 1.7B for
text-to-speech in 10 languages, including English and German: voice cloning,
9 preset speakers with style instructions, and voice design from a description.

Runs on Linux with an NVIDIA GPU via `qwen-tts`, and on Apple Silicon via
[mlx-audio](https://github.com/Blaizzy/mlx-audio). `qwen3_backend.py` picks the
library by platform.

## Setup

```bash
uv sync
```

The first run of each model downloads its weights from Hugging Face.

## Usage

```bash
uv run gradio-qwen3-tts-hello.py        # browser app on http://127.0.0.1:7860
uv run main.py                          # non-interactive one line with speaker Aiden to output.wav

# Proper CLI tool
uv run qwen3-speak.py --help
uv run qwen3-speak.py text.txt --voice andreas-de   # saved voice clone to text.mp3
uv run qwen3-speak.py text.txt --speaker ryan --instruct "Calm audiobook narration"
uv run qwen3-speak.py text.txt --voice andreas-de --precision 4bit  # smaller model, Mac only
uv run qwen3-speak.py text.txt --voice-design "A deep, calm male voice"

./ctts.sh --voice andreas-de notes.md   # Transform readable text, tehm convert to audio

./friendlify.sh book.txt                # Make book.txt text-to-speech - friendly

./run-time-benchmark.sh                 # time Kokoro and every Qwen3 precision on text.txt
```

- Each line of the text is spoken as its own piece.
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

The script reads `text/chapter_NN.txt`, splits each chapter at sentence
boundaries into chunks of at most `--max-chars` characters (default 300),
speaks every chunk, and stitches them into one WAV per chapter in
`audiobook/`. The chunks stay in `audio/chapter_NN/`; a rerun skips existing
chunks, so an interrupted run continues where it stopped. It warns about
chunks that sound too fast or too slow for their word count.

The voice comes from `--speaker` with optional `--instruct`, `--voice NAME`,
`--ref-audio` with optional `--ref-text`, or `--voice-design`.

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
RAM peak is the highest memory MLX held while the model also spoke one short line
Ppeaks vary by up to about 1 GB between runs.

For comparison: Kokoro 2.00 GB RAM idle; 5.71 GB at peak.
