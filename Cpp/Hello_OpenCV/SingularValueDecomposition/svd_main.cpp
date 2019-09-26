#define HELLO

#include <barn_common.hpp>
#include <opencv2/opencv.hpp>

using namespace std;
using namespace cv;

void main() {


    int rows, cols;
    cout << " Enter matrix dimensions [rows cols]: ";
    cin >> rows >> cols;

    //create matrix
    Mat1d vals(rows,cols);
    for( int r=0; r<rows; ++r)
        for( int c=0; c<cols; ++c) {
            float i;
            vals(r,c) = modf(rand()/1000.0, &i);
        }
    /**/
    /*
    rows = 3; cols = 3;
    Mat1d vals(rows, cols);
    vals << 1.0, 2.0, 3.0,
            4.0, 5.0, 6.0,
            7.0, 8.0, 9.0;
    /**/

    // calc covar matrices
    vector<Mat1d> vars(cols), means(cols);
    for( int c=0; c<cols;++c) {

        cv::calcCovarMatrix(vals.col(c), vars[c], means[c], CV_COVAR_NORMAL | CV_COVAR_ROWS);
    }

    cout << "vars:\n";
    for(int c=0; c<cols; ++c) {
        cout << vars[c] << "\n";
    }
    cout << "means:\n";
    for(int c=0; c<cols; ++c) {
        cout << means[c] << "\n";
    }
    

    Mat1d vars2, means2;
    cv::calcCovarMatrix(vals, vars2, means2, CV_COVAR_NORMAL | CV_COVAR_ROWS);

    cout << "vars2:\n" <<
            vars2 << "\n";

    cout << "means2:\n" <<
            means2 << "\n";


    getch();
}