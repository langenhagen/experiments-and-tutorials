
#include <conio.h>
#include <fstream>
#include <iostream>
#include <string>


using namespace std;

void main() {


    string fname = "file.txt";


    //ifstream ifile( fname);
    //if( ifile.is_open()) {

    //    string line;

    //    while( getline(ifile, line)) {
    //        cout << line << "\n";
    //    }


    //} else {
    //    cerr << "unable to open in-file!";
    //}
    //ifile.close();


    // erases the contents of the file
    //ofstream file( fname, ios::out | ios::trunc);
    //file.close();

    ofstream file( fname, ios::out | ios::app);
    if( file.is_open()) {
        while(1) {
            string line;
            getline(cin,line);
            file << line << "\n";
            file.flush();
        }
    } else {
        cerr << "unable to open out-file!";
    }

    file.close();

    _getch();
}