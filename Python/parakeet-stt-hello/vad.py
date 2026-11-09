"""Live speech segmentation with Silero VAD (ONNX on the CPU).

Feed microphone samples in any block size. A segment is finished once a pause
of --min-silence-ms follows speech, or once it reaches --max-segment-seconds.
Transcribing whole segments instead of fixed chunks keeps words and sentences
intact. A short pre-roll before the detected speech start keeps first syllables.
"""

from collections import deque
from pathlib import Path

import numpy as np
import onnxruntime as rt

VAD_REPO = "istupakov/silero-vad-onnx"
VAD_FILE = "silero_vad.onnx"

# Silero VAD at 16 kHz takes 512-sample frames plus 64 samples of context.
SAMPLE_RATE = 16000
FRAME_SAMPLES = 512
CONTEXT_SAMPLES = 64
PRE_ROLL_FRAMES = 8  # 256 ms


def resolve_vad_path() -> Path:
    """Return the cached Silero VAD file, downloading only if it is not cached."""
    from huggingface_hub import hf_hub_download
    from huggingface_hub.errors import LocalEntryNotFoundError

    try:
        return Path(hf_hub_download(VAD_REPO, VAD_FILE, local_files_only=True))
    except LocalEntryNotFoundError:
        return Path(hf_hub_download(VAD_REPO, VAD_FILE))


class SpeechSegmenter:
    """Split a live 16 kHz mono stream into speech segments at pauses."""

    def __init__(
        self,
        min_silence_ms: int,
        max_segment_seconds: float,
        threshold: float = 0.5,
    ) -> None:
        """Load Silero VAD and set the pause and length limits of a segment."""
        self._session = rt.InferenceSession(
            resolve_vad_path(),
            providers=["CPUExecutionProvider"],
        )
        self._threshold = threshold
        # Hysteresis as in Silero's reference code: speech ends below this.
        self._neg_threshold = threshold - 0.15
        self._min_silence_frames = max(1, min_silence_ms * SAMPLE_RATE // 1000 // FRAME_SAMPLES)
        self._max_segment_frames = max(1, int(max_segment_seconds * SAMPLE_RATE / FRAME_SAMPLES))

        self._state = np.zeros((2, 1, 128), dtype=np.float32)
        self._context = np.zeros(CONTEXT_SAMPLES, dtype=np.float32)
        self._unframed = np.empty(0, dtype=np.float32)
        self._pre_roll: deque[np.ndarray] = deque(maxlen=PRE_ROLL_FRAMES)
        self._segment: list[np.ndarray] = []
        self._silent_frames = 0

    @property
    def current_segment(self) -> np.ndarray | None:
        """Return the unfinished segment, or None outside of speech."""
        return np.concatenate(self._segment) if self._segment else None

    def feed(self, samples: np.ndarray) -> list[np.ndarray]:
        """Consume samples and return the segments finished by them."""
        finished = []
        self._unframed = np.concatenate([self._unframed, samples])
        while len(self._unframed) >= FRAME_SAMPLES:
            frame = self._unframed[:FRAME_SAMPLES]
            self._unframed = self._unframed[FRAME_SAMPLES:]
            if (segment := self._step(frame)) is not None:
                finished.append(segment)
        return finished

    def flush(self) -> np.ndarray | None:
        """Return the unfinished segment including unframed samples, and reset."""
        if not self._segment:
            return None
        segment = np.concatenate([*self._segment, self._unframed])
        self._segment = []
        self._unframed = np.empty(0, dtype=np.float32)
        return segment

    def _step(self, frame: np.ndarray) -> np.ndarray | None:
        """Classify one frame and return a segment if this frame finishes one."""
        probability = self._speech_probability(frame)
        if not self._segment:
            if probability >= self._threshold:
                self._segment = [*self._pre_roll, frame]
                self._pre_roll.clear()
                self._silent_frames = 0
            else:
                self._pre_roll.append(frame)
            return None

        self._segment.append(frame)
        self._silent_frames = self._silent_frames + 1 if probability < self._neg_threshold else 0
        if (
            self._silent_frames >= self._min_silence_frames
            or len(self._segment) >= self._max_segment_frames
        ):
            segment = np.concatenate(self._segment)
            self._segment = []
            return segment
        return None

    def _speech_probability(self, frame: np.ndarray) -> float:
        """Run Silero VAD on one frame and carry its recurrent state."""
        output, self._state = self._session.run(
            ["output", "stateN"],
            {
                "input": np.concatenate([self._context, frame])[np.newaxis, :],
                "state": self._state,
                "sr": np.array([SAMPLE_RATE], dtype=np.int64),
            },
        )
        self._context = frame[-CONTEXT_SAMPLES:]
        return float(output[0, 0])
