# Kokoro Hello

## Setup

```bash
sudo apt update && sudo apt install --yes ffmpeg
uv sync
source .venv/bin/activate
```

Download the ONNX model files (not tracked in git):

```bash
wget --no-clobber https://github.com/thewh1teagle/kokoro-onnx/releases/download/model-files/kokoro-v0_19.onnx
wget --no-clobber https://github.com/thewh1teagle/kokoro-onnx/releases/download/model-files/voices.json
```

## Audiobook Workflow

From ebook to MP3 in four steps. All commands assume an activated venv.

### 1. Split the Ebook Into Chapter Text Files

    ebook-convert BOOK.pdf BOOK.epub    # also converts .mobi/.lit -> .epub
    split-epub.py BOOK.epub --out text/

### 2. Clean the Chapter Text Files

    python clean-chapter-textfiles.py --text-dir text/

### 3. Generate the Audiobook WAVs

    python create-audiobook-from-textfiles.py --text-dir text-clean/                     # whole book
    python create-audiobook-from-textfiles.py --text-dir text-clean/ --only-chapters 12  # test run

### 4. Convert to MP3

    ./convert-wav-files-to-mp3.sh audiobook/
