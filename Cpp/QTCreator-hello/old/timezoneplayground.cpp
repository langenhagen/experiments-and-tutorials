#define HELLO

#include <cstring>
#include <ctime>
#include <limits>
#include <type_traits>

#include <iomanip>

#include <barn_common.hpp>




using namespace std;

using strstrmap = std::map<std::string, std::string>;
using intv = std::vector<int>;


uint64_t pepe( const string& date_header )
{
    /// date_header ist ein str der das datum beinhaltet

    const auto s_date_time_parsing_resolution_ms = 1000;

    struct tm tm;
    memset( &tm, 0, sizeof( struct tm ) );

    // on mapple: they don't really parse the %Z .....
    strptime( date_header.c_str( ), "%a, %d %b %Y %H:%M:%S %Z", &tm );

    time_t timegm_res = 0;

    auto is_dst_before_conversion = tm.tm_isdst;

    //$ "                                         is_dst_before_conversion: " << is_dst_before_conversion;

    if( 1 /*mapple*/)
    {
        timegm_res = timelocal( &tm ); // DEPRECATED, but works on mapple
    }
    else
    {
        $ "LINUX";

        // this goes for the linux
        timegm_res = timegm( &tm );
    }

    return static_cast< uint64_t >( timegm_res ) * s_date_time_parsing_resolution_ms;
}

    using i64 = int64_t;

int main()
{
    std::cout << "\n"
                 " *** PROGRAM START ***\n\n";

    // --------------------------------------------------------------------------------------------

    $ "WHAT'S CURRENT: ===================================\n";
    time_t t = time(NULL);
    struct tm lt = {0};

    localtime_r(&t, &lt);
    printf("Offset to GMT is %lds.\n", lt.tm_gmtoff);
    printf("The time zone is '%s'.", lt.tm_zone);
    i64 current_time_millis = time( 0 ) * 1000 ;
    $ "the current time in millis: " << current_time_millis;
    $ "===================================================";
    $ "NO DST";
    $e

    const string gmts = "Sat, 20 Feb 2016 12:59:21 GMT";
    const i64 gmta = 1455973161000;
    const i64 gmtr = pepe(gmts);
    const i64 gmtd = gmta - gmtr;
    $ "gmtd " << gmtd << " " << ( gmtd == 0 ? "  match  " : ( gmtd < 0 ? "actual bigger" : "expected bigger" ) );
    const string nils = "Sat, 20 Feb 2016 12:59:21";                // aka 11:59 gmt
    const i64 nila = 1455969561000;
    const i64 nilr = pepe(nils);
    const i64 nild = nila - nilr;
    $ "nild " << nild << " " << ( nild == 0 ? "  match  " : ( nild < 0 ? "actual bigger" : "expected bigger" ) );

    $ "===================================================";
    $ "WIDTH DST";
    $e
    const string gmtss = "Sat, 20 Jul 2016 12:59:21 GMT";
    const i64 gmtas = 1469019561000;
    const i64 gmtrs = pepe(gmtss);
    const i64 gmtds = gmtas - gmtrs;
    $ "gmtds " << gmtds << " " << ( gmtds == 0 ? "  match  " : ( gmtds < 0 ? "actual bigger" : "expected bigger" ) );
    const string nilss = "Sat, 20 Jul 2016 12:59:21";               // aka 10:59 gmt
    const i64 nilas = 1469012361000;
    const i64 nilrs = pepe(nilss);
    const i64 nilds = nilas - nilrs;
    $ "nilds " << nilds << " " << ( nilds == 0 ? "  match  " : ( nilds < 0 ? "actual bigger" : "expected bigger" ) );


    // --------------------------------------------------------------------------------------------

    std::cout << std::endl <<
                 "\n*** PROGRAM END ***\n" << std::endl;
    return 0;
}
