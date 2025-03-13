"""
Showcase the usage of the 3rd party library `cffi`

This is a magical factory can build an ffi module for `uuids`.

see:
    - https://cffi.readthedocs.io/en/latest/
    - https://www.youtube.com/watch?v=X5irxO5VCHw&ab_channel=anthonywritescode

author: andreasl
"""

from cffi import FFI

# functions that we want to include in our new module
CDEF = """
typedef unsigned char uuid_t[16];

void uuid_generate(uuid_t out);
"""

ffibuilder = FFI()
ffibuilder.cdef(CDEF)
ffibuilder.set_source(
    "_my_cffi_uuid_module",  # new module name
    "#include <uuid/uuid.h>",  # header to use
    libraries=["uuid"],  # link against the library `uuid`
)
