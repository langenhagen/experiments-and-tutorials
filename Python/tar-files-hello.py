#!/usr/bin/env python3
"""Showcase usage of *.tar files with Python including with Pax headers.

Pax headers in *.tar files are a mechanism used to extend the traditional .tar
format to support features not covered by the original specifications. They were
introduced to address limitations in the traditional tar format, like handling
long filenames, large file sizes, and extended file attributes. Custom Pax
headers are possible.

You can see the Pax headers in cleartet when opening the tar archive as a
textfile.
"""
import tarfile

print("--- 1 create tar file with Pax headers ---\n")

with tarfile.open("myarchive.tar", "w") as tar:
    files = ["tar-files-hello.py"]
    for filename in files:
        # Create a new member with custom Pax headers
        member = tarfile.TarInfo(name=filename)
        # Set custom Pax headers using the pax_headers attribute
        member.pax_headers = {
            "my_custom_header": "value1",
            "another_header": "42 - sorry no ints allowed",
        }
        # Add the member to the tarfile
        with open(filename, "rb") as f:
            tar.addfile(member, fileobj=f)

print("\n--- 2 inspect tar file with Pax headers ---\n")

with tarfile.open("myarchive.tar", "r") as tar:
    # List all members in the tarfile
    for member in tar.getmembers():
        print(f"File: {member.name}")
        if hasattr(member, "pax_headers"):
            print("Pax Headers:")
            for key, value in member.pax_headers.items():
                print(f"  {key}: {value}")
        else:
            print("No Pax Headers found.")
