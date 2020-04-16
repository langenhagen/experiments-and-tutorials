cmake_minimum_required(VERSION 3.10)

execute_process(
    COMMAND basename "${CMAKE_SOURCE_DIR}"
    WORKING_DIRECTORY "${CMAKE_SOURCE_DIR}"
    OUTPUT_VARIABLE target
    OUTPUT_STRIP_TRAILING_WHITESPACE)

project("${target}" VERSION 0.1.0)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

set(sources
    "src/main.cpp")

add_executable("${target}"
    "${sources}")

target_include_directories("${target}"
    PRIVATE
        "include")

target_link_libraries("${target}"
    xdo)
