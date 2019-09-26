#include "stdafx.h"
#include <iostream>
#include <ctime>

using namespace std;

inline void warten(unsigned int seconds);

class Ampel
{
	public:
		enum Status { aus, rot, gruen, gelb };
	private:
		Status status;
	public:
		Ampel(Status s = aus) : status(s) {}
		Status getStatus() const {return status;}
		void setStatus(Status s)
		{
			switch(s)
			{
				case aus:		cout << "\tAUS\t";		break;
				case rot:		cout << "\tROT\t";		break;
				case gruen:	cout << "\tGRUEN\t";  break;
				case gelb:	cout << "\tGELB\t";		break;
				default: return;
			}
			status = s;

		}
};



int main()
{
	Ampel A1, A2;
	enum  Zeiten { gruenZeit1 = 5, gelbZeit1 =1 , gruenZeit2 = 6, gelbZeit2 = 2 };

cout <<	"\tSimulation zweier Ampeln!\n\n"
				"\tDieses Programm mit <Strg> + <C> beenden!\n\n"
				"\t1. Ampel\t2. Ampel\n"
				"\t\b\b----------------------------\n";

	while(true)
	{
		A1.setStatus(Ampel::rot);
		A2.setStatus(Ampel::gelb);
		warten(gelbZeit2);

		cout << endl<< "\t\t";
		A2.setStatus(Ampel::gruen);
		warten(gruenZeit2);

		cout << endl<< "\t\t";
		A2.setStatus(Ampel::gelb);
		warten(gelbZeit2);
		
		cout << endl;
		A1.setStatus(Ampel::gelb);
		A2.setStatus(Ampel::rot);
		warten(gelbZeit1);
		
		cout << endl;
		A1.setStatus(Ampel::gruen);
		warten(gruenZeit1);

		cout << endl;
		A1.setStatus(Ampel::gelb);
		warten(gelbZeit1);
		
		cout << endl;
		

	}

return 0;
}

inline void warten(unsigned int seconds)
{
	time_t zeit = time(NULL) + seconds;
	while(time(NULL)<zeit) ;
}

