"""Delete all given file types, folders with given names
In the subtree. Can also filter parts of the subtree
that shall not be affected.
"""

import fnmatch
import getopt
import os
import os.path
import re
import shutil
import sys

"""USAGE:
eras0r.py [-r <root-dir>] [-i <subdir to be ignored> ] [...] [<filetype-glob/foldername>] [...]
EXAMPLE:
eras0r.py -r "C:\Crunch it\" -i .\_Lib -i .\Liepham *.tlog ipch *.pdb
"""


def main(argv):
    # get command line arguments
    try:
        opts, deletes = getopt.getopt(argv, "r:i:")
    except getopt.GetoptError:
        print(
            "USAGE:\n"
            + "eras0r.py [-r <root-dir>] [-i <subdir to be ignored> ] [...] [<filetype-glob/foldername>] [...]\n"
            + "EXAMPLE:\n"
            + 'eras0r.py -r "C:\\Crunch it\\" -i .\\_Lib -i .\\Liepham *.tlog ipch *.pdb\n'
        )
        sys.exit(1)

    # select root dir
    if [v for k, v in opts if k == "-r"]:
        root_dir = [v for k, v in opts if k == "-r"][0]
    else:
        root_dir = "."

    # get ignore files
    ignores = [v for k, v in opts if k == "-i"]
    print("ROOT DIRECTORY: ", os.path.realpath(root_dir), "\nIGNORES: ",ignores,"\nDELETES:", deletes,"\n")

    # make sure the user really wants to proceed
    if input("About to delete files. Proceed? (y): ") != "y":
        sys.exit(2)

    # transform glob patterns to regular expressions
    deletes = r"|".join([fnmatch.translate(x) for x in deletes]) or r"$."

    # walk through directory tree
    for root, dirs, files in os.walk(root_dir):
        # ignore dirs
        dirs[:] = [d for d in dirs if os.path.join(root, d) not in ignores]
        del_dirs = [d for d in dirs if re.match(deletes, d)]
        dirs[:] = [d for d in dirs if d not in del_dirs]

        # exclude/include files
        files = [f for f in files if re.match(deletes, f)]
        files = [f for f in files if f not in ignores]

        # delete files
        for file in files:
            print("REMOVING:     ", os.path.join(root, file))
            try:
                os.remove(os.path.join(root, file))
            except OSError as error:
                print(error)

        # delete directories
        for dir in del_dirs:
            print("REMOVING DIR: ", os.path.join(root, dir))
            try:
                shutil.rmtree(os.path.join(root, dir))
            except OSError as error:
                print(error)

    print("Done.")


if __name__ == "__main__":
    main(sys.argv[1:])
