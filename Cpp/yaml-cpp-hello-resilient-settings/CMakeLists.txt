# CmakeLists file for building an application that uses yaml-cpp.
#
# author: andreasl
cmake_minimum_required(VERSION 3.10)

execute_process(
    COMMAND basename "${CMAKE_SOURCE_DIR}"
    WORKING_DIRECTORY "${CMAKE_SOURCE_DIR}"
    OUTPUT_VARIABLE target
    OUTPUT_STRIP_TRAILING_WHITESPACE)

project("${target}" VERSION 0.1.0)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

if ("${CMAKE_CXX_COMPILER_ID}" STREQUAL "Clang")
    set(experimental_libraries c++experimental)
elseif ("${CMAKE_CXX_COMPILER_ID}" STREQUAL "GNU")
    set(experimental_libraries stdc++fs)
endif()

include("cmake/yaml-cpp.cmake")

set(sources
    "src/main.cpp")

add_executable("${target}"
    "${sources}")
target_include_directories("${target}"
    PRIVATE
        "${YAML_CPP_INCLUDE_PATH}")
target_link_libraries("${target}"
    "${experimental_libraries}"
    "${YAML_CPP_LIB}")
