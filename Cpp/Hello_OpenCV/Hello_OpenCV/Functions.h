#include <iostream>
#include <opencv2/opencv.hpp>

using namespace std;
using namespace cv;


// shows the image
/*
img	the image to be displayed
win	the window name
dur	wait number of ms or until key is pressed
*/
void showImage(Mat& img, string win, double dur = 0){
  
    // use copy for normalization
    Mat tempDisplay;
    if (img.channels() == 1)
		normalize(img, tempDisplay, 0, 255, CV_MINMAX);
    else
		tempDisplay = img.clone();
    
    tempDisplay.convertTo(tempDisplay, CV_8UC1);
    
    // create window and display omage
    namedWindow( win.c_str(), 0 );
    imshow( win.c_str(), tempDisplay );
    // wait
    if (dur>=0) cvWaitKey(dur);
    // be tidy
    destroyWindow(win.c_str());   
}


/*
  computes directional gradients
  image:	the input image
  sigma:	standard deviation of the kernel
  return:	the two-channel gradient image
*/
Mat calcDirectionalGrad(Mat& image, double sigma){

  // compute kernel size
  int ksize = max(sigma*3,3.);
  if (ksize % 2 == 0)
      ksize++;
  double mu = ksize/2.0;

  // generate kernels for x- and y-direction
  double val, sum=0;
  Mat kernel(ksize, ksize, CV_32FC1);
  //Mat kernel_y(ksize, ksize, CV_32FC1);
  for(int i=0; i<ksize; i++){
      for(int j=0; j<ksize; j++){
	
	  val  = pow((i+0.5-mu)/sigma,2);
	  val += pow((j+0.5-mu)/sigma,2);
	  val = exp(-0.5*val);
	  sum += val;
	  
	  kernel.at<float>(i, j) = -(j+0.5-mu)*val;
	  
      }
  }
  kernel /= sum;
  
  // use those kernels to compute gradient in x- and y-direction independently
  vector<Mat> grad(2);
  filter2D(image, grad[0], -1, kernel);
  filter2D(image, grad[1], -1, kernel.t());
  
  // combine both real-valued gradient images to a single complex-valued image
  Mat output;
  merge(grad, output);
  
  return output;
  
}



Mat retrieveEdges( Mat& input_image, double threshold)
{
	Mat ret;

	for( int r=0; r<input_image.rows; ++r)
		for( int c=0; c<input_image.cols; ++c)
		{
			
			for( int channel=0; channel<input_image.channels(); ++channel)
			{
				 

			}

			vector<Mat> channels;
			split( input_image, channels);
			Mat max_mat;
			max(channels[0], channels[1], max_mat); 
		}
		return ret;
}

void findMaxValue( Mat& mat)
{
	int sz[] = {3, 3, 3};
	Mat accumarray(3, sz, CV_8U, Scalar::all(0));
	accumarray.at<uchar>(0, 1, 2) = 20;
	double testMaxval;
	int maxIdx[3];
	minMaxIdx(mat, 0, &testMaxval);
	cout << testMaxval << endl ;
	cout << maxIdx[0] << ", " << maxIdx[1] << ", " << maxIdx[2] << endl;
}