/* Functionality for downloading the text from a website.

author: andreasl
*/
#pragma once

#include <string>

#include <curl/curl.h>

namespace barn {
namespace web {

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

/*Download a website's text.*/
DownloadResult get_website_text(const std::string& url);

} // namespace web
} // namespace barn
