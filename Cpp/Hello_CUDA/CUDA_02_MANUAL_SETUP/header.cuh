#ifndef __HEADER_CUH__
#define __HEADER_CUH__

#include "cuda_runtime.h"



__global__ void addKernel(int *c, const int *a, const int *b);

cudaError_t addWithCuda(int *c, const int *a, const int *b, unsigned int size);


#endif //__HEADER_CUH__