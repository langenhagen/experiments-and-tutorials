/* Showcase a very simple usage of libcurl. Write a website into a data structure and print it

author: andreasl

based on:
    - https://curl.haxx.se/libcurl/c/simple.html
    - https://stackoverflow.com/questions/2329571/c-libcurl-get-output-into-a-string
    - https://curl.haxx.se/libcurl/c/CURLOPT_WRITEFUNCTION.html
    - https://stackoverflow.com/questions/28283822/curl-returns-binary-data-instead-of-html
    - https://curl.haxx.se/libcurl/c/CURLOPT_WRITEFUNCTION.html
*/
#include <cstring>
#include <iostream>
#include <sstream>
#include <stdio.h>
#include <stdlib.h>

#include <curl/curl.h>

/*Callback function, curl defines the interface.
"size is always 1; nmemb is the size of the chunk*/
size_t my_callback(void* ptr, const size_t size, const size_t nmemb, std::ostringstream* oss) {
    std::cout << "chunk  size: " << size << "  nmemb: " << nmemb << std::endl;
    (*oss) << reinterpret_cast<char*>(ptr);
    return size * nmemb;
}

int main(int argc, const char* argv[]) {
    CURL* curl = curl_easy_init();
    if (!curl) {
        std::cerr << "Failed to init CURL" << std::endl;
        return 1;
    }

    curl_easy_setopt(curl, CURLOPT_URL, "https://example.com");
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);  /*follow redirections*/
    curl_easy_setopt(curl,CURLOPT_ENCODING , "");  /*handle also gzip-encoded results*/

    /*Connect the callback function*/
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, my_callback);
    std::ostringstream oss;
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &oss);

    /* Perform the request, res will get the return code */
    CURLcode res = curl_easy_perform(curl);  /*if missing callback, write output to stdout*/

    if (res != CURLE_OK) {
        std::cerr << "Error: curl_easy_perform() failed: " << curl_easy_strerror(res) << std::endl;
        return 2;
    }

    curl_easy_cleanup(curl);

    std::cout << "---\n" << oss.str() << "\n---" << std::endl;
    return 0;
}
