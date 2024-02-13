my-hello-deb-package.deb:
	dpkg-deb --build my-hello-deb-package

.PHONY: install
install:
	sudo dpkg -i ./my-hello-deb-package.deb

.PHONY: check
check:
	dpkg -l hello-deb-packages-0.1.0
	ls /etc/my-hello-deb-package/ /usr/bin/allyourbase.sh

# keeps configs around
.PHONY: uninstall
uninstall:
	sudo dpkg --remove hello-deb-packages-0.1.0

# purges also configs
.PHONY: purge
purge:
	sudo dpkg --purge hello-deb-packages-0.1.0
