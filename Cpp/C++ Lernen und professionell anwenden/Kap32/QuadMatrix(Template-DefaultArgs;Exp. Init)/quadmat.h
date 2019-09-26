/////////////////////////////////////////////////////////////////////////////
// Header-Datei quadmat.h f�r das Klassen-Template QuadMatrix<T,anz>
// zur Darstellung von quadratischen Matrizen
/////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include <stdexcept>
using namespace std;

//####################################################################################### QuadMatrix<T,anz>
// Klassen-Template QuadMatrix<T,anz>
template <class T = int, int anz = 10>
class QuadMatrix
{
private:
	T mat[anz][anz];
public:
	int dim() const { return anz; }														// Gibt eine Dimensionsl�nge wieder

	T* operator[](int line) throw( out_of_range)							// []-Operator
	{																													// Exceptions ausl�sen bei Line-Index oor
		if( line < 0 || line >=anz)
			throw out_of_range("QuadMatrix: Line-Index out of range\n");
		else
			return mat[line];
	}

	const T* operator[](int line) const												// const []-Operator �berladung
		throw( out_of_range) 																		// Exceptions ausl�sen bei Line-Index oor
	{																													
		if( line < 0 || line >=anz)
			throw out_of_range("QuadMatrix: Line-Index out of range\n");
		else
			return mat[line];
	}

	//**********************************************************
	// Friend-Funktionen

	friend QuadMatrix& operator+(const QuadMatrix&);

};