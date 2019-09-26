#author: langenhagen
#version: 18-01-11

# examples of cmake messages

function(print_messages)

    message("!! A normal message !!")                                     # A normal message
    message(STATUS "!! Incidental message !!")                            # -- Incidental message
    message(WARNING "!! CMake warning .. continue processing !!")
    message(AUTHOR_WARNING "!! CMake warning (dev) !!")
    message(DEPRECATION "!! CMake Deprecation error, or Warning if variable CMAKE_ERROR_DEPRECATED or
        CMAKE_WARN_DEPRECATED is enabled,
        else no message !!")
    message(SEND_ERROR "!! CMake error, continue processsing, but skip generation !!")
    message(FATAL_ERROR "!! CMake Error, stop processing and generation !!")
    message("!! Should not be printed due to fatal error !!")

endfunction()