/////////////////////////////////////////////////////////////////////////////
// Header Wohnung.h mit der Klasse Wohnung
/////////////////////////////////////////////////////////////////////////////
#ifndef _WOHNUNG_
#define _WOHNUNG_
#include <iostream>
#include <string>
using namespace std;

class Wohnung
{
private:
	int qm;
	short  rooms, kitchen, bath;
	bool  balkon;
	string comment;
public:
	Wohnung(	int Quad_meter = 0, short anzZimmer = 1, short anzKitchen = 1, short anzBath = 1,
						bool Balkon = false, const string& Kommentar = "")
		:	qm(Quad_meter), rooms(anzZimmer), kitchen(anzKitchen), bath(anzBath),
			balkon(Balkon), comment(Kommentar)	{}

	int getQM() const { return qm; }
	void setQM( int QM) { qm = QM; }
	short getRooms() const { return rooms; }
	void setRooms( short r) { rooms = r; }
	short getKitchen() const { return kitchen; }
	void setKitchen( short k) { kitchen = k; }
	short getBath() const { return bath; }
	void setBath( short b) { bath = b; }
	bool getBalkon() const { return balkon; }
	void setBalkon( bool b) { balkon = b; }
	const string& getComment() const { return comment; }
	void setComment( const string& c) { comment = c; }

	void display() const;

};

void Wohnung::display() const
{
	cout	<< "\n----------------------------------------------------\n"
				<< rooms << "-Zimmerwohnung (" << qm << " Quadratmeter)\n";
	if(balkon)
		cout << "mit ";
	else
		cout << "ohne ";
	cout	<< "Balkon\nKueche: " << kitchen
				<< "\nBad: " << bath << endl << endl << comment
				<< "\n----------------------------------------------------\n";
}			

#endif