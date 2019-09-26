//
// *** not only header only *** link it ***
//

#include <Tweak.hpp>


#include <algorithm>
#include <cstdlib> // atof
#include <stdarg.h> // va_list, ...
#include <conio.h>
#include <fstream>
#include <iostream>
#include <vector>
#include <boost/program_options.hpp>


using namespace std;


struct po_values {

    int testvalint1, 
        testvalint2,
        testvalint3;

    float testvalfloat;

    string config_file,
           config_file2;
};


/** Prints a help message consisting of all given opptions_description pointers.
 * @param args The number of given options_description pointers.
 * @param ... an arbitrary number of options_description pointers.
 */
void print_help_message( int args, ... ) {

    using namespace boost::program_options; // options_description

    va_list vl;
    va_start(vl,args);

    for( int i=0; i< args; ++i) {

        options_description* description = va_arg(vl,options_description*);
        std::cout << *description << "\n";
    }

    va_end(vl);
}

int init_program_options( int argc, char* argv[], po_values& values) {

    using namespace boost::program_options;

    
    // declare the options ********************************

    // Declare group of options that will be allowed only on command line.
    options_description cmd_desc("Generic options");
    cmd_desc.add_options()
        ( "help", "produce help message")
        ( "config,c", value<string>(&values.config_file)->default_value("config.cfg"), "name of the config file")
        ;

    // option group description on config file 1
    options_description config_desc("Configuration 1");
    config_desc.add_options()
        ("testvalint1",  value<int>(&values.testvalint1)->default_value(101), "Test val int 1")
        ("testvalint2",  value<int>(&values.testvalint2),    "Test val int 2")
        ("testvalfloat", value<float>(&values.testvalfloat), "Test val float")
        ("configfile2",  value<string>(&values.config_file2), "Config file 2")
        ;

    // option group description on config file 2
    options_description config2_desc("Configuration 2");
    config2_desc.add_options()
        ("testvalint3",  value<int>(&values.testvalint3), "Test val int 3")
        ;



    try {
        // *** parse the command line and the config files ***
        
        variables_map vm;
        Tweak& tweak = Tweak::instance_ref();

        // parse command line *****************************
        store(parse_command_line(argc, argv, cmd_desc), vm);
        notify(vm);

        
        if( vm.count("help")) {
            // *** special 'help' case ***

            print_help_message( 3, &cmd_desc, &config_desc, &config2_desc);
            return 0;
        }

        // TODO MAKE A LOOP OUT OF IT

        // parse config file 1 ****************************
        ifstream in_file(values.config_file.c_str());
        if (!in_file) {
            cout << "can not open config file: " << values.config_file << "\n";
            getch();
            return 0;

        } else {
            // file access works
            
            parsed_options parsed_config (parse_config_file(in_file, config_desc, true));
            store( parsed_config, vm);
            notify(vm);

            // find unknown options and add them to tweak
            for( auto it = parsed_config.options.begin(); it != parsed_config.options.end(); ++it) {
               if (vm.find(it->string_key) == vm.end()) {
                    
                   tweak.map[it->string_key] = it->value[0];
                }
            }
        }
        in_file.close();


        // parse config file 2 ****************************
        in_file = ifstream(values.config_file2.c_str());
        if (!in_file) {
            cout << "can not open config file 2: " << values.config_file2 << "\n";
            return 0;

        } else {
            // file access works

            parsed_options parsed_config (parse_config_file(in_file, config2_desc, true));
            store( parsed_config, vm);
            notify(vm);

            // find unknown options and add them to tweak
            for( auto it = parsed_config.options.begin(); it != parsed_config.options.end(); ++it) {
               if (vm.find(it->string_key) == vm.end()) {

                   tweak.map[it->string_key] = it->value[0];
                }
            }
        }
        in_file.close();

    } catch( std::exception& e) {
        cout << "Exception at " << __FILE__ << "::" << __LINE__ << ": " << e.what() << "\n";
    }

    return 1;
}


int main(int argc, char* argv[]) {

    // get tweak singleton
    Tweak& tweak = Tweak::instance_ref();
    

    string config_file;

    po_values pov;

    if( !init_program_options( argc, argv, pov)) {
        getch();
        return 1;
    }

    
    cout << "config_file=   " << pov.config_file  << "\n"
            "config_file 2= " << pov.config_file2 << "\n"
            "testvalint1=   " << pov.testvalint1  << "\n"
            "testvalint2=   " << pov.testvalint2  << "\n"
            "testvalint3=   " << pov.testvalint3  << "\n"
            "testvalfloat=  " << pov.testvalfloat << "\n"
            "unknownFloat=  " << tweak["unknownFloat"] << "\n";

    getch();
    return 0;
}