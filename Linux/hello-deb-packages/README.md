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
dpkg-deb --info my-hello-deb-package.deb

dpkg-deb --contents my-hello-deb-package.deb
```

Install like:
```bash
sudo dpkg -i ./my-hello-deb-package.deb
# or
sudo apt install ./my-hello-deb-package.deb
```

If you install via `apt` from a working directory that is not accessible for the user `_apt`, below
e.g. `$HOME`, you may get a warning like:

> N: Download is performed unsandboxed as root as file
> '/home/myuser/myfolder/hello-deb-packages-0.1.0.deb' couldn't be accessed by user '_apt'. -
> pkgAcquire::Run (13: Permission denied)

The stuff gets still installed.
If you want to get rid of this warning, the probably easiest way ist to move the `.deb` file to a
place where the `_apt` user can access it, e.g. to the `/tmp/` folder:
```bash
mv my-hello-deb-package.deb /tmp/
```

Check if files got installed:
```bash
ls /etc/my-hello-deb-package/ /usr/bin/allyourbase.sh
```

Uninstall like:
```bash
sudo dpkg --remove hello-deb-packages-0.1.0  # keeps configs around
# or
sudo apt remove hello-deb-packages-0.1.0  # keeps configs around
# or
sudo dpkg --purge hello-deb-packages-0.1.0  # remove everything; calls `postrm` twice!
```


## Control File
According to ChatGPT, this is an exhaustive, but unproven list of things you can specify:
```
Package: my-package
Version: 1.0
Architecture: amd64
Essential: no
Section: custom
Priority: optional
Maintainer: John Doe <john.doe@example.com>
Installed-Size: 1024
Homepage: http://www.example.com
Description: A brief description of your package.
 This is where you provide a longer description
 of your package. You can use multiple lines.
 .
 You can also use markdown-like syntax for formatting.
Depends: libc6 (>= 2.28), libstdc++6 (>= 8.3), python3 (>= 3.6)
Recommends: some-recommended-package
Suggests: some-suggested-package
Conflicts: conflicting-package (<< 1.0)
Replaces: replaced-package (<< 1.0)
Provides: provided-package
Enhances: enhanced-package
Pre-Depends: some-other-package (>= 1.0)
Replaces: old-package
Built-Using: gcc-9, debhelper (>= 13)
```

Explanation of the control file fields:
- Package: Name of the package.
- Version: Version number of the package.
- Architecture: Supported architecture (e.g., amd64, i386).
- Essential: Indicates whether the package is essential for the system.
- Section: Section of the Debian archive where the package belongs.
- Priority: Priority level of the package.
- Maintainer: Name and email address of the package maintainer.
- Installed-Size: Estimated installed size of the package in kilobytes.
- Homepage: URL of the package's homepage.
- Description: Short and long description of the package.
- Depends: Packages that must be installed for your package to work.
- Recommends: Suggested additional packages.
- Suggests: Optional suggested packages.
- Conflicts: Packages that cannot be installed at the same time.
- Replaces: Packages that your package replaces.
- Provides: Virtual package names provided by your package.
- Enhances: Packages whose functionality is enhanced by your package.
- Pre-Depends: Packages that must be installed before your package is installed.
- Built-Using: List of packages used to build the package.


Further reading: https://www.debian.org/doc/debian-policy/ch-controlfields.html


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
