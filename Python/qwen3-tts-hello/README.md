# Qwen3 TTS Hello

Showcase Alibaba's [Qwen3-TTS](https://github.com/QwenLM/Qwen3-TTS) for
text-to-speech in 10 languages, including English and German:

- Voice cloning
- 9 preset speakers with style instructions
- Voice design from a description

It runs on:

- Linux with an NVIDIA GPU, via `qwen-tts`
- Apple Silicon, via [mlx-audio](https://github.com/Blaizzy/mlx-audio)

`qwen3_backend.py` picks the library by platform.

The model is really good, much slower than Kokoro though. However, Qwen3 works
with chunks and can stream, meaning it can output parts before the whole
generation is done, which makes it real-time capable.

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
uv run qwen3-speak.py text.txt --voice andreas-de --precision 4                # 1.7B model in 4 bit, Mac only
uv run qwen3-speak.py text.txt --voice andreas-de --model 0.6B --precision 4   # smaller 0.6B model in 4 bit, Mac only
uv run qwen3-speak.py text.txt --speaker ryan --model 0.6B                     # 0.6B model in full precision (bf16)
uv run qwen3-speak.py text.txt --voice-design "A deep, calm male voice"        # 1.7B only; there is no 0.6B voice design

uv run chunking.py --help
uv run chunking.py text.txt     # chunk given text, outputs to stdout

./run-time-benchmark.sh         # time Kokoro and every Qwen3 size and precision on text.txt

# untested / dirty helpers

uv run create-audiobook-from-textfiles.py --help
uv run create-audiobook-from-textfiles.py --only-chapters 1  # test run, preset speaker Ryan
uv run create-audiobook-from-textfiles.py --language german --voice andreas-de  # saved voice clone

./ctts.sh --voice andreas-de notes.md   # Transform readable text, then convert to audio
./friendlify.sh book.txt                # Make book.txt text-to-speech-friendly
```

- The text is spoken in chunks of at most `--max-chars` characters (default 500,
  a slider in the Gradio app), split by `chunking.py`. Line breaks within a
  paragraph become spaces, a chunk never crosses a blank line, and short
  sentences are packed together. Each chunk ends:
  - after the last sentence end that fits (`. `, `? `, `! `, also before
    closing quotes),
  - else after the last `; `,
  - else after the last `, `,
  - else at the last space.
- `chunking.py` also runs alone and prints the chunks, one per line with a
  blank line between paragraphs.
- The Gradio app's live player, a separate page at
  http://127.0.0.1:7860/live, plays the speech while the model generates it: in
  pieces of about 2 s on the Mac, whole chunks on Linux.
- The Gradio app lists every chunk below the output with its own player and
  download button. It also offers two links:
  - One downloads all chunks as a zip of WAV files plus `chunks.txt`, which has
    the text of each chunk on its own line.
  - One opens `chunks.txt` in a new browser tab.
- The Gradio app's "Save voice" button stores a voice clone in the git-ignored
  `voices/NAME/` folder; `--voice NAME` uses it.
- `qwen3-speak.py` has the same settings as the Gradio app.
- The voice option picks the model:
  - `--voice` and `--ref-audio` use Base.
  - `--speaker` uses CustomVoice.
  - `--voice-design` uses VoiceDesign.
- `--model` and `--precision` pick the size and precision of that model (see
  Models).
- `ctts.sh` lets Claude rewrite the text as prose with one sentence per line,
  then passes all other options to `qwen3-speak.py`.
- `run-time-benchmark.sh` runs Kokoro and Qwen3 voice cloning three times each
  and writes the seconds per run and the mean to `benchmark-results.txt`.
  - Two of the three renderings of the 0.6B 4-bit variant showed runaway
    effects in the audio, which explains their poor performance.
- `friendlify.sh` lets Claude clean a text, for example one copied from a PDF,
  without rewriting it. It only:
  - joins broken lines,
  - writes all-caps words, acronyms, and abbreviations as spoken,
  - removes Markdown,
  - puts one sentence per line.

  Everything else stays verbatim.

## Audiobooks

```bash
uv run create-audiobook-from-textfiles.py --help
uv run create-audiobook-from-textfiles.py --only-chapters 1          # test run, preset speaker Ryan
uv run create-audiobook-from-textfiles.py --language german --voice andreas-de  # saved voice clone
```

`create-audiobook-from-textfiles.py` does:
- reads `text/chapter_NN.txt`,
- splits each chapter with `chunking.py` into chunks of at most `--max-chars`
  characters (default 500),
- speaks every chunk and stitches them into one WAV per chapter in
  `audiobook/`,
- keeps the chunks in `audio/chapter_NN/`; a rerun skips existing chunks, so an
  interrupted run continues where it stopped,
- warns about chunks that sound too fast or too slow for their word count.

The voice comes from one of:
- `--speaker`, with optional `--instruct`
- `--voice NAME`
- `--ref-audio`, with optional `--ref-text`
- `--voice-design`

`--temperature` (default 0.9) sets the randomness of the delivery.

Guesstimated good values for audiobooks:

┌────────────────────┬───────────────────────────────────────────────────────────────────────────────────────────────┐
│      Setting       │                                        Recommendation                                         │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Mode               │ Voice cloning with a transcript, or Preset voice (Ryan) with "Calm, even audiobook narration" │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Precision          │ bf16                                                                                          │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Language           │ not `auto`, set it, for example to `German`                                                   │
├────────────────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤
│ Max characters     │ 200 to 300 to avoid runaway effects (speakers become faster or crazy)                         │
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

`--temperature` sets how adventurous the model is when picking the next bit of
sound.

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
- A longer recording is not better. It costs more time and memory (6 minutes
  are about 4500 positions in the context, 20 seconds about 250), and a long
  reference audio makes the output degenerate. Use a clean clip of up to about
  30 seconds.
- On the Mac, `mlx-audio` raises the repetition penalty to at least 1.5 in
  this mode to keep the output from looping.
- A wrong transcript misleads the model about which sounds are which words.

## Voice Design

You can specify the voice via short prompt. Tipps, according to my reasearch:

- Prompt length: approx. 1–3 sentences
- Recommended length: 15–40 words
- Shorter prompts often work better.
- Maximum length: approx. 2,048 characters.
- Describable characteristics:
  - Gender
  - Age
  - Vocal range and pitch
  - Voice quality and texture
  - Speaking speed
  - Emotions and mood
  - Accent and pronunciation
  - Speaking style and emphasis
- Avoid vague descriptions.
- Use clear and specific characteristics.

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

`--model` and `--precision` pick the model that loads:

- `--model`, the size:
  - `1.7B` (default)
  - `0.6B`: smaller and faster; can do voice cloning and preset voices,
    not for voice design
- `--precision`:
  - `bf16` (default): full precision
  - `8`, `6`, `5`, `4`: the bits of smaller, faster versions; Mac only, Linux
    uses full precision only

RAM of the 1.7B `mlx-community` models used on the Mac:
```
┌─────────────┬───────────────────┬───────────────────┬───────────────────┐
│             │      Base RAM     │  CustomVoice RAM  │  VoiceDesign RAM  │
│             ├─────────┬─────────┼─────────┬─────────┼─────────┬─────────┤
│ --precision │   idle  │   peak  │   idle  │   peak  │   idle  │   peak  │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ bf16        │ 4.61 GB │ 8.40 GB │ 4.58 GB │ 9.09 GB │ 4.58 GB │ 8.38 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 8           │ 3.17 GB │ 6.79 GB │ 3.14 GB │ 6.83 GB │ 3.14 GB │ 7.40 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 6           │ 2.78 GB │ 6.72 GB │ 2.76 GB │ 7.88 GB │ 2.76 GB │ 6.94 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 5           │ 2.59 GB │ 6.42 GB │ 2.57 GB │ 6.64 GB │ 2.57 GB │ 6.68 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┼─────────┼─────────┤
│ 4           │ 2.40 GB │ 6.41 GB │ 2.37 GB │ 6.50 GB │ 2.37 GB │ 6.70 GB │
└─────────────┴─────────┴─────────┴─────────┴─────────┴─────────┴─────────┘
```

RAM of the 0.6B models, measured the same way (there is no 0.6B VoiceDesign):
```
┌─────────────┬───────────────────┬───────────────────┐
│             │      Base RAM     │  CustomVoice RAM  │
│             ├─────────┬─────────┼─────────┬─────────┤
│ --precision │   idle  │   peak  │   idle  │   peak  │
├─────────────┼─────────┼─────────┼─────────┼─────────┤
│ bf16        │ 2.58 GB │ 6.30 GB │ 2.56 GB │ 6.75 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┤
│ 8           │ 2.05 GB │ 5.60 GB │ 2.04 GB │ 6.36 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┤
│ 6           │ 1.91 GB │ 5.14 GB │ 1.90 GB │ 5.63 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┤
│ 5           │ 1.84 GB │ 5.46 GB │ 1.83 GB │ 6.01 GB │
├─────────────┼─────────┼─────────┼─────────┼─────────┤
│ 4           │ 1.77 GB │ 5.63 GB │ 1.76 GB │ 6.01 GB │
└─────────────┴─────────┴─────────┴─────────┴─────────┘
```

- RAM idle is the memory MLX holds right after loading a model.
- RAM peak is the highest memory MLX held while the model also spoke one short
  line. Peaks vary by up to about 1 GB between runs.
- The peaks were measured on one text:
  - 1.7B: 151 characters (two sentences), which came out as 8 to 17 seconds of
    audio.
  - 0.6B: 133 characters.
- Generation memory grows with the length of the audio, so a longer chunk
  (`--max-chars`) peaks higher than the tables show.

For comparison, Kokoro uses 2.00 GB RAM idle and 5.71 GB at peak.
