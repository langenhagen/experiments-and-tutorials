#!/usr/bin/env python3
"""Showcase the 3rd party library `bark`, a neural network solution for
text-to-speech synthesis.

Uses gigabytes of models and might be slow without GPUs. WTH.

https://github.com/suno-ai/bark


For now, just got:
```
Traceback (most recent call last):
  File "/home/andreasl/Dev/experiments-and-tutorials/Python/bark-hello/bark_hello.py", line 10, in <module>
    from IPython.display import Audio
    ^^^^^^^^^^^^^^^^
  File "/home/andreasl/Dev/experiments-and-tutorials/Python/bark-hello/.venv/lib/python3.11/site-packages/bark/generation.py", line 362, in preload_models
    _ = load_model(
        ^^^^^^^^^^^
  File "/home/andreasl/Dev/experiments-and-tutorials/Python/bark-hello/.venv/lib/python3.11/site-packages/bark/generation.py", line 310, in load_model
    model = _load_model_f(ckpt_path, device)
            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  File "/home/andreasl/Dev/experiments-and-tutorials/Python/bark-hello/.venv/lib/python3.11/site-packages/bark/generation.py", line 246, in _load_model
    _download(model_info["path"], ckpt_path)
  File "/home/andreasl/Dev/experiments-and-tutorials/Python/bark-hello/.venv/lib/python3.11/site-packages/bark/generation.py", line 180, in _download
    raise ValueError("ERROR, something went wrong")
ValueError: ERROR, something went wrong
```

"""
from bark import SAMPLE_RATE, generate_audio, preload_models
from IPython.display import Audio
from scipy.io.wavfile import write as write_wav

# download and load all models
preload_models()

print(f"{SAMPLE_RATE=}")

# generate audio from text
text_prompt = """
    Hello, my name is Suno. And, uh — and I like pizza. [laughs]
    But I also have other interests such as playing tic tac toe.
"""
audio_array = generate_audio(text_prompt)

# play text in notebook
# Audio(audio_array, rate=SAMPLE_RATE)


# write audio to disk
write_wav("bark_generation.wav", SAMPLE_RATE, audio_array)
