"""
Showcase the package 'inspect'

author: andreasl
version: 19-07-02
"""
import inspect
import sys

def get_function_arguments():
    """Retrieve the calling function's parameter and argument dictionary."""
    frame = sys._getframe(1)
    _, _, _, param_names_values = inspect.getargvalues(frame)
    param_names_values.pop('self', None)  # you might want to comment that out
    return param_names_values


def print_function_arguments():
    """Print the calling function parameters."""
    frame = sys._getframe(1)
    argnames, args_name, kwargs_name, param_names_values = inspect.getargvalues(frame)
    param_names_values.pop('self', None)  # you might want to comment that out
    print("argnames:           " + str(argnames))
    print("param_names_values: " + str(param_names_values))
    print("args_name:          " + str(args_name))
    print("kwargs_name:        " + str(kwargs_name))

def foo(s, i=0, *myargs, **mykwargs):
    print_function_arguments()

class C:
    def foo(self, a, b=14, *yourargs, **yourkwargs):
        print_function_arguments()

foo("hallo")
print('---')
foo("welt", 12)
print('---')
foo("this", 13, 14.1, 15.2, 16.3, foo='this', bar='works')
print('---')
print('On the module level:')
print(get_function_arguments())
print('---')
print('On the Method level')
c = C()
c.foo(11)
print('---')

