
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <algorithm>
#include <assert.h>
#include <conio.h>
#include <limits>
#include <random>
#include <time.h>

#include <boost/chrono.hpp>

#include <thrust/device_vector.h>
#include <thrust/host_vector.h>
#include <thrust/extrema.h>

using namespace std;
using namespace boost::chrono;


#define NSIZE 1024


template< int SIZE>
__global__
void normalize_kernel( float* o, float* i, int size) {

    //// collaboratively load the array
    //__shared__ float i_s[SIZE];
    //i_s[threadIdx.x] = i[ threadIdx.x];
    //__syncthreads();


    //float mn = i_s[0];
    //float mx = i_s[0];
    //
    //// find min / max
    //// could be made more divide&conquerish
    //for( int a=1; a<size; ++a) {
    //    mn = min( i_s[a], mn);
    //    mx = max( i_s[a], mx);
    //}

    //float scale = mx - mn;
    //o[threadIdx.x] = i_s[threadIdx.x]/scale + mn;
    o[threadIdx.x] = threadIdx.x;
}


void normalize_h( float* o, float* i, int size) {

    float min = i[0];
    float max = i[0];
    
    // find min / max
    for( int a=1; a<size; ++a) {
        if ( i[a] < min)
            min = i[a];
        else if( i[a] > max)
            max = i[a];
    }
    

    // normalize
    float scale = max - min;
    for( int a=1; a<size; ++a) {
        o[a] = i[a]/scale + min;
    }
}



void normalize_d( float* o, float* i, int size) {

    float *d_o, *d_i;

    cudaMalloc( (void**)&d_i, size*sizeof(float));
    cudaMemcpy( d_i, i, size*sizeof(float), cudaMemcpyHostToDevice);
    //cudaMalloc( (void**)&d_o, size*sizeof(float));
    //
    //dim3 dimBlock(NSIZE,1,1);
    //dim3 dimGrid(1,1,1);
    //
    //normalize_kernel<NSIZE><<<dimGrid, dimBlock>>>( o, i, size);

//    cudaMemcpy( o, d_o, size*sizeof(float), cudaMemcpyDeviceToHost);

    //cudaFree( d_o);
    cudaFree( d_i);

}



struct normalize_functor{
    
    const float scaler;
    const float addend;

    normalize_functor(float _scaler, float _addend) : scaler(_scaler), addend(_addend) 
    {} 


    __host__ __device__ 
    float operator()( const float& x) const { 
        return scaler * x + addend; 
    } 
};

void normalize_thrust( float* o, float* i, int size) {

    thrust::device_vector<float> i_d(i, i+size);
    

    //typedef thrust::pair<thrust::device_vector<float>::iterator, thrust::device_vector<float>::iterator> result_type;
    auto mnmx_d = thrust::minmax_element( i, i+size);
    
    float mn = *mnmx_d.first;
    float scale = *mnmx_d.second - mn;


    thrust::transform( i_d.begin(), i_d.end(), i_d.begin(), normalize_functor( scale, mn));
    
    thrust::copy(i_d.begin(), i_d.end(), o);
}


void main() {

    int n_runs = 100;
    steady_clock::time_point timer_start;
    unsigned int dur_h=0;
    unsigned int dur_d=0;
    unsigned int dur_t=0;

    srand( time(NULL));
    const int bin_size = NSIZE;

    cout << "Running " << n_runs << " times each.\n\n";


    float* arr  = (float*) malloc(bin_size*sizeof(float));
    float* narr = (float*) malloc(bin_size*sizeof(float));;

    for( int i=0; i<bin_size; ++i)
        arr[i] = rand();

    
    for( int i=0; i<n_runs; ++i) {       

        timer_start = steady_clock::now();
        normalize_h(narr, arr, bin_size);
        dur_h += round<microseconds>( steady_clock::now() - timer_start).count();

    }

    for( int i=0; i<n_runs; ++i) {

        timer_start = steady_clock::now();
        normalize_d( narr, arr, bin_size);
        dur_d += round<microseconds>( steady_clock::now() - timer_start).count();
    }


    for( int i=0; i<n_runs; ++i) {

        timer_start = steady_clock::now();
        normalize_thrust( narr, arr, bin_size);
        dur_t += round<microseconds>( steady_clock::now() - timer_start).count();
    }

    cout << "dur_h: " << dur_h << endl;
    cout << "dur_d: " << dur_d << endl;
    cout << "dur_t: " << dur_t << endl;

    cudaDeviceReset();
    getch();
}

