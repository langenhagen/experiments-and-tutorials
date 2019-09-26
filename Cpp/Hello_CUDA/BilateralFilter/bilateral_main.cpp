#include <conio.h>
#include <iostream>
#include <math.h>


#include <boost/chrono.hpp>
#include <opencv2/opencv.hpp>


#include "bilateral_gaussian.cuh"

using namespace std;
using namespace boost::chrono;


void main() {

    steady_clock::time_point timer_start;
    
    cv::Mat_<cv::Vec3b> img = cv::imread( "me&cerns.jpg", CV_LOAD_IMAGE_COLOR);
    cv::Mat_<cv::Vec3f> fimg;
    cv::Mat_<cv::Vec3f> omg( img.rows, img.cols);
    fimg = img / 255;

    float spacial_sigma(4);
    float color_sigma(1);
    while( 1) {
        
        cout << "\nspacial sigma: " << spacial_sigma;
        cout << "\ncolor sigma:   " << color_sigma << endl;
        
        timer_start = steady_clock::now();
        //test_bilateral_gaussian_9x9x3(
        test_bilateral_gaussian_17x17x3( 
            (float*)fimg.data,
            (float*)omg.data,
            fimg.rows,
            fimg.cols,
            spacial_sigma,
            color_sigma);
        auto d = round<milliseconds>(steady_clock::now() - timer_start);
        cout << "invocation time: " << d << endl;

        

        cv::imshow( "fimg", fimg);
        cv::imshow( "omg", omg);
        int key = cv::waitKey();
        //cout << key;
        
        if( key == 2424832 /*left*/)
            color_sigma = max( color_sigma-0.01f, 0.01f);
        else if( key == 2555904 /*right*/)
            color_sigma+=0.01f;
        else if( key == 2490368 /*up*/)
            spacial_sigma+=0.1f;
        else if( key == 2621440 /*down*/)
            spacial_sigma = max( spacial_sigma-0.1f, 0.1f);
        else if( key == 113 /*q*/ || key == 27 /*ESC*/)
            break;
        /**/
    }


    cudaDeviceReset();
    //getch();
}


