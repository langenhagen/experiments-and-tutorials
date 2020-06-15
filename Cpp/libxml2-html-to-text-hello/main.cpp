/* Get the text from a html page using libxml2.

author: andreasl

based on:
    - https://stackoverflow.com/questions/10740250/c-cpp-version-of-beautifulsoup-especially-at-handling-malformed-html
    - http://www.xmlsoft.org/html/libxml-HTMLparser.html
*/
#include <iostream>

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

/*Download a website.*/
DownloadResult get_website(const std::string& url) {
    CURL *curl = curl_easy_init();
    if (!curl) {
        std::cerr << "Failed to init CURL" << std::endl;
        return {DownloadResultErrorCode::FAILED_INIT_CURL, 0, ""};
    }

    curl_easy_setopt(curl, CURLOPT_URL, "https://example.com");
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);

    CURLcode res = curl_easy_perform(curl);
    if (res != CURLE_OK) {
        std::cerr << "Error: curl_easy_perform() failed: " << curl_easy_strerror(res) << std::endl;
        return {DownloadResultErrorCode::CURL_ERROR, res, ""};
    }

    curl_easy_cleanup(curl);
    return {DownloadResultErrorCode::OK, CURLE_OK, };
}

int main(int argc, const char* argv[]) {

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

    return 0;
}
