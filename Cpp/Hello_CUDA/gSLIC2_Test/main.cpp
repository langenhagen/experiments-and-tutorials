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

    
    
    
Mat_<int> do_gSLIC( Mat& img) {

    Mat_<int> ret = Mat_<int>(img.rows, img.cols);

	const int cluster_count = 1200;
	const float regularizer = 0.8f;

    
	FastImgSeg* mySeg = new FastImgSeg();

	mySeg->initializeFastSeg(img.cols,img.rows, cluster_count);

	unsigned char* imgBuffer=(unsigned char*)malloc(img.cols * img.rows * sizeof(unsigned char)*4);
	memset(imgBuffer,0, img.cols * img.rows*sizeof(unsigned char)*4);


	// gSLIC currently only support 4-dimensional image 
	for(int y=0;y<img.rows; ++y) {
		for (int x=0; x<img.cols; ++x) {
			int bufIdx=(y*img.cols+x)*4;

			imgBuffer[bufIdx] =  img.at<Vec3b>(y,x)[0];
			imgBuffer[bufIdx+1]= img.at<Vec3b>(y,x)[1];
			imgBuffer[bufIdx+2]= img.at<Vec3b>(y,x)[2];
		}
	}

    
	mySeg->LoadImg(imgBuffer);
	mySeg->DoSegmentation(XYZ_SLIC,regularizer);
    

   
    // write results to matrix
    for(int y=0;y<img.rows; ++y) {
		for (int x=0; x<img.cols; ++x) {
            ret(y,x) = mySeg->segMask[y*img.cols + x]; 
		}
	}

    delete mySeg;

    return ret;
}

int main(int argc, const char* argv[])
{
    const string fname = "chris-reeve.jpg";

    Mat img = imread(fname, CV_LOAD_IMAGE_COLOR);

    Mat seg = do_gSLIC(img);

    namedWindow("MA01 - img", WINDOW_AUTOSIZE);
	imshow( "MA01 - img", seg);
    imwrite( "segg.png", seg);
    waitKey(0);


    return 0;

}
    
    
int mainy(int argc, const char* argv[])
{
	std::cout << "**************************************************\n"
                 "*** Welcome to Andis Masterarbeit Projekt 01_2 ***\n" 
                 "**************************************************\n"
				 "\n";

	steady_clock::time_point timer_start;
	milliseconds duration;

	const string fname = "chris-reeve.jpg";	

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

#pragma region build abstraction
/*
	timer_start = steady_clock::now();

	vector<Vec3i> means_per_label = vector<Vec3i>();
	vector<list<Vec3b*>*> label_values_sorted = vector<list<Vec3b*>*>();

	// find max value of the labels --> number of the labels
	double labels_min_val, labels_max_val;
	minMaxLoc( labels, &labels_min_val, &labels_max_val);

	cout << "labels_min_val= " << labels_min_val << "      labels_max_val= " << labels_max_val << "\n";



	// init label_values sorted
	for( int i = 0; i <= labels_max_val; ++i)
	{
		label_values_sorted.push_back( new list<Vec3b*>());
		means_per_label.push_back( Vec3i(0,0,0));
	}

	// fill label_values_sorted
	for(int y=0; y < labels.rows; ++y)
	{
		for(int x=0; x < labels.cols; ++x )
		{
			label_values_sorted[labels.at<int>(y,x)]->push_back( &img.at<Vec3b>( y,x) );		
		}
	}

	// calculate means
	for( unsigned int i= 0; i< means_per_label.size(); ++i)
	{
		auto label_values = label_values_sorted.at(i);
		
		for( auto it= label_values->begin(); it != label_values->end(); ++it)
		{
			// sum up all values
			means_per_label.at(i) += **it;
		}

		means_per_label.at(i) /= (float)label_values->size();
	}


	Mat abstraction ( img.rows, img.cols, CV_8UC3);

	// draw abstracted image
	for(int y=0; y < labels.rows; ++y)
	{
		for(int x=0; x < labels.cols; ++x )
		{
			abstraction.at<Vec3b>(y,x) = means_per_label.at( labels.at<int>(y,x));
		}
	}

	duration = round<milliseconds>(boost::chrono::steady_clock::now() - timer_start);
	cout << "Calculated mean colors for segments & built abstraction map. ";
	print_duration();


	string window_name = "abstraction";
	namedWindow(window_name, WINDOW_AUTOSIZE);
	imshow( window_name, abstraction);
	imwrite( "abstraction.png", abstraction);

*/
#pragma endregion

	

#pragma region element uniqueness
	
	//means_per_label

#pragma endregion


#pragma region write label map to disk
/*
	Mat out_labels( labels.size(), CV_8UC4);

	// create rgb-label-map
	for(int y=0; y < out_labels.rows; ++y)
	{
			
		for(int x=0; x < out_labels.cols; ++x )
		{
			unsigned int val = labels.at<int>(y,x);

			// B G R A
			// we leave the A channel out here bc 24 bit are more than sufficient for later processing here

			out_labels.at<Vec4b>( y,x)[0] = ( val >> 16 ) & 0x000000ff; // B
			out_labels.at<Vec4b>( y,x)[1] = ( val >>  8 ) & 0x000000ff; // G
			out_labels.at<Vec4b>( y,x)[2] = ( val >>  0 ) & 0x000000ff; // R
			out_labels.at<Vec4b>( y,x)[3] = 255;//( val >> 24  ) & 0x000000ff; // A


			//cout	<< "[ " << y << " , " << x <<" ]     " << (( val >> 24 ) & 0x000000ff) << " : " << (( val >> 16 ) & 0x000000ff) 
			//		<< " : " << (( val >> 8 ) & 0x000000ff) << " : " << (( val >> 0 )  & 0x000000ff) << endl;
		}
	}

	imwrite( "out_labels.png", out_labels);
*/
#pragma endregion

	
#pragma region make edges visible
/*
	Mat out ( img.rows, img.cols, CV_8UC3);


	// make segment-edges visible
	for(int y=0; y < labels.rows-1; ++y)
	{
		for(int x=0; x < labels.cols-1; ++x )
		{
			if( labels.at<int>(y,x) != labels.at<int>(y+1,x  ) ||
				labels.at<int>(y,x) != labels.at<int>(y  ,x+1) ||
				labels.at<int>(y,x) != labels.at<int>(y+1,x+1) )
			{
				out.at<Vec3b>(y,x)[0] = 0;
				out.at<Vec3b>(y,x)[1] = 0;
				out.at<Vec3b>(y,x)[2] = 0;
			}
			else
			{
				out.at<Vec3b>(y,x)[0] = 255; //min( img.at<cv::Vec3b>(y,x)[0] + 70, 255);
				out.at<Vec3b>(y,x)[1] = 255; //min( img.at<cv::Vec3b>(y,x)[1] + 70, 255);
				out.at<Vec3b>(y,x)[2] = 255; //min( img.at<cv::Vec3b>(y,x)[2] + 70, 255);
			}

		}
	}


	window_name = "out";
	namedWindow(window_name, WINDOW_AUTOSIZE);
	imshow( window_name, out);
	imwrite( "out.png", out);
/**/
#pragma endregion

		


	// TODO delete label_values_sorted sublists!
	

	cout << "\n*** Jippyajey schweinebacke! ***";
	

	waitKey(0);
	return 0;
}


