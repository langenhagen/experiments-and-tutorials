#!/usr/bin/env python3
"""Go through the English voices for to see how which one sounds.

- voice list based on: https://github.com/nazdridoy/kokoro-tts

Usage:

    python kokoro-voices-hello.py [TEXTFILE]

Examples:

    python kokoro-voices-hello.py              # read standard text
    python kokoro-voices-hello.py myfile.txt   # read text from given file
"""

import sys

import soundfile as sf
from kokoro import KPipeline

if len(sys.argv) > 1:
    filename = sys.argv[1]
    with open(filename, "r", encoding="utf-8") as f:
        text = f.read()
else:
    filename = ""
    text = "The quick brown fox jumps over the lazy dog."

american_female = [
    "af_alloy",
    "af_aoede",
    "af_bella",
    "af_heart",
    "af_jessica",
    "af_kore",
    "af_nicole",
    "af_nova",
    "af_river",
    "af_sarah",
    "af_sky",
]
american_male = [
    "am_adam",
    "am_echo",
    "am_eric",
    "am_fenrir",
    "am_liam",
    "am_michael",
    "am_onyx",
    "am_puck",
]
american_voices = american_female + american_male

british_female = [
    "bf_alice",
    "bf_emma",
    "bf_isabella",
    "bf_lily",
]
british_male = [
    "bm_daniel",
    "bm_fable",
    "bm_george",
    "bm_lewis",
]
british_voices = british_female + british_male

# 🇺🇸 'a' => American English;
# 🇬🇧 'b' => British English
languages2voices = {
    "a": american_voices,
    "b": british_voices,
}
samplerate = 24000

for lang, voices in languages2voices.items():
    pipeline = KPipeline(lang_code=lang)  # make sure lang_code matches voice

    for voice in voices:
        generator = pipeline(
            text,
            voice=voice,
            speed=1,
            split_pattern=r"\n+",
        )
        print(f"{voice=}")
        for i, (gs, ps, audio) in enumerate(generator):
            print(i)  # i: index
            print(gs)  # gs: graphemes/text
            print(ps)  # ps: phonemes
            sf.write(f"voice-samples/{voice}-{i}.wav", audio, samplerate=samplerate)
