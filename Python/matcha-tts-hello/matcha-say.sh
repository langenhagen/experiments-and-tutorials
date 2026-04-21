#!/usr/bin/env bash
# Tiny wrapper showcasing the `matcha-tts` CLI tool.
#
# Examples:
#
# - ./matcha-say.sh --text "Hello, there!"
# - ./matcha-say.sh --file foo.txt
# - cat foo.txt | ./matcha-say.sh --model matcha_vctk --spk 42
#
set -euo pipefail

model='matcha_ljspeech'
spk='0'
text=''
file=''

while [[ $# -gt 0 ]]; do
    case "$1" in
    --text)
        text="$2"
        shift 2
        ;;
    --file)
        file="$2"
        shift 2
        ;;
    --model)
        model="$2"
        shift 2
        ;;
    --spk)
        spk="$2"
        shift 2
        ;;
    *)
        break
        ;;
    esac
done

# Input precedence:
# 1) piped stdin, 2) --file content, 3) --text argument.
if [[ -p /dev/stdin ]]; then
    text="$(cat)"
elif [[ -n "$file" ]]; then
    text="$(<"$file")"
fi

spk_opt=()
[[ "$model" == "matcha_vctk" ]] && spk_opt=(--spk "$spk")

TORCH_FORCE_NO_WEIGHTS_ONLY_LOAD=1 time uv run matcha-tts --model "$model" "${spk_opt[@]}" --text "$text"
