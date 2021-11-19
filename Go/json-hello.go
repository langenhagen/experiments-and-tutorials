// Showcase how to construct and work with nested json objects.
package main

import (
	"encoding/json"
	"fmt"
)

type simpleType struct {
	Field1 bool `json:"field1" form:"field1"`
	Field2 bool `json:"field2" form:"field2"`
}

type nestedType struct {
	Id     uint       `json:"id"`
	Simple simpleType `json:"nested"`
}

// Prints `hello` then `nice` then `world`.
func main() {
	fmt.Println("--- 1 simple type --- ")

	s := simpleType{Field1: true, Field2: false}
	fmt.Printf("simple type: %+v\n", s)

	j, e := json.Marshal(s)
	if e != nil {
		fmt.Printf("Error: %s\n", e)
	}
	fmt.Printf("simple json: %s\n", j)

	fmt.Println("\n--- 2 nested type --- ")

	c := nestedType{Id: 23, Simple: s}
	fmt.Printf("simple type: %+v\n", c)

	j, e = json.Marshal(c)
	if e != nil {
		fmt.Printf("Error: %s\n", e)
	}
	fmt.Printf("nested json: %s\n", j)

	fmt.Println("End.")
}
