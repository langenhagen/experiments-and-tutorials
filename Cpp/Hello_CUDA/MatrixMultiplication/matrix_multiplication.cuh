#ifndef  __MATRIX_MULTIPLICATION_CUH__
#define __MATRIX_MULTIPLICATION_CUH__



#include "cuda_runtime.h"



void matrix_multiplication( float* m, float* n, float* out_mat, int width);


__global__ void matrix_multiplication_kernel( float* md, float* nd, float* od, int width);
__global__ void matrix_multiplication_kernel_old( float* md, float* nd, float* od, int width);

#endif // __MATRIX_MULTIPLICATION_CUH__