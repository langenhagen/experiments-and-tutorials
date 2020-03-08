cmake_minimum_required(VERSION 3.10)

set(target "x11-with-xft-text-hello")
project("${target}" VERSION 0.1.0)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

add_executable("${target}" "main.cpp")
find_package(X11 REQUIRED)
find_package(Freetype REQUIRED)

target_include_directories("${target}"
    PRIVATE
        "${X11_INCLUDE_PATH}"
        "${FREETYPE_INCLUDE_DIRS}"
        "include")

# Xft can be omitted when not used
target_link_libraries("${target}"
    "${X11_LIBRARIES}"
    "Xft")
