# Define a function that sets an input variable inside its scope
# and test whether it will then be set out side the function scope

## CAUTION: variables passed in must have a different name than the variable names inside the functions,
## i.e. argument names must differ from parameter names.

# In short, it works like this:
function(myfunction var)
    if(${var})
        message("checks for the setting of the outside variable, i.e braces around var necessary")
    endif()
    set(${var} "new value" PARENT_SCOPE)
endfunction()

# but I will do more elaborate experiments here
# ---

function(foo MYVAR)
    # sets a variable over one level
    if(MYVAR)  # corresponds to, if MYVAR is set, i.e. always true
        message("if(MYVAR)")
    else()
        message("not if(MYVAR)")
    endif()

    if(${MYVAR})  # corresponds whether the outside given var that myvar is pointing is set()
        message("if(\${MYVAR})")
    else()
        message("not if(\${MYVAR})")
    endif()

    set(${MYVAR} "jojojo" PARENT_SCOPE)  # sets the variable MYVAR is pointing to

    # ${MYVAR} is the function name ${${MYVAR}} is its value, but since it is in PARENT_SCOPE,
    # it is in what ${MYVAR} was initially pointing to, i.e, not the set value,
    # but the value from before
    message("\${MYVAR} inside the function: ${MYVAR} and \${\${MYVAR}}: ${${MYVAR}}")
endfunction()


function(bar MYVAR)
    # to do a nested variable setting,     you seem to have to set it explicitly
    foo(my_inner_var)
    set(${MYVAR} ${my_inner_var} PARENT_SCOPE)
endfunction()


message( " ------------------------------------------------------------------ ")
message( " one level function value setting/invocation")

message(STATUS "with a not defined variable - set variable")
message("before the function variable is set to: ${my_not_defined_variable}")
foo(my_not_defined_variable)
message("after the function variable is set to: ${my_not_defined_variable}\n\n")

message(STATUS "with an empty, blank defined variable - set(myvar) variable")
set(my_blank_variable)
message("before the function variable is set to: ${my_blank_variable}")
foo(my_blank_variable)
message("after the function variable is set to: ${my_blank_variable}\n\n")

message(STATUS "with an empty string defined variable - set(myvar \"\") variable")
set(my_empty_variable "")
message("before the function variable is set to: ${my_empty_variable}")
foo(my_empty_variable)
message("after the function variable is set to: ${my_empty_variable}\n\n")

message(STATUS "with an empty string defined variable - set(myvar \"keks\") variable")
set(my_variable "keks")
message("before the function variable is set to: ${my_variable}")
foo(my_variable)
message("after the function variable is set to: ${my_variable}\n\n")


message( " ------------------------------------------------------------------ ")
message( " two level function value setting/invocation")

message(STATUS "with a not defined variable - set variable")
message("before the function variable is set to: ${my_not_defined_variable2}")
bar(my_not_defined_variable2)
message("after the function variable is set to: ${my_not_defined_variable2}\n\n")

message(STATUS "with an empty, blank defined variable - set(myvar) variable")
set(my_blank_variable2)
message("before the function variable is set to: ${my_blank_variable2}")
bar(my_blank_variable2)
message("after the function variable is set to: ${my_blank_variable2}\n\n")

message(STATUS "with an empty string defined variable - set(myvar \"\") variable")
set(my_empty_variable2 "")
message("before the function variable is set to: ${my_empty_variable2}")
bar(my_empty_variable2)
message("after the function variable is set to: ${my_empty_variable2}\n\n")

message(STATUS "with an empty string defined variable - set(myvar \"keks\") variable")
set(my_variable2 "keks")
message("before the function variable is set to: ${my_variable2}")
bar(my_variable2)
message("after the function variable is set to: ${my_variable2}\n\n")
