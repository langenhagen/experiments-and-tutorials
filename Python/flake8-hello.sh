#!/bin/bash
# see: https://flake8.pycqa.org/en/latest/user/error-codes.html
# see: https://gist.github.com/sharkykh/c76c80feadc8f33b129d846999210ba3
# see: https://pycodestyle.readthedocs.io/en/latest/intro.html#error-codes Caution: I found some false statements

cd ~/Dev/experiments-and-tutorials/Python

error_code_array=(
    'E101'  # indentation contains mixed spaces and tabs'
    'E11'   # indentations
    'E90'   # indentations
    'F401'  # 'foo' imported but unused
    'F402'  # import module from line N shadowed by loop variable
    'F403'  # ‘from module import *’ used; unable to detect undefined names
    'F405'  # name may be undefined, or defined from star imports: module
    'F811'  # redefinition of unused 'foo' from line N
    'F821'  # undefined name 'foo'
    'F841'  # local variable 'foo' is assigned to but never used
    'W291'  # trailing whitespace
    'W293'  # blank line contains whitespaces
)
error_codes="$(printf '%s,' "${error_code_array[@]}")"
flake8 --benchmark --isolated --select="$error_codes" 'erroneous-script-for-linter-tests.py'
echo "Status: $?"

# I suggest to also sort through these:

# 'D300'  # Use """triple double quotes"""  # did not work for me on flake8 v3.6.7

