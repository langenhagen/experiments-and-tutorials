#include <iostream>
#include <boost/array.hpp>
#include <boost/numeric/ublas/vector.hpp>
#include <boost/numeric/ublas/io.hpp>
#include <vector>


using namespace std;

#define CINRETURN cin.get(); return


class Conti;
string itos(int i);

typedef boost::numeric::ublas::vector<Conti*> contiVec;

class Conti
{
public:

	int i;
	double d;

	void print()
	{
		cout << "i= " << i << "\t" << "d= " << d << endl;
	}
};




void main()
{
	system("TITLE Andis Spaß");

	boost::numeric::ublas::zero_vector<double> arch (3);
    std::cout << arch << std::endl;


	Conti* a = new Conti();
	Conti* b = new Conti();
	Conti* c = new Conti();

	a->i=0;
	a->d=0.5;
	b->i=1;
	b->d=1.5;
	c->i=2;
	c->d=2.5;

	/*	
	contiVec v = contiVec(3);
	v(0) = a;
	v(1) = b;
	v(2) = c;

	v(0)->print();
	v(1)->print();
	v(2)->print();
	*/
	
	vector<Conti*> v = vector<Conti*>();


	v.push_back(a);
	v.push_back(b);
	v.push_back(c);

	v[0]->print();
	v[1]->print();
	v[2]->print();

	
	int i = 0;
	while(true)
	{
		cin.get();
		string s = "COLOR ";
		s = s + itos(i++);
		system( s.c_str());
		cout << s;
	}

	CINRETURN;
	
}

string itos(int i)
{
	stringstream ss;
	ss << i;
	return ss.str();
}