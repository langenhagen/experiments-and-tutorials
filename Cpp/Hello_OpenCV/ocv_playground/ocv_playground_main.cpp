#pragma warning( push )
#pragma warning( disable: 4996) //  [getch] deprecated warning


#define HELLO

#include <iostream>
#include <iomanip> // setprecision
#include <conio.h>
#include <sstream>
#include <stdarg.h> // va_list, va_start, va_arg, va_end

#include <Windows.h>


#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>

#include <barn_common.hpp>
#include <barn_open_cv_common.hpp>

#include <Persistence1D/src/persistence1d/persistence1d.hpp>


using namespace cv;
using namespace std;




int main() {


    Mat3b im = imread("IMG_5117.jpg");
    Mat3f fim;

    im.convertTo( fim, CV_32FC3);

    mean_shift<float, 3>( fim, 12, 30, 3);
    
    fim.convertTo(im, CV_8U);
    //cv::normalize( fim, fim);

    imwrite("out.png", im);


    cout << "Program END";
    cout << "\a";

    waitKey();
    getch();
    return 0;
}






#pragma warning( pop )