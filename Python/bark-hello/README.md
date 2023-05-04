# Bark Hello
Showcase Bark, a transformer-based text-to-audio model.

https://github.com/suno-ai/bark

Did not get it to work locally, but got it to work on a PC rig with a 2080 TI. I then opened an
interactive jupyter session and forwared the port via SSH:

On the host rig, I did:
```bash
jupyter lab --ip=0.0.0.0 --port=8888
```

On the Client, I did:
```bash
ssh -L "0.0.0.0:8888:mia-mpsi.lan:8888" "andreasl@mia-mpsi.lan"
```
