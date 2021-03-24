// Showcase a simple Go REST client.
//
// see:
//   - https://dev.to/plutov/writing-rest-api-client-in-go-3fkg
//   - https://eager.io/blog/go-and-json/
//   - https://www.scaledrone.com/blog/creating-an-api-client-in-go/
//   - was helpful: https://mholt.github.io/json-to-go/
//
// author: andreasl
package main

import (
	"context"
	"fmt"

	"example.com/rest-client/client"
)

func main() {
	c := client.NewClient()

	ctx := context.Background()
	res, err := c.Get(ctx, "foo=bar")
	if err != nil {
		fmt.Println("Error: ", err)
	}

	fmt.Printf("Success %#v\n", res)
}
