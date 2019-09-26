# taken from: https://stackoverflow.com/questions/24297999/function-vs-macro-in-cmake

set(var "ABC")

macro(Moo arg)
  # macros seem not to change introduced input vars. Supposedly, they are:
  # " They are string replacements much like the C preprocessor would do with a macro.
  #   If you want true CMake variables and/or better CMake scope control you should
  #   look at the function command. "

  message("arg = ${arg}")
  set(arg "abc")
  message("# After change the value of arg.")
  message("arg = ${arg}")
endmacro()
message("=== Call macro ===")
Moo(${var})

function(Foo arg)
  message("arg = ${arg}")
  set(arg "abc")
  message("# After change the value of arg.")
  message("arg = ${arg}")
endfunction()
message("=== Call function ===")
Foo(${var})