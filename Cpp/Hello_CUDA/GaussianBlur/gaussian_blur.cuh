/******************************************************************************
/* @file Convolution kernel functions header.
/* 
/* Rename file
/* 
/* @author langenhagen
/* @version 141210
/******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include "cuda_runtime.h"

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


///////////////////////////////////////////////////////////////////////////////
// KERNEL FUNCTIONS 

template< int RADIUS, int CHANNELS> __global__ 
void convolve_kernel( float* m,
                      float* o,
                      int rows,
                      int cols,
                      float* convolution_kernel);


///////////////////////////////////////////////////////////////////////////////
// TEST FUNCTIONS

void test_convolution_17x17x3_kernel( int rows, 
                                      int cols,
                                      const float* m,
                                      float* o,
                                      float sigma);