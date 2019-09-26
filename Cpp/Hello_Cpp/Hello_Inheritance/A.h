#include <iostream>

using namespace std;

class A
{
private:

	int i;

	int* j;

public:

	A()
	{
		cout << "\tConstruct A" << endl;
		j = new int(55);
	}

	A( const A& a)
		: i(a.i),			// look, how you can access the private vars of a
		j(new int(*a.j))
	{}

	~A()
	{
		cout << "\tDestruct A" << endl;
	}


	int foo()
	{
		cout << "\tA::foo()" << endl;
		return 42;
	}

	void setI( int i)
	{
		cout << "\tA::setI(" << i << ")" << endl;
		this->i = i;
	}

	int getI()
	{
		cout << "\tA::getI() = " << i << endl;
		return i;
	}

	void setJ( int* j)
	{
		cout << "\tA::setJ(" << j << ")" << endl;
		this->j = j;
	}

	int* getJ()
	{
		cout << "\tA::getJ() = " << j << endl;
		return j;
	}

protected:

	void bar()
	{
		cout << "\tA::bar()" << endl;
	}

};

class B : public A
{
public:

	B()
	{
		cout << "\tConstruct B" << endl;
	}

	~B()
	{
		cout << "\tDestruct B" << endl;
	}

	B( int i)
	{
		cout << "\tDestruct B with param i: " << i << endl;
	}

	void crap()
	{
		cout << "\tB::crap()" << endl;
		bar();
		cout << "\tEND B::crap()" << endl;
	}

};


class C
{
public:
	
	virtual void foo()
	{
		cout << "C::foo()" << endl;
	}

};

class D : public C
{
public:
	void foo()
	{
		cout << "D::foo()" << endl;
	}
};