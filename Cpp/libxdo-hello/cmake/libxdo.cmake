# CMake instructions to download and build libxdo.a at CMake configure time.
# Use in combination with the libxdo-CMakeLists.txt.in.
#
# Usage:
# include(libxdo.cmake)
#
# Set variables:
#   "${LIBXDO_INCLUDE_PATH}
#   "${LIBXDO_LIB}"
#
# author: andreasl

set(EXT_PROJECT_NAME "libxdo")

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
execute_process(
    COMMAND make libxdo.a
    RESULT_VARIABLE result
    WORKING_DIRECTORY "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}-src")
if(result)
  message(FATAL_ERROR "Make step for ${EXT_PROJECT_NAME} failed: ${result}")
endif()

set(LIBXDO_INCLUDE_PATH "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}/include")
file(GLOB header_files "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}-src/*")
file(
    COPY ${header_files}
    DESTINATION "${LIBXDO_INCLUDE_PATH}/${EXT_PROJECT_NAME}")

set(lib_filename "libxdo.a")
set(LIBXDO_LIB "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}/${lib_filename}")
file(
    COPY        "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}-src/${lib_filename}"
    DESTINATION "${CMAKE_BINARY_DIR}/${EXT_PROJECT_NAME}")
