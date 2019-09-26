#include <opencv2/opencv.hpp>

#include <stdio.h>
#include "macros.h"


#include "permutohedral.h"

#include <conio.h>

void xmain(int argc, char **argv) {
    printf("TEST");
    getch();
}


int main(int argc, char **argv) {
    
    // printf("        For instance, try ./bilateral input.png output.png 4 0.5\n");

    cv::Mat3f img = cv::imread("in.png", CV_LOAD_IMAGE_COLOR);
    

    float sp_std_dev;
    float cl_std_dev;
    std::cout << "Spatial std dev?: ";
    std::cin >> sp_std_dev;
    std::cout << "Color std dev?: ";
    std::cin >> cl_std_dev;

    const float invSpatialStdev = 1.0f/sp_std_dev;
    const float invColorStdev   = 1.0f/cl_std_dev;
    
    printf("Constructing inputs...\n");
    // Construct the position vectors out of x, y, r, g, and b. // TODO b g and r!!
    float *positions = new float[img.cols*img.rows*5];
    float *pPtr = positions;
    for (int y = 0; y < img.rows; y++) {
        for (int x = 0; x < img.cols; x++) {
            *pPtr++ = invSpatialStdev * x;
            *pPtr++ = invSpatialStdev * y;
            *pPtr++ = invColorStdev * img(y,x)[0];
            *pPtr++ = invColorStdev * img(y,x)[1];
            *pPtr++ = invColorStdev * img(y,x)[2];
        }
    }


    // Filter the input with respect to the position vectors.
    //void filter(float *im, float *ref, int pd, int vd, int w, int h, bool accurate) {
    printf("Calling filter... ");
    filter((float*)img.data, positions, 5, 3, img.cols, img.rows, true);
    printf("done.\n");
    
    cv::imshow("out",img);
    cv::waitKey();

    return 0;
}
