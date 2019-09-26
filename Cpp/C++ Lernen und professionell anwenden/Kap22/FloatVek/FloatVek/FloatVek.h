/////////////////////////////////////////////////////////////////////////////
// Header-Datei FloatVek.h mit der Klasse FloatVek
/////////////////////////////////////////////////////////////////////////////
#ifndef _FLOATVEK_
#define _FLOATVEK_
#include <iostream>
#include <iomanip>
using namespace std;

class FloatVek
{
private:
	float* vekPtr;
	int max, anz;

	void expand(int neueGroesse);
public:
	FloatVek(int n = 256);																							// Konstruktoren
	FloatVek(int n, float wert);
	FloatVek(const FloatVek &src);
	~FloatVek();																												// Destruktor

	float&		operator[]( int i);																				// Operatoren
	float			operator[]( int i) const;																	// !
	FloatVek&	operator=( const FloatVek &src);
	FloatVek& operator+=( float wert );
	FloatVek& operator+=( const FloatVek &v);
	
	int				length() const { return anz;}															// Sonstige Methoden
	void			append(float wert);
	void			append(const FloatVek &v);
	bool			insert(float wert, int pos);
	bool			insert(const FloatVek &v, int pos);
	bool			remove(int pos);

	friend ostream& operator<<(ostream& os, const FloatVek &v);		// friend-Funktionen
 };
#endif