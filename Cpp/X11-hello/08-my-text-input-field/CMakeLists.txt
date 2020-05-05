cmake_minimum_required(VERSION 3.10)

execute_process(
    COMMAND basename "${CMAKE_SOURCE_DIR}"
    WORKING_DIRECTORY "${CMAKE_SOURCE_DIR}"
    OUTPUT_VARIABLE target
    OUTPUT_STRIP_TRAILING_WHITESPACE)

project("${target}" VERSION 0.1.0)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

find_package(X11 REQUIRED)
find_package(Freetype REQUIRED)

set(SOURCES
    "src/main.cpp"
    "src/App.cpp")

add_executable("${target}" "${SOURCES}")

target_include_directories("${target}"
    PRIVATE
        "${X11_INCLUDE_PATH}"
        "${FREETYPE_INCLUDE_DIRS}"
        "include")

target_link_libraries("${target}"
    "${X11_LIBRARIES}"
    "Xft")
