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
	sudo dpkg -i ./my-package-a.deb

.PHONY: install-b
install-b: my-package-b.deb
	sudo dpkg -i ./my-package-b.deb

.PHONY: install
install: install-a install-b

.PHONY: list
list:
	-dpkg --list my-package
	@echo
	@echo package contents:
	-ls /etc/my-package-a/ /etc/my-packag-b/
	@echo package a:
	-cat /etc/my-package-a/*
	@echo package b:
	cat /etc/my-package-b/*

# keeps configs around
# note the order; if you uninstall a first, it keeps it somewhat around
# since b depends on it
.PHONY: uninstall
uninstall:
	-sudo dpkg --remove my-package-a
	sudo dpkg --remove my-package-b

# not so happy when b is installed
.PHONY: purge
purge:
	-sudo dpkg --purge my-package-a
	sudo dpkg --purge my-package-b

.PHONY: clean
clean: purge
	rm my-package-a.deb my-package-b.deb

.PHONY: apt-a
apt-a: my-package-a.deb
	sudo apt install --allow-downgrades -y ./my-package-a.deb

.PHONY: apt-b
apt-b: my-package-b.deb
	sudo apt install --allow-downgrades -y ./my-package-b.deb
