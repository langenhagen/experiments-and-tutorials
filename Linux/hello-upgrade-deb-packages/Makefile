my-package-v1.deb:
	dpkg-deb -Zgzip --build my-package-v1

my-package-v1.5.deb:
	dpkg-deb -Zgzip --build my-package-v1.5

build: my-package-v1.deb my-package-v1.5.deb

.PHONY: info
info:
	-dpkg-deb --info my-package-v1.deb
	@echo
	dpkg-deb --info my-package-v1.5.deb

.PHONY: install-v1
install-v1:
	sudo dpkg -i ./my-package-v1.deb

.PHONY: install-v1.5
install-v1.5:
	sudo dpkg -i ./my-package-v1.5.deb

.PHONY: install
install: install-v1 install-v1.5

.PHONY: list
list:
	-dpkg --list my-package
	@echo
	@echo package contents:
	-ls /etc/my-package
	cat /etc/my-package/*

# keeps configs around
# note the order; if you uninstall a first, it keeps it somewhat around
# since b depends on it
.PHONY: uninstall
uninstall:
	sudo dpkg --remove my-package

# not so happy when b is installed
.PHONY: purge
purge:
	sudo dpkg --purge my-package

.PHONY: clean
clean: purge
	rm my-package-v1.deb my-package-v1.5.deb

.PHONY:
apt: build
	sudo apt install -y ./my-package-v1.deb
	sudo apt install -y ./my-package-v1.5.deb

.PHONY:
apt-wrong-order: build
	-sudo apt install -y ./my-package-v1.5.deb
	sudo apt install -y ./my-package-v1.deb
