#include <iostream>
#include <iomanip>

#include <boost/numeric/ublas/vector.hpp>
#include <boost/numeric/ublas/io.hpp>

using namespace std;
using namespace boost;
using namespace boost::numeric::ublas;


class Goebbels
{
public:
	int hallo;
	double grind;
};


void main()
{
	Goebbels g = Goebbels();
	Goebbels h = Goebbels();
	Goebbels i = Goebbels();

	g.hallo = 42;
	g.grind = 12.13;
	
	cout << &g << endl << g.hallo << endl << g.grind << endl;

	boost::numeric::ublas::vector<Goebbels> vec = boost::numeric::ublas::vector<Goebbels>(3);
	vec(0)=g;
	vec(1)=h;
	vec(2)=i;

	cout << vec(0).grind << endl;

	
	cin.get();
}

