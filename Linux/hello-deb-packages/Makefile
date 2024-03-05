# rename packages to their full package names
# somewhat standard names.
# not necessary but sounds recommendable.
hello-deb-packages_1.0_amd64.deb:
	dpkg-deb -Zgzip --build my-hello-deb-package
	dpkg-name my-hello-deb-package.deb  # rename from dpkg-deb created name to a standardized full name

build: hello-deb-packages_1.0_amd64.deb

.PHONY: info
info: hello-deb-packages_1.0_amd64.deb
	dpkg-deb --info hello-deb-packages_1.0_amd64.deb

	@echo
	@echo 'And some contents:'
	dpkg-deb --contents hello-deb-packages_1.0_amd64.deb

.PHONY: install
install: hello-deb-packages_1.0_amd64.deb
	sudo dpkg --install ./hello-deb-packages_1.0_amd64.deb

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
	-rm ./hello-deb-packages_1.0_amd64.deb

.PHONY: apt
apt: hello-deb-packages_1.0_amd64.deb
	sudo apt install -y ./hello-deb-packages_1.0_amd64.deb

