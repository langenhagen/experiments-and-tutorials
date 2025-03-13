#!/usr/bin/env python3
"""Showcase the usage of the neat command line line option parsing third party
library hydra

Available via: pip install hydra-core

See: https://cli.dev/docs/intro
"""

import hydra


@hydra.main()
def my_app_1(cfg):
    print(f"type(cfg)={type(cfg)}")
    print(cfg.pretty())


if __name__ == "__main__":
    my_app_1()

# run like
# python hydra-hello.py this is my city  # crashes
# python hydra-hello.py --this --is --my --city  # error:  unrecognized arguments: --this --is --my --city
# python my_app.py db.driver=mysql db.user=omry db.pass=secret  # works

# --> naive impl different from what one expects from posix.
# non posix style handy, though

# hydra can also deal with configuration yaml files.
# there it appears to shine better

# one can apparently enable tab-completion
