#include <iostream>

using namespace std;




class A
{
protected:
	static int VALUE;

public:

	int getValueA()
	{
		return A::VALUE;
	}

	void setValueA( int i)
	{
		A::VALUE = i;
	}

};


int A::VALUE;	// make VALUE visible in the file (used for VS Linker)



class B : A
{
public:
	int getValue()
	{
		return B::VALUE;
	}

	void setValue( int i)
	{
		B::VALUE = i;
	}
};

void main()
{
	A a;
	B b;


	cout << "A: " << a.getValueA() << endl;
	cout << "Setting Value B::VALUE to 454..." << endl;
	b.setValue(454);

	cout << "A: " << a.getValueA() << endl;
	cout << "B: " << b.getValue() << endl;

	cout << "Setting Value A::VALUE to 666..." << endl;
	a.setValueA(666);

	cout << "A: " << a.getValueA() << endl;
	cout << "B: " << b.getValue() << endl;

	cin.get();
}