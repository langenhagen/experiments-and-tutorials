# Hello Deb Packages
Showcase the structure of a custom debian package and how to build/install it.

The `.deb` files are actually Unix Archive (`.ar`) files. You can create, inspect and manipulate
those files with the tool `ar` manually.

Build like:
```bash
dpkg-deb --build my-hello-deb-package/
```

Get info about the debfile:
```bash
dpkg-deb --info hello-deb-packages_1.0_amd64.deb

dpkg-deb --contents hello-deb-packages_1.0_amd64.deb
```

Install like:
```bash
sudo dpkg -i ./hello-deb-packages_1.0_amd64.deb
# or
sudo apt install ./hello-deb-packages_1.0_amd64.deb
```

If you install via `apt` from a working directory that is not accessible for the user `_apt`, below
e.g. `$HOME`, you may get a warning like:

> N: Download is performed unsandboxed as root as file
> '/home/myuser/myfolder/hello-deb-packages.deb' couldn't be accessed by user '_apt'. -
> pkgAcquire::Run (13: Permission denied)

The stuff gets still installed.
If you want to get rid of this warning, the probably easiest way ist to move the `.deb` file to a
place where the `_apt` user can access it, e.g. to the `/tmp/` folder:
```bash
mv hello-deb-packages_1.0_amd64.deb /tmp/
```

Check if files got installed:
```bash
ls /etc/my-hello-deb-package/ /usr/bin/allyourbase.sh
```

Uninstall like:
```bash
sudo dpkg --remove hello-deb-packages  # keeps configs around
# or
sudo apt remove hello-deb-packages  # keeps configs around
# or
sudo dpkg --purge hello-deb-packages  # remove everything; calls `postrm` twice!
```


## Control File
See: https://www.debian.org/doc/debian-policy/ch-controlfields.html

Wrt binary packages, for the impatient:
```
Package: my-package                                 # Specifies the unique name of the Debian package, mandatory.
Version: 1.0                                        # Version number of the package, could be anything I guess, mandatory.
Architecture: amd64                                 # Supported architecture (e.g., amd64, i386), mandatory.
Essential: no                                       # Indicates whether the package is essential for the system.
Section: custom                                     # Section of the Debian archive where the package belongs, recommended.
Priority: optional                                  # Priority level of the package, recommended, https://www.debian.org/doc/debian-policy/ch-archive.html#priorities
Maintainer: John Doe <john.doe@example.com>         # mandatory
Installed-Size: 1024                                # Estimated installed size of the package in kilobytes, optional.
Homepage: http://www.example.com
Description: A brief description of your package.   # Short and long description of the package, mandatory
 This is where you provide a longer description
 of your package. You can use multiple lines.
 .
 Simple point '.' means empty line.
 Allegedly, markdown-like syntax formatting is OK.
Depends: libc6 (>= 2.28), libstdc++6 (>= 8.3), python3 (>= 3.6)   # Packages that must be installed for your package to work.
Recommends: some-recommended-package                              # Suggested additional packages.
Suggests: some-suggested-package                                  # Optional suggested packages.
Conflicts: conflicting-package (<< 1.0)                           # Packages that cannot be installed at the same time.
Replaces: replaced-package (<< 1.0)                               # Packages that your package replaces.
Provides: provided-package                                        # Virtual package names provided by your package, see below.
Enhances: enhanced-package                                        # Packages whose functionality is enhanced by your package.
Pre-Depends: some-other-package (>= 1.0)                          # Packages that must be installed before your package is installed.
Built-Using: gcc-9, debhelper (>= 13)                             # List of packages used to build the package.
Package-Type:                                                     # deb for binary packages, udeb for micro binary packages, omit for source packages.
```

### The `Provides` Key
Let's say there is a package `foo` that depends on `bar`:
```
Package: foo
Depends: bar
```
and someone else releases an enhanced version of `bar`. They can say:
```
Package: bar-plus
Provides: bar
```
Mean's `bar-plus` also satisfies the dependency in `foo`. I.e., a client that has `bar-plus`
installed can install `foo`, because its dependency is fulfilled.

See: https://www.debian.org/doc/debian-policy/ch-relationships.html

### The `Sections` Key
See: https://www.debian.org/doc/debian-policy/ch-archive.html#s-subsections

> The Debian archive maintainers provide the authoritative list of sections. At present, they are:
> admin, cli-mono, comm, database, debug, devel, doc, editors, education, electronics, embedded,
> fonts, games, gnome, gnu-r, gnustep, graphics, hamradio, haskell, httpd, interpreters,
> introspection, java, javascript, kde, kernel, libdevel, libs, lisp, localization, mail, math,
> metapackages, misc, net, news, ocaml, oldlibs, otherosfs, perl, php, python, ruby, rust, science,
> shells, sound, tasks, tex, text, utils, vcs, video, web, x11, xfce, zope. The additional section
> debian-installer contains special packages used by the installer and is not used for normal Debian
> packages.

## Preinst, Postinst, Prerm and Postrm
- are optional
- must be executable, between permission set 0555 - 0777
- they can be any kind of executable, e.g. a Python script, e.g. `#!/usr/bin/python3 -BEs`
- they can do basically anything. I/O with the user, file system operations,... you name it


## Logs for Installing / Uninstalling
Calls to `apt install` and `apt remove` get logged into `/var/log/dpkg.log`:
```bash
tail -f /var/log/dpkg.log
```


## Notes:
- Might be that the bundled files in the `.deb` file are compressed as `.zst` files. Debian 11 does
  not support them. Support was only added for Debian 12. See:
  https://unix.stackexchange.com/questions/669004/zst-compression-not-supported-by-apt-dpkg Thus, I
  specify the compression type on the command line when building the `.deb` file via:
  `dpkg-deb -Zgzip --build`
