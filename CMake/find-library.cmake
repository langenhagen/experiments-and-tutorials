# find_library() finds libraries and puts their paths into a given variable.
# if the variable is already set, it will not be overwritten.

# classical way
find_library(prior_notset log4cplus)  # should find it on my mac under /usr/local/lib/liblog4cplus.dylib, if installed
message("prior_notset: ${prior_notset}")

set(prior_set_to_something "hallo i bims 1 nice variable")
find_library(prior_set_to_something log4cplus)
message("prior_set_to_something: ${prior_set_to_something}")

find_library(unfindable_notset megacrapthatneverexistshere)  # will set it to "unfindable_notset-NOTFOUND"
message("unfindable_notset: ${unfindable_notset}")

set(unfindable_set "ich bin schon da :)")
find_library(unfindable_set megacrapthatneverexistshere)
message("unfindable_set: ${unfindable_set}")
