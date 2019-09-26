#pragma once

#include <iostream>

using namespace std;

class Foo
{

public:

	const int bar;

	Foo(int i) : bar(i)
	{
		cout << "Konstante Foo::bar= " << bar << endl;
		cin.get();
	}

	~Foo(void)
	{
	}

};

