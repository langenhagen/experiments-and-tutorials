#include <conio.h>
#include <iostream>

#include "cuda_runtime.h"

#include <opencv2/opencv.hpp>
#include <boost/chrono.hpp>

#include "gaussian_blur.cuh"

using namespace std;
using namespace cv;
using namespace boost::chrono;



void main() {
    steady_clock::time_point timer_start;

    Mat_<Vec3b> img = imread("chris-reeve2.jpg", CV_LOAD_IMAGE_COLOR);
    Mat_<Vec3f> fimg = img / 255;
    Mat_<Vec3f> omg(img.rows, img.cols);


    float spacial_sigma(4);
    while( 1) {
        
        cout << "\nspacial sigma: " << spacial_sigma << endl;
        
        timer_start = steady_clock::now();
        //test_bilateral_gaussian_9x9_kernel(
        test_convolution_17x17x3_kernel( 
            fimg.rows,
            fimg.cols,
            (float*)fimg.data,
            (float*)omg.data,
            spacial_sigma);
        auto d = round<milliseconds>(steady_clock::now() - timer_start);
        cout << "invocation time: " << d << endl;

        
        cv::imshow( "fimg", fimg);
        cv::imshow( "omg", omg);
        int key = cv::waitKey();
        //cout << key;
        
        if( key == 2490368 /*up*/)
            spacial_sigma+=0.1f;
        else if( key == 2621440 /*down*/)
            spacial_sigma = max( spacial_sigma-0.1f, 0.1f);
        else if( key == 113 /*q*/ || key == 27 /*ESC*/)
            break;
        /**/
    }

    cudaDeviceReset();
}