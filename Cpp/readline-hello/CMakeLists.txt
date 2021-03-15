# A simple app that uses the readline library.
# Taken from https://stackoverflow.com/questions/33451868/cmake-and-readline-library-in-c-program
#
# author: andreasl
cmake_minimum_required(VERSION 3.6)

project(readline-hello)

include_directories(/usr/include/readline)

add_executable(readline-hello
    "main.cpp"
)
target_link_libraries(readline-hello readline)
