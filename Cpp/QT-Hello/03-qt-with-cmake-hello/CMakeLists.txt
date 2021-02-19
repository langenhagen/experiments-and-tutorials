# CMakeLists for a QT Project
#
# see:
#   - https://doc.qt.io/qt-5/cmake-get-started.html
#
cmake_minimum_required(VERSION 3.16)  # I just happen to run CMake 3.16 as of today 2021-02-19.

execute_process(
    COMMAND basename "${CMAKE_SOURCE_DIR}"
    WORKING_DIRECTORY "${CMAKE_SOURCE_DIR}"
    OUTPUT_VARIABLE target
    OUTPUT_STRIP_TRAILING_WHITESPACE
)

project("${target}" VERSION 0.1.0 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

add_executable("${target}"
    "main.cpp"
)

find_package(Qt5 COMPONENTS Widgets REQUIRED)

target_link_libraries("${target}"
    Qt5::Widgets
)
