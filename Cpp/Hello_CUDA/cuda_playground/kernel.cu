#define HELLO
/******************************************************************************
/* @file Playground kernel function. Have fun!
/*
/*
/* @author langenhagen
/* @version YYMMDD
/******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers


///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include <cstdlib>
#include <iostream>
#include <math.h>

#include <cuda_runtime.h>
#include "device_launch_parameters.h"

#include "thrust/host_vector.h"
#include "thrust/device_vector.h"
#include "thrust/sort.h"
#include <thrust/iterator/constant_iterator.h>
#include <thrust/iterator/counting_iterator.h>
#include <thrust/iterator/transform_iterator.h>
#include <thrust/iterator/zip_iterator.h>


//#include <barn_common.hpp> // << provokes linker errors!
#include <matrix.cuh>
#include <barn_cuda_common.cuh>

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS


///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


using namespace std;
using namespace thrust;


typedef unsigned char uchar;



__global__ 
void play_kernel( Matrix<int>* m,
                  const int Kx,
                  const int Ky) {

    const int tx = blockIdx.x * blockDim.x + threadIdx.x;

    if( tx+1 > m->n_cells())
        return;

    const int r = tx / m->cols;
    const int c = tx - r*m->cols;
    
    const int k_r = r * Ky / m->rows;
    const int k_c = c * Kx / m->cols;

    m->at(r,c) = k_r * Kx + k_c;
}


struct row_calculator : public thrust::unary_function<unsigned int /*input*/, unsigned int /*output*/> {

    unsigned int _n_cols;

    row_calculator( unsigned int n_cols) : _n_cols(n_cols)
    {}

    __host__ __device__
    unsigned int operator()(const unsigned int &i) const { 
        return i/_n_cols;
    }
};

struct col_calculator : public thrust::unary_function<unsigned int, unsigned int> {

    unsigned int _n_cols;

    col_calculator( unsigned int n_cols) : _n_cols(n_cols)
    {}

    __host__ __device__
    unsigned int operator()(const unsigned int &i) const { 
        unsigned int r = i/_n_cols;
        return i - r*_n_cols;
    }
};


void play() {
    Matrix<int> segmat( 25, 10);
    Matrix<int> *d_segmat = segmat.h2d();

    // create segment mat
    play_kernel<<< (segmat.rows*segmat.cols-1)/1024+1, 1024>>>( d_segmat, 5, 2);
    segmat.d2h( d_segmat, false);

    device_vector<int> segvec_d( segmat.data, segmat.data+segmat.n_cells()); // aka keys

    for( auto it=segvec_d.begin(); it!=segvec_d.end(); ++it)
        cout << *it << "\t";


    // constant iterator for count of segment size später
    constant_iterator<int> const_beg_it(1);
    constant_iterator<int> const_beg_end( const_beg_it + segmat.n_cells());
    
    cout << "const iter: \n"
            "const_beg_it[0]: " << const_beg_it[0] << "\n"
            "const_beg_it[1]: " << const_beg_it[1] << "\n"
            "const_beg_it[2]: " << const_beg_it[2] << "\n";
            

    // hiilfs-gnördel für die row/col iterators: countet alle cells durch _und_ist_änderbar_
    device_vector<unsigned int> value_mappings( segmat.rows*segmat.cols);
    sequence(value_mappings.begin(), value_mappings.end());
 
    /*
    // hiilfs-iterator für row/col iterators: countet alle data cells durch
    counting_iterator<int> cnt_beg_it(0);
    counting_iterator<int> cnt_end_it( cnt_beg_it + segmat.n_cells());

    cout << "counting iter: \n"
            "cnt_beg_it[0]: " << cnt_beg_it[0] << "\n"
            "cnt_beg_it[1]: " << cnt_beg_it[1] << "\n"
            "cnt_beg_it[2]: " << cnt_beg_it[2] << "\n";*/

    
    
    // retrieves row / column for given data element

  //  typedef transform_iterator< row_calculator, thrust::counting_iterator<int>> row_it_t;
  //  typedef transform_iterator< col_calculator, thrust::counting_iterator<int>> col_it_t;

    auto row_beg_it = make_transform_iterator( counting_iterator<int>(0), row_calculator( segmat.cols));
    auto row_end_it = make_transform_iterator( value_mappings.end(),   row_calculator( segmat.cols));
    auto col_beg_it = make_transform_iterator( counting_iterator<int>(0), col_calculator( segmat.cols));
    auto col_end_it = make_transform_iterator( value_mappings.end(),   col_calculator( segmat.cols));


    cout << "row iter (with " << segmat.cols << " cols): \n"
            "row_beg_it[ 0]: " << row_beg_it[ 0] << "\n"
            "row_beg_it[ 1]: " << row_beg_it[ 1] << "\n"
            "row_beg_it[ 2]: " << row_beg_it[ 2] << "\n"
            "row_beg_it[12]: " << row_beg_it[12] << "\n"
            "row_beg_it[17]: " << row_beg_it[17] << "\n"
            "row_beg_it[29]: " << row_beg_it[29] << "\n";

    cout << "col iter (with " << segmat.cols << " cols): \n"
            "col_beg_it[ 0]: " << col_beg_it[ 0] << "\n"
            "col_beg_it[ 1]: " << col_beg_it[ 1] << "\n"
            "col_beg_it[ 2]: " << col_beg_it[ 2] << "\n"
            "col_beg_it[12]: " << col_beg_it[12] << "\n"
            "col_beg_it[17]: " << col_beg_it[17] << "\n"
            "col_beg_it[29]: " << col_beg_it[29] << "\n";


    /*
    auto values_beg_it = make_zip_iterator( make_tuple( row_beg_it, col_beg_it));
    auto values_end_it = make_zip_iterator( make_tuple( row_end_it, col_end_it));
    */

    

    sort_by_key(segvec_d.begin(), segvec_d.end(), value_mappings.begin());

    for( int i=0; i<segmat.rows*segmat.cols; ++i) {
        
        cout << "i: " << i << "  segment: " << segvec_d[i] << "  row: " << row_beg_it[value_mappings[i]] << "  col: " << col_beg_it[value_mappings[i]] << "\n";
        
    }



}




__global__ 
void play_kernel2() {
    
}

void play2() {

}



///////////////////////////////////////////////////////////////////////////////
// WARMUP KERNEL AND INVOCATION FUNCTION

__global__ 
void warmup_kernel() {
    printf( "Warming up... ");
}

void warmup() {
    warmup_kernel<<<1,1>>>();
    cudaDeviceSynchronize();
    printf("Done.\n");
}

///////////////////////////////////////////////////////////////////////////////

//struct myfunctor {
//    
//    unsigned char x;
//
//    myfunctor( unsigned char _x) : x(_x)
//    {}
//
//    __host__ __device__
//    unsigned char operator()(const unsigned char &c) const { 
//        return x-c;
//    }
//};