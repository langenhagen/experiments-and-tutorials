#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <iostream>
#include <stdio.h>
#include <stdlib.h>


using namespace cv;
using namespace std;

inline int parent( std::vector<cv::Vec4i> hierarchy, int contour_index);

int main( int argc, char** argv ) {

    Mat src1, bw1, drawing1;
    vector<vector<Point> > contours1;
    vector<Vec4i> hierarchy1;

    const int thresh = 128;
    const int max_thresh = 255;
    
    // Load source image, bw-mask it and find contours
    src1 = imread( "peng2.png", CV_LOAD_IMAGE_GRAYSCALE );
    threshold(src1, bw1, thresh, max_thresh, CV_THRESH_BINARY);
    findContours( bw1, contours1, hierarchy1, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE);

    cout << contours1.size() << " contours found...\n"
            "\n";

    
    cout << "Hierarchy:\n";
    for( int i=0; i< hierarchy1.size(); ++i) {
        cout << i << ":  next: " << hierarchy1[i][0] << "  prev: " << hierarchy1[i][1] << 
            "  1st child: " << hierarchy1[i][2] << "  parent: " << hierarchy1[i][3] << "\n";
    }
    cout << "\n";
    

    cout << "Top level contours:\n";
    for( int i=0; i< hierarchy1.size(); ++i) {
        if( parent( hierarchy1, i) == -1)
            cout << i << ", ";
    }
    cout << "\n\n";


    // draw contours and their numbers to image =================
    drawing1 = Mat::zeros( bw1.size(), CV_8UC3 );
    for( int i=0; i<contours1.size(); i++ ) {

        vector<Point> c = contours1[i];
        //Point2f center;
        
        Scalar font_color = Scalar( 96, parent(hierarchy1, i) == -1 ? 192 : 96, parent(hierarchy1, i) == -1 ? 192 : 96);
        Scalar color = Scalar( 255, 255, 255);
        
        Scalar contour_mean = mean( c);
        Point2f center(contour_mean.val[0], contour_mean.val[1]);
        

        // draw numbers into contour centers, plus a red pixel into contour-centers in drawing1
        string text( to_string((long long)i));
        Size text_size = getTextSize(text, FONT_HERSHEY_SIMPLEX, 1, 4, nullptr);

        center.x -= text_size.width/2;
        center.y += text_size.height/2;

        putText( drawing1, text, center, FONT_HERSHEY_SIMPLEX, 1, font_color, 4);
        putText( src1, text, center, FONT_HERSHEY_SIMPLEX, 1, font_color, 4);
        drawContours( drawing1, contours1, i, color, 1, 8 );
        
        drawing1.at<Vec3b>(center)[0] = 0;
        drawing1.at<Vec3b>(center)[1] = 0;
        drawing1.at<Vec3b>(center)[2] = 255;
    }
    // END draw contours to matrices ============================

    // compare every with every::: ==============================
    for( int i=0; i!=contours1.size(); ++i) {
        for( int j=i+1; j!=contours1.size(); ++j) {

            vector<Point> &c1 = contours1[i];
            vector<Point> &c2 = contours1[j];

            double matchval1 = matchShapes(c1, c2, CV_CONTOURS_MATCH_I1, 0 /*parameter not supported by matchShapes by now anyway*/);
            double matchval2 = matchShapes(c1, c2, CV_CONTOURS_MATCH_I2, 0);
            double matchval3 = matchShapes(c1, c2, CV_CONTOURS_MATCH_I3, 0);
            
            cout << "Matching contours " << i << " <-> " << j << "\n"
                    "CV_CONTOURS_MATCH_I1 = " << matchval1 << "\n"
                    "CV_CONTOURS_MATCH_I2 = " << matchval2 << "\n"
                    "CV_CONTOURS_MATCH_I3 = " << matchval3 << "\n"
                    "\n";
        }
    }
    // END compare every with every::: ===========================


    imshow( "src1", src1);
    imshow( "drawing1", drawing1);

    waitKey(0);
    return(0);
}


/** A convenience function for better readability of code.
 * equals to hierarchy[contour_index][3];
 * @param hierarchy A hierarchy of contours.
 * @param contour_index The index of a contour.
 * @return The index of the parent contour, -1 if there is no parent-contour.
 */
inline int parent( std::vector<cv::Vec4i> hierarchy, int contour_index) {
    return hierarchy[contour_index][3];
}