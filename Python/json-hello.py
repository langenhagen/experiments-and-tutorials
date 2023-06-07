"""Showcase some dict to JSON things in Python."""
import json

print("--- 1 to minified JSON ---")
s = json.dumps(
    {
        "myvar": 42,
        "files": [
            "/some/image.jpg",
            "/some/file.txt",
        ],
    },
    separators=(",", ":"),  # makes thing minify
)
print(s)  # {"myvar":42,"files":["/some/image.jpg","/some/file.txt"]}
