/******************************************************************************
/* @file Impl of bilateral_gaussian.cuh
/* 
/* @author langenhagen
/* @version 141209
/******************************************************************************/

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers

#include "bilateral_gaussian.cuh"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include <math.h>  // expf
#include <cstdlib> // malloc


///////////////////////////////////////////////////////////////////////////////
// KERNEL FUNCTIONS 


/**
 */
template<int RADIUS, int CHANNELS> __global__
void bilateral_gaussian_kernel( float* m,
                                float* o,
                                unsigned int rows,
                                unsigned int cols,
                                float* spacial_convolution_kernel,
                                const float inv_two_color_variance) {                                    

#if defined(KERNEL_WIDTH)
#error Macro KERNEL_WIDTH is already defined.
#endif

#define KERNEL_WIDTH (RADIUS+RADIUS+1)

    const int tx = threadIdx.x;
    const int ty = threadIdx.y;
    const int tz = threadIdx.z;

    // collaboratively create the kernel
    __shared__ float kernel_s[KERNEL_WIDTH][KERNEL_WIDTH];
    kernel_s[ty][tx] = spacial_convolution_kernel[ ty*KERNEL_WIDTH + tx];
    __syncthreads();


    cols *= CHANNELS;
    const int r = (blockIdx.y*KERNEL_WIDTH + ty);
	const int c = (blockIdx.x*KERNEL_WIDTH + tx)*CHANNELS + tz;
    
    if( r >= rows || c >= cols)
		return;

    const int height = rows-1;
    const int width  = cols-CHANNELS;
    
    const float old_v = m[r*cols+c];
    float v(0.0f);
    float normalization(0);

#pragma unroll
    for (int i=-RADIUS; i<=RADIUS; ++i)   // rows
#pragma unroll
	for (int j=-RADIUS; j<=RADIUS; ++j) { // cols
			
        // clamp filter to image borders
        const float m_v = m[min(max(r+i, 0), height)*cols + min(max(c+j*CHANNELS, tz), width+tz)];
        
        float weight = /*spacial part*/ kernel_s[i+RADIUS][j+RADIUS] * 
                       /* color part */ __expf( -((m_v-old_v)*(m_v-old_v)) * inv_two_color_variance);

		v += m_v * weight;
        normalization += weight;
	}

    o[r*cols+c] = v/normalization;

#undef KERNEL_WIDTH
}


///////////////////////////////////////////////////////////////////////////////
// HELPER FUNCTIONS 

/**
 */
__host__
float* prepare_spacial_kernel( int kernel_radius, float sigma) {
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
    for( int i=0; i<width*width; ++i) {
        ret[i] *= sum;
    }

    return ret;
}


///////////////////////////////////////////////////////////////////////////////
// TEST FUNCTIONS


/**
 */
__host__
void test_bilateral_gaussian_9x9x3( float* m,
                                    float* o,
                                    const unsigned int rows,
                                    const unsigned int cols,
                                    const float spacial_sigma,
                                    const float color_sigma) {

#define RADIUS_ 4
#define WIDTH_ (RADIUS_ + RADIUS_ + 1)
#define CHANNELS_ 3


    float *d_m, *d_o, *kernel, *d_kernel;
    int m_size = rows*cols*CHANNELS_*sizeof(float);
    int kernel_size = WIDTH_*WIDTH_*sizeof(float);

    kernel = prepare_spacial_kernel( RADIUS_, spacial_sigma);

    cudaMalloc( (void**)&d_m, m_size);
    cudaMemcpy( d_m, m, m_size, cudaMemcpyHostToDevice);
    cudaMalloc( (void**)&d_kernel, kernel_size);
    cudaMemcpy( d_kernel, kernel, kernel_size, cudaMemcpyHostToDevice);
    cudaMalloc( (void**)&d_o, m_size);


    const dim3 dimBlock( WIDTH_, WIDTH_, CHANNELS_);
    const dim3 dimGrid( (cols-1)/WIDTH_+1, (rows-1)/WIDTH_+1);
    

    bilateral_gaussian_kernel<RADIUS_, CHANNELS_><<<dimGrid, dimBlock>>>(
        d_m,
        d_o,
        rows,
        cols,
        d_kernel,
        1.0f/( 2*color_sigma*color_sigma));


    cudaMemcpy( o, d_o, m_size, cudaMemcpyDeviceToHost);

    cudaFree( d_m);
    cudaFree( d_o);
    cudaFree( d_kernel);

#undef RADIUS_
#undef WIDTH_
#undef CHANNELS_
}




/**
 */
__host__
void test_bilateral_gaussian_17x17x3( float* m,
                                      float* o,
                                      const unsigned int rows,
                                      const unsigned int cols,
                                      const float spacial_sigma,
                                      const float color_sigma) {

#define RADIUS_ 8
#define WIDTH_ (RADIUS_ + RADIUS_ + 1)
#define CHANNELS_ 3


    float *d_m, *d_o, *kernel, *d_kernel;
    int m_size = rows*cols*CHANNELS_*sizeof(float);
    int kernel_size = WIDTH_*WIDTH_*sizeof(float);

    kernel = prepare_spacial_kernel( RADIUS_, spacial_sigma);

    cudaMalloc( (void**)&d_m, m_size);
    cudaMemcpy( d_m, m, m_size, cudaMemcpyHostToDevice);
    cudaMalloc( (void**)&d_kernel, kernel_size);
    cudaMemcpy( d_kernel, kernel, kernel_size, cudaMemcpyHostToDevice);
    cudaMalloc( (void**)&d_o, m_size);


    const dim3 dimBlock( WIDTH_, WIDTH_, CHANNELS_);
    const dim3 dimGrid( (cols-1)/WIDTH_+1, (rows-1)/WIDTH_+1);
    

    bilateral_gaussian_kernel<RADIUS_, CHANNELS_><<<dimGrid, dimBlock>>>(
        d_m,
        d_o,
        rows,
        cols,
        d_kernel,
        1.0f/( 2*color_sigma*color_sigma));


    cudaMemcpy( o, d_o, m_size, cudaMemcpyDeviceToHost);

    cudaFree( d_m);
    cudaFree( d_o);
    cudaFree( d_kernel);

#undef RADIUS_
#undef WIDTH_
#undef CHANNELS_
}