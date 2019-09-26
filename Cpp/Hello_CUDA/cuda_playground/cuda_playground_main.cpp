#pragma warning(disable: 4996) // getch 
#define HELLO

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include <iostream>
#include <conio.h>

#include <boost/chrono.hpp>

#include <cuda_runtime.h>
#include <device_launch_parameters.h>

#include <opencv2/opencv.hpp>

#include <thrust/host_vector.h>
#include <thrust/device_vector.h>
#include <thrust/sort.h>

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers

#include <barn_common.hpp>
#include "CudaSafeCall.cuh"
#include "Matrix.cuh"

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS

#define CUDA_ERROR_CHECK
#define CUDA_ERROR_CHECK_CAREFUL


///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS

using namespace std;
using namespace boost;
using namespace boost::chrono;
using namespace cv;
using namespace thrust;

void warmup();
void play();
void play2();


///////////////////////////////////////////////////////////////////////////////
// Main function
void main() {
    steady_clock::time_point timer_start1, timer_start2;
    unsigned int acc_time1(0), acc_time2(0);
    cout << "Program start\n\n";

#ifdef CUDA_ERROR_CHECK
    cout << "CUDA_ERROR_CHECK activated\n";
#ifdef CUDA_ERROR_CHECK_CAREFUL
    cout << "CUDA_ERROR_CHECK_CAREFUL activated\n";
#endif
    cout << "\n";
#endif

    warmup(); // warmup kernel

#define N_RUNS 1

    // *** YOUR CODE GOES HERE *** //


    play();

    
    
    /*
    // *** for comparative studies ***
    timer_start1 = steady_clock::now();
    for(int i=0; i<N_RUNS; ++i)
        play(&imm, &omm);
    acc_time1 += round<microseconds>(boost::chrono::steady_clock::now() - timer_start1).count();


    timer_start1 = steady_clock::now();
    for(int i=0; i<N_RUNS; ++i)
        play2(&imm, &omm);
    acc_time2 += round<microseconds>(boost::chrono::steady_clock::now() - timer_start1).count();


    cout << "\n" <<
            "accumulated playtime 1: " << acc_time1 << endl <<
            "accumulated playtime 2: " << acc_time2 << endl;
    /**/

    // *** END YOUR CODE GOES HERE *** //

    cout << "\nProgram end\n";
    cudaDeviceReset();
    getch();
}