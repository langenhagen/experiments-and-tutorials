# Hello Sidegrading Deb Packages
Showcase the behavior of sidegrading 2 packages.

Basically, if you can install package B over package A with both being distinct packages with
different package names, but providing the same stuff.

Basically, the trinity of the `control` fields:
```
Conflicts: my-sidegradeable-package
Replaces: my-sidegradeable-package
Provides: my-sidegradeable-package
```
Makes the current package go away when installing another one.

The `dpkg --install` commands play nicely. The `apt install` commands may need `--allow-downgrades`.
See Makefile.


See:
- https://www.debian.org/doc/debian-policy/ch-relationships.html#s-conflicts
- https://www.debian.org/doc/debian-policy/ch-relationships.html#s-conflicts
- https://www.debian.org/doc/debian-policy/ch-relationships.html#s-replaces
