my-hello-deb-package.deb:
	dpkg-deb -Zgzip --build my-hello-deb-package

build: my-hello-deb-package.deb

.PHONY: info
info:
	dpkg-deb --info my-hello-deb-package.deb

	@echo
	@echo 'And some contents:'
	dpkg-deb --contents my-hello-deb-package.deb

.PHONY: install
install: my-hello-deb-package.deb
	sudo dpkg --install ./my-hello-deb-package.deb

.PHONY: list
list:
	-dpkg --list hello-deb-packages
	@echo
	ls /etc/my-hello-deb-package/ /usr/bin/allyourbase.sh

# keeps configs around
.PHONY: uninstall
uninstall:
	sudo dpkg --remove hello-deb-packages

# purges also configs
.PHONY: purge
purge:
	sudo dpkg --purge hello-deb-packages

.PHONY: clean
clean: purge
	rm ./my-hello-deb-package.deb
