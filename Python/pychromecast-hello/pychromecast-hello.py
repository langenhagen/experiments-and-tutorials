#!/usr/bin/env python3
"""Showcase the 3rd party libraries `pychromecast` which allows for interacting
with a Chromecast device and `pytube` which allows for extracting streams from
Youtube.

Nicely, this avoids ads from Youtube videos.

- based on: https://www.youtube.com/watch?v=bStar02cRiU&list=WL&index=8&t=2s&ab_channel=Ben%27sElectricalEscapades
"""
import mimetypes
import re

from pychromecast import CastBrowser, Chromecast, get_listed_chromecasts
from pytube import YouTube


def main():
    chromecasts: list[Chromecast]
    browser: CastBrowser
    # chromecasts, browser = get_listed_chromecasts(["doesn't exit"])
    # print(f"{chromecasts=}\n{browser=}")

    # adjust name accordingly
    # chromecast, browser = get_listed_chromecasts(["My Chromecast"])
    chromecasts, browser = get_listed_chromecasts(["Living Room TV"])
    print(f"{chromecasts=}\n{browser=}")

    cast: Chromecast = chromecasts[0]

    cast.wait()  # wait until you find the thing; maybe not necessary

    print(cast.status)

    mc = cast.media_controller

    # mp4 internet video
    # url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"  # a video
    # mime_type = mimetypes.guess_type(url)[0]

    # yt video
    # had issues with no video or no audio when choosing highest vieo res or other mime type
    # streams = YouTube("https://www.youtube.com/watch?v=BWXWivwhi14&ab_channel=ReginaSpektor").streams
    # stream = streams.filter(type="video").order_by("abr").desc().first()
    # url = stream.url
    # mime_type = stream.mime_type
    # print(f"{streams=}")
    # print(f"{stream=}")

    # yt audio
    # streams = YouTube("https://www.youtube.com/watch?v=BWXWivwhi14&ab_channel=ReginaSpektor").streams
    # stream = streams.filter(type="audio").order_by("abr").desc().first()  # order by abr, i.e. audio quality, pick the highest
    # url = stream.url
    # mime_type = stream.mime_type
    # print(f"{streams=}")
    # print(f"{stream=}")

    # local media
    # a bit tricky; host the files via http, e.g. via `python -m http.server`,
    # # then find out your IP add and compose the URL
    host_ip = "192.168.178.21"  # adjust
    # url = f"http:{host_ip}:8000/Vicetone%20-%20Nevada%20%28ft.%20Cozi%20Zuehlsdorff%29.opus"
    # url = f"http:{host_ip}:8000/02%20Midnight%20Angels.mp3"
    url = f"http:{host_ip}:8000/findet-nemo.m4v"
    mime_type = mimetypes.guess_type(url)[0]

    print(f"{url=}")
    print(f"{mime_type=}")
    mc.play_media(url, mime_type)
    mc.block_until_active()


if __name__ == "__main__":
    main()
