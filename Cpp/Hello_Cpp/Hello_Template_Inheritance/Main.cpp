#include <iostream>

using namespace std;

inline int log2( int i);

template <class N = int>
class Mom
{
public:
	N momstuff;

public:

	Mom( N n)
	{
		momstuff = n;
	}

	virtual N momfunc() = 0;

};


class Child : public Mom<int>
{
public:

	Child() : Mom(42){}

	int momfunc()
	{
		return 13;
	}
};

template <class N = int>
class Otherchild : public Mom<N>
{
public:
	Otherchild( N n) : Mom<N>(n){}

	N momfunc()
	{
		return momstuff;
	}
};

class Thirdchild : public Mom<int>
{
public:
	Thirdchild( int i) : Mom(i){}

	int momfunc()
	{
		return 13;
	}
};


void main()
{
	Child c = Child();
	Mom<int>* m = &c;
	Otherchild<int> o = Otherchild<int>(56);
	Thirdchild t = Thirdchild(31);

	cout << "Child::momfunc(): " << m->momfunc() << endl << "Otherchild::momfunc(): " << o.momfunc();
	cin.get();

	cout << "Und nun das log2..." << endl;
	int i = 1;
	while(1)
	{
		cout << i << "\t" << log2(i) << endl;
		//i*=2;
		i++;
		cin.get();
	}



	cin.get();
}

// log2 for dummies
inline int log2( int i)
{
	int ret = -1; 
	for (;i > 0; i >>= 1) 
		ret++; 
  
	return ret; 
}






