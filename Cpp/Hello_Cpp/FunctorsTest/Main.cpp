#include <iostream>

using namespace std;

struct Functor 
{ 
   int operator()(const int a, const int b, const int c) 
   { 
     return a+b+c;
   } 

   int operator()(const int a, const int b) 
   {
     return a*b;
   } 
};


template <class F>
void foo( F functorFunc)
{

	cout << "H " << functorFunc(4,2) << endl;
	cout << "I " << functorFunc(4,2, 1);
}

void main()
{
	Functor f;

	foo( f);

	cin.get();
}