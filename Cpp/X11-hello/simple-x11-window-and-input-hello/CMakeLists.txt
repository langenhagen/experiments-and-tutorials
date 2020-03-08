cmake_minimum_required(VERSION 3.10)

set(target "simple-x11-window-and-input-hello")
project("${target}" VERSION 0.1.0)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

add_executable("${target}" "main.cpp")
find_package(X11 REQUIRED)

target_include_directories("${target}"
    PRIVATE
        "${X11_INCLUDE_DIR}"
        "include")

target_link_libraries("${target}"
    "${X11_LIBRARIES}")
