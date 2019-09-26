/////////////////////////////////////////////////////////////////////////////
// Header-Datei Supermarkt.h mit der Klasse Product
// und den davon abgeleiteten Klassen PackedFood und Unpacked Food
/////////////////////////////////////////////////////////////////////////////
#include <string>
using namespace std;

///////////////////////////////////////////////////////////////
// Klasse Product
class Product
{
private:
	long code;
	string name;
public:
	Product(  const string  &Bezeichnung = "Default Product", long Barcode = 1111111 )	// Konstruktor
		: code(Barcode), name(Bezeichnung) {}
	virtual ~Product() {}																																// Destruktor

	void setCode( long Barcode ) { code = Barcode; }
	long getCode() const { return code; }
	void setBez( const string &Bez ) { name = Bez; }
	const string& getBez() const { return name; }

	virtual bool scanner();
	virtual void printer() const;
};

///////////////////////////////////////////////////////////////
// Klasse PackedFood
class PackedFood : public Product
{
private:
	double stkpreis;
public:
	PackedFood(	const string  &Bezeichnung = "Default PackedFood", long Barcode = 1111111,
							double Price = 0.0)
		: Product( Bezeichnung, Barcode), stkpreis(Price)
	{}

	void setPreis( double Preis ) { stkpreis = Preis; }
	double getPreis() const { return stkpreis; }

	bool scanner();
	void printer() const;
};

///////////////////////////////////////////////////////////////
// Klasse UnpackedFood
class UnpackedFood : public Product
{
private:
	double klopreis;
	double weight;
public:
	UnpackedFood(	const string  &Bezeichnung = "Default UnackedFood", long Barcode = 1111111,
								double Kilopreis = 0.0, double Weight = 0.0)
		: Product(Bezeichnung,Barcode), klopreis(Kilopreis), weight(Weight)
	{}

	void setKiloPreis( double Preis ) { klopreis = Preis; }
	double getKiloPreis() const { return klopreis; }
	void setWeight( double Weight ) { weight = Weight; }
	double getWeight() const { return weight; }

	bool scanner();
	void printer() const;
};