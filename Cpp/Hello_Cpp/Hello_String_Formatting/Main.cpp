#include <list>
#include <vector>
#include <string>
#include <iostream>
#include <iomanip>

#include <sstream>
#include <algorithm>
#include <iterator>

void printList( std::list<std::string> strings, unsigned char min_space = 1, bool expand_min_space = true, unsigned char max_line_size = 80);

std::vector<std::string> &split(const std::string &s, char delim, std::vector<std::string> &elems);
std::vector<std::string> split(const std::string &s, char delim);

void main()
{

	std::list<std::string> l;
	l.push_back( "Hallo");
	l.push_back( "DU!");
	l.push_back( "Das ist");
	l.push_back( "eine ");
	l.push_back( "moeglichst");
	l.push_back( "lange Liste");
	l.push_back( "voll mit");
	l.push_back( "groossartigem");
	l.push_back( "Bullshit.");
	l.push_back( "Nur die Anordnung");
	l.push_back( "ist interessant");
	l.push_back( "fuer");
	l.push_back( "mich");
	l.push_back( "zumindest");
	l.push_back( "...yay!");

	printList(l);

	std::cin.get();

	printList(l, 1,false);

	std::cin.get();

	std::cout << "\nnext part: splitting strings...\n\n";

	std::string str("Koepft ihn!, den Nikl! den man koepfen muss!!!");

	std::cout << "Splitting: " << str << std::endl;

	std::vector<std::string>& str_vec = split( str, '!');
	

	for( auto it = str_vec.begin(); it != str_vec.end(); it++)
	{
		std:: cout << *it << std::endl;
	}

	std::cin.get();

}

/**
 * Prints a list of strings onto the console.
 * @param strings The list of strings to print onto the console.
 * @param min_space The minimum number of space characters between two elements of the list.
 * @param expand_min_space Specifies, if the minimum number of spaces between 
 * two printed list elements shall be expanded as much as possible without beginning a new line.
 * @param max_line_size The maximum length of a line in the console. In standard cases it is 80
 * but you can redefine it. A printed line of this method will not exceed the given value.
 * TRUE - expand as much as possible
 * FALSE - use min_space as absolute minimum spaces
 */
void printList( std::list<std::string> strings, unsigned char min_space, bool expand_min_space, unsigned char max_line_size)
{
	// find the minimum size of a block
	unsigned char block_size(1);
	for( auto it = strings.begin(); it != strings.end(); it++)
	{
		if( it->size() + min_space > block_size)
		{
			block_size = it->size() + min_space;
		}	
	}

	// expand block_size if necessary
	if( expand_min_space)
	{
		block_size +=(max_line_size % block_size) / (max_line_size/block_size);
	}

	// print strings
	unsigned char line_size(0);
	for( auto it = strings.begin(); it != strings.end(); it++)
	{
		line_size += block_size;

		if( line_size > max_line_size)
		{
			std::cout << std::endl;
			line_size = block_size;
		}
		else if( line_size == max_line_size)
		{
			line_size = block_size;
		}

		std::cout << std::left << std::setw( block_size) << *it;
	}

	std::cout << std::endl;
}


/**
 * Splits a string at the specified delimeters and writes the results into the specified vector.
 * @param s The string to split.
 * @param delim The delimeter to use.
 * @param elems The vector containing all substrings.
 */
std::vector<std::string>& split(const std::string& s, char delim, std::vector<std::string>& elems)
{
	std::stringstream ss(s);
	std::string item;
	while( std::getline(ss, item, delim))
	{
		elems.push_back(item);
	}
	return elems;
}


/**
 * Splits a string at the specified delimeters.
 * @param s The string to split.
 * @param delim The delimeter to use.
 * @return A vector containing all substrings.
 */
std::vector<std::string> split(const std::string &s, char delim)
{
	std::vector<std::string> elems;
	return split(s, delim, elems);
}