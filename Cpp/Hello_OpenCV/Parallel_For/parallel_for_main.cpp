
#include <iostream>
#include <conio.h>

#include <boost/chrono.hpp>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>


template <class T>
class Parallel_clipBufferValues: public cv::ParallelLoopBody {   
private:
    T *_buffer;

 public:
    Parallel_clipBufferValues(T* bufferToProcess)
        : _buffer(bufferToProcess)
    {}

    virtual void operator()( const cv::Range &r ) const {
        for (register int i = r.start; i != r.end; ++i) {
            _buffer[i] = 255 - _buffer[i];
        }
    }
};


void main() {

    // die sequenzielle klassische schleife ist immer schneller Oo

    using namespace boost::chrono;
    using namespace cv;
    using namespace std;

    Mat img = imread("chris-reeve.jpg", CV_LOAD_IMAGE_COLOR);


    steady_clock::time_point timer_start;
   
    timer_start = steady_clock::now();
    const int size=img.rows*img.cols * img.channels();

    int minVal=0, maxVal=255;
    parallel_for_(cv::Range(0,size-1), Parallel_clipBufferValues<uchar>(img.data));
    cout << " : " << round<nanoseconds>(boost::chrono::steady_clock::now() - timer_start) << "\n";
    
    timer_start = steady_clock::now();
    for( int i=0; i < size; ++i)
        img.data[i] = 255 - img.data[i];
    cout << " : " << round<nanoseconds>(boost::chrono::steady_clock::now() - timer_start);

    namedWindow("img", WINDOW_AUTOSIZE);
	imshow( "img", img);
    waitKey(0);

    getch();
}