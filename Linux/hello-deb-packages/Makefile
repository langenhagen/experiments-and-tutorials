my-hello-deb-package.deb:
	dpkg-deb --build my-hello-deb-package

.PHONY: info
info:
	dpkg-deb --info my-hello-deb-package.deb

	@echo
	@echo 'And some contents:'
	dpkg-deb --contents my-hello-deb-package.deb

.PHONY: install
install:
	sudo dpkg -i ./my-hello-deb-package.deb

.PHONY: list
list:
	-dpkg --list hello-deb-packages-0.1.0
	@echo
	ls /etc/my-hello-deb-package/ /usr/bin/allyourbase.sh

# keeps configs around
.PHONY: uninstall
uninstall:
	sudo dpkg --remove hello-deb-packages-0.1.0

# purges also configs
.PHONY: purge
purge:
	sudo dpkg --purge hello-deb-packages-0.1.0
