"""Showcase the existence and usage of conventional constants in Python.

Python constants are all upper case. They are basically variables and not really
constant. However, VSCode marks constants. Possibly linters would realize when
one changes a constant.

Constants are upper-case.
"""

print("--- 1 constants ---\n")

# This is a Python constant. It's type is `str``.
# VSCode marks its type as `Literal["Ima constant!"]`.
MY_CONSTANT = "Ima constant!"
print(f"{MY_CONSTANT=}")
print(f"{type(MY_CONSTANT)=}")

NUMERIC_CONSTANT = 12.3
print(f"{NUMERIC_CONSTANT=}")
print(f"{type(NUMERIC_CONSTANT)=}")

my_variable = "foo"

print("\n--- 2 constants are just variables at runtime ---\n")
MY_CONSTANT += " Yeah!"
print(f"{MY_CONSTANT=}")
print(f"{type(MY_CONSTANT)=}")
