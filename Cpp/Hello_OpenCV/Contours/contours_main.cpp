#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <iostream>
#include <stdio.h>
#include <stdlib.h>



using namespace cv;
using namespace std;

Mat src;
Mat src_gray;

int thresh = 100;
int max_thresh = 255;

RNG rng(12345);

/// Function header
void thresh_callback(int, void* );

/** @function main */
int main( int argc, char** argv ) {

    /// Load source image and convert it to gray
    src = imread( "lena.png", CV_LOAD_IMAGE_COLOR );
    
    /// Convert image to gray
    cvtColor( src, src_gray, CV_BGR2GRAY );
    

    /// Create Window
    char* source_window = "Source";
    namedWindow( source_window, CV_WINDOW_AUTOSIZE );
    imshow( source_window, src );
    createTrackbar( " Canny thresh:", "Source", &thresh, max_thresh, thresh_callback );
    thresh_callback( 0, 0 );

    waitKey(0);
    return(0);
}

/** @function thresh_callback */
void thresh_callback(int trackbar_val, void* ) {

    vector<vector<Point> > contours;
    vector<Vec4i> hierarchy;

    Mat bw_mat;

    cv::threshold(src_gray, bw_mat, trackbar_val, 255, CV_THRESH_BINARY);

    findContours( bw_mat, contours, hierarchy, CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE, Point(0, 0) );

    /// Draw contours
    Mat drawing = Mat::zeros( bw_mat.size(), CV_8UC3 );
    
    for( int i=0; i<contours.size(); i++ ) {
        Scalar color = Scalar( rng.uniform(0, 255), rng.uniform(0,255), rng.uniform(0,255) );
        drawContours( drawing, contours, i, color, 1, 8, hierarchy, 0, Point() );
    }

    
    cout << "\n"
            "#hierarchy " << hierarchy.size() << "\n"
            "#contours " << contours.size() << "\n";
    /*
    for( int i=0; i<contours.size(); ++i) {

        auto c = contours[i];

        cout << ":contour " << i << "  " << contours[i].size() << "\n";
        for_each( c.begin(), c.end(), [](Point& p){ cout << "(" << p.x << ", " << p.y << ") "; });
        cout << "\n";
        cout << "\n";
    }
    /**/



    /// Show in a window
    namedWindow( "Contours", CV_WINDOW_AUTOSIZE );
    imshow( "Contours", drawing );
}