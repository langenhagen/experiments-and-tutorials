#!/usr/bin/env python3
"""
Run protoc with the gRPC plugin to generate protobuf messages and gRPC code.

Write the output to the subfolder `generated/`.
Repeated calls to this script are OK. They will update the generated files.

Usage:

    python3 generate_grpc_code.py
"""
from os import chdir
from pathlib import Path

from grpc_tools import protoc

proj_dir = Path(__file__).parent.parent
chdir(proj_dir)

proto_dir = "protos"
proto_file = f"{proto_dir}/my_service.proto"
target_folder = "generated"

print(
    "Generating Protobuf messages and gRPC code "
    f'from "{proto_file}" '
    f'to "{target_folder}"...'
)

success = protoc.main(
    (
        "",
        f"--proto_path={target_folder}={proto_dir}",
        "--python_out=.",
        "--grpc_python_out=.",
        proto_file,
    )
)

if success == 0:
    print("Success.")
else:
    print("Got errors.")
