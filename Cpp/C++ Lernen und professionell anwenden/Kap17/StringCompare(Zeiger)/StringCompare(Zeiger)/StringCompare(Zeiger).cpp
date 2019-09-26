#include <iostream>

using namespace std;

int str_cmp(const char* str1, const char* str2);

void main()
{
	char line1[80],line2[80];
	int i, len=sizeof(line1) / sizeof(char);
	

	int v[10] = {1,2,99,4,5,6,7,8,9,10 };

	cout << "Aalso, jib ma nen String ein, Joudek!\n";
	cin.get(line1,len);
	cin.sync();
	
	cout << "\nUND NOCH EEENE DU NAZZI!!\n";
	cin.get(line2,len);
	cin.sync();

	cout << "\n\nStrings gleich?!";
	cin.get();

	if(str_cmp(line1,line2)==0)
		cout << "Jo gleich!\n\n";
	else
		cout << "Neee\n\n";
}

int str_cmp(const char* str1, const char* str2)
{
	int i;
	for(i=0; *(str1+i) == *(str2+i) && *(str1+i) != '\0'; i++) ;
	return (*(str1+i) - *(str2+i));
}
