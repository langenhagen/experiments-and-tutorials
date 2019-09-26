/******************************************************************************
/* @file GPU parallel Implementation of the slic superpixels algorithm.
/*
/* TODO everything TODO re-doc
/*
/* @author langenhagen
/* @version 150104
/******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers

#include "superpixel_statistic.h"

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include "cuda_runtime.h"
#include <opencv2/opencv.hpp>
#include <limits>
#include <vector>

#include <matrix.cuh>
#include <barn_cuda_common.cuh> // TODO rename

#include <thrust/host_vector.h>
#include <thrust/device_vector.h>
#include <thrust/sort.h>
#include <thrust/reduce.h>

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS

/** Initializes the segmentation matrix by tiling the matrix in Kx*Ky squares.
 * @param m The segmentation matrix.
 * @param Kx The number of segments in x direction.
 * @param Ky The number of segments in y direction.
 */
__global__
void init_segmentation_mat( Matrix<int>* m,
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


/** DOC
 */
__global__
void assign_cluster( superpixel_statistic *stats,
                     const int n_stats,
                     const Matrix<float> *im,
                     const Matrix<int> *segmat) {
                     
    const int tx = blockIdx.x*blockDim.x + threadIdx.x;

    // XXX collaborative tiled load of stats into shm!

    if( tx >= im->rows*im->cols)
        return;
        
    const int r = tx / im->cols;
    const int c = tx - r*im->cols;
        
    const float L = im->at(r,c,0);
    const float a = im->at(r,c,1);
    const float b = im->at(r,c,2);
    
    int best_seg;
    int best_dist( std::numeric_limits<float>::max()); // OR FLT_MAX 1E+37 from #include <float.h>
    for( int i=0; i<n_stats; ++i) {
    
        const superpixel_statistic& s = stats[i];
        
        const float dist = powf(s.x-r, 2.0f) + powf(s.y-c, 2.0f); // TODO maybe change
        if( dist < best_dist) {
            best_dist = dist;
            best_seg = i;
        }
    }
    segmat->at(r,c) = best_seg;
}


/** DOC
 */
__global__
void calculate_means( superpixel_statistic *stats,
                     const int n_stats,
                     const Matrix<float> *im,
                     const Matrix<int> *segmat) {
    
    const int tx = blockIdx.x*blockDim.x + threadIdx.x;
    if( tx >= n_stats)
        return;
    
    superpixel_statistic s;
    
    // XXX collaborative tiled loads of im into shm
    // XXX collaborative tiled load of segmat into shm

    for( int r=0; r<im->rows; ++r)
    for( int c=0; c<im->cols; ++c) {
    
        if( tx == segmat->at(r,c)) {
            s.y += r;
            s.x += c;
            s.L += im->at(r,c,0);
            s.a += im->at(r,c,1);
            s.b += im->at(r,c,2);
            s.size++;
        }
    }
    
    s.x = pos.x / size;
    s.y = pos.y / size;
    s.L = pos.x / size;
    s.a = pos.y / size;
    s.b = pos.z / size;
    stats[tx] = s;
}

/** Geodesic SLIC implementation on the GPU.
 */
class GGeodesicSlic {

protected: // vars

    int _K;             ///< number superpixels
    int _n_iter;        ///< number of k means iterations
    float _col_width;   ///< initial width of each column
    
public: // c'tor & d'tor
    
    /** Main constructor.
     * @param K number of superpixels.
     * @param n_iterations The number of k means iterations.
     * @param column_width The initial width of each column.
     */
    GGeodesicSlic( int K, int n_iterations, float column_width)
        : _K(K), _n_iter(n_iterations), _col_width(column_width)
    {}
    
public: // methods

    /** Performs a geodesic superpixel segmentation on 
     * an input image and returns the segmentation matrix.
     * @param im The input image.
     * @return DOC
     */
    cv::Mat_<int> segment( const cv::Mat_<cv::Vec3f>& im) const {
        cv::Mat_<int> ret( im.rows, im.cols);
        Matrix<float> h_im( im.rows, im.cols, im.channels(), true, (float*)im.data);
        Matrix<float>* d_im = h_im.h2d();
        Matrix<int> h_segmentation( im.rows, im.cols, 1, true, (int*)ret.data);
        Matrix<int> *d_segmentation = h_segmentation.h2d();
        
        // TODO mersenne twister init
        
        // Compute the spacing and grid size of the superpixels
        double sp_area = 1.0 * im.cols * im.rows / _K;
        int Kx = 0.5 + im.cols / sqrt( sp_area);
        int Ky = 0.5 + im.rows / sqrt( sp_area);
        int K = Kx*Ky;
        
        int win_sz = 1.0 * sqrt(sp_area) + 1;
        
        
        thrust::device_vector< superpixel_statistic> d_sp_stats(K);
        superpixel_statistic* d_sp_stats_p = thrust::raw_pointer_cast( d_sp_stats.data());

        // initialize segmentation mat on regular grid
        dim3 dimBlock( 128);
        dim3 dimGrid( (im.rows*im.cols-1) / dimBlock.x+1);
        init_segmentation_mat<<< dimGrid, dimBlock>>>( 
            d_segmentation, 
            Kx, 
            Ky);
        

        // calculate superpixel statistics
        dimBlock = dim3( 128);
        dimGrid  = dim3( (K-1) / dimBlock.x+1);
        calculate_means<<<dimGrid, dimBlock>>>( 
            d_sp_stats_p, 
            K,
            d_im,
            d_segmentation);


        // assign cluster to segmentation pixels
        dimBlock = dim3( 128);
        dimGrid  = dim3( (K-1) / dimBlock.x+1);
        assign_cluster<<< dimGrid, dimBlock>>>(
            d_sp_stats_p,
            K,
            d_im,
            d_segmentation);


        // 2. init dx & dy
        // 3. RUN K means
        
        // 4. update (?)
        
        
        
    }
    
    /** DOC
     */
    std::vector<SuperpixelStatistic> statistics( const cv::Mat_<cv::Vec3f>& im, const cv::Mat_<cv::Vec3b>& rgb, const cv::Mat_<int>& segmentation) const {
        // TODO
    }

    // TODO more to go....
     
};