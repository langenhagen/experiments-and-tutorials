#include <iostream>
#include <string>
#include <queue>
using namespace std;

class Paket
{
private:
	unsigned int prio;
	string info;
public:
	Paket( unsigned int p, const string &s)
		: prio(p), info(s)
	{}

	friend bool operator<( const Paket& x1, const Paket& x2)
	{ return x1.prio < x2.prio; }

	friend ostream& operator<<( ostream& os, const Paket& x)
	{ os << x.prio << "   " << x.info << endl; return os;}
};

void main()
{
	priority_queue<Paket> pq;

	pq.push( Paket( 7, "Uwe"));
	pq.push( Paket( 1, "Peter"));
	pq.push( Paket( 4, "Susann"));

	while( !pq.empty())
	{
		cout << pq.top();
		pq.pop();
	}
}