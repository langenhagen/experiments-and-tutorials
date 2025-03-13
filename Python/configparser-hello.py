#!/usr/bin/env python3
"""Showcase simple usage of Python's standard library INI config parser."""

from configparser import ConfigParser

ini_file = "myconfig.ini"


def print_config(parser: ConfigParser) -> None:
    """Print an IN config."""
    for section in parser.sections():
        print(f"Section: {section}")
        for key, value in parser.items(section):
            print(f"  {key} = {value}")


print("--- 1 create an INI file ---\n")

config = ConfigParser()
config["DEFAULT"] = {
    "ServerAliveInterval": "45",
    "Compression": "yes",
}
config["bitbucket.org"] = {}
config["bitbucket.org"]["User"] = "hg"
config["topsecret.server.com"] = {}
topsecret = config["topsecret.server.com"]
topsecret["Port"] = "50022"

with open(ini_file, "w", encoding="utf-8") as configfile:
    config.write(configfile)

print("\n--- 2 read INI file ---\n")

# running configparser 2 times adds values to the set of values; i.e.,
# it does not reset when calling `config.read()`.
# Thus, better create a new parser, otherwise it mixes contents of several files
config = ConfigParser()
config.read(ini_file)
print_config(config)

print("\n--- 3 modify INI file --\n")

config.set("DEFAULT", "Compression", "no")
config.set("bitbucket.org", "User", "git")
config.set("topsecret.server.com", "Port", "22")

with open(ini_file, "w", encoding="utf-8") as configfile:
    config.write(configfile)

config = ConfigParser()
config.read(ini_file)
print_config(config)
