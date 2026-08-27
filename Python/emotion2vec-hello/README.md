# Emotion2Vec Hello

Showcase `emotion2vec_plus_large` speech emotion recognition with a Gradio
interface.

Upload a speech audio file (ogg, mp3, wav, m4a, ...) and the app runs
utterance-level emotion recognition. Results show the dominant emotion plus a
bar chart of all nine class probabilities.

The model is loaded once at startup through FunASR (a PyTorch library). The
weights (~300 MB) download automatically on first run to the Hugging Face
cache.

## Memory Requirements

CPU RAM-heavy; peaks ~25 GB on a 3-minute clip. Prefer short snippets.

## Temporal Emotion Timeline

`timeline.py` extends this to an emotion timeline for an entire file: it slides
a window across the audio and classifies each window separately. Because each
window is classified on its own, timeline-splitting is less heavy on RAM than
classifying the whole file in a single pass.

When the hop is shorter than the window, consecutive windows overlap. Bigger
windows give more stable, coarser readings; a smaller hop gives a smoother,
costlier timeline.

```bash
uv run python timeline.py
```

The timeline UI exposes the window and hop (seconds) as sliders.

## Nine Emotion Classes

angry, disgusted, fearful, happy, neutral, other, sad, surprised, unknown

## Usage

```bash
uv sync
uv run python main.py
```

Run `timeline.py` for the temporal timeline (see above). Both apps open the
printed local URL (default `http://127.0.0.1:7860`).
`timeline.py` is much less heavy on the RAM because it works on windows
in a sliding window approach.

Optional environment variables:

```bash
EMOTION2VEC_DEVICE=cpu     # or cuda for GPU acceleration (default: cpu)
EMOTION2VEC_HUB=hf         # hf (Hugging Face) or ms (ModelScope) (default: hf)
EMOTION2VEC_WINDOW_SEC=3.0 # timeline window size in seconds (default: 3.0)
EMOTION2VEC_HOP_SEC=1.0    # timeline hop in seconds (default: 1.0)
```

The first inference downloads the model, so the initial run is slow. CPU
inference is portable but slower than GPU.
