#define BOOST_FILESYSTEM_NO_DEPRECATED

#include <algorithm>
#include <conio.h>
#include <iostream>
#include <sstream>
#include <boost/filesystem.hpp>

using namespace std;
using namespace boost::filesystem;

/** Performs a callback for every directory entry in a given path including its subdirecories.
 * Also performs exception handling. If a filesystem_exception occurs, it will be caught and 
 * the iteration will be aborted.
 * @param p The root directory path in which to start the iteration.
 * @param callback The callback to perform on every directory entry.
 *        It takes a boost::filesystem::directory_entry as an argument.
 * @param exception_callback The function that is called when an exception occurs.
 *        Takes a boost::filesystem::filesystem_error as an argument.
 * @param max_level The maximum level to iterate into the directory tree starting at 0.
 *        If max_level is set to -1, the function iterates into every file-tree depth.
 * @return Returns TRUE in case of complete success.
 *         Returns FALSE in case of exception.
 */
bool for_each_file_in_direcory_tree( path& p,
                                     std::function<void( boost::filesystem::directory_entry&)> callback,
                                     std::function<void( boost::filesystem::filesystem_error&)> exception_callback,
                                     int max_level = -1) {
    bool ret(true);
    try {
        for( recursive_directory_iterator it(p); it != recursive_directory_iterator(); ++it)
            if( max_level == -1 || it.level() <= max_level )
                callback(*it);
    } catch (filesystem_error& e) {
        exception_callback(e);
        ret = false;
    }
    return ret;
}

void hardlink_test( std::string& s) {

    string root_dir = "hallo";

    // create output directoriy
    if( !boost::filesystem::exists( root_dir) &&
        !boost::filesystem::create_directories( path(root_dir))) {
        cerr << "ERREUR#1";
    }

    path p(s);
    p.make_preferred();
    stringstream ss;
    ss << root_dir << '/' << p.stem().string() << p.extension().string();
    cout << ss.str() << "\n";
    path newpath( ss.str());
    newpath.make_preferred();

    try {
        if( !boost::filesystem::exists( newpath))
            // effectively a copy
            boost::filesystem::create_hard_link(p, newpath);
    } catch( std::exception& e) {
        cerr << e.what();
    }
}

void symlink_test( std::string& s) {

    string root_dir = "hallo";

    // create output directoriy
    if( !boost::filesystem::exists( root_dir) &&
        !boost::filesystem::create_directories( path(root_dir))) {
        cerr << "ERREUR#1";
    }

    path p(s);
    p.make_preferred();
    stringstream ss;
    ss << root_dir << '/' << p.filename().string();
    cout << ss.str() << "\n";
    path newpath( ss.str());
    newpath.make_preferred();

    try {
        if( !boost::filesystem::exists( newpath))
            // on windows7: works only with elevated rights!
            boost::filesystem::create_symlink( p, newpath);
    } catch( std::exception& e) {
        cerr << e.what();
    }

}



int main(int argc, char* argv[]) {

    string s = "D:/Grafik";
    


    //hardlink_test(s);

    
    for_each_file_in_direcory_tree( path(s),
                                    []( directory_entry& e){ cout << path(e).filename().string() << "\n"; },
                                    []( filesystem_error& e){ cout << "ERROR " << e.what() << "\n"; });
                                    
    getch();
    return 0;
}

/*
if (exists(p)) {   
    if (is_regular_file(p))        // is p a regular file?   
        cout << p << " size is " << file_size(p) << '\n';
    else if (is_directory(p)) {    // is p a directory?
        cout << p << " is a directory containing:\n";
        copy( directory_iterator(p), 
                directory_iterator(),
                ostream_iterator<directory_entry>(cout, "\n")); // is directory_entry, which is
                                                                // converted to a path by the
                                                                // path stream inserter
    } else
        cout << p << " exists, but is neither a regular file nor a directory\n";
} else
    cout << p << " does not exist\n";
*/