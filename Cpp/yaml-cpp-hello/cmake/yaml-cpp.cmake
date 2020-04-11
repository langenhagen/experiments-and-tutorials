# CMake instructions to download and build yaml-cpp at CMake configure time.
# Use in combination with the yaml-cpp-CMakeLists.txt.in.
#
# Usage:
# include(yaml-cpp.cmake)
#
# Set variables:
#   "${YAML_CPP_INCLUDE_PATH}
#   "${YAML_CPP_LIB}"
#
# author: andreasl

configure_file(
    "${CMAKE_CURRENT_LIST_DIR}/yaml-cpp-CMakeLists.txt.in"
    "yaml-cpp-download/CMakeLists.txt")

# Download yaml-cpp sources
execute_process(
    COMMAND ${CMAKE_COMMAND} -G "${CMAKE_GENERATOR}" .
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${CMAKE_BINARY_DIR}/yaml-cpp-download")
if(result)
  message(FATAL_ERROR "CMake step for yaml-cpp-download failed: ${result}")
endif()
execute_process(
    COMMAND ${CMAKE_COMMAND} --build .
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${CMAKE_BINARY_DIR}/yaml-cpp-download")
if(result)
  message(FATAL_ERROR "Build step for yaml-cpp-download failed: ${result}")
endif()

# Build yaml-cpp
set(build_dir "${CMAKE_BINARY_DIR}/yaml-cpp")
file(MAKE_DIRECTORY "${build_dir}")
execute_process(
    COMMAND ${CMAKE_COMMAND} -G "${CMAKE_GENERATOR}"
        -DYAML_CPP_BUILD_TESTS=OFF
        -DYAML_CPP_BUILD_TOOLS=OFF
        -DYAML_CPP_INSTALL=OFF
        -DYAML_BUILD_SHARED_LIBS=OFF
        "${CMAKE_BINARY_DIR}/yaml-cpp-src"
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${build_dir}")
if(result)
  message(FATAL_ERROR "CMake step for yaml-cpp failed: ${result}")
endif()
execute_process(COMMAND ${CMAKE_COMMAND} --build .
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${build_dir}")
if(result)
  message(FATAL_ERROR "Build step for yaml-cpp failed: ${result}")
endif()

set(lib_filename "${CMAKE_STATIC_LIBRARY_PREFIX}yaml-cpp${CMAKE_STATIC_LIBRARY_SUFFIX}")

set(YAML_CPP_LIB "${CMAKE_BINARY_DIR}/yaml-cpp/${lib_filename}")
set(YAML_CPP_INCLUDE_PATH "${CMAKE_BINARY_DIR}/yaml-cpp-src/include")
