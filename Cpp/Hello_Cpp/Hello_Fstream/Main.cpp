#include <fstream>
#include <iostream>


/**
 * @brief Checks, whether a file exists or not. Doesn't consider capital letters/non-capital letters.
 * @param fname the filename as a c string.
 * @return
 * Returns TRUE, if the file exists,
 * returns FALSE if the file does not exist.
 */
inline bool fileExists( const char* fname)
{
	// try to read from file
	std::fstream file;
	file.open( fname, std::ios::in);
	file.close();

	return !file.fail();
}


void main()
{
	std::cout << "HALLO\n";
	std::string fname;
	
	while(1)
	{
		std::cout << "Type filename: ";
		std::cin >> fname;

		std::cout << std::endl << fname << std::endl;

		std::cout << (fileExists( fname.c_str()) ? "EXISTS!" : "DOESN'T EXIST!");

		std::cin.sync();
		std::cin.get();
	}
}