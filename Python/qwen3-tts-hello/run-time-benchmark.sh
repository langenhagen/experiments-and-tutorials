#!/usr/bin/env bash
#
# Time Kokoro and Qwen3-TTS voice cloning at every precision on text.txt.
# 3 runs each.
# Writes the times and their mean to benchmark-results.txt and the
# audio to benchmark-*.wav next to this script.
#
# Note that kokoro may be running via kokoserv and thus may not have to load the model.

set -euo pipefail
cd "$(dirname "$0")"

# timeit COMMAND...: run the command, show "COMMAND: N s" on the screen
# and print N, its whole seconds, for the caller.
timeit() {
    local start="${EPOCHREALTIME/[.,]/}" seconds # microseconds
    "$@" >/dev/null
    seconds=$(((${EPOCHREALTIME/[.,]/} - start) / 1000000))
    echo "$*: $seconds s" >&2
    echo "$seconds"
}

# report NAME: append the times a, b, c and their mean as a table row.
report() {
    printf '| %-10s | %5d | %5d | %5d | %5d |\n' "$1" "$a" "$b" "$c" $(((a + b + c) / 3)) | tee -a benchmark-results.txt
}

qwen() { uv run qwen3-speak.py text.txt --voice andreas-de --language english "$@"; }

printf 'Seconds per run:\n\n| %-10s | %5s | %5s | %5s | %5s |\n| %-10s | %5s | %5s | %5s | %5s |\n' \
    Setup 'Run 1' 'Run 2' 'Run 3' Mean ---------- ----- ----- ----- ----- >benchmark-results.txt

a=$(timeit kokoro --out-file benchmark-kokoro-1.wav text.txt)
b=$(timeit kokoro --out-file benchmark-kokoro-2.wav text.txt)
c=$(timeit kokoro --out-file benchmark-kokoro-3.wav text.txt)
report Kokoro

a=$(timeit qwen --precision 4bit --out-file benchmark-qwen3-4bit-1.wav)
b=$(timeit qwen --precision 4bit --out-file benchmark-qwen3-4bit-2.wav)
c=$(timeit qwen --precision 4bit --out-file benchmark-qwen3-4bit-3.wav)
report "Qwen3 4bit"

a=$(timeit qwen --precision 5bit --out-file benchmark-qwen3-5bit-1.wav)
b=$(timeit qwen --precision 5bit --out-file benchmark-qwen3-5bit-2.wav)
c=$(timeit qwen --precision 5bit --out-file benchmark-qwen3-5bit-3.wav)
report "Qwen3 5bit"

a=$(timeit qwen --precision 6bit --out-file benchmark-qwen3-6bit-1.wav)
b=$(timeit qwen --precision 6bit --out-file benchmark-qwen3-6bit-2.wav)
c=$(timeit qwen --precision 6bit --out-file benchmark-qwen3-6bit-3.wav)
report "Qwen3 6bit"

a=$(timeit qwen --precision 8bit --out-file benchmark-qwen3-8bit-1.wav)
b=$(timeit qwen --precision 8bit --out-file benchmark-qwen3-8bit-2.wav)
c=$(timeit qwen --precision 8bit --out-file benchmark-qwen3-8bit-3.wav)
report "Qwen3 8bit"

a=$(timeit qwen --precision bf16 --out-file benchmark-qwen3-bf16-1.wav)
b=$(timeit qwen --precision bf16 --out-file benchmark-qwen3-bf16-2.wav)
c=$(timeit qwen --precision bf16 --out-file benchmark-qwen3-bf16-3.wav)
report "Qwen3 bf16"
