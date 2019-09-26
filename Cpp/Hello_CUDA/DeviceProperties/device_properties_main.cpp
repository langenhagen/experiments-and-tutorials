#include <conio.h>
#include <iostream>


#include "cuda_runtime.h"

using namespace std;


void print_display_properties();

void main() {

    print_display_properties();
    
    getch();
}


void print_display_properties() {
    const int kb = 1024;
    const int mb = kb * kb;

    cout << "CUDA version:   v" << CUDART_VERSION << endl;    
    //cout << "Thrust version: v" << THRUST_MAJOR_VERSION << "." << THRUST_MINOR_VERSION << endl << endl; 

    int dev_count;
    cudaGetDeviceCount(&dev_count);
    cout << "CUDA Devices: " << endl << endl;

    for(int i = 0; i < dev_count; ++i)
    {
        cudaDeviceProp props;
        cudaGetDeviceProperties(&props, i);
        cout << i << ": " << props.name << ": " << props.major << "." << props.minor << endl;
        cout << "  Global memory:   " << props.totalGlobalMem / mb << "mb" << endl;
        cout << "  Shared memory:   " << props.sharedMemPerBlock / kb << "kb" << endl;
        cout << "  Constant memory: " << props.totalConstMem / kb << "kb" << endl;
        cout << "  Block registers: " << props.regsPerBlock << endl << endl;
        cout << "  Warp size:         " << props.warpSize << endl;
        cout << "  Threads per block: " << props.maxThreadsPerBlock << endl;
        cout << "  Max block dimensions: [ " << props.maxThreadsDim[0] << ", " << props.maxThreadsDim[1]  << ", " << props.maxThreadsDim[2] << " ]" << endl;
        cout << "  Max grid dimensions:  [ " << props.maxGridSize[0] << ", " << props.maxGridSize[1]  << ", " << props.maxGridSize[2] << " ]" << endl;

        // props has far more in petto

        cout << endl;
    }
}