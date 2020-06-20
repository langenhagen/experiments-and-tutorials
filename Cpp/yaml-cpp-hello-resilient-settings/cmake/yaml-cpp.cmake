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

set(EXT_PROJECT_NAME "yaml-cpp")

configure_file(
    "${CMAKE_CURRENT_LIST_DIR}/${EXT_PROJECT_NAME}-CMakeLists.txt.in"
    "${EXT_PROJECT_NAME}-download/CMakeLists.txt")

# Download sources
execute_process(
    COMMAND ${CMAKE_COMMAND} -G "${CMAKE_GENERATOR}" .
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}-download")
if(result)
  message(FATAL_ERROR "CMake step for ${EXT_PROJECT_NAME}-download failed: ${result}")
endif()
execute_process(
    COMMAND ${CMAKE_COMMAND} --build .
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}-download")
if(result)
  message(FATAL_ERROR "Build step for ${EXT_PROJECT_NAME}-download failed: ${result}")
endif()

# Build
set(build_dir "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}")
file(MAKE_DIRECTORY "${build_dir}")
execute_process(
    COMMAND ${CMAKE_COMMAND} -G "${CMAKE_GENERATOR}"
        -DYAML_CPP_BUILD_TESTS=OFF
        -DYAML_CPP_BUILD_TOOLS=OFF
        -DYAML_CPP_INSTALL=OFF
        -DYAML_BUILD_SHARED_LIBS=OFF
        "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}-src"
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${build_dir}")
if(result)
  message(FATAL_ERROR "CMake step for ${EXT_PROJECT_NAME} failed: ${result}")
endif()
execute_process(COMMAND ${CMAKE_COMMAND} --build .
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${build_dir}")
if(result)
  message(FATAL_ERROR "Build step for ${EXT_PROJECT_NAME} failed: ${result}")
endif()

set(lib_filename "${CMAKE_STATIC_LIBRARY_PREFIX}${EXT_PROJECT_NAME}${CMAKE_STATIC_LIBRARY_SUFFIX}")

set(YAML_CPP_LIB "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}/${lib_filename}")
set(YAML_CPP_INCLUDE_PATH "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}-src/include")
