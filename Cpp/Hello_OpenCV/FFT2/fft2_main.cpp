
#include <iostream>
#include <conio.h>
#include <sstream>
#include <stdarg.h>     // va_list, va_start, va_arg, va_end

#include <boost/chrono.hpp>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>

using namespace cv;
using namespace std;
using namespace boost::chrono;


inline void show_img( const std::string& img_name, const Mat& img, int wait_key=-1) {
	imshow( img_name, img);
    if(wait_key >= 0)
        waitKey(wait_key);
}


void show_imgs(int n, ...)
{
    va_list vl;
    va_start(vl,n);

    for(int i=0; i<n ; ++i) {

        cv::Mat& mat = va_arg(vl,cv::Mat);

        std::stringstream window_name;
        window_name << "matrix #" << i;
        cv::imshow( window_name.str(), mat);
    }
    va_end(vl);

    cv::waitKey(0);
}


int main() {

     Mat inputImage, fImage, fourierTransform, inverseTransform, finalImage;

    // Load an image
    inputImage = imread("chris-reeve.jpg", 0);

    // Go float
    fImage;
    inputImage.convertTo(fImage, CV_32F);

    // FFT
    std::cout << "Direct transform...\n";
    fourierTransform;
    dft(fImage, fourierTransform, DFT_SCALE|DFT_COMPLEX_OUTPUT);

    // Some processing
    //doSomethingWithTheSpectrum();

    // IFFT
    std::cout << "Inverse transform...\n";

    dft(fourierTransform, inverseTransform, DFT_INVERSE|DFT_REAL_OUTPUT);

    // Back to 8-bits
    finalImage;
    inverseTransform.convertTo(finalImage, CV_8U);

    show_imgs(4, inputImage, fImage, inverseTransform, finalImage);

    return 0;    
}