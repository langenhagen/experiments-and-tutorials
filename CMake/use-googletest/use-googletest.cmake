# shows how to download and install google test via CMake

# Using the following produced a warning, that it is discouraged to have a global google test build.
# Rather, google test should be configured build by every project that is using it.
# This also alleviates compiler/linker/platform/configuration mismatch issues.
# The following is straightforward, but discouraged, and unlikely to work with googletest:
#
#find_package(GTest REQUIRED)
#if(NOT GTEST_FOUND)
#    message(FATAL_ERROR "Did not find GTest!")
#else()
#    message(STATUS "GTESD DIRECTORIES HERE: ${GTEST_INCLUDE_DIRS}")
#endif()

# instead, see: https://github.com/google/googletest/blob/master/googletest/README.md
# and thus do, taken from the above link:

# Download and unpack googletest at configure time
configure_file(use-googletest/CMakeLists.txt.in googletest-download/CMakeLists.txt)
execute_process(COMMAND ${CMAKE_COMMAND} -G "${CMAKE_GENERATOR}" .
  RESULT_VARIABLE result
  WORKING_DIRECTORY ${CMAKE_BINARY_DIR}/googletest-download )
if(result)
  message(FATAL_ERROR "CMake step for googletest failed: ${result}")
endif()
execute_process(COMMAND ${CMAKE_COMMAND} --build .
  RESULT_VARIABLE result
  WORKING_DIRECTORY ${CMAKE_BINARY_DIR}/googletest-download )
if(result)
  message(FATAL_ERROR "Build step for googletest failed: ${result}")
endif()

# Prevent overriding the parent project's compiler/linker
# settings on Windows
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)

# Add googletest directly to our build. This defines
# the gtest and gtest_main targets.
add_subdirectory(${CMAKE_BINARY_DIR}/googletest-src
                 ${CMAKE_BINARY_DIR}/googletest-build
                 EXCLUDE_FROM_ALL)

# The gtest/gtest_main targets carry header search path
# dependencies automatically when using CMake 2.8.11 or
# later. Otherwise we have to add them here ourselves.
if (CMAKE_VERSION VERSION_LESS 2.8.11)
  include_directories("${gtest_SOURCE_DIR}/include")
endif()

# # Now simply link against gtest or gtest_main as needed. Eg
# add_executable(example example.cpp)
# target_link_libraries(example gtest_main)
# add_test(NAME example_test COMMAND example)