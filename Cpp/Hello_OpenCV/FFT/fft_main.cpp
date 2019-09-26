
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



void rearrange( Mat& mat) {

    // crop the spectrum, if it has an odd number of rows or columns
    mat = mat(Rect(0, 0, mat.cols & -2, mat.rows & -2));

    // rearrange the quadrants of Fourier image  so that the origin is at the image center
    int cx = mat.cols/2;
    int cy = mat.rows/2;

    Mat q0(mat, Rect(0, 0, cx, cy));   // Top-Left - Create a ROI per quadrant
    Mat q1(mat, Rect(cx, 0, cx, cy));  // Top-Right
    Mat q2(mat, Rect(0, cy, cx, cy));  // Bottom-Left
    Mat q3(mat, Rect(cx, cy, cx, cy)); // Bottom-Right

    Mat tmp;                           // swap quadrants (Top-Left with Bottom-Right)
    q0.copyTo(tmp);
    q3.copyTo(q0);
    tmp.copyTo(q3);

    q1.copyTo(tmp);                    // swap quadrant (Top-Right with Bottom-Left)
    q2.copyTo(q1);
    tmp.copyTo(q2);
}


int main() {

    //const char* filename = "chris-reeve.jpg";
    const char* filename = "e.png";

    Mat I, padded, planes[2], complex, mag, phs/*e*/; 

    I = imread(filename, CV_LOAD_IMAGE_GRAYSCALE);


    //expand input image to optimal size -  on the border add zero values
    int m = getOptimalDFTSize( I.rows );
    int n = getOptimalDFTSize( I.cols );

    copyMakeBorder(I, padded, 0, m - I.rows, 0, n - I.cols, BORDER_CONSTANT, Scalar::all(0));

    planes[0] = Mat_<float>(padded);
    planes[1] = Mat::zeros(padded.size(), CV_32F);

    merge(planes, 2, complex);    // Add to the expanded another plane with zeros
    dft(complex, complex);        // this way the result may fit in the source matrix

    // compute the magnitude & phase
    split(complex, planes);                     // planes[0] = Re(DFT(I), planes[1] = Im(DFT(I))
    magnitude(planes[0], planes[1], mag);       // planes[0] = magnitude
    phase(planes[0],planes[1], phs);
    
    float radius = sqrt( pow( complex.at<Vec2f>(0, 1)[0], 2) + 
                         pow( complex.at<Vec2f>(0, 1)[1], 2));
    cout << "Radius: " << radius;

    /*
    It turns out that the dynamic range of the Fourier coefficients is too large to be displayed on the screen.
    We have some small and some high changing values that we can’t observe like this. 
    Therefore the high values will all turn out as white points, while the small ones as black. 
    To use the gray scale values to for visualization we can transform our linear scale to a logarithmic one:
    */

    // switch to logarithmic scale
    mag += Scalar::all(1);                    
    log(mag, mag);
    phs += Scalar::all(1);
    log(phs,phs);

    rearrange(mag);
    rearrange(phs);

    
    // Transform the matrices with float values into a viewable image form (float between values 0 and 1).
    normalize(mag, mag, 0, 1, CV_MINMAX);
    normalize(phs, phs, 0, 1, CV_MINMAX);


    Mat out;
    mag.convertTo(out, CV_8UC1,255);
    imwrite("out.png",out);

    

    imshow("Input Image", I );    // Show the result
    imshow("spectrum magnitude", mag);
    imshow("phase", phs);
    waitKey();

    return 0;
}