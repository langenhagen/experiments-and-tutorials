#pragma once

#define BLOCK_SIZE 64

//#define _DEBUG
#include "cutil.h"
#include <cuda_runtime.h>
#include <stdio.h>


#include <time.h>

#include "MirroredArray.h"
#include "cuda_memory.h"

#include "hash_table.h"


void init_cuda(int argc, char **argv);

struct MatrixEntry;

template<int pd>
__global__ static void createMatrix(const int w, const int h, 
                                    const float *positions, 
                                    const float *values, 
                                    const float *scaleFactor,
                                    MatrixEntry *matrix);


template<int kd>
__global__ static void cleanHashTable(int n, MatrixEntry *matrix);


template<int pd, int vd>
__global__ static void splat(const int w, const int h, float *values, MatrixEntry *matrix);

template<int pd, int vd>
__global__ static void splatCache(const int w, const int h, float *values, MatrixEntry *matrix);

template<int pd, int vd>
__global__ static void blur(int n, float *newValues, MatrixEntry *matrix, int color);

template<int pd, int vd>
__global__ static void slice(const int w, const int h, float *values, MatrixEntry *matrix);

template<int vd, int pd>
void filter_(float* im, float *ref, int w, int h, bool accurate);




void filter(float *im, float *ref, int pd, int vd, int w, int h, bool accurate);

