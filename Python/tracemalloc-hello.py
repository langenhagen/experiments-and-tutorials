#!/usr/bin/env python3
"""Showcase the usage of the python standard library `tracemalloc`.

Allows for monitoring memory allocation over time, useful for identifying memory leaks.

See docs: https://docs.python.org/3/library/tracemalloc.html
"""

import tracemalloc

print("--- 1 track memory allocations grouped by line number ---\n")

tracemalloc.start()  # start tracing


def allocate_memory(n: int = 100_000, offset: int = 0) -> list[int]:
    """Allocate memory to showcase tracemalloc."""
    print(f"Allocating a list of {n} integers...")
    return list(range(0 + offset, n + offset))


l1 = allocate_memory()

snapshot: tracemalloc.Snapshot = tracemalloc.take_snapshot()

print("Top 10 memory allocation sources:")
top_stats: list[tracemalloc.Statistic] = snapshot.statistics("lineno")
for stat in top_stats[:10]:
    print(stat)

tracemalloc.stop()  # stop tracing

print("\n--- 2 snapshot comparison ---\n")

tracemalloc.start()

l2 = allocate_memory()

snapshot2 = tracemalloc.take_snapshot()
stats: tracemalloc.StatisticDiff = snapshot2.compare_to(snapshot, "lineno")

print("Memory differences after modification:")
for stat in stats:
    print(stat)

tracemalloc.stop()

print("\n--- 3 filtering snapshot by files ---\n")

snapshot_filtered = snapshot.filter_traces(
    (
        tracemalloc.Filter(False, "<unknown>"),
        tracemalloc.Filter(True, __file__),
    )
)

print("Filtered top memory allocation sources:")
top_stats = snapshot_filtered.statistics("lineno")
for stat in top_stats[:10]:
    print(stat)

print("\n--- 4 limit the number of frames ---\n")

# define how deep the malloc tracing should go; store 5 frames; default is 1
# I believe more frames mean slower execution
tracemalloc.start(5)

print("Restarted tracemalloc with limited stack frames.")

l3 = allocate_memory()

snapshot = tracemalloc.take_snapshot()
top_stats = snapshot.statistics("lineno")

print("Top 10 memory allocation sources with limited frames:")
for stat in top_stats[:10]:
    print(stat)

tracemalloc.stop()

print("\n--- 5 group by traceback ---\n")

tracemalloc.start(9)  # more means deeper traceback


def allocate_nested() -> list[int]:
    return allocate_memory()


l4 = allocate_memory()
l5 = allocate_memory()
l6 = allocate_nested()

snapshot = tracemalloc.take_snapshot()

top_stats = snapshot.statistics("lineno")

print("\nTop 10 memory allocation sources by lineno:")
for stat in top_stats[:10]:
    print(stat)

print("\nTop 10 memory allocation sources grouped by traceback:")
top_stats = snapshot.statistics("traceback")
for index, stat in enumerate(top_stats[:10]):
    print(f"Group {index + 1}:")
    for line in stat.traceback.format():
        print(line)
    print(f"Total allocated size: {stat.size / 1024**2:.1f} MiB")

tracemalloc.stop()
