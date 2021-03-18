// A simple rest client
// based on:
//   - https://medium.com/@marcus.olsson/writing-a-go-client-for-your-restful-api-c193a2f4998c
//   - https://golang.org/pkg/net/http/
//   - https://golang.org/pkg/io/ioutil/
// author: andreasl

package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

// dispatch a GET request and print the results
func get() {
	resp, err := http.Get("http://example.com/")
	if err != nil {
		fmt.Println("Looks like an error: ", err)
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body) // ioutil.ReadALl returns a byte slice `byte[]``
	if err != nil {
		fmt.Println("Looks like another error: ", err)
	}
	fmt.Println(string(body))
}

func main() {
	fmt.Print("hi!\n\n")

	get()

	fmt.Println("\nbye!")
}
