
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdio.h>

#include <conio.h>




inline int block_num( int thread_num, int block_size) {
	return (thread_num-1)/block_size+1;
}



/// todo doc
__device__
float3 rgb2xyz_px( uchar3 rgb) {
    float3 ret;
    
    rgb.x = rgb.x / 255.0f;
    rgb.y = rgb.y / 255.0f;
    rgb.z = rgb.z / 255.0f;

    ret.x = 0.412453f * rgb.x + 0.357580f * rgb.y + 0.180423f * rgb.z;
    ret.y = 0.212671f * rgb.x + 0.715160f * rgb.y + 0.072169f * rgb.z;
    ret.z = 0.019334f * rgb.x + 0.119193f * rgb.y + 0.950227f * rgb.z;

    return ret;
}

/// TODO doc
__device__
float3 xyz2lab_px( float3 xyz) {
    float3 ret;
    
    xyz.x /= 0.950456f;
	float y3 = __expf( __logf(xyz.y)/3.0f);
	xyz.z /= 1.088754f;

	

	xyz.x = xyz.x>0.008856f ? __expf (__logf (xyz.x)/3.0f) : (7.787f * xyz.x + 0.13793f);
	xyz.y = xyz.y>0.008856f ? y3 : 7.787f * xyz.y + 0.13793f;
	xyz.z = xyz.z>0.008856f ? xyz.z /= __expf(__logf(xyz.z)/3.0f) : (7.787f * xyz.z + 0.13793f);
	
	ret.x = xyz.y > 0.008856f ? (116.0f *y3 - 16.0f) : 903.3f * xyz.y; // l
	ret.y = (xyz.x - xyz.y) * 500.0f; // a
	ret.z = (xyz.y - xyz.z) * 200.0f; // b
	
    return ret;
}


__device__
float3 rgb2lab_px( uchar3 rgb) {

    return xyz2lab_px( rgb2xyz_px(rgb));
}


__global__ 
void rgb2cielab(uchar3* in_image, float3* out_image, int width, int height) {
	int block_offset = blockIdx.y * blockDim.y * width + blockIdx.x * blockDim.x;
	int offset = block_offset + threadIdx.x + threadIdx.y * width;

	uchar3 pixel = in_image[offset];


	out_image[offset]= rgb2lab_px( pixel);

    //manage overlapping
}



// Helper function for using CUDA to add vectors in parallel.
cudaError_t rgb2cielab()
{
    cudaError_t ret;

    // Choose which GPU to run on, change this on a multi-GPU system.
    ret = cudaSetDevice(0);
    if (ret != cudaSuccess) {
        fprintf(stderr, "cudaSetDevice failed!  Do you have a CUDA-capable GPU installed?");
        // call cudaFree() ...
    }

    // Allocate GPU buffers for three vectors (two input, one output)    .
    ret = cudaMalloc((void**)&dev_c, size * sizeof(int));
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaMalloc failed!");

    }

    cudaStatus = cudaMalloc((void**)&dev_a, size * sizeof(int));
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaMalloc failed!");

    }

    cudaStatus = cudaMalloc((void**)&dev_b, size * sizeof(int));
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaMalloc failed!");

    }

    // Copy input vectors from host memory to GPU buffers.
    cudaStatus = cudaMemcpy(dev_a, a, size * sizeof(int), cudaMemcpyHostToDevice);
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaMemcpy failed!");

    }

    cudaStatus = cudaMemcpy(dev_b, b, size * sizeof(int), cudaMemcpyHostToDevice);
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaMemcpy failed!");

    }

    // Launch a kernel on the GPU with one thread for each element.
    addKernel<<<1, size>>>(dev_c, dev_a, dev_b);

    // Check for any errors launching the kernel
    cudaStatus = cudaGetLastError();
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "addKernel launch failed: %s\n", cudaGetErrorString(cudaStatus));

    }
    
    // cudaDeviceSynchronize waits for the kernel to finish, and returns
    // any errors encountered during the launch.
    cudaStatus = cudaDeviceSynchronize();
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaDeviceSynchronize returned error code %d after launching addKernel!\n", cudaStatus);

    }

    // Copy output vector from GPU buffer to host memory.
    cudaStatus = cudaMemcpy(c, dev_c, size * sizeof(int), cudaMemcpyDeviceToHost);
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaMemcpy failed!");

    }


    return cudaStatus;
}




int main()
{
    cout << "

	getch();
    return 0;
}