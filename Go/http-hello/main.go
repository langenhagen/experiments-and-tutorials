// Showcase the usage of the standard library `net/http` to dispatch HTTP REST requests.
package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"

	"moul.io/http2curl"
)

// Showcase a simple GET request.
func get() {
	resp, err := http.Get("https://httpbin.org/get")
	if err != nil {
		panic(err)
	}
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Response:\n%v\n\n", resp)
	fmt.Printf("Response code:\n%d\n\n", resp.StatusCode)
	fmt.Printf("Response body:\n%s\n\n", string(body))
}

// A sample JSON request schema.
type myRequestSchema struct {
	MyFields []int `json:"my_fields" binding:"required"`
}

// Showcase a GET request with a body.
// It's a bit more complicated than simple GET or even POST.
func getWithBody() {
	url := "https://httpbin.org/get"
	reqBody, err := json.Marshal(myRequestSchema{MyFields: []int{1, 2, 3}})
	if err != nil {
		panic(err)
	}
	req, err := http.NewRequest(http.MethodGet, url, bytes.NewBuffer(reqBody))
	if err != nil {
		panic(err)
	}
	req.Header.Add("Content-Type", "application/json")

	curlCommand, err := http2curl.GetCurlCommand(req)
	if err != nil {
		panic(err)
	}

	client := http.Client{}
	resp, err := client.Do(req) // Do() flushes the body of the request
	if err != nil {
		panic(err)
	}

	fmt.Printf("Request:\n%v\nn", req)
	fmt.Printf("Request body:\n%v\n\n", req.Body)
	fmt.Printf("Request in Curl:\n%v\n\n", curlCommand)

	fmt.Printf("Response:\n%v\n\n", resp)
	fmt.Printf("Response code:\n%d\n\n", resp.StatusCode)

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Response body:\n%s\n\n", string(body))
}

func main() {
	fmt.Println("--- 1 GET ---")
	get()

	fmt.Println("\n--- 2 GET with JSON body ---")
	getWithBody()
}
