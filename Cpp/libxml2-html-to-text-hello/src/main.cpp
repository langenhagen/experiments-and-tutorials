/* Get the text from a html page using libxml2.

author: andreasl

based on:
    - https://stackoverflow.com/questions/10740250/c-cpp-version-of-beautifulsoup-especially-at-handling-malformed-html
    - http://www.xmlsoft.org/html/libxml-HTMLparser.html
    - http://xmlsoft.org/html/libxml-xmlerror.html#xmlParserErrors


following would be enough if it weren't for the non-visible stuff, like `script` nodes:
    std::string str(static_cast<char*>(xmlNodeGetContent(xmlDocGetRootElement(parser->myDoc))));
    std::cout << str << std::endl;
*/
#include "scrape.hpp"

#include <iostream>

int main(int argc, const char* argv[]) {
    if (argc > 1) {
        for(int i=1; i<argc; ++i) {
            ::barn::web::DownloadResult result  = ::barn::web::get_website_text(argv[i]);
            std::cout << result.content << "\n==========" << std::endl;
        }
    } else {
        ::barn::web::DownloadResult result  = ::barn::web::get_website_text("https://amazon.com");
        std::cout << result.content << std::endl;
    }
    return 0;
}
