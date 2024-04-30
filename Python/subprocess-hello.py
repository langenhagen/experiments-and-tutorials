#!/usr/bin/env python3
"""Call a shell command from within Python."""
import os
import subprocess

print("--- 1 run a command ---\n")

# Run a command and get the output
os.chdir("../../scripts")

# shell=True: spawn a shell and run the command there. for some weird reason,
# this has to be set when using command line options, otherwise you have to put
# the options in an array.
#
# stdout=subprocess.PIPE: capture the output for later usage
result = subprocess.run("git log --oneline -5", shell=True, stdout=subprocess.PIPE)

print(f"{result.returncode=}\n")
# apparently, the output is utf-8 encoded, so, decode it prior to reading
print(result.stdout.decode("utf-8"))
print(result.stderr)  # Probably None


print("\n--- 2 subprocess.run() is blocking ---\n")

print('Calling subprocess.run(["sleep", "2"]) ...')
subprocess.run(["sleep", "2"])
print("Done")

print("\n--- 3 subprocess.Popen is non-blocking ---\n")

print('Calling subprocess.Popen("sleep 2", shell=True, stderr=subprocess.DEVNULL,) ...')
subprocess.Popen("sleep 2", shell=True, stderr=subprocess.DEVNULL)
print("Done")
