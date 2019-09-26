/******************************************************************************
/* @file Impl of gaussian_blur.cuh
/* 
/* There can be a lot optmized (e.g. for separable filters) but the current
/* implementation is quite straight forward and simple,  leading to
/* okay-ish results.
/* 
/* TODO rename 
/*
/* @author langenhagen
/* @version 141212
/******************************************************************************/

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers

#include "gaussian_blur.cuh"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include <math.h>  // expf
#include <cstdlib> // malloc
#include <iostream>

///////////////////////////////////////////////////////////////////////////////
// KERNEL FUNCTIONS 

/**
 */
template<int RADIUS, int CHANNELS> __global__ 
void convolve_kernel( float* m,
                      float* o,
                      int rows,
                      int cols,
                      float* convolution_kernel) {

#if defined(KERNEL_WIDTH)
#error Macro KERNEL_WIDTH is already defined.
#endif

#define KERNEL_WIDTH (2*RADIUS+1)
        
    const int tx = threadIdx.x;
    const int ty = threadIdx.y;
    const int tz = threadIdx.z;
    
    // collaboratively create the kernel
    __shared__ float kernel_s[KERNEL_WIDTH][KERNEL_WIDTH];
    kernel_s[ty][tx] = convolution_kernel[ ty*KERNEL_WIDTH + tx];
    
    __syncthreads();

    cols *= CHANNELS;
    const int r = (blockIdx.y*KERNEL_WIDTH + ty);
	const int c = (blockIdx.x*KERNEL_WIDTH + tx)*CHANNELS + tz;
    
    if( r >= rows || c >= cols)
		return;

    const int height = rows-1;
    const int width  = cols-CHANNELS;
    float v(0.0f);

#pragma unroll
    for (int i=-RADIUS; i<=RADIUS; ++i)   // rows
#pragma unroll
    for (int j=-RADIUS; j<=RADIUS; ++j) { // cols
			
        // clamp filter to image borders
		const int m_r = min(max(r+i, 0), height);
		const int m_c = min(max(c+j*CHANNELS, tz), width+tz);
        
		v += m[m_r*cols+m_c] * kernel_s[i+RADIUS][j+RADIUS];
	}
    
    o[r*cols+c] = v;

#undef KERNEL_WIDTH
}


///////////////////////////////////////////////////////////////////////////////
// HELPER FUNCTIONS 

/*
 */
__host__ 
float* prepare_gaussian_kernel( int kernel_radius, float sigma) {
    
    const int width(kernel_radius+kernel_radius+1);
    float* ret = (float*)malloc( width*width*sizeof(float));
    float sum(0);
    
    // calculate weights
    float reciprocal_two_sigma_squared = 1/(2.0f*sigma*sigma);
    for( int r=-kernel_radius; r<=kernel_radius; ++r)
    for( int c=-kernel_radius; c<=kernel_radius; ++c) {
    
        float weight = expf( -(c*c+r*r) * reciprocal_two_sigma_squared);
        int idx = (r+kernel_radius)*width + c+kernel_radius;
        ret[idx] = weight;
        sum += weight;
    }
    
    // normalize
    sum = 1.0f/sum;
    for( int i=0; i<width*width; ++i)
        ret[i] *= sum;
    
    return ret;
}


///////////////////////////////////////////////////////////////////////////////
// TEST FUNCTIONS

/**
 */
__host__
void test_convolution_17x17x3_kernel( int rows, 
                                      int cols,
                                      const float* m,
                                      float* o,
                                      float sigma) {

#define RADIUS_ 8
#define WIDTH_ (RADIUS_ + RADIUS_ + 1)
#define CHANNELS_ 3

    float* d_m, *d_o, *kernel, *d_kernel;
    int m_size = rows*cols*CHANNELS_*sizeof(float);
    int k_size = WIDTH_*WIDTH_*sizeof(float);

    kernel = prepare_gaussian_kernel( RADIUS_, sigma);


    // incorp texture memory //

    cudaMalloc( (void**) &d_m, m_size);
    cudaMemcpy( d_m, m, m_size, cudaMemcpyHostToDevice);
    cudaMalloc( (void**) &d_kernel, k_size);
    cudaMemcpy( d_kernel, kernel, k_size, cudaMemcpyHostToDevice);
    cudaMalloc( (void**)&d_o, m_size);


    const dim3 dimBlock( WIDTH_, WIDTH_, CHANNELS_);
    const dim3 dimGrid( (cols-1)/WIDTH_+1, (rows-1)/WIDTH_+1);

    convolve_kernel<RADIUS_,CHANNELS_><<<dimGrid, dimBlock>>>(
        d_m,
        d_o,
        rows, 
        cols,
        d_kernel);


    cudaMemcpy( o, d_o, m_size, cudaMemcpyDeviceToHost);

    cudaFree(d_kernel);
    cudaFree(d_m);
    cudaFree(d_o);

#undef RADIUS_
#undef WIDTH_
#undef CHANNELS_ 
}