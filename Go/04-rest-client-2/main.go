// Showcase a simple Go REST client.
//
// see:
//   - https://dev.to/plutov/writing-rest-api-client-in-go-3fkg
//   - https://eager.io/blog/go-and-json/
//   - https://www.scaledrone.com/blog/creating-an-api-client-in-go/
//   - https://stackoverflow.com/questions/24455147/how-do-i-send-a-json-string-in-a-post-request-in-go
//   - was helpful: https://mholt.github.io/json-to-go/
//
// author: andreasl
package main

import (
	"context"
	"encoding/json"
	"fmt"

	"example.com/rest-client/client"
)

func main() {
	c := client.NewClient()

	fmt.Println("=== 1 GET request ===")

	ctx := context.Background()
	res, err := c.Get(ctx, "foo=bar")
	if err != nil {
		fmt.Println("Error: ", err)
	}

	pretty_result, err := json.MarshalIndent(res, "", "  ")
	if err != nil {
		fmt.Println("Error: ", err)
	}
	fmt.Printf("Get Result:\n%s\n", pretty_result)

	fmt.Println("\n=== 2 POST request ===")

	res, err = c.Post(ctx, `{"andi":"mandi"}`, "foo=bar")
	if err != nil {
		fmt.Println("Error: ", err)
	}

	pretty_result, err = json.MarshalIndent(res, "", "  ")
	if err != nil {
		fmt.Println("Error: ", err)
	}
	fmt.Printf("POST Result:\n%s\n", pretty_result)
}
