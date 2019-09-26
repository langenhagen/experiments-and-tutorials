#ifndef _FOO_
#define _FOO_

template <class T>
class Foo
{
public:
	T publ;

	T bar( T t);

};

#include "Foo_Impl.hpp"

#endif