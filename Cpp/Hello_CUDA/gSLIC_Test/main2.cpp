#include <conio.h>
#include <iostream>
#include <ctime>
#include <vector>
#include <list>

#include <boost/chrono.hpp>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>

#include "rlutil.h"

#include "..\gSLIC\FastImgSeg.h"



using namespace std;
using namespace cv;
using namespace boost::chrono;

//typedef Color3UC std::tuple<unsigned byte, , std::string>


#define print_duration() \
	rlutil::setColor(14); \
	cout << duration << "\n"; \
	rlutil::setColor(7);

int main(int argc, const char* argv[])
{
	std::cout << "**************************************************\n"
                 "*** Welcome to Andis Masterarbeit Projekt 01_1 ***\n" 
                 "**************************************************\n"
				 "\n";
	steady_clock::time_point timer_start;
	milliseconds duration;

	//const string fname = "chris-reeve.jpg";
    const string fname = "stackoverflow.jpg";

	// ** Image loading and preparation **
    Mat img, grayimg, floatimg;
	
	timer_start = steady_clock::now();
	img = imread(fname, CV_LOAD_IMAGE_COLOR); //CV_LOAD_IMAGE_GRAYSCALE );
	duration = round<milliseconds>(boost::chrono::steady_clock::now() - timer_start);

	cout << "Image \"" << fname << "\" read. Image size: " << img.cols << 'x' << img.rows << ". ";
	print_duration();

	namedWindow("MA01 - img", WINDOW_AUTOSIZE);
	imshow( "MA01 - img", img);
	
	timer_start = steady_clock::now();
	cvtColor( img, grayimg, CV_RGB2GRAY);
	grayimg.convertTo(floatimg, CV_32FC1); // CV_32FC1 for grayscale
	duration = round<milliseconds>(boost::chrono::steady_clock::now() - timer_start);
	
    cout << "Color conversion & channel conversion. ";
	print_duration();


	// ** slic segmentation **
	
	int cluster_count = 1200;
	int region_size = 10; // doesn't exist on gpu solution
	float regularizer = 0.8f;
	//int min_region_size = 10; // doesn't exist on gpu solution
	
	// Mat doesn't support 32-bit unsigned directly, but this should work fine just to hold data.
	Mat labels(floatimg.size(), CV_32SC1);

	timer_start = steady_clock::now();


	FastImgSeg* mySeg=new FastImgSeg();

	mySeg->initializeFastSeg(img.cols,img.rows, cluster_count);

	unsigned char* imgBuffer=(unsigned char*)malloc(img.cols * img.rows * sizeof(unsigned char)*4);
	memset(imgBuffer,0, img.cols * img.rows*sizeof(unsigned char)*4);


	// gSLIC currently only support 4-dimensional image 
	for(int y=0;y<img.rows; ++y)
	{
		for (int x=0; x<img.cols; ++x)
		{
			int bufIdx=(y*img.cols+x)*4;

			imgBuffer[bufIdx] =  img.at<Vec3b>(y,x)[0];
			imgBuffer[bufIdx+1]= img.at<Vec3b>(y,x)[1];
			imgBuffer[bufIdx+2]= img.at<Vec3b>(y,x)[2];
		}
	}


	mySeg->LoadImg(imgBuffer);
	mySeg->DoSegmentation(XYZ_SLIC,regularizer);
	mySeg->Tool_GetMarkedImg();


	duration = round<milliseconds>(boost::chrono::steady_clock::now() - timer_start);
	cout << "Slic segmentation with\n"
			"  cluster count=   " << cluster_count << "\n"
			"  regularizer=     " << regularizer << "\n"
			"  ";
	print_duration();


	Mat result( img.rows, img.cols, CV_8UC3);

	// draw result image
	for(int y=0; y < labels.rows; ++y)
	{
		for(int x=0; x < labels.cols; ++x )
		{
			int bufIdx=(y*img.cols+x)*4;

			result.at<Vec3b>(y,x)[0] = mySeg->markedImg[bufIdx];
			result.at<Vec3b>(y,x)[1] = mySeg->markedImg[bufIdx+1];
			result.at<Vec3b>(y,x)[2] = mySeg->markedImg[bufIdx+2];
		}
	}


	string window_name = "result";
	namedWindow(window_name, WINDOW_AUTOSIZE);
	imshow( window_name, result);
	imwrite( "result.png", result);


	cout << "\n*** Jippyajey schweinebacke! ***";
	

	waitKey(0);
	return 0;
}


