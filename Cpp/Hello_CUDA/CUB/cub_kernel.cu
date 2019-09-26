
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <iostream>

#include <cub/cub.cuh>

using namespace std;

//
// Block-sorting CUDA kernel
//
template <int BLOCK_THREADS, int ITEMS_PER_THREAD>
__global__ void block_sort_kernel(int *d_in, int *d_out)
{
    using namespace cub;

    // Specialize BlockLoad, BlockStore, and BlockRadixSort collective types
    typedef cub::BlockLoad<
        int*, BLOCK_THREADS, ITEMS_PER_THREAD, BLOCK_LOAD_TRANSPOSE> BlockLoadT;
    typedef cub::BlockStore<
        int*, BLOCK_THREADS, ITEMS_PER_THREAD, BLOCK_STORE_TRANSPOSE> BlockStoreT;
    typedef cub::BlockRadixSort<
        int, BLOCK_THREADS, ITEMS_PER_THREAD> BlockRadixSortT;

    // Allocate type-safe, repurposable shared memory for collectives
    __shared__ union {
        typename BlockLoadT::TempStorage       load; 
        typename BlockStoreT::TempStorage      store; 
        typename BlockRadixSortT::TempStorage  sort;
    } temp_storage; 

    // Obtain this block's segment of consecutive keys (blocked across threads)
    int thread_keys[ITEMS_PER_THREAD];
    int block_offset = blockIdx.x * (BLOCK_THREADS * ITEMS_PER_THREAD);   
    BlockLoadT(temp_storage.load).Load(d_in + block_offset, thread_keys);
    
    __syncthreads();    // Barrier for smem reuse
    // Collectively sort the keys
    BlockRadixSortT(temp_storage.sort).Sort(thread_keys);
    __syncthreads();    // Barrier for smem reuse
    // Store the sorted segment 
    BlockStoreT(temp_storage.store).Store(d_out + block_offset, thread_keys);
}



// Helper function for using CUDA to add vectors in parallel.
void block_sort(int *o, const int *m, int m_sz)
{
    int* d_o, *d_m;
    int m_bytes= m_sz*sizeof(int);


    cudaMalloc((void**)&d_o, m_bytes);
    cudaMalloc((void**)&d_m, m_bytes);
    cudaMemcpy( d_m, m, m_bytes, cudaMemcpyHostToDevice);

    
#define N_THREADS_P_BLOCK 128
#define N_KEYS_P_THREAD 16

    const dim3 dimBlock( N_THREADS_P_BLOCK);
    const dim3 dimGrid( (m_sz/N_KEYS_P_THREAD-1)/dimBlock.x+1);

    cout << "dimBlock.x = " << dimBlock.x << "\n"
            "dimGrid.x  = " << dimGrid.x << endl;
 
    // launch a block-sorting kernel in which each block of N_THREADS threads 
    // sorts segments of N_KEYS_P_THREAD*N_THREADS keys
    block_sort_kernel<N_THREADS_P_BLOCK, N_KEYS_P_THREAD><<<dimGrid, dimBlock>>>(d_m, d_o); 

    cudaMemcpy( o, d_o, m_bytes, cudaMemcpyDeviceToHost);

    cudaFree(d_o);
    cudaFree(d_m);

#undef N_THREADS
}

