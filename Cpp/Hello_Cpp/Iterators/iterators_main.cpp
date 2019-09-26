
#define HELLO

#include <barn_common.hpp>
//#include <iostream>
#include <conio.h>

#include "Iterator.hpp"
#include "bidirectional_iterator.hpp"

//
//
//int main() {
//
//	// works!
//	int list[] = { 1, 3, 5, 2, 4, 3, 5, 10, 10 };
//	my_array<int> a(list, list + sizeof(list) / sizeof(int));
//
//	// works!
//	for (my_array<int>::const_iterator it = a.begin(), end = a.end();
//	it != end; ++it)
//		std::cout << ' ' << *it;
//	std::cout << std::endl;
//
//	// works!
//	std::for_each(a.begin(), a.end(), print<int>);
//	std::cout << std::endl;
//
//	// works!
//	my_array<int> b(a.size());
//	std::copy(a.begin(), a.end(), b.begin());
//
//	// works!
//	my_array<int>::iterator end = std::remove(a.begin(), a.end(), 5);
//	std::for_each(a.begin(), end, print<int>);
//	std::cout << std::endl;
//
//	// works!
//	std::random_shuffle(a.begin(), end);
//	std::for_each(a.begin(), end, print<int>);
//	std::cout << std::endl;
//
//	// works!
//	std::cout << "Counts of 3 in array = " << std::count(a.begin(), end, 3) << std::endl << std::endl;
//
//	// works!
//	std::sort(a.begin(), end);
//	std::for_each(a.begin(), end, print<int>);
//	std::cout << std::endl;
//
//	// works!
//	if (!std::binary_search(a.begin(), a.end(), 5))
//		std::cout << "Removed!" << std::endl;
//
//	_getch();
//	return 0;
//}

#include <iterator>

#pragma warning(disable:4996)

void main() {
	using moa_t = my_own_array<int>;

	moa_t arr = my_own_array<int>(5);
	int cntr = 100;
	for (auto& e : arr)
		e = ++cntr;

	for (moa_t::const_iterator it = arr.begin(); it != arr.end(); ++it)
		$ *it;

	// Microsoft specific issue: unchecked iterator: causes Warning C4996 which is treated as an ERROR
	// Can be solved by 
	// Properties => Configuration Properties => C/C++ => Advanced => Disable Specific Warnings and enter 4996
	std::transform(arr.begin(), arr.end(), arr.begin(), [](auto i) { return i *= 2; });

	

	$ "\ntransformed:";
	for (auto& e : arr)
		$ e;

	_getch();
}