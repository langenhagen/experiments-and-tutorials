# CMakeLists files in this project can
# refer to the root source directory of the project as ${HELLO_SOURCE_DIR} and
# to the root binary directory of the project as ${HELLO_BINARY_DIR}.
cmake_minimum_required (VERSION 3.10.0)
project (MY_CMAKE_HELLO_WORLD)

#include(custom-cmake-command.cmake)

#include(if-defined.cmake)

#include(messages.cmake)
#print_messages()

#include(lists.cmake)

#include(set-vs-option.cmake)


#include(predefined-cmake-vars.cmake)

#include(use-java.cmake)


#include(macros-vs-functions.cmake)

#include(function-return-values.cmake)

#include(find-library.cmake)


# include(cmake-parse-arguments.cmake)
# foo(ARG_A "hallo"
#     ARG_B "welt"
#     MULTI_VAL_ARG "one"
#     MULTI_VAL_ARG "two"            # you
#     MULTI_VAL_ARG "three"          # can comment
#     MULTI_VAL_ARG "four;five;six"  # without problems
#     MULTI_VAL_ARG seven eight)


include(use-googletest/use-googletest.cmake)