#include <iostream>

using namespace std;

class Base
{
public: 

	int i;

	virtual void method()
	{
		cout << "OLD" << endl;
	}

	virtual Base* copy()
	{
		return new Base();
	}

	virtual void method2( int param)
	{
		cout << "OLD METHOD 2 with param '" << param  << "'" << endl;
	}

	void method3( float param)
	{
		cout << "OLD METHOD 3 with param '" << param << "'" << endl;
	}
};

class Child : public Base
{
public:

	virtual void method()
	{
		cout << "child! NEW!!!" << endl;
	}

	virtual Base* copy()
	{
		Child* ret = new Child();
		ret->i = this->i;
		return ret;
	}

	virtual void method2( int param)
	{
		Base::method2(param);

		cout << "NEW CHILD!!! METHOD 2 with param '" << param  << "'" << endl;
	}

	void method3( float param)
	{
		cout << "NEW CHILD!!! METHOD 3 with param '" << param << "'" << endl;
	}
};