"""
Introspect the current function name and get a reference to the function.

See:
https://stackoverflow.com/questions/990422/how-to-get-a-reference-to-current-modules-attributes-in-python
"""

import sys

print("---")
print("Function/Method names")


class C:
    def get_name_of_function(self, frame_nr=0):
        frame = sys._getframe(frame_nr)
        function_name = frame.f_code.co_name
        return function_name


def bar(frame_nr=0):
    c = C()
    return c.get_name_of_function(frame_nr)


function_name = bar(0)  # 'get_name_of_function'
outer_function_name = bar(1)  # 'bar'
print(function_name)
print(outer_function_name)

# for free functions do
# function_ref = globals()[function_name]
# print(function_ref)

# for bound methods do:
c = C()
method_ref = c.__getattribute__(function_name)
print(method_ref)


def get_frame(frame_nr=0):
    i = 42
    j = 12.3
    frame = sys._getframe(frame_nr)
    return frame


get_frame()
