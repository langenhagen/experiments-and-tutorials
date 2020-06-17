/* Get the text from a html page using libxml2.

author: andreasl

based on:
    - https://stackoverflow.com/questions/10740250/c-cpp-version-of-beautifulsoup-especially-at-handling-malformed-html
    - http://www.xmlsoft.org/html/libxml-HTMLparser.html
    - http://xmlsoft.org/html/libxml-xmlerror.html#xmlParserErrors
*/
#include <cstring>
#include <iostream>
#include <sstream>
#include <string>

#include <curl/curl.h>
#include <libxml2/libxml/encoding.h>
#include <libxml2/libxml/HTMLparser.h>

/*Error code when downloading a web site.*/
enum class DownloadResultErrorCode {
    OK = 0  /*No error*/,
    FAILED_INIT_CURL = 1  /*Initializing CURL failed*/,
    CURL_ERROR = 2  /*Downloading via CURL failed*/
};

/*Result of downloading a website.*/
struct DownloadResult {
    DownloadResultErrorCode code;  /*Download result error code.*/
    CURLcode curl_code;  /*Curl error code.*/
    std::string content;  /*Downloaded content.*/
};

/*Parse the chunk of incoming data.*/
size_t curl_callback(void* ptr, const size_t size, const size_t nmemb, htmlParserCtxtPtr* parser) {
    htmlParseChunk(*parser, reinterpret_cast<char*>(ptr), nmemb, 0);
    return size * nmemb;
}

/*Check whether the given xml node contains children that contain non-visible text.*/
bool check_if_node_is_safe_to_print(const xmlNode* root) {
    for (const xmlNode* node = root; node; node = node->next) {
        if (!node->name
            || std::strcmp(reinterpret_cast<const char*>(node->name), "script") == 0
            || !check_if_node_is_safe_to_print(node->children)) {
            return false;
        }
    }
    return true;
}

/*Iterate over the xml tree and write printable stuff to the given stringstream.*/
void walk_xml_tree(const xmlNode* root, std::ostringstream& oss) {
    for (const xmlNode* node = root; node; node = node->next) {
        if (!check_if_node_is_safe_to_print(node)) {
            walk_xml_tree(node->children, oss);
            continue;
        }
        oss << reinterpret_cast<char*>(xmlNodeGetContent(node)) << " ";
    }
}

/*Download a website's text.*/
DownloadResult get_website_text(const std::string& url) {
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
        return {DownloadResultErrorCode::CURL_ERROR, res, ""};
    }
    curl_easy_cleanup(curl);

    /*//following would be enough if it weren't for the non-visible stuff, like `script` nodes
    std::string s(reinterpret_cast<char*>(xmlNodeGetContent(xmlDocGetRootElement(parser->myDoc))));
    std::cout << "content: " << s << std::endl;
    /**/

    std::ostringstream oss;
    walk_xml_tree(xmlDocGetRootElement(parser->myDoc), oss);
    return {DownloadResultErrorCode::OK, CURLE_OK, oss.str()};
}

int main(int argc, const char* argv[]) {
    if (argc > 1) {
        for(int i=1; i<argc; ++i) {
            DownloadResult result  = get_website_text(argv[i]);
            std::cout << result.content << "\n=======================================" <<std::endl;
        }
    } else {
        DownloadResult result  = get_website_text("https://amazon.com");
        std::cout << result.content << std::endl;
    }
    return 0;
}
