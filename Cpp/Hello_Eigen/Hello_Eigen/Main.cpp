#include <iostream>
#include <Eigen/Dense>

using namespace Eigen;
using namespace std;

void main()
{
	MatrixXi m = MatrixXi::Random( 3, 3);
	m = (m + MatrixXi::Constant(3,3,1)) * 50;
	cout << "m =" << endl << m << endl;
	VectorXi v(3);
	v << 1, 2, 3;
	cout << "m * v =" << endl << m * v << endl;

	cin.get();
	return;
}