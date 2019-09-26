#pragma once

#include "cuda_memory.h"

//#include "device_functions.h"

//#define USE_ADDITIVE_HASH

// turn this on if you want to get slighly less memory consumption and slightly longer run times.
//#define LINEAR_D_MEMORY 

//#define USE_CUSTOM_MODULO

__device__ __constant__ float *table_values;
__device__ __constant__ signed short *table_keys;
__device__ __constant__ int *table_entries;
__device__ __constant__ unsigned int table_capacity;
__device__ __constant__ signed short *table_zeros;
__device__ __constant__ char *table_rank;


/*************************************************************/
/* Fast computation of modulo operator with constant divisor */
/*************************************************************/
__device__ __constant__ unsigned int __div_m;
__device__ __constant__ unsigned int __div_l;
__device__ __constant__ unsigned int __div_c;

#ifdef USE_CUSTOM_MODULO
__device__ inline unsigned int modHash(unsigned int n) {
    unsigned int t1 = __umulhi(__div_m, n);
    return n - ((t1+((n-t1)>>1))>>(__div_l-1)) * __div_c;
}

#else
#define modHash(n) ((n)%(2*table_capacity));
#endif

/*************************************************************/
/* End modulo                                                */
/*************************************************************/

__device__ __constant__ unsigned int hOffset[64];

template<int, int> void createHashTable(int capacity);


//template <int vd>
//static void resetHashTable();

void destroyHashTable();

template<int kd> __device__ __host__ unsigned int hash(signed short *key);

template<int kd> __device__ __host__ unsigned int hash(int *key);

//template<int d> __device__ static bool matchKey(int idx, signed short * key);


//template<int d> __device__ static void generateKey(int idx, signed short * key);

float* swapHashTableValues(float *newValues);


template<int kd>
__device__ int hashTableInsert(unsigned int fh, signed short *key, unsigned int slot);

template<int kd>
__device__ int hashTableInsert(signed short *key, unsigned int slot);



//template<int kd> 
//__device__ int hashTableRetrieveWithHash(unsigned int fh, signed short *key);

template<int kd>
__device__ int hashTableRetrieve(signed short *key);