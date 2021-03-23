package main

import (
	"context"
	"fmt"

	"example.com/rest-client/client"
)

func main() {

	c := client.NewClient()

	ctx := context.Background()
	response, err := c.Get(ctx)
	if err != nil {
		fmt.Println("Error: ", err)
	}

	fmt.Println("Success: ", response)
}
