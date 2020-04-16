#include <iostream>
#include <fstream>

#include "json/value.h"
#include "json/reader.h"
#include "json/writer.h"

using namespace std;

void main()
{
	// gniddel file
	std::ifstream in_stream("test.json");

	Json::Value root;   // will contains the root value after parsing.
	Json::Reader reader;
	bool parsingSuccessful = reader.parse( in_stream, root );
	if ( !parsingSuccessful )
	{
		// report to the user the failure and their locations in the document.
		std::cout  << "Failed to parse configuration\n" << reader.getFormatedErrorMessages();

		cin.sync();
		cin.get();
		return;
	}

	// Get the value of the member of root named 'encoding', return 'UTF-8' if there is no
	// such member.
	std::string encoding = root.get("encoding", "UTF-8" ).asString();
	// Get the value of the member of root named 'encoding', return a 'null' value if
	// there is no such member.
	const Json::Value plugins = root["plug-ins"];
	for ( int index = 0; index < plugins.size(); ++index )
	{
		// Iterate over the sequence elements.
		cout << "+ Plugin: " << plugins[index].asString() << endl;
	}

	cout << "+ Indent: " << root["indent"].get("length", 3).asInt() << endl;
	cout << "+ Use space: " << root["indent"].get("use_space", true).asBool() << endl;

	// ...
	// At application shutdown to make the new configuration document:
	// Since Json::Value has implicit constructor for all value types, it is not
	// necessary to explicitly construct the Json::Value object:

	cout << endl << endl;

	root["encoding"] = "FRUUUUMP";
	root["indent"]["length"] = "zwanzich";
	root["indent"]["use_space"] = "nö";

	Json::StyledWriter writer;
	// Make a new JSON document for the configuration. Preserve original comments.
	std::string outputConfig = writer.write( root );

	// You can also use streams.  This will put the contents of any JSON
	// stream at a particular sub-value, if you'd like.
	//	std::cin >> root["subtree"];

	// doit the old way...
	cout << "\nNow enter sumthing that shall be added to the \"FUKR\" node: ";
	string line;
	cin.sync();
	cin >> line;
	root["FUKR"] = line;

	// And you can write to a stream, using the StyledWriter automatically.
	std::cout << endl << root;

	cout << endl << " *** END OF PROGRAM ***";
	cin.sync();
	cin.get();

}