/* Implementation for scrape.hpp.

author: andreasl
*/
#include "scrape.hpp"

#include <cstring>
#include <sstream>
#include <string>

#include <curl/curl.h>
#include <libxml2/libxml/encoding.h>
#include <libxml2/libxml/HTMLparser.h>

namespace barn {
namespace web {

namespace {

/*Parse a chunk of incoming data.*/
size_t parse_chunk(void* ptr, const size_t size, const size_t nmemb, htmlParserCtxtPtr* parser) {
    htmlParseChunk(*parser, static_cast<char*>(ptr), nmemb, 0);
    return size * nmemb;
}

/*Check whether the given xml node contains children that contain non-visible text.*/
bool check_if_node_is_safe_to_print(const xmlNode* root) {
    for (const xmlNode* node = root; node; node = node->next) {
        if (!node->name
            || std::strcmp(reinterpret_cast<const char*>(node->name), "script") == 0
            || std::strcmp(reinterpret_cast<const char*>(node->name), "comment") == 0
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

} // namespace

/*Download a website's text.*/
DownloadResult get_website_text(const std::string& url) {
    CURL* curl = curl_easy_init();
    if (!curl) {
        return {DownloadResultErrorCode::FAILED_INIT_CURL, CURLE_OK, ""};
    }

    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
    curl_easy_setopt(curl,CURLOPT_ENCODING , "");  /*handle also gzip-encoded results*/
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, parse_chunk);
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
        return {DownloadResultErrorCode::CURL_ERROR, res, ""};
    }
    curl_easy_cleanup(curl);

    std::ostringstream oss;
    walk_xml_tree(xmlDocGetRootElement(parser->myDoc), oss);
    return {DownloadResultErrorCode::OK, CURLE_OK, oss.str()};
}

} // namespace web
} // namespace barn
