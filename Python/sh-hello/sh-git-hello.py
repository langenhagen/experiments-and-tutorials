#!/usr/bin/env python3
"""Showcase the usage of the git subpackage of the 3rd party package `sh`."""
# import sh
from sh import git


def main():
    """Program entry point."""

    print("--- 1 git status ---")
    status = git.status()  # sane as git("status")
    print("status:")
    print(status)

    porcelain_status = git.status("--porcelain")
    print(f"{porcelain_status=}")

    diff_files = git.diff("--numstat", "HEAD..HEAD~1")
    print("diff_files:")
    print(diff_files)

    print("--- 2 diffs can be problematic with diff-so-fancy - would hang ---")
    # git.diff("HEAD..HEAD~1")

if __name__ == "__main__":
    main()
