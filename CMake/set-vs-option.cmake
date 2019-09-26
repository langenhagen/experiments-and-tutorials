# the different ways of wrapping things up in colons in if-clauses :)

# this filec checks the behavior of the functions set and option.
# also try  cmake -DC=foo ..

# set set
# set opt
# opt opt
# opt set


set(A "A-set-set-1")
message("set A: ${A}")
set(A "A-set-set-2")
message("set A: ${A}\n")

set(B "B-set-option-1")
message("set B: ${B}")
option(B "my help string" "B-set-option-2")
message("opt B: ${B}\n")

option(C "my help string" "C-option-option-1")
message("opt C: ${C}")
option(C "my help string" "C-option-option-2")
message("opt C: ${C}\n")

option(D "my help string" "D-set-option-1")
message("opt D: ${D}")
set(D "D-set-option-2")
message("set D: ${D}\n")