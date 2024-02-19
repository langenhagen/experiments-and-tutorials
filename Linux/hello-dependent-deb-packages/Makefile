my-package-a.deb:
	dpkg-deb -Zgzip --build my-package-a

my-package-b.deb:
	dpkg-deb -Zgzip --build my-package-b

build: my-package-a.deb my-package-b.deb

.PHONY: info
info:
	-dpkg-deb --info my-package-a.deb
	@echo
	dpkg-deb --info my-package-b.deb

.PHONY: install-a
install-a: my-package-a.deb
	sudo dpkg --install ./my-package-a.deb

.PHONY: install-b
install-b: my-package-b.deb
	sudo dpkg --install ./my-package-b.deb

.PHONY: install
install: install-a install-b

.PHONY: list
list:
	-dpkg --list my-package-a
	@echo
	@echo package a contents:
	-ls /etc/my-package-a/
	@echo
	-dpkg --list my-package-b
	@echo
	@echo package b contents:
	ls /etc/my-package-b/

# keeps configs around
# note the order; if you uninstall a first, it keeps it somewhat around
# since b depends on it
.PHONY: uninstall
uninstall:
	sudo dpkg --remove my-package-b
	-sudo dpkg --remove my-package-a

# not so happy when b is installed
.PHONY: purge-a
purge-a:
	sudo dpkg --purge my-package-a

.PHONY: purge-b
purge-b:
	sudo dpkg --purge my-package-b

# purges also configs
.PHONY: purge
purge:
	-sudo dpkg --purge my-package-b
	sudo dpkg --purge my-package-a

.PHONY: clean
clean: purge
	rm my-package-a.deb my-package-b.deb
