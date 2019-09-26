
# shows the use of variables that come with UseJava :)

# docpage: https://cmake.org/cmake/help/v3.0/module/UseJava.html

include(UseJava)

if(DEFINED CMAKE_JAVA_INCLUDE_PATH)
    message("CMAKE_JAVA_INCLUDE_PATH is defined")
endif()
if(CMAKE_JAVA_INCLUDE_PATH)
    message("CMAKE_JAVA_INCLUDE_PATH is :)")
endif()

message("CMAKE_JAVA_COMPILE_FLAGS: ${CMAKE_JAVA_COMPILE_FLAGS}")
message("CMAKE_JAVA_INCLUDE_PATH: ${CMAKE_JAVA_INCLUDE_PATH}")
message("CMAKE_JNI_TARGET: ${CMAKE_JNI_TARGET}")


set(CMAKE_JAVA_INCLUDE_PATH "katze")
message("after set: CMAKE_JAVA_INCLUDE_PATH: ${CMAKE_JAVA_INCLUDE_PATH}")

include(UseJava)
message("after reinclude UseJava: CMAKE_JAVA_INCLUDE_PATH: ${CMAKE_JAVA_INCLUDE_PATH}") # stays set to "katze"
