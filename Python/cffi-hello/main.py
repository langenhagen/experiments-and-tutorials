"""
Showcase the usage of the cffi module

author: andreasl
"""

import uuid

from _my_cffi_uuid_module import ffi, lib


def gen_uuid() -> uuid.UUID:
    c_uuid = ffi.new("uuid_t")
    lib.uuid_generate(c_uuid)
    return uuid.UUID(bytes=bytes(c_uuid))


if __name__ == "__main__":
    u = gen_uuid()
    print(u)
