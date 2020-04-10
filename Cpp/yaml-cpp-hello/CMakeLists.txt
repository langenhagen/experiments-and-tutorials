cmake_minimum_required(VERSION 3.10)

execute_process(
    COMMAND basename "${CMAKE_SOURCE_DIR}"
    WORKING_DIRECTORY "${CMAKE_SOURCE_DIR}"
    OUTPUT_VARIABLE target
    OUTPUT_STRIP_TRAILING_WHITESPACE)

project("${target}" VERSION 0.1.0)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

include(ExternalProject)
set(yaml_cpp_build_dir "${CMAKE_BINARY_DIR}/yaml-cpp-build")
set(yaml_cpp_lib_filename "${CMAKE_STATIC_LIBRARY_PREFIX}yaml-cpp${CMAKE_STATIC_LIBRARY_SUFFIX}")
set(yaml_cpp_lib "${yaml_cpp_build_dir}/${yaml_cpp_lib_filename}")
set(yaml_cpp_src_dir "${CMAKE_BINARY_DIR}/yaml-cpp-src")
set(yaml_cpp_include_path "${yaml_cpp_src_dir}/include")
ExternalProject_Add(yaml-cpp
    GIT_REPOSITORY https://github.com/jbeder/yaml-cpp
    GIT_TAG yaml-cpp-0.6.3
    SOURCE_DIR "${yaml_cpp_src_dir}"
    BINARY_DIR "${yaml_cpp_build_dir}"
    CMAKE_ARGS
        -DYAML_CPP_BUILD_TESTS=OFF
        -DYAML_CPP_BUILD_TOOLS=OFF
        -DYAML_CPP_INSTALL=OFF
        -DYAML_BUILD_SHARED_LIBS=OFF
    BUILD_BYPRODUCTS "${yaml_cpp_lib}"
    BUILD_BYPRODUCTS "${yaml_cpp_include_path}"
    INSTALL_COMMAND "")

set(SOURCES
    "src/main.cpp")

add_executable("${target}" "${SOURCES}")

target_include_directories("${target}"
    PRIVATE
        "${yaml_cpp_include_path}")

target_link_libraries("${target}"
    "${yaml_cpp_lib}")
