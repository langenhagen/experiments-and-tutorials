my-package_1.0_amd64.deb:
	dpkg-deb -Zgzip --build my-package-v1
	dpkg-name my-package-v1.deb  # rename the packages to their standard names

my-package_1.5_amd64.deb:
	dpkg-deb -Zgzip --build my-package-v1.5
	dpkg-name my-package-v1.5.deb

build: my-package_1.0_amd64.deb my-package_1.5_amd64.deb

.PHONY: info
info:
	-dpkg-deb --info my-package_1.0_amd64.deb
	@echo
	dpkg-deb --info my-package_1.5_amd64.deb

.PHONY: install-v1
install-v1: my-package_1.0_amd64.deb
	sudo dpkg --install ./my-package_1.0_amd64.deb

.PHONY: install-v1.5
install-v1.5: my-package_1.5_amd64.deb
	sudo dpkg --install ./my-package_1.5_amd64.deb

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
	rm my-package_1.0_amd64.deb my-package_1.5_amd64.deb

.PHONY:
apt: build
	sudo apt install -y ./my-package_1.0_amd64.deb
	sudo apt install -y ./my-package_1.5_amd64.deb

.PHONY:
apt-wrong-order: build
	-sudo apt install -y ./my-package_1.5_amd64.deb
	# sudo apt install -y  --allow-downgrades ./my-package_1.0_amd64.deb  # downgrades
	sudo apt install -y ./my-package_1.0_amd64.deb  # doesn't downgrade
