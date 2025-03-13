"""
Showcase of using a custom C-extension module.

The .so should be in the path of the module

author: andreasl
"""

import myModule

if __name__ == "__main__":
    v = myModule.version()
    print(v)
    r = myModule.fib(11)  # 89
    print(f"Result of fib(11) is {r}")
