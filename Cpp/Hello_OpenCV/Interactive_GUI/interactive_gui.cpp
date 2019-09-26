#pragma warning( push )
#pragma warning( disable: 4996) //  [getch] deprecated warning

#include <iostream>
#include <iomanip> // setprecision
#include <conio.h>
#include <sstream>
#include <stdarg.h> // va_list, va_start, va_arg, va_end



#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>

/*
#include <barn_common.hpp>
#include <barn_open_cv_common.hpp>
*/
using namespace cv;
using namespace std;

struct DoubleBuffer {
    Mat_<Vec3b> show;
    Mat_<Vec3b> orig;

    int s1;
    int e1;
    int s2;
    int e2;
    int s3;
    int e3;
};


void mouse_callback( int evt, int x, int y, int flags, void* userdata) {

    DoubleBuffer* buf = (DoubleBuffer*)userdata;
    Mat_<Vec3b>& show = buf->show;
    Mat_<Vec3b>& orig = buf->orig;


    if( evt == cv::EVENT_RBUTTONDOWN) {
        orig.copyTo(show);
        buf->s1 = buf->s2 = buf->s3 = buf->e1 = buf->e2 = buf->e3 = 0;
        imshow("im", show);
    }

    if( evt == cv::EVENT_LBUTTONDOWN) {
        cout << "L Button DOWN [" << x << ", " << y << "] ";
    
        // ctrl alt shift + some event flag utton
        int event_flag_button = EVENT_FLAG_LBUTTON;
        if ( flags == (EVENT_FLAG_CTRLKEY + event_flag_button) ) {
            cout << "CTRL ";
            buf->s1 = x;
        } else if ( flags == (EVENT_FLAG_ALTKEY + event_flag_button) ) {
            cout << "ALT ";
            buf->s2 = x;
        } else if ( flags == (EVENT_FLAG_SHIFTKEY + event_flag_button) ) {
            cout << "SHIFT ";
            buf->s3 = x;
        }
        cout << endl;
        
    } else if( evt == cv::EVENT_LBUTTONUP) {
        cout << "L Button UPPP [" << x << ", " << y << "] ";
        // ctrl alt shift + some event flag utton
        int event_flag_button = 0;
        if ( flags == (EVENT_FLAG_CTRLKEY + event_flag_button) ) {
            cout << "CTRL ";
            buf->e1 = x;
        } else if ( flags == (EVENT_FLAG_ALTKEY + event_flag_button) ) {
            cout << "ALT ";
            buf->e2 = x;
        } else if ( flags == (EVENT_FLAG_SHIFTKEY + event_flag_button) ) {
            cout << "SHIFT ";
            buf->e3 = x;
        }
        cout << endl;

        orig.copyTo(show);

        cout << "drawing red at " << buf->s1 << " - " << buf->e1 << "\n";
        // draw red overlay ctrl
        for( int r=0; r<show.rows; ++r) {
            for( int c=0; c<buf->s1; ++c) {
                show(r,c) = show(r,c);
            }
            for( int c=buf->s1; c<buf->e1; ++c) {
                show(r,c) += Vec3b( 0, 0, 255);
            }
            for( int c=buf->e1; c<show.cols; ++c) {
                show(r,c) = show(r,c);
            }    
        }
        // draw green overlay alt
        for( int r=0; r<show.rows; ++r) {
            for( int c=0; c<buf->s2; ++c) {
                show(r,c) = show(r,c);
            }
            for( int c=buf->s2; c<buf->e2; ++c) {
                show(r,c) += Vec3b( 0, 255, 0);
            }
            for( int c=buf->e1; c<show.cols; ++c) {
                show(r,c) = show(r,c);
            }    
        }
        // draw blue overlay shift
        for( int r=0; r<show.rows; ++r) {
            for( int c=0; c<buf->s3; ++c) {
                show(r,c) = show(r,c);
            }
            for( int c=buf->s3; c<buf->e3; ++c) {
                show(r,c) += Vec3b( 255, 0, 0);
            }
            for( int c=buf->e3; c<show.cols; ++c) {
                show(r,c) = show(r,c);
            }    
        }

        
        imshow("im", show);
    }
        /*
    } else if( evt == cv::EVENT_MOUSEMOVE) {
        cout << "[" << x << ", " << y << "]\n";
    } */
}


/*
mouse down - active mark mode
mouse up - stop mark mode
userdata points to the mgmt struct that stores the positions and stuff
*/

void main() {

    DoubleBuffer buf;
    Mat_<Vec3b> im = imread("chris-reeve.jpg");
    Mat_<Vec3b> bk = imread("chris-reeve.jpg");
    buf.show = im;
    buf.orig = bk;
    buf.s1 = buf.s2 = buf.s3 = buf.e1 = buf.e2 = buf.e3 = 0;

    namedWindow( "im");
    setMouseCallback("im",  mouse_callback, &buf);
    
    // only works with CV_WINDOW_AUTOSIZE
    //cv:: void updateWindow(const string& winname)

    imshow("im", buf.show);
    

    
    waitKey();
}

#pragma warning( pop )