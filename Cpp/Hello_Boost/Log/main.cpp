
#include <logging.hpp>


#include <conio.h>

using namespace logging;

int main(int argc, char* argv[])
{
    if(std::cout.fail()) { // always remember to put such statements into brackets, bc LOG(...) is more than just one function call
        LOG(error) << "\aALERT"; 
    }

    init_log( "sample.log");
    
    

    LOG(beep_notify) << "A normal severity message, will not pass to the file";
    LOG(info) << "A normal severity message, will not pass to the file";
    LOG(notify) << "A normal severity message, will not pass to the file";
    LOG(warn) << "A warning severity message, will pass to the file";
    LOG(error) << "An error severity message, will pass to the file";

    DLOG( info, "a debug message");


    getch();
    return 0;
}
