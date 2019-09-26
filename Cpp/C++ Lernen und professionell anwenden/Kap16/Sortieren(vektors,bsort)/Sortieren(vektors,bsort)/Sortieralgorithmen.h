/////////////////////////////////////////////////////////////////////////////
//Header mit verschiedenen Sortier-Algorithmen
/////////////////////////////////////////////////////////////////////////////

#ifndef _SORTIERALGORITHMEN_
#define _SORTIERALGORITHMEN_

/////////////////////////////////////////////////////////////////////////////
//Sortier-Algorithmus BubbleSort Array das Array, f�r uGrenze und oGrenze
//die entsprechenden Positionen(!) im Array eintragen

///////////////////////////////////////////////////////////////
//BubbleSort in der INT-�berladung
void BubbleSort(int Array[], int uGrenze, int oGrenze)
{
	int t;
	bool changed = false;
 	
	do
	{
		changed = false;
		for(int i = uGrenze; i<oGrenze; i++)
		{
			if(Array[i]>Array[i+1])
			{ 
				t = Array[i+1];
				Array[i+1] = Array[i];
				Array[i] = t;
				changed = true;
			}
		}
	}
	while(changed == true);
}

///////////////////////////////////////////////////////////////
//BubbleSort in der LONG-�berladung
void BubbleSort(long Array[], int uGrenze, int oGrenze)
{
	long t;
	bool changed = false;
 	
	do
	{
		changed = false;
		for(int i = uGrenze; i<oGrenze; i++)
		{
			if(Array[i]>Array[i+1])
			{ 
				t = Array[i+1];
				Array[i+1] = Array[i];
				Array[i] = t;
				changed = true;
			}
		}
	}
	while(changed == true);
}

///////////////////////////////////////////////////////////////
//BubbleSort in der DOUBLE-�berladung
void BubbleSort(double Array[], int uGrenze, int oGrenze)
{
	double t;
	bool changed = false;
 	
	do
	{
		changed = false;
		for(int i = uGrenze; i<oGrenze; i++)
		{
			if(Array[i]>Array[i+1])
			{ 
				t = Array[i+1];
				Array[i+1] = Array[i];
				Array[i] = t;
				changed = true;
			}
		}
	}
	while(changed == true);
}

///////////////////////////////////////////////////////////////
//BubbleSort in der CHAR-�berladung
void BubbleSort(char Array[], int uGrenze, int oGrenze)
{
	
	char t;
	bool changed = false;
 	
	do
	{
		changed = false;
		for(int i = uGrenze; i<oGrenze; i++)
		{
			if(Array[i]>Array[i+1])
			{ 
				t = Array[i+1];
				Array[i+1] = Array[i];
				Array[i] = t;
				changed = true;
			}
		}
	}
	while(changed == true);
}

/////////////////////////////////////////////////////////////////////////////
//Sortier-Algorithmus Selection-Sort, f�r Vector das Array, f�r lenght
//die L�nge des Vektors eintragen
/////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////
//SelectionSort in der INT-�berladung
void SelSort(int Vector[], int length)
{
	int i, temp, *sml, start = 0;
		
	for(start = 0; start<length; start++)
	{
		sml = &Vector[start];
		for(i = start+1; i<length; i++)
			if(*sml > Vector[i])
				sml = &Vector[i];
			temp					= Vector[start];
			Vector[start] =	*sml; 
			*sml					= temp;
	}
}

///////////////////////////////////////////////////////////////
//SelectionSort in der LONG-�berladung
void SelSort(long Vector[], int length)
{
	int i,  start = 0;
	long temp, *sml;
		
	for(start = 0; start<length; start++)
	{
		sml = &Vector[start];
		for(i = start+1; i<length; i++)
			if(*sml > Vector[i])
				sml = &Vector[i];
			temp					= Vector[start];
			Vector[start] =	*sml; 
			*sml					= temp;
	}
}

///////////////////////////////////////////////////////////////
//SelectionSort in der CHAR-�berladung
void SelSort(char Vector[], int length)
{
	int i,  start = 0;
	char temp, *sml;
		
	for(start = 0; start<length; start++)
	{
		sml = &Vector[start];
		for(i = start+1; i<length; i++)
			if(*sml > Vector[i])
				sml = &Vector[i];
			temp					= Vector[start];
			Vector[start] =	*sml; 
			*sml					= temp;
	}
}

///////////////////////////////////////////////////////////////
//SelectionSort in der DOUBLE-�berladung
void SelSort(double Vector[], int length)
{
	int i,  start = 0;
	double temp, *sml;
		
	for(start = 0; start<length; start++)
	{
		sml = &Vector[start];
		for(i = start+1; i<length; i++)
			if(*sml > Vector[i])
				sml = &Vector[i];
			temp					= Vector[start];
			Vector[start] =	*sml; 
			*sml					= temp;
	}
}

#endif
