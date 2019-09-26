#include <iostream>

using namespace std;

enum EnumLustig
{
	LustigA,
	LustigB,
	LustigC,
};

enum EnumTraurig
{
	TraurigA,
	TraurigB,
	TraurigC,
};

// fairly nice
#define coutl std::cout << std::endl

// annoying people
#define return std::cout << char('n') << char('a') << char('p') << char('p') << char('e') << char('l'); return


//the power of ##  usage example: MAP(A,B)
#define MAP(a, b) case Lustig##a: coutl << "Maps " << Lustig##a << " to " << Traurig##b; break;

// yet another cpp macro
#define YACM( keyword) \
	if( std::string("crazy").compare(keyword) == 0) \
	{ \
		coutl << " YACM - crazy - yes"; \
	} \
	else \
	{ \
		coutl << " YACM - crazy - no: " << keyword; \
	}