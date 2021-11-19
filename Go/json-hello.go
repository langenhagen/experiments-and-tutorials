// Showcase how to construct and work with nested json objects.
// In colang, json is of type `[]byte`.
//
// json.Marshal escapes string while serializing it.
// See also: https://goinbigdata.com/how-to-correctly-serialize-json-string-in-golang/
//
// run via:
//
//   go run json-hello.go
//
package main

import (
	"encoding/json"
	"fmt"
)

type hello struct {
	Hello string `json:"hello"`
}

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
	fmt.Println("--- 1 marshal string to json --- ")
	s := `{"hello":"world"}`
	fmt.Printf("string: %s\n", s)
	b := []byte(s) // necessary for strings to convert to []byte
	fmt.Printf("bytes message: %+v\n", b)
	// the following works, too:
	// r := json.RawMessage(s)
	// b, e := r.MarshalJSON()
	fmt.Printf("json is valid: %t\n", json.Valid(b))

	fmt.Println("\n--- 2 unmarshal json to string --- ")
	var h hello
	e := json.Unmarshal(b, &h) // just pass in a the simple bytes-array as input
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled json: %+v\n", h)

	fmt.Println("\n--- 3 marshal simple type to json --- ")
	o := simpleType{Field1: true, Field2: false}
	fmt.Printf("simple type: %+v\n", o)
	j, e := json.Marshal(o)
	if e != nil {
		panic(e)
	}
	fmt.Printf("simple json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))

	fmt.Println("\n--- 4 unmarshal json to simple type --- ")

	var simple simpleType
	e = json.Unmarshal(j, &simple)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled simple type: %+v\n", simple)

	fmt.Println("\n--- 5 marshal nested type to json --- ")
	c := nestedType{Id: 23, Simple: o}
	fmt.Printf("simple type: %+v\n", c)

	j, e = json.Marshal(c)
	if e != nil {
		panic(e)
	}
	fmt.Printf("nested json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))

	fmt.Println("\n--- 6 unmarshal json to nested type --- ")
	var nested nestedType
	e = json.Unmarshal(j, &nested)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled nested type: %+v\n", nested)

	fmt.Println("\nEnd.")
}
