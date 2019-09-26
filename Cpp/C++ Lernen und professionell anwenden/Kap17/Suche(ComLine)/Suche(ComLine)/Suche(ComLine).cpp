#include <iostream>
using namespace std;

#define MAXL 200

char* str_str(const char *s1, const char *s2);

void main(int argc, char *argv[])
{
	if(argc == 1)
		cerr << "Ein Suchmuster angeben!\n";
	else
	{
		char zeile[500];
		int zN = 0;

		while(cin.getline(zeile,MAXL))
		{
			zN++;
			if(str_str(zeile,argv[1]) != NULL)
			{
				cout.width(3);
				cout << zN << ": " << zeile << endl;
			}
		}
	}
}

char* str_str(const char *s1, const char *s2)
{
	int len = strlen(s2);
	for(; *s1!='\0'; s1++)
		if(strncmp(s1,s2,len) == 0)
			return (char *)s1;
	return NULL;
}