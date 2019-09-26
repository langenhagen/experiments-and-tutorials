#!/usr/bin/env python3
"""
Iterates over a given subtree and apply a given program onto each file with a given extension.
Can also filter parts of the subtree

Usage:
  applicat0r.py [-r <root-dir>] [-i <subdir to be ignored> ] [...] [-a program to be applied] [<filetype-glob] [...]

Example:
  applicat0r.py -r "C:\Root Folder\" -i .\NotThisFolder -i ".\This Neither" -a BpmCounter.exe *.mp3 *.wav
"""
import sys
import getopt
import fnmatch
import os, os.path
import re
import subprocess


def main(argv):

    # get command line arguments
    try:
        opts, applicants = getopt.getopt( argv, "r:i:a:")
    except getopt.GetoptError:
        print( "USAGE:\n" + \
               "applicat0r.py [-r <root-dir>] [-i <subdir to be ignored> ] [...] [-a program to be applied] [<filetype-glob] [...]\n" + \
               "EXAMPLE:\n" + \
               "applicat0r.py -r \"C:\\Root Folder\\\" -i .\\NotThisFolder -i \".\This Neither\" -a BpmCounter.exe *.mp3 *.wav\n")
        sys.exit(1);

    # select root dir
    if [v for k, v in opts if k == "-r"]:
        root_dir = [v for k, v in opts if k == "-r"][0]
    else:
        root_dir = '.'

    # select application to apply
    if [v for k, v in opts if k == "-a"]:
        application = [v for k, v in opts if k == "-a"][0]
    else:
        print( "USAGE:\n" + \
               "applicat0r.py [-r <root-dir>] [-i <subdir to be ignored> ] [...] [-a program to be applied] [<filetype-glob] [...]\n" + \
               "EXAMPLE:\n" + \
               "applicat0r.py -r \"C:\\Root Folder\\\" -i .\\NotThisFolder -i \".\This Neither\" -a BpmCounter.exe *.mp3 *.wav\n")
        sys.exit(1);

    # get ignore files
    ignores = [v for k, v in opts if k == "-i"]

    print(  "ROOT DIRECTORY: ", os.path.realpath(root_dir),
            "\nIGNORES: ",ignores,
            "\nAPPLICATION: ", application,
            "\nAPPLICANTS: ", applicants,"\n")

    # make sure the user really wants to proceed
    #if input("Proceed? (y): ") != "y":
    #    sys.exit(2)
    #print('')

    # transform glob patterns to regular expressions
    applicants = r'|'.join([fnmatch.translate(x) for x in applicants]) or r'$.'

    # walk through directory tree
    for root, dirs, files in os.walk( root_dir):

        # ignore dirs
        dirs[:] = [d for d in dirs if os.path.join(root, d) not in ignores]
        del_dirs = [d for d in dirs if re.match(applicants, d)]
        dirs[:] = [d for d in dirs if d not in del_dirs]

        # exclude/include files
        files = [f for f in files if re.match(applicants, f)]
        files = [f for f in files if f not in ignores]

        #apply files
        for file in files:
            print(application + ' \"' + os.path.join(root, file) + '\"')
            try:
                subprocess.call([application, os.path.join(root, file)])
            except OSError as error:
                print( error)

    print( "Done.")


if __name__ == "__main__":
    main(sys.argv[1:])
