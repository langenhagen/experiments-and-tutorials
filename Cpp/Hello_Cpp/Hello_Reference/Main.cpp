#include <iostream>

#include <vector>

using namespace std;


class Clazz
{
	vector<float> v;

public:

	Clazz()
	{
		v.push_back(23);
		v.push_back(47);
		v.push_back(223);
	}

	vector<float>& getVector()
	{
		return v;
	}

	const vector<float>& getVectorSafe()
	{
		return v;
	}

	void printVec()
	{
		for( auto it = v.begin(); it != v.end(); it++)
		{
			cout << *it << endl;
		}
	}
};



void main()
{
	int a = 10;
	int b = 5;

	int& x = a;
	int& y = b;

	cout	<< "a: " << a << endl
			<< "b: " << b << endl << endl
			<< "x: " << x << endl
			<< "y: " << y << endl << endl << endl;

	x = 12;

	cout << " x=12;\n";
	cout	<< "a: " << a << endl
			<< "b: " << b << endl << endl
			<< "x: " << x << endl
			<< "y: " << y << endl << endl << endl;

	y = a;

	cout << " y=a;\n";
	cout	<< "a: " << a << endl
			<< "b: " << b << endl << endl
			<< "x: " << x << endl
			<< "y: " << y << endl << endl;


	a = 23;

	cout << " a=23;\n";
	cout	<< "a: " << a << endl
			<< "b: " << b << endl << endl
			<< "x: " << x << endl
			<< "y: " << y << endl << endl;

	cin.get();

	cout << endl << "And now... getting references of members..." << endl << endl;


	Clazz obj;

	cout << "Initial obj.v:" << endl;
	obj.printVec();

	cout << endl << "obj.getVector()[2] = 88;" << endl;
	obj.getVector()[2] = 88;

	cout << "obj.v now:" << endl;
	obj.printVec();



	cin.get();
}

