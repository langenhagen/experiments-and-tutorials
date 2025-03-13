#!/usr/bin/env python3
"""Showcase how to continuously track memory allocations with tracemalloc in an
off-thread.

Note that while peaks are registered, the snapshots may be taken too late to
catch the real memory hogs!

Catching a quick memory peak depends on the off-thread's schedule, its
sleep-duration and how whether the a transient memory hog vanishes between
spotting a peak and taking a snapshot. May well be that some quick object
peaking gets unnoticed, like a quick transient memory hog.

author: andreasl
"""

import threading
import time
import tracemalloc


def hog_memory(n: int):
    return [i for i in range(n)]


def bold(v):
    """Print bold."""
    print(f"\033[1m{v}\033[0m")


def pink(v):
    """Print bold magenta."""
    print(f"\033[1;35m{v}\033[0m")


def track_allocations():
    """Monitor live allocations, intended for off-thread use.

    Track memory peaks and makes a snapshot as soon as a peak is registered.
    It always finds the next global "all-time-high" memory usage maximum, no
    local maxima.

    Note that while peaks are registered, the snapshots may be taken too late to
    catch the real memory hogs!

    The `old_peak` variable controls the minimum usage in bytes to be considered
    a peak. Good as an initial "high-pass filter", i.e. to ignore little memory
    peaks, e.g. at program startup and to focus on the really big fish.

    The `resolution` variable controls the amount of sleep between each check.
    The higher the resolution, the more likely it will be to catch very quick
    transient memory peaks, however, the more often this function will work and
    subsequently the higher the toll on the CPU.
    """
    old_peak = 100_000  # ignore peaks less than 100 kB
    resolution = 0.0001  # controls the resolution of the checking, tweak accordingly

    n_peaks = 0
    while not shutdown_event.is_set():
        time.sleep(resolution)
        current, peak = tracemalloc.get_traced_memory()  # in bytes
        if peak <= old_peak:
            continue

        old_peak = peak

        n_peaks += 1
        pink(f"=== Memory peak {n_peaks} detected: {peak / 1024:.2f} KiB " + "=" * 40)

        # Get current memory allocations
        snapshot = tracemalloc.take_snapshot()
        top_stats = snapshot.statistics("traceback")

        # Print top allocations
        for i, stat in enumerate(
            top_stats[:3], 1
        ):  # Limit output to the top 3 offenders
            print(f"  {stat.size / 1024:.1f} KiB allocated at:")
            bold(f"--- Top {i} offender: ---")
            print(stat)
            print("Traceback:")
            for line in stat.traceback.format():
                print(f"    {line}")
            print("-" * 80)


shutdown_event = threading.Event()  # Signal to stop the thread


def main():
    """Allocate a few short-lived/transient memory hogs and see if we can catch
    them."""

    # Start tracemalloc
    tracemalloc.start(2)  # 5: number of frames to save, defaults to 1

    # Run the tracker in an off-thread
    tracker_thread = threading.Thread(target=track_allocations, daemon=True)
    tracker_thread.start()
    time.sleep(0.5)  # give the Thread some time to get busy

    # Run some code that causes temporary memory spikes
    x = hog_memory(100_000)  # Allocation
    # time.sleep(0.1)
    del x  # Immediate deallocation

    x = hog_memory(200_000)
    # time.sleep(0.1)
    del x

    # Transient allocation; might not get caught because vanishes very quickly
    hog_memory(300_000)

    # Aaand call it a day
    shutdown_event.set()
    tracker_thread.join()

    tracemalloc.stop()


if __name__ == "__main__":
    main()
