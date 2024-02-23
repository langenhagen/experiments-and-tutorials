# Hello Upgrade Deb Packages
Showcase the behavior of a package updating.

The `DEBIAN/control/Version` string is evidently used as an indicator for determining which version is
bigger.

also try `sudo apt install` thingo and see apt complaining about downgrading:
```bash
make apt-wrong-order
```

Also tried with a `reprepro` apt repo, works:
```bash
# on repo host, first install
reprepro  --ask-passphrase --verbose includedeb ubuntu /srv/my_deb_package_repo/ubuntu/deb/my-package_1.0_amd64.deb
reprepro ls my-package  # 1.0
sudo apt install my-package  # 1.0

# on repo host, upgrade to 1.5
reprepro  --ask-passphrase --verbose includedeb ubuntu /srv/my_deb_package_repo/ubuntu/deb/my-package_1.5_amd64.deb
reprepro ls my-package  # 1.5
sudo apt update && sudo apt upgrade  # 1.5

# downgrade to 1.0 requires downgrade on the apt repo AND locally
# Skipping inclusion of 'my-package' '1.0' in 'ubuntu|main|amd64', as it has already '1.5'.
reprepro  --ask-passphrase --verbose includedeb ubuntu /srv/my_deb_package_repo/ubuntu/deb/my-package_1.0_amd64.deb
reprepro --verbose remove ubuntu my-package
reprepro  --ask-passphrase --verbose includedeb ubuntu /srv/my_deb_package_repo/ubuntu/deb/my-package_1.0_amd64.deb
sudo apt update && sudo apt upgrade  # still 1.5
sudo apt install my-package  # still 1.5
sudo dpkg --purge my-package
sudo apt install my-package  # 1.0 again
```
