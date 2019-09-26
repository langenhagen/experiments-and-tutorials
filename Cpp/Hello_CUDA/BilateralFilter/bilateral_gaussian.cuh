/******************************************************************************
/* @file Bilateral kernel functions header.
/* 
/* @author langenhagen
/* @version 141209
/******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include "cuda_runtime.h"

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


///////////////////////////////////////////////////////////////////////////////
// KERNEL FUNCTIONS 


template<int RADIUS, int CHANNELS> __global__
void bilateral_gaussian_kernel( float* m,
                                float* o,
                                unsigned int rows,
                                unsigned int cols,
                                unsigned int n_channels,
                                float* spacial_convolution_kernel,
                                const float inv_two_color_variance);



///////////////////////////////////////////////////////////////////////////////
// TEST FUNCTIONS 

__host__
void test_bilateral_gaussian_9x9x3( float* m,
                                    float* o,
                                    const unsigned int rows,
                                    const unsigned int cols,
                                    const float spacial_sigma,
                                    const float color_sigma);

__host__
void test_bilateral_gaussian_17x17x3( float* m,
                                      float* o,
                                      const unsigned int rows,
                                      const unsigned int cols,
                                      const float spacial_sigma,
                                      const float color_sigma);



