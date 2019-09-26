#include <time.h>
#include <stdio.h>
#include "..\gSLIC\FastImgSeg.h"
#include <opencv/cv.h>
#include <opencv\highgui.h>

using namespace std;

void mainx()
{
	cvNamedWindow("frame",0);
	CvCapture* capture = 0;

	 capture = cvCaptureFromCAM(0);
	
	 IplImage* frame=cvQueryFrame(capture);
	FastImgSeg* mySeg=new FastImgSeg();
	mySeg->initializeFastSeg(frame->width,frame->height,500);

	unsigned char* imgBuffer=(unsigned char*)malloc(frame->width*frame->height*sizeof(unsigned char)*4);
	memset(imgBuffer,0,frame->width*frame->height*sizeof(unsigned char)*4);

	while (1)
	{
		frame=cvQueryFrame(capture);
		
		// gSLIC currently only support 4-dimensional image 
		for (int i=0;i<frame->height;i++)
		{
			for (int j=0;j<frame->width;j++)
			{
				int bufIdx=(i*frame->width+j)*4;

				imgBuffer[bufIdx]=CV_IMAGE_ELEM(frame,unsigned char,i,j*3);
				imgBuffer[bufIdx+1]=CV_IMAGE_ELEM(frame,unsigned char,i,j*3+1);
				imgBuffer[bufIdx+2]=CV_IMAGE_ELEM(frame,unsigned char,i,j*3+2);
			}
		}

		mySeg->LoadImg(imgBuffer);
		mySeg->DoSegmentation(XYZ_SLIC,0.3);
		mySeg->Tool_GetMarkedImg();

		for (int i=0;i<frame->height;i++)
		{
			for (int j=0;j<frame->width;j++)
			{
				int bufIdx=(i*frame->width+j)*4;

				CV_IMAGE_ELEM(frame,unsigned char,i,j*3)=mySeg->markedImg[bufIdx];
				CV_IMAGE_ELEM(frame,unsigned char,i,j*3+1)=mySeg->markedImg[bufIdx+1];
				CV_IMAGE_ELEM(frame,unsigned char,i,j*3+2)=mySeg->markedImg[bufIdx+2];
			}
		}
		
		cvShowImage("frame",frame);

		if( cvWaitKey(10) == 27 )
			break;
	}

	free(imgBuffer);
	cvReleaseCapture( &capture );
	cvDestroyWindow( "frame" );

}