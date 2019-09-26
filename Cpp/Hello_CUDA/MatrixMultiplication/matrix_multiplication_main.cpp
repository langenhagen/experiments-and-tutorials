#include <iostream>
#include <conio.h>

#include "cuda_runtime.h"
#include <opencv2/opencv.hpp>

#include <boost/chrono.hpp>


using namespace cv;


void matrix_multiplication( float* m, float* n, float* out_mat, int width);

namespace chrono = boost::chrono;

void main() {
    /*
    Mat_<float> m(4,4);
    m << 2, 0, 0, 0,
         0, 2, 0, 0,
         0, 0, 2, 0,
         0, 0, 0, 2;

    Mat_<float> n(4,4);
    n <<    1,    2,    3,    4,
          8.0, 16.0, 32.0, 64.0,
          0.1,  0.2,  0.3,  0.4,
         12.2, 13.4, 14.5, 15.6;

    Mat_<float> o(4,4);
    /**/
    

    chrono::steady_clock::time_point timer_start;
    

    Mat_<float> m, n;
    Mat_<uchar> img = imread("chris-reeve.jpg", CV_LOAD_IMAGE_GRAYSCALE);
    m = img;
    n = img.t();
    Mat_<float> o( m.rows, n.cols);
    Mat_<float> p( m.rows, n.cols);
    /**/

    timer_start = chrono::steady_clock::now();
    matrix_multiplication( (float*)m.data, (float*)n.data, (float*)o.data, o.rows);
    auto gputime = chrono::round<chrono::milliseconds>(boost::chrono::steady_clock::now() - timer_start);
    std::cout << "GPU mult: " << gputime << std::endl << std::endl;
    cudaDeviceReset(); 

    timer_start = chrono::steady_clock::now();
    p = m * n;
    auto cputime = chrono::round<chrono::milliseconds>(boost::chrono::steady_clock::now() - timer_start);
    std::cout << "CPU mult: " << cputime << std::endl << std::endl;


    std::cout << "Izza CPU:GPU ratio of: " << float(cputime.count())/gputime.count() << std::endl;

    cv::normalize(o,o, 255);
    cv::normalize(p,p, 255);
    
    imshow("o", o);
    imshow("p", p);
    
    cv::waitKey();
    getch();
}