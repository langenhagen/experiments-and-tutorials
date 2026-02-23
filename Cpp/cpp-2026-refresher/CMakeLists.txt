cmake_minimum_required(VERSION 3.20)

project(cpp_round_trip LANGUAGES CXX)

add_executable(cpp_round_trip main.cpp)
target_compile_features(cpp_round_trip PRIVATE cxx_std_23)

if(CMAKE_CXX_COMPILER_ID MATCHES "GNU|Clang")
  target_compile_options(cpp_round_trip PRIVATE -Wall -Wextra -Wpedantic)
endif()
