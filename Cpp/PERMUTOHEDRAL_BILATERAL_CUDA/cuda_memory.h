#ifndef CUDA_MEMORY_H
#define CUDA_MEMORY_H

#include "macros.h"

size_t cu_memory_allocation = 0;


void cu_malloc(void ** pointer, size_t size) {
    cu_err_chk(cudaMalloc(pointer, size));
    cu_memory_allocation += size;
}

#endif