# E201: whitespace after '('
# E202: whitespace before ')'
# E203: whitespace before ':'
# E211: whitespace before '('
# E221: multiple spaces before operator
# E222: multiple spaces after operator
# E223: tab before operator
# E224: tab after operator
# E225: missing whitespace around operator
# E226: missing whitespace around arithmetic operator
# E227: missing whitespace around bitwise or shift operator
# E228: missing whitespace around modulo operator
# E231: missing whitespace after ',', ';', or ':'
# E241: multiple spaces after ','
# E242: tab after ','
# E251: unexpected spaces around keyword / parameter equals
# E261: at least two spaces before inline comment
# E262: inline comment should start with '# '
# E265: block comment should start with '# '
# E266: too many leading '#' for block comment
# E271: multiple spaces after keyword
# E272: multiple spaces before keyword
# E273: tab after keyword
# E274: tab before keyword
# E275: missing whitespace after keyword
# Blank line
# E301: expected 1 blank line, found 0
# E302: expected 2 blank lines, found 0
# E303: too many blank lines (3)
# E304: blank lines found after function decorator
# E305: expected 2 blank lines after end of function or class
# E306: expected 1 blank line before a nested definition
# Import
# E401: multiple imports on one line
# E402: module level import not at top of file
# Line length
# E501: line too long (82 > 79 characters)
# E502: the backslash is redundant between brackets
# Statement
# E701: multiple statements on one line (colon)
# E702: multiple statements on one line (semicolon)
# E703: statement ends with a semicolon
# E704: multiple statements on one line (def)
# E711: comparison to None should be 'if cond is None:'
# E712: comparison to True should be 'if cond is True:' or 'if cond:'
# E713: test for membership should be 'not in'
# E714: test for object identity should be 'is not'
# E721: do not compare types, use 'isinstance()'
# E722: do not use bare except, specify exception instead
# E731: do not assign a lambda expression, use a def
# E741: do not use variables named 'l', 'O', or 'I'
# E742: do not define classes named 'l', 'O', or 'I'
# E743: do not define functions named 'l', 'O', or 'I'
# Runtime
# E901: SyntaxError or IndentationError
# E902: IOError
# Indentation warning
# W191: indentation contains tabs
# Whitespace warning
# W291: trailing whitespace
# W292: no newline at end of file
# W293: blank line contains whitespace
# Blank line warning
# W391: blank line at end of file
# Line break warning
# W503: line break before binary operator
# W504: line break after binary operator
# W505: doc line too long (82 > 79 characters)
# Deprecation warning
# W601: .has_key() is deprecated, use 'in'
# W602: deprecated form of raising exception
# W603: '<>' is deprecated, use '!='
# W604: backticks are deprecated, use 'repr()'
# W605: invalid escape sequence '\x'
# W606: 'async' and 'await' are reserved keywords starting with Python 3.7
# pyflakes / flake8
# F401: module imported but unused
# F402: import module from line N shadowed by loop variable
# F403: 'from module import *' used; unable to detect undefined names
# F404: future import(s) name after other statements
# F405: name may be undefined, or defined from star imports: module
# F406: 'from module import *' only allowed at module level
# F407: an undefined __future__ feature name was imported
# F601: dictionary key name repeated with different values
# F602: dictionary key variable name repeated with different values
# F621: too many expressions in an assignment with star-unpacking
# F622: two or more starred expressions in an assignment (a, *b, *c = d)
# F631: assertion test is a tuple, which are always True
# F701: a break statement outside of a while or for loop
# F702: a continue statement outside of a while or for loop
# F703: a continue statement in a finally block in a loop
# F704: a yield or yield from statement outside of a function
# F705: a return statement with arguments inside a generator
# F706: a return statement outside of a function/method
# F707: an except: block as not the last exception handler
# F721: doctest syntax error
# F722: syntax error in forward type annotation
# F811: redefinition of unused name from line N
# F812: list comprehension redefines name from line N
# F821: undefined name name
# F822: undefined name name in __all__
# F823: local variable name ... referenced before assignment
# F831: duplicate argument name in function definition
# F841: local variable name is assigned to but never used
# F901: raise NotImplemented should be raise NotImplementedError
# mccabe
# C901: Function is too complex
# pep8-naming
# N801: class names should use CapWords convention
# N802: function name should be lowercase
# N803: argument name should be lowercase
# N804: first argument of a classmethod should be named 'cls'
# N805: first argument of a method should be named 'self'
# N806: variable in function should be lowercase
# N807: function name should not start or end with '__'
# N811: constant imported as non constant
# N812: lowercase imported as non lowercase
# N813: camelcase imported as lowercase
# N814: camelcase imported as constant
# N815: mixedCase variable in class scope
# N816: mixedCase variable in global scope
# flake8-docstrings (pydocstyle)
# Missing Docstrings
# D100: Missing docstring in public module
# D101: Missing docstring in public class
# D102: Missing docstring in public method
# D103: Missing docstring in public function
# D104: Missing docstring in public package
# D105: Missing docstring in magic method
# D106: Missing docstring in public nested class
# D107: Missing docstring in __init__
# Whitespace Issues
# D200: One-line docstring should fit on one line with quotes
# D201: No blank lines allowed before function docstring
# D202: No blank lines allowed after function docstring
# D203: 1 blank line required before class docstring
# D204: 1 blank line required after class docstring
# D205: 1 blank line required between summary line and description
# D206: Docstring should be indented with spaces, not tabs
# D207: Docstring is under-indented
# D208: Docstring is over-indented
# D209: Multi-line docstring closing quotes should be on a separate line
# D210: No whitespaces allowed surrounding docstring text
# D211: No blank lines allowed before class docstring
# D212: Multi-line docstring summary should start at the first line
# D213: Multi-line docstring summary should start at the second line
# D214: Section is over-indented
# D215: Section underline is over-indented
# Quotes Issues
# D300: Use """triple double quotes"""
# D301: Use r""" if any backslashes in a docstring
# D302: Use u""" for Unicode docstrings
# Docstring Content Issues
# D400: First line should end with a period
# D401: First line should be in imperative mood
# D401: First line should be in imperative mood; try rephrasing
# D402: First line should not be the function's "signature"
# D403: First word of the first line should be properly capitalized
# D404: First word of the docstring should not be This
# D405: Section name should be properly capitalized
# D406: Section name should end with a newline
# D407: Missing dashed underline after section
# D408: Section underline should be in the line following the section's name
# D409: Section underline should match the length of its name
# D410: Missing blank line after section
# D411: Missing blank line before section
# D412: No blank lines allowed between a section header and its content
# D413: Missing blank line after last section
# D414: Section has no content
# flake8-import-order
# I100: Your import statements are in the wrong order.
# I101: The names in your from import are in the wrong order.
# I201: Missing newline between import groups.
# I202: Additional newline in a group of imports.
# flake8-quotes
# Q000: Remove bad quotes
# Q001: Remove bad quotes from multiline string
# Q002: Remove bad quotes from docstring

# D300: Use """triple double quotes"""

# I101: The names in your from import are in the wrong order.
# I202: Additional newline in a group of imports.
