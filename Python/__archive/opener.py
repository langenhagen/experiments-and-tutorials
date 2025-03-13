"""Open a random folder from a given directory."""

import os
import random
import subprocess

target_path = "//BARN-BIG/Stuff/Apps und Tools/what_was_considered_to_be_daria/_safe"

dirs = [
    os.path.join(target_path, d)
    for d in os.listdir(target_path)
    if os.path.isdir(os.path.join(target_path, d))
]
dir = random.choice(dirs)
dir = os.path.realpath(dir)
print(dir)
subprocess.Popen(r'explorer "{0}"'.format(dir))
