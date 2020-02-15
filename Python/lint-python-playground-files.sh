#!/bin/bash
#
# Runs linters with the project's preferences.
# Run it from the project's root directory in order to work.
#
# author: andreasl

file_pattern="${1:-*.py}"

flake8_error_codes_array=(
    # 'E128'  # continuation line under-indented for visual indent
    # 'E501'  # line too long (87 > 79 characters)
    # 'E712'  # comparison to False should be 'if cond is False:' or 'if not cond:'
    # 'W504'  # line break after binary operator
)
flake8_error_codes="$(printf '%s,' "${flake8_error_codes_array[@]}")"
flake8 --ignore="$flake8_error_codes" ${file_pattern}
flake8_exit_code="$?"

pylint_error_codes_array=(
    'C0102'  # Black listed name "foo"
    'C0103'  # doesn't conform to snake case naming style
    'C0111'  # missing function docstring
    # 'C0121'  # Comparison to False should be 'not expr' or 'expr is False'
    # 'C0301'  # line too long
    'C0330'  # Wrong continued indentation (add 2 spaces).
    'E0401'  # unable to import foo.bar
    'R0903'  # too few public methods

)
pylint_error_codes="$(printf '%s,' "${pylint_error_codes_array[@]}")"
pylint3 --disable="$pylint_error_codes" --msg-template='{msg_id} {path}:{line}: {msg}' ${file_pattern}
pylint3_exit_code="$?"

((exit_code = flake8_exit_code + pylint3_exit_code))
exit $exit_code
