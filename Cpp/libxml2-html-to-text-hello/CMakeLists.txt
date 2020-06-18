cmake_minimum_required(VERSION 3.10)

execute_process(
    COMMAND basename "${CMAKE_SOURCE_DIR}"
    WORKING_DIRECTORY "${CMAKE_SOURCE_DIR}"
    OUTPUT_VARIABLE target
    OUTPUT_STRIP_TRAILING_WHITESPACE)

project("${target}" VERSION 0.1.0)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

add_executable("${target}" "src/main.cpp" "src/scrape.cpp")
find_package(LibXml2 REQUIRED) # install via: sudo apt install libxml2
find_package(CURL REQUIRED)  # install via: sudo apt install libcurl4-openssl-dev

target_include_directories("${target}"
    PRIVATE
        "${CURL_INCLUDE_DIR}"
        "${LIBXML2_INCLUDE_DIR}"
        "include")

target_link_libraries("${target}"
    "${CURL_LIBRARIES}"
    "${LIBXML2_LIBRARIES}")
