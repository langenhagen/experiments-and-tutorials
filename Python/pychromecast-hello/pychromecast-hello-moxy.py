#!/usr/bin/env python3
"""Use the 3rd party library `pychromecast` for interacting with the chromecast
in your Moxy hotel room

Usage:

    python pychromecast-hello-moxy.py <ROOM_NR>

Example:

    python pychromecast-hello-moxy.py 123
"""
import mimetypes
import sys

from pychromecast import CastBrowser, Chromecast, get_listed_chromecasts


def main():

    room_nr = sys.argv[1]
    chromecast_name = f"Room_{room_nr}"

    chromecasts: list[Chromecast]
    browser: CastBrowser
    chromecasts, browser = get_listed_chromecasts([chromecast_name])
    print(f"{chromecasts=}\n{browser=}")

    cast: Chromecast = chromecasts[0]

    cast.wait()  # wait until you find the thing; maybe not necessary

    print(cast.status)

    mc = cast.media_controller

    # mp4 internet video
    # url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"  # a video
    url = "https://ad.bigtimedelivery.net/_v13/ce033f5e9185158cd20b13ec651d3f225c1b74ea6b806055c53c84b141981d30f713842eac7b84c1cb3fe406e8a819a17ae73b067198be37fed03188a300b74688d0a5aff002886e835d6f20a370a665fabd7a4c1468bdf529ff09947205912cf54a0efdce66cce004311a8ef2ff3551dbccf1579d744476975945da31a23a293f29da3acc2bf0bb49b93cf33e584986/720/index.m3u8"
    mime_type = mimetypes.guess_type(url)[0]


    # yt video
    # had issues with no video or no audio when choosing highest vieo res or other mime type
    # streams = YouTube("https://www.youtube.com/watch?v=BWXWivwhi14&ab_channel=ReginaSpektor").streams
    # stream = streams.filter(type="video").order_by("abr").desc().first()
    # url = stream.url
    # mime_type = stream.mime_type
    # print(f"{streams=}")
    # print(f"{stream=}")

    # local media
    # a bit tricky; host the files via http, e.g. via `python -m http.server`,
    # then find out your IP add and compose the URL
    # host_ip = "172.20.12.91"  # adjust
    # url = f"http:{host_ip}:8000/star-wars-the-bad-batch-s01-e02.m4v"
    # mime_type = mimetypes.guess_type(url)[0]

    print(f"{url=}")
    print(f"{mime_type=}")
    mc.play_media(url, mime_type)
    mc.block_until_active()


if __name__ == "__main__":
    main()
