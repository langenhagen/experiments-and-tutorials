/* Get the text from a html page using libxml2.

author: andreasl

based on:
    - https://stackoverflow.com/questions/10740250/c-cpp-version-of-beautifulsoup-especially-at-handling-malformed-html
    - http://www.xmlsoft.org/html/libxml-HTMLparser.html
*/
#include <iostream>
#include <sstream>

#include <curl/curl.h>
#include <libxml2/libxml/encoding.h>
#include <libxml2/libxml/HTMLparser.h>

/*Error code when downloading a web site.*/
enum class DownloadResultErrorCode {
    OK = 0,
    FAILED_INIT_CURL = 1,
    CURL_ERROR = 2
};

/*Result of downloading a website.*/
struct DownloadResult {
    DownloadResultErrorCode code;  /*Download result.*/
    CURLcode curl_code;  /*Curl error code.*/
    std::string content;  /*Downloaded content.*/
};

size_t curl_callback(void* ptr, const size_t size, const size_t nmemb, htmlParserCtxtPtr* parser) {
    std::cout
        << "htmlParseChunk(*parser, reinterpret_cast<char*>(ptr), " << nmemb << ", 0) = "
        << htmlParseChunk(*parser, reinterpret_cast<char*>(ptr), nmemb, 0)
        << std::endl;
    return size * nmemb;
}

void walk_tree(xmlNode* a_node) {
    std::cout << "walk_tree() a_node: " << a_node << "\n";

    xmlNode* cur_node = nullptr;
    xmlAttr* cur_attr = nullptr;
    for (cur_node = a_node; cur_node; cur_node = cur_node->next) {
        // do something with that node information, like... printing the tag's name and attributes
        std::cout << "Got tag : " << cur_node->name << std::endl;
        for (cur_attr = cur_node->properties; cur_attr; cur_attr = cur_attr->next) {
            std::cout << "  ->; with attribute : " << cur_attr->name << std::endl;
        }
        walk_tree(cur_node->children);
    }
}

/*Download a website.*/
DownloadResult get_website(const std::string& url) {
    CURL* curl = curl_easy_init();
    if (!curl) {
        std::cerr << "Failed to init CURL" << std::endl;
        return {DownloadResultErrorCode::FAILED_INIT_CURL, CURLE_OK, ""};
    }

    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
    curl_easy_setopt(curl,CURLOPT_ENCODING , "");  /*handle also gzip-encoded results*/

    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, curl_callback);
    htmlParserCtxtPtr parser = htmlCreatePushParserCtxt(
        nullptr,
        nullptr,
        nullptr,
        0,
        nullptr,
        XML_CHAR_ENCODING_UTF8);
    htmlCtxtUseOptions(
        parser,
        HTML_PARSE_NOBLANKS | HTML_PARSE_NOERROR | HTML_PARSE_NOWARNING | HTML_PARSE_NONET);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &parser);
    CURLcode res = curl_easy_perform(curl);
    if (res != CURLE_OK) {
        std::cerr << "Error: curl_easy_perform() failed: " << curl_easy_strerror(res) << std::endl;
        return {DownloadResultErrorCode::CURL_ERROR, res, "TODO"};
    }
    curl_easy_cleanup(curl);

    std::cout
            << "htmlParseChunk(parser, nullptr, 0, 1) = "
            << htmlParseChunk(parser, nullptr, 0, 1)
            << std::endl;
    std::cout << parser->myDoc << std::endl;

    walk_tree(xmlDocGetRootElement(parser->myDoc));

    return {DownloadResultErrorCode::OK, CURLE_OK, "TODO"};
}

int main(int argc, const char* argv[]) {

    get_website("https://example.com");

    return 0;
}
