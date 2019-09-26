#include <stdarg.h>
#include <iostream>

unsigned int min( unsigned int i=0, ...)
{	va_list argp;
	va_start(argp,i);

	unsigned int mn, cur;
	mn = i;

	while( ( cur = va_arg(argp, unsigned int)) != 0 )
	{	if( mn > cur)
			mn = cur;
	}

	va_end(argp);

	return mn;
}

void main()
{	std::cout << min(4,3,5,2,0);
}