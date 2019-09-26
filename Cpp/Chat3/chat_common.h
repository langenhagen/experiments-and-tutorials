/**
 * @file File contains common elements of the TCPReader and TCPWriter classes.
 * @author barn
 * @version 20131105
 */
#pragma once

#include <sstream>

#include <boost/bind.hpp>
#include <boost/asio.hpp>

#include <rlutil/rlutil.h>

using boost::asio::ip::tcp;
namespace asio = boost::asio;


/// Convenience macro that help saving LoC when logging.
/// Just handle some string as parameter, concatenate with stream out operator << .
#define CHAT_PRINT_INFO(MSG) std::ostringstream oss; \
                             oss << MSG; \
                             printInfo( oss.str(), 1, rlutil::trows());


/// The maximum message length.
const int max_message_length(1024);


/**
 * Prints an info message at the lower end of the console window.
 * @param sring The string to be printed.
 * @col The 1 based number of the column at which to print.
 * @row The 1 based number of the row at which to print.
 */
void printInfo( std::string& string, unsigned int col, unsigned int row)
{
    string.append( std::string(rlutil::tcols()-string.length(), ' '));
    rlutil::locate( col, row);
    printf( string.c_str());
}


/**
 * Captures and prints the at the specified position in the console input
 * until ENTER is pressed or the maximum string buffer size is reached.
 * DEL deletes the last character.
 * param col The column on which to position the cursor when writing the strnig.
 * param row The row on which to position he cursor when writing the string.
 * @return The string that is captured until ENTER is pressed
 * or the maximum string buffer size is reached.
 */
std::string captureAndPrintLine(unsigned int col, unsigned int row)
{
    char ret[max_message_length]("\0");
    unsigned int cursor(0);

    rlutil::locate(col,row);

    // non-blocking getch
    int kbval ( rlutil::nb_getch());

    while( 13!=kbval )
    {
        // until ENTER is pressed

        if(0==kbval)
        {
            // NOTHING
        }
        else if( 8==kbval && cursor > 0)
        {
            // DEL
            ret[--cursor] = '\0';

            rlutil::locate(col,row);

            printf(ret);
            printf(" ");
            rlutil::locate(col+cursor,row);
        }
        else if( 27== kbval)
        {
            // ESC
            rlutil::cls();
            exit(0); // TODO u can do that better
        }
        else if(cursor < max_message_length)
        {
            // STANDARD CHARACTERS

            rlutil::locate(col+cursor,row);
            ret[cursor] = unsigned char(kbval);

            printf( &ret[cursor++]);
        }

        kbval = rlutil::nb_getch();
    }

    rlutil::locate( col, row);
    printf( std::string(cursor, ' ').c_str());

    return std::string(ret);
}


/**
 * Prints an error message with the given error and a string that specifies a user given information.
 * @param error_code The original error code.
 * @param error_in An additional information string
 * that precedes the other information given by the error code.
 * @param exit_code The exit code that shall be thrown by the application.
 */
void printErrorMessage( const boost::system::error_code& error_code, const std::string& error_in, int exit_code)
{
    rlutil::cls();
    rlutil::locate( 1, rlutil::trows()/2);
    std::cerr << "ERROR: " << error_in << ":\n"
                    "   Error No:  " << error_code.value() << "\n"
                    "   Error Msg: " << error_code.message() << "\n";
}
