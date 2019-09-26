#include "Functions.h"

#include <iostream>
#include <algorithm>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

using namespace std;
using namespace cv;


void main()
{
	cout << "First, simple Matrix fun...\n";

	Mat m = Mat(4,4, CV_8UC1);
	
	m.at<uchar>(0,0)= 1;
	m.at<uchar>(0,1)= 2;
	m.at<uchar>(0,2)= 3;
	m.at<uchar>(0,3)= 4;

	m.at<uchar>(1,0)= 5;
	m.at<uchar>(1,1)= 6;
	m.at<uchar>(1,2)= 7;
	m.at<uchar>(1,3)= 8;

	m.at<uchar>(2,0)= 9;
	m.at<uchar>(2,1)= 10;
	m.at<uchar>(2,2)= 11;
	m.at<uchar>(2,3)= 12;

	m.at<uchar>(3,0)= 13;
	m.at<uchar>(3,1)= 14;
	m.at<uchar>(3,2)= 15;
	m.at<uchar>(3,3)= 16;

	Mat n =  m( Rect(0,0,2,2));

	n.at<uchar>(1,1) = 99;

	cout << m << endl <<  m( Rect(1,1,2,2));
	
	cin.get();

	cout << endl << "Second, doing something with images..." << endl;


	String pic_name = "lena2.png";

	Mat input_img = imread( pic_name, 0);
	if (!input_img.data) {
		cout << "ERROR: Cannot load template image from\n" << pic_name << endl;
		cin.get();
		return;
    }


	//showImage( input_img, "Win_Title", 0);


	const double sigma(1);
	Mat inv_img = 255-input_img;
	Mat& grad( calcDirectionalGrad( input_img, sigma));
	Mat& grad_inv( calcDirectionalGrad( inv_img, sigma));
	
	grad += grad_inv;

	imwrite( "inverted.png", inv_img);


	cout << "Some info about inv_img:\n" << 
		"inv_img.size().width: "<< inv_img.size().width << " inv_img.size().height: " << inv_img.size().height << endl <<
		"inv_img.cols: " << inv_img.cols << " inv_img.rows: " << inv_img.rows << endl;


	vector<Mat> c;
	split( grad, c);

	showImage( c[0], "Gradx");
	showImage( c[1], "Grady");
	Mat edges = c[0] + c[1];

	// retrieve maximum gradient magnitude
	double max_mag = 0;
	for( int r=0; r<grad.rows; ++r)
	{
		for( int c=0; c<grad.cols; ++c)
		{
			int v = grad.at<Vec2b>(r,c)[0];
			int w = grad.at<Vec2b>(r,c)[1];
			int mag = v*v + w*w;
			if( mag > max_mag)
				max_mag = mag; 
		}	
	}
	
	double t = 40;
	cv::threshold( edges, edges, t, max_mag, THRESH_BINARY);

	showImage( edges, "Edges");
	
	double max_val;
    
	minMaxIdx(edges, 0, &max_val);

	cout << "minMaxIdx: " << max_val;

	cin.get();



	cout << "*** FINISHED ***";
	cin.get();
}