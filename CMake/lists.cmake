# author: andreasl
# version: 18-01-08

# how to deal with lists :)

set(mylist "A" "B")
message("mylist: ${mylist}")

if( "A" IN_LIST mylist)
    message("A is in mylist!")  # holds
endif()
if( "a" IN_LIST mylist)
    message("a is in mylist!")  # doesn't hold
endif()

if( "C" IN_LIST mylist)
    message("C is in mylist!")  # doesn't hold
endif()