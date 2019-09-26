#include "A.h"
#include "PrototypeTest.h"

void main()
{
	cout << "B* b = new B();" << endl;
	B* b = new B();
	cout << endl;


	cout << "b->foo():\n";
	b->foo();
	cout << endl;



	cout << "b->crap():\n";
	b->crap();
	cout << endl;

	cout << "delete b;" << endl;
	delete b;
	cout << endl;


	cout << "B c = B();" << endl;
	B c = B();
	c.setI( 99);
	cout << endl;

	cout << "B* d = new B( c);     // (Specialized) copy constructor" << endl;
	B* d = new B( c); // (Specialized) copy constructor
	cout << endl;

	cout << "c.setI(88)" << endl;
	c.setI(88);
	cout << "c.getI()" << endl;
	c.getI();
	cout << "d->getI()" << endl;
	d->getI();
	cout << endl;


	cout << "c.getJ()" << endl;
	c.getJ();
	cout << "d->getJ()" << endl;
	d->getJ();
	cout << endl;

	cout << endl << "====================================================================" << endl;


	Base* x = new Child();

	x->i=69;

	cout << "x->i: " << x->i << endl;

	Base* y = x->copy(); // works

	cout << "y->i: " << y->i << endl;

	x->i = 22;
	cout << "x->i: " << x->i << endl;
	cout << "y->i: " << y->i << endl;

	y->method();

	
	y->method2( 77);
	cout << endl;

	y->method3(1.5f);
	cout << endl;

	cout << endl << "====================================================================" << endl;

	cout << "B* e = new B(42);    // with param! (constructor differs from A's)" << endl;
	B* e = new B(42);
	cout << endl;


	cout << endl << "====================================================================" << endl;


	cout << "C aa;" << endl
		 << "aa.foo(): "; 
	
	C aa;
	aa.foo();

	cout << "D ab;" << endl
		 << "ab.foo(): ";

	D ab;
	ab.foo();

	
    cout << endl << "====================================================================" << endl;

    C* f = new C();
    C* g = new D();

    cout << "C* f = new C();\n"
            "C* g = new D(); // !\n"
            "\n"
            "f->foo(): ";
    f->foo();

    cout << "g->foo(): ";
    g->foo();

    cin.get();
}