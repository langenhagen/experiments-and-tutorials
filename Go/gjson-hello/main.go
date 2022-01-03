// Showcase the usage of the 3rd party library `gjson`
package main

import (
	"fmt"

	uuid "github.com/satori/go.uuid"
	"github.com/tidwall/gjson"
)

// "github.com/google/uuid" // unmarshalling UUIDs works also with google/uuid

func jsonStringToGjson() {
	s := `
		{
			"hi": "there",
			"answer": 42,
			"values": [1, 2, 3],
			"nested": {
				"name": "Derp",
				"lastname": "Derpsen"
			},
			"id": "11111111-1111-4111-8111-111111111111"
		}`
	fmt.Printf("string: %s\n", s)

	r := gjson.Parse(s)
	fmt.Printf("gjson result: %v\n", r)
	fmt.Printf("hi: %v\n", r.Get("hi"))
	fmt.Printf("anwers: %v\n", r.Get("answer"))
	fmt.Printf("values is an array: %v\n", r.Get("values").IsArray())
	fmt.Printf("values: %v\n", r.Get("values"))
	fmt.Printf("nested.name: %v\n", r.Get("nested.name").String())
	id, e := uuid.FromString(r.Get("id").String())
	if e != nil {
		panic(e)
	}
	fmt.Printf("id: %v\n", id)
}

func defaults() {
	s := `{}`
	fmt.Printf("string: %s\n", s)

	r := gjson.Parse(s)

	fmt.Printf("gjson result: %v\n", r)

	nonexisting := r.Get("values").Int()
	fmt.Printf("nonexisting: %v\n", nonexisting)
}

func main() {
	fmt.Println("--- 1 json string - gjson ---")
	jsonStringToGjson()

	fmt.Println("--- 2 gjson defaults ---")
	defaults()
}
