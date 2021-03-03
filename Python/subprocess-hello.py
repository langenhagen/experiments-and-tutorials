#!/usr/bin/env python3
"""Call a shell command from within Python."""
import os
import subprocess

# Run a command and get the output
os.chdir("../../scripts")

# shell=True: spawn a shell and run the command there
#             for some weird reason, this has to be set when using
#             command line options
# stdout=subprocess.PIPE: capture the output for later usage
result = subprocess.run(
    "git log --oneline",
    shell=True,
    stdout=subprocess.PIPE
)

print(f"Return code: {result.returncode}")
# apparently, the output is utf-8 encoded, so, decode it prior to reading
print(result.stdout.decode("utf-8"))
print(result.stderr)  # Probably None
