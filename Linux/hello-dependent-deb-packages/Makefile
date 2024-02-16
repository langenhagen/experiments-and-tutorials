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
install-a:
	sudo dpkg -i ./my-package-a.deb

.PHONY: install-b
install-b:
	sudo dpkg -i ./my-package-b.deb

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
# if you
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
