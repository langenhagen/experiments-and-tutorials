#!/usr/bin/env bash
set -euo pipefail

SOURCE_DIR="."
BUILD_DIR="build"
GENERATOR="Ninja"
TARGET="cpp_round_trip"

cmake -S "${SOURCE_DIR}" -B "${BUILD_DIR}" -G "${GENERATOR}"
cmake --build "${BUILD_DIR}" --parallel
"./${BUILD_DIR}/${TARGET}"
