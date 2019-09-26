#author: langenhagen
#version: 18-01-11

# usage of cmake-parse-arguments

# use like
#
# include(cmake-parse-arguments.cmake)
# foo(ARG_A "hallo"
#     ARG_B "welt"
#     MULTI_VAL_ARG "one"
#     MULTI_VAL_ARG "two"
#     MULTI_VAL_ARG "three"
#     MULTI_VAL_ARG "four;five;six"
#     MULTI_VAL_ARG seven eight)
#
function(foo)
    message( "Foo with cmake-parse-arguments STARTS")

    set(options)
    set(oneValueArgs ARG_A ARG_B)
    set(multiValueArgs MULTI_VAL_ARG)
    cmake_parse_arguments(myargs "${options}" "${oneValueArgs}"
                                 "${multiValueArgs}" ${ARGN})

    # unlike the difference between if(DEFINED myvar) and if(myvar) with manually defined
    # variables, the cmake_parse_arguments() either creates them or not, even though they
    # are empty but set. This means, if(DEFINED myvar) and if(myvar) behave same with
    # variables set through cmake_parse_arguments()
    #
    # conclusion, use  if(myvar)  instead of  if(DEFINED myvar)  with cmake_parse_arguments() vars.
    # it looks to be common practice, too.

    if(DEFINED myargs_ARG_A)
        message("myargs_ARG_A DEFINED. myargs_ARG_A=${myargs_ARG_A}")
    endif()
    if(myargs_ARG_A)
        message("myargs_ARG_A there.   myargs_ARG_A=${myargs_ARG_A}")
    endif()



    if(DEFINED myargs_ARG_B)
        message("myargs_ARG_B DEFINED. myargs_ARG_B=${myargs_ARG_B}")
    endif()
    if(myargs_ARG_B)
        message("myargs_ARG_B there.   myargs_ARG_B=${myargs_ARG_B}")
    endif()




    if(myargs_MULTI_VAL_ARG)
        message("myargs_MULTI_VAL_ARG there.   myargs_MULTI_VAL_ARG=${myargs_MULTI_VAL_ARG}")

        list(LENGTH myargs_MULTI_VAL_ARG arg_length)
        message("length of myargs_MULTI_VAL_ARG as a list: ${arg_length}")
    endif()

    message("--------------------------------------")



    message( "Foo with cmake-parse-arguments ENDS")
endfunction()
