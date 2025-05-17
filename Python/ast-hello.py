#!/usr/bin/env python3
"""Showcase the usage of the Abstract Syntax Tree module ast."""

import ast
import sys
from dataclasses import dataclass

# ------------------------------------------------------------------------------
# Test structures


def foo() -> None:
    print("Hi!")


def bar() -> None:
    """"""

    def inner_fun() -> None:
        pass

    inner_fun()


class MyTestClass:
    def member_fun(self) -> None:
        pass


@dataclass
class MyDataClass:
    """Just for testing something rather complex."""

    i: int
    s: str = "default string"


# ------------------------------------------------------------------------------
# Business code


def parse_python_file(filename: str):
    """Return the ast from a given python file."""
    with open(filename) as file:
        return ast.parse(file.read())


def get_last_deep_child(ast_node):
    if not hasattr(ast_node, "body"):
        return ast_node
    return get_last_deep_child(ast_node.body[-1])


def find_all_function_nodes(ast_root):
    if not hasattr(ast_root, "body"):
        return []

    fun_nodes = []
    for node in ast_root.body:
        if isinstance(node, ast.FunctionDef):
            fun_nodes.append(node)
        fun_nodes.extend(find_all_function_nodes(node))
    return fun_nodes


def show_function_info(node) -> None:
    argnames = [n.arg for n in node.args.args]
    lineno = node.lineno
    end_lineno = get_last_deep_child(node).lineno
    print(f"def {node.name}{argnames} @ {lineno}-{end_lineno}")
    print(f'   """{ast.get_docstring(node)}"""')


def run() -> None:
    filename = sys.argv[1] if len(sys.argv) > 1 else __file__
    root = parse_python_file(filename)

    # maybe helpful: print all ast nodes
    # for node in root.body:
    #     print(node)

    fun_nodes = find_all_function_nodes(root)
    for node in fun_nodes:
        show_function_info(node)


if __name__ == "__main__":
    run()
