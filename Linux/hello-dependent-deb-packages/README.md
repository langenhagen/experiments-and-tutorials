# Hello Deb Packages
Showcase the behavior of a package depending on another.

`Pre-Depends`, actually. Packge B pre-depends on A.

This basically installs package A when you request package B and don't have A yet. If you have A
already, installing package B will not reinstall package A.


Stuff geps automatically pulled in when installing via `apt`, tried with an `apt` repo I set up with
`reprepro`.
