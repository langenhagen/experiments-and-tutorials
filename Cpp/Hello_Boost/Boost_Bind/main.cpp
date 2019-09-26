#include <iostream>
#include "boost/function.hpp" 
#include <boost/bind.hpp>

// callback function
#define FUNC_SIGNATURE void(void)
typedef boost::function<FUNC_SIGNATURE> callback;

using namespace std;
using namespace boost;


// !!! since cpp11 rather use standard header <functional> !!!

namespace TestNamespace
{

	// classic function
	void function(int number, float floatation, std::string string) 
	{
		cout << "Classic function\n";
		cout << "Int: " << number << "\nFloat: " << floatation 
			<< "\nString: " << string << "" << std::endl; 
	}
 
	// class with member function
	class TestClass
	{
	public:
		int i;
		float f;
		string s;

	public:

		TestClass(int number, float floatation, std::string string)
		{
			i = number;
			f = floatation;
			s = string;
		}

		void method()
		{
			cout << "Member function\n";
			cout << "Int: " << i << "\nFloat: " << f 
				<< "\nString: " << s << "" << std::endl;
		}
	};

} // namespace



// main function
void main()
{
	cout << "HALLO" << endl << endl ;

	boost::function<FUNC_SIGNATURE> f = boost::bind( &TestNamespace::function, 42, 2.3, "Hello Boost Bind!");

	f();


	cout << endl << endl << endl;


	TestNamespace::TestClass* obj  = new TestNamespace::TestClass( 1, 99.8, "Hello Boost member Bind!");
	boost::function<FUNC_SIGNATURE> g = boost::bind( &TestNamespace::TestClass::method, obj);

	g();

	cin.get();
}

