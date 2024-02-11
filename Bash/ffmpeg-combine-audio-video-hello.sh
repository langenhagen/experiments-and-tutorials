#!/bin/bash
# Showcase how to extract and combine audio/video streams from video files via ffmpeg.
# It's generally fast, like milliseconds/seconds fast.

# Extract audio
# -vn: video no I guess
time ffmpeg -i myvideo.mp4 -vn -acodec copy audio.aac

# Extract video from the first file
# -an: audio no I guess
time ffmpeg -i myvideo.mp4 -an -vcodec copy video-only.mp4

# Combine the good video and audio into a new file
# -c:v specifies the video codec to be used
# -c:a specifies the audio codec to be used
# --strict experimental` enables the use of experimental AAC encoding features
time ffmpeg -i video-only.mp4 -i audio.aac -c:v copy -c:a aac -strict experimental combined.mp4
