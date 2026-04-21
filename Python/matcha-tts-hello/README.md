# TTS Hello / Matcha Hello

Showcase `matcha-tts` for text-to-speech.

## Usage

```bash
uv run python main.py --help

time uv run python main.py                      # Generate speech with default text
time uv run python main.py --text "Hi there!"   # Generate speech with custom text
time uv run python main.py --file my-lines.txt  # Generate speech from a text file

# Use another model and write to a folder
time uv run python main.py \
  --model matcha_vctk \
  --text "Testing another model" \
  --output-folder outputs

# CPU mode
time uv run python main.py --cpu
```

## Tiny CLI Wrapper

`matcha-say.sh` wraps the CLI tool `matcha-tts`.

```bash
./matcha-say.sh --text "Hello, there!"
./matcha-say.sh --file foo.txt
cat foo.txt | ./matcha-say.sh --model matcha_vctk --spk 42
```

The first run downloads model files and can take a while.
