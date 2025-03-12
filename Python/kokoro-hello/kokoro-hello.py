#!/usr/bin/env python3
"""Showcase the AI powered text-to-speech package Kokoro, famous for being
comparably small and efficient while still having good quality.

based on: https://github.com/hexgrad/kokoro

Usage:

    python kokoro-hello.py [TEXTFILE]

Examples:

    python kokoro-hello.py              # read standard text
    python kokoro-hello.py myfile.txt   # read text from given file
"""

import sys

import soundfile as sf
from kokoro import KPipeline

# 🇺🇸 'a' => American English
# 🇬🇧 'b' => British English
pipeline = KPipeline(lang_code="a")  # make sure lang_code matches voice

if len(sys.argv) > 1:
    filename = sys.argv[1]
    with open(filename, "r", encoding="utf-8") as f:
        text = f.read()
else:
    filename = ""
    text = """
    The sky above the port was the color of television, tuned to a dead channel.
    "It's not like I'm using," Case heard someone say, as he shouldered his way through the crowd around the door of the Chat. "It's like my body's developed this massive drug deficiency."
    It was a Sprawl voice and a Sprawl joke. The Chatsubo was a bar for professional expatriates; you could drink there for a week and never hear two words in Japanese.

    These were to have an enormous impact, not only because they were associated with Constantine, but also because, as in so many other areas, the decisions taken by Constantine (or in his name) were to have great significance for centuries to come. One of the main issues was the shape that Christian churches were to take, since there was not, apparently, a tradition of monumental church buildings when Constantine decided to help the Christian church build a series of truly spectacular structures. The main form that these churches took was that of the basilica, a multipurpose rectangular structure, based ultimately on the earlier Greek stoa, which could be found in most of the great cities of the empire. Christianity, unlike classical polytheism, needed a large interior space for the celebration of its religious services, and the basilica aptly filled that need. We naturally do not know the degree to which the emperor was involved in the design of new churches, but it is tempting to connect this with the secular basilica that Constantine completed in the Roman forum (the so-called Basilica of Maxentius) and the one he probably built in Trier, in connection with his residence in the city at a time when he was still caesar.

    [Kokoro](/kˈOkəɹO/) is an open-weight TTS model with 82 million parameters. Despite its lightweight architecture, it delivers comparable quality to larger models while being significantly faster and more cost-efficient. With Apache-licensed weights, [Kokoro](/kˈOkəɹO/) can be deployed anywhere from production environments to personal projects.
    """

samplerate = 24000
generator = pipeline(
    text,
    voice="af_heart",  # change voice here
    speed=1,
    split_pattern=r"\n+",
)

with sf.SoundFile(f"{filename}-combined.wav", mode="w", channels=1, samplerate=samplerate) as outfile:
    for i, (gs, ps, audio) in enumerate(generator):
        print(i)  # i: index
        print(gs)  # gs: graphemes/text
        print(ps)  # ps: phonemes
        # sf.write(f"{filename}{i}.wav", audio, samplerate=samplerate)  # save each audio fragment
        outfile.write(audio)  # concat all audio fragments to a big file
