
#include <iostream>
#include <iomanip> // setprecision
#include <conio.h>
#include <sstream>
#include <stdarg.h>     // va_list, va_start, va_arg, va_end


#include <boost/chrono.hpp>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>

#include <barn_common.hpp>
#include <barn_open_cv_common.hpp>

using namespace cv;
using namespace std;
using namespace boost::chrono;



int main() {

    Mat src, gray, mask;
    vector<vector<Point> > contours;
    vector<Vec4i> hierarchy;
    vector<Mat> subregions;

    src = imread( "abannoc.png", CV_LOAD_IMAGE_COLOR );
    cvtColor( src, gray, CV_BGR2GRAY);
    cv::threshold(gray, mask, 128, 255, CV_THRESH_BINARY);


    findContours( mask, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));
    cout << contours.size() << " contours extracted.\n";

    //mask = Mat::zeros(src.size(), CV_8UC1);
    //drawContours(mask, contours, i, Scalar(255), CV_FILLED); // This is a OpenCV function

    Mat masked_image;
    src.copyTo(masked_image, mask);
    
    for (int i = 0; i < contours.size(); i++) {
    
        if( parent( hierarchy, i) != -1 || contourArea( contours[i]) < 400)
            continue;

        cout << "Area: " << i << " " << contourArea( contours[i])  << "\n";

        Rect roi = boundingRect(contours[i]);
        Mat contour_image = masked_image(roi);
        subregions.push_back(contour_image);
    }


    for( int i=0; i< subregions.size(); ++i) {
        stringstream ss;
        ss << i;
        imshow( ss.str(), subregions[i]);
    }

    waitKey(0);
    return(0);
}


