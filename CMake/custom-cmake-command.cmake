# author: langenhagen
# version: 18-05-17

# usage of cmake-custom command,
# i.e. make_directory, touch, copy_directory, remove_directory

# use like:
#
# include(custom-cmake-command.cmake)
#
# beware the custom targets that you want to invoke from within the build folder.


message("Doing stuff with environment variable...")


execute_process(COMMAND env)


message("Doing stuff with directories and files...")

set(dir_to_create "${CMAKE_CURRENT_BINARY_DIR}/my-new-dir/")
set(assets "folder1" "folder2" "folder3")


add_custom_target("MyCustomTarget")

add_custom_command(TARGET "MyCustomTarget" PRE_BUILD
        COMMAND ${CMAKE_COMMAND} ARGS -E make_directory ${dir_to_create}
        COMMAND ${CMAKE_COMMAND} ARGS -E make_directory ${assets}
        COMMAND ${CMAKE_COMMAND} ARGS -E touch "folder1/miau.txt"
        COMMAND ${CMAKE_COMMAND} ARGS -E touch "folder2/katz.txt"
        COMMAND ${CMAKE_COMMAND} ARGS -E touch "folder3/grrr.txt"
        WORKING_DIRECTORY ${CMAKE_CURRENT_LIST_DIR})


# this copies the *contents* for ${assets} folders into ${dir_to_create}
# add_custom_command(TARGET "MyCustomTarget" POST_BUILD
#         COMMAND ${CMAKE_COMMAND} ARGS -E copy_directory ${assets} "${dir_to_create}"  # assets must not be enclosed in quotation marks
#         WORKING_DIRECTORY ${CMAKE_CURRENT_LIST_DIR})

foreach(asset ${assets})
    message("xx asset: ${asset}")
    add_custom_command(TARGET "MyCustomTarget" POST_BUILD
        COMMAND ${CMAKE_COMMAND} ARGS -E copy_directory "${asset}" "${dir_to_create}/innerDir/${asset}"
        WORKING_DIRECTORY ${CMAKE_CURRENT_LIST_DIR})
endforeach()

add_custom_target("MyCleanTarget")

add_custom_command(TARGET "MyCleanTarget" PRE_BUILD
        COMMAND ${CMAKE_COMMAND} ARGS -E remove_directory ${dir_to_create}
        WORKING_DIRECTORY ${CMAKE_CURRENT_LIST_DIR})
foreach(dir ${assets})
    add_custom_command(TARGET "MyCleanTarget" PRE_BUILD
        COMMAND ${CMAKE_COMMAND} ARGS -E remove_directory ${dir}
        WORKING_DIRECTORY ${CMAKE_CURRENT_LIST_DIR})
endforeach()

