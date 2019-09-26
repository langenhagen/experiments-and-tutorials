
#include <iostream>
#include <iomanip> // setprecision
#include <conio.h>
#include <sstream>
#include <stdarg.h>     // va_list, va_start, va_arg, va_end

#include <barn_common.hpp>
#include <barn_open_cv_common.hpp>

#include <boost/chrono.hpp>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>

using namespace cv;
using namespace std;
using namespace boost::chrono;


Mat doCalcHist( Mat& src, Mat& out, Scalar color, int& bin_size, int& bins);

void main( int argc, char** argv )
{
    Mat src, src2, fsrc, fsrc2, hsv, hsv2, res, layers[3];
 

    int bin_size = 2, bins = 360;
    res.create( 500, bin_size*bins, CV_32FC3);
    

    src = imread("globe.jpg", CV_LOAD_IMAGE_COLOR);
    src.convertTo( fsrc, CV_32FC3);
    cvtColor(fsrc, hsv, CV_BGR2HSV);
    split( hsv, layers);
    layers[0] -= 180;
    doCalcHist( layers[0], res, Scalar( 0, 0, 255), bin_size, bins);

    src2 = imread("cr2.png", CV_LOAD_IMAGE_COLOR);
    src2.convertTo( fsrc2, CV_32FC3);
    cvtColor(fsrc2, hsv2, CV_BGR2HSV);
    split( hsv2, layers);
    layers[0] -= 180;
    doCalcHist( layers[0], res, Scalar( 0, 255, 0), bin_size, bins);
    
    
    /*
    //oder...
    split( hsv2, layers);
    for( int r=0; r<layers[0].rows; ++r)
        for( int c=0; c<layers[0].cols; ++c) {
            float f = layers[0].at<float>(r,c);
            layers[0].at<float>(r,c) = wrap(f+20);
        }

    layers[0] -= 180;
    doCalcHist( layers[0], res, Scalar( 0, 255,0), bin_size, bins);
    */

    imshow("src1", src);
    imshow("src2", src2);

    imshow("1", res);
    waitKey();
}

Mat doCalcHist( Mat& src, Mat& h_hist_img, Scalar color, int& bin_size, int& bins) {

    // H HIST //////////////////////////////////////////////////////////////////////

    int h_histSize = bins * bin_size;
    
    float h_range[] = { -180, 180} ;
    const float* h_histRange = { h_range };
    
    Mat h_hist;
    const int h_binsize=1;

    calcHist( &src, 1, 0, Mat(), h_hist, 1, &h_histSize, &h_histRange, true, false );

    double min, max;
    minMaxLoc( h_hist, &min, &max,0,0);

    normalize( h_hist, h_hist, 0, h_hist_img.rows, NORM_MINMAX); 

    std::vector<float> v;
    for( int h = 0; h < h_histSize; ++h ) {
        v.push_back( h_hist.at<float>(h));
    }


    for( int h = 1; h < h_histSize; ++h ) {
        int binVal_old = cvRound( h_hist.at<float>(h-1));
        int binVal = cvRound( h_hist.at<float>(h));
        


        line( h_hist_img,
              Point( h_binsize*(h-1), h_hist_img.rows - binVal_old),
              Point( h_binsize*(h  ), h_hist_img.rows - binVal),
              color, 
              1, 
              CV_AA, 
              0);
    }

    return h_hist_img;
}


#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <iostream>
#include <stdio.h>

using namespace std;
using namespace cv;

/**
 * @function main
 */
int mainz( int argc, char** argv )
{
  Mat src, dst;

  /// Load image
  src = imread("globe.jpg", CV_LOAD_IMAGE_COLOR);

  /// Separate the image in 3 places ( B, G and R )
  vector<Mat> bgr_planes;
  split( src, bgr_planes );

  /// Establish the number of bins
  int histSize = 4;

  /// Set the ranges ( for B,G,R) )
  float range[] = { 0, 256 } ;
  const float* histRange[] = { range };

  bool uniform = true; bool accumulate = false;

  Mat b_hist, g_hist, r_hist;

  /// Compute the histograms:
  calcHist( &bgr_planes[0], 1, 0, Mat(), b_hist, 1, &histSize, histRange, uniform, accumulate );
  calcHist( &bgr_planes[1], 1, 0, Mat(), g_hist, 1, &histSize, histRange, uniform, accumulate );
  calcHist( &bgr_planes[2], 1, 0, Mat(), r_hist, 1, &histSize, histRange, uniform, accumulate );
  

  // Draw the histograms for B, G and R
  int hist_w = 512; int hist_h = 400;
  int bin_w = cvRound( (double) hist_w/histSize );

  Mat histImage( hist_h, hist_w, CV_8UC3, Scalar( 0,0,0) );

  /// Normalize the result to [ 0, histImage.rows ]
  normalize(b_hist, b_hist, 0, histImage.rows, NORM_MINMAX, -1, Mat() );
  normalize(g_hist, g_hist, 0, histImage.rows, NORM_MINMAX, -1, Mat() );
  normalize(r_hist, r_hist, 0, histImage.rows, NORM_MINMAX, -1, Mat() );

  /// Draw for each channel
  for( int i = 1; i < histSize; i++ )
  {
      line( histImage, Point( bin_w*(i-1), hist_h - cvRound(b_hist.at<float>(i-1)) ) ,
                       Point( bin_w*(i), hist_h - cvRound(b_hist.at<float>(i)) ),
                       Scalar( 255, 0, 0), 2, 8, 0  );
      line( histImage, Point( bin_w*(i-1), hist_h - cvRound(g_hist.at<float>(i-1)) ) ,
                       Point( bin_w*(i), hist_h - cvRound(g_hist.at<float>(i)) ),
                       Scalar( 0, 255, 0), 2, 8, 0  );
      line( histImage, Point( bin_w*(i-1), hist_h - cvRound(r_hist.at<float>(i-1)) ) ,
                       Point( bin_w*(i), hist_h - cvRound(r_hist.at<float>(i)) ),
                       Scalar( 0, 0, 255), 2, 8, 0  );
  }

  /// Display
  namedWindow("calcHist Demo", CV_WINDOW_AUTOSIZE );
  imshow("calcHist Demo", histImage );

  waitKey(0);

  return 0;
}