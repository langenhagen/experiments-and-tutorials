/////////////////////////////////////////////////////////////////////////////
// Header-File search.h
// Enthält Funktionen und Templates mit diversen Suchalgorithmen
/////////////////////////////////////////////////////////////////////////////

//####################################################################################### interpolSearch()
// Funktions-Template interpolSearch() zur Zahlensuche  
// in numerischen, aufsteigend sortierten Vektoren
template <class T>
int interpolSearch( T key, T* vp, unsigned int vSize)
{
	unsigned int	except,
								begin = 0,
								end		= --vSize;
	double temp;

	// vorherige Fehlerquellen ausschalten
	if( key < *vp || key > vp[end] || vSize==0)
		return -1;

	while( begin < end)
	{
		// die Interpolationssuche ///////////////
		temp = (double)(key - vp[begin]);			
		temp	/= ( vp[end] - vp[begin]);
		temp	= temp * ( end - begin ) + 0.5;
		except = begin + ( unsigned int)temp;
		
		if( except > end)
			return-1;

		if( vp[except] == key)
			return except;
		else if( vp[except] > key)
			end = except-1;
		else if( vp[except] < key)
			begin = except+1;
	}
	return -1;
}