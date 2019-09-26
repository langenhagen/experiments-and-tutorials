#include "matrix_multiplication.cuh"


#define TILE_WIDTH 32


void matrix_multiplication( float* m, float* n, float* out_mat, int width) {
    
    int size = width * width * sizeof(float);
    float* md, *nd, *od;
    
    // Transfrer m and n to device memory
    cudaMalloc( (void**) &md, size);
    cudaMemcpy( md, m, size, cudaMemcpyHostToDevice);
    cudaMalloc( (void**) &nd, size);
    cudaMemcpy( nd, n, size, cudaMemcpyHostToDevice);

    // allocate output matrix on device
    cudaMalloc( (void**) &od, size);

    // kernel invocation
    dim3 dimBlock( TILE_WIDTH, TILE_WIDTH);
    dim3 dimGrid( width/TILE_WIDTH,width/TILE_WIDTH);
    matrix_multiplication_kernel<<<dimGrid, dimBlock>>>( md, nd, od, width);


    // transfer output matrix from device to host
    cudaMemcpy( out_mat, od, size, cudaMemcpyDeviceToHost);

    // free device memory
    cudaFree(md);
    cudaFree(nd);
    cudaFree(od);
}



__global__ void matrix_multiplication_kernel( float* md, float* nd, float* od, int width) {

    __shared__ float mds[TILE_WIDTH][TILE_WIDTH];
    __shared__ float nds[TILE_WIDTH][TILE_WIDTH];

    // cache block & thread indices for faster access
    int bx( blockIdx.x);    int tx( threadIdx.x);
    int by( blockIdx.y);    int ty( threadIdx.y);
    
    // identify row & column of the matrix element to work on
    int row = by * TILE_WIDTH + ty;
    int col = bx * TILE_WIDTH + tx;
    
    // stores the od element that is computed by the thread
    float v(0);

    // loop over md and nd tiles required to compute v
    for( int i=0; i<width/TILE_WIDTH; ++i) {
   
        // collaborative loading of md and nd tiles into shared memory
        mds[ty][tx] = md[row*width + (i*TILE_WIDTH + tx)];
        nds[ty][tx] = nd[ (i*TILE_WIDTH + ty)*width + col];
        __syncthreads();

        for( int j=0; j<TILE_WIDTH; ++j)
            v += mds[ty][j] * nds[j][tx];
        __syncthreads();
    }
    od[row*width + col] = v;
}


__global__ void matrix_multiplication_kernel_old( float* md, float* nd, float* od, int width) {

    // cache block & thread indices for faster access
    int bx( blockIdx.x);    int tx( threadIdx.x);
    int by( blockIdx.y);    int ty( threadIdx.y);
    
    
    // stores the od element that is computed by the thread
    float v(0);

    for( int i=0; i<width; ++i) {
        v +=  md[ty*width+i] * nd[i*width + tx];
    };
    od[ty*width+tx] = v;
}