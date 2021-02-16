#!/usr/bin/env python3
"""Showcase simple usage of the nonstandard package pyyaml.

Get it via `pip install pyyaml`.
"""
import pprint

import yaml

import tree

pp = pprint.PrettyPrinter(indent=4)

print("--- 1 - parse a yaml file ---")

with open("labels.yml") as file:
    data = yaml.load(file, Loader=yaml.FullLoader)
    pp.pprint(data)

print("--- 2 - transform yaml data to a tree structure ---")

class Label(tree.Node):
    """A label that can be attached to a transaction post."""

    def __init__(self, name: str, regex: str = None):
        """Create a new Label with the given name and regex."""
        super().__init__()
        self.name: str = name
        self.regex: str = regex


def build_label_tree(dictionary: dict, root: Label = None):
    """Build a tree from a given dictionary of dictionaries."""
    for key, value in dictionary.items():
        if key == "regex":
            continue
        if isinstance(value, dict):
            regex=value.get("regex")
            label = Label(name=key, regex=regex)
            build_label_tree(dictionary=value, root=label)
        else:
            label = Label(name=key, regex=None)

        if isinstance(root, Label):
            root.children.append(label)


assert isinstance(data.get("Income"), dict)
income = Label("Income")
build_label_tree(data["Income"], income)

assert isinstance(data.get("Expense"), dict)
expense = Label("Expense")
build_label_tree(data["Expense"], expense)
