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

type helloType struct {
	Hello string `json:"hello"`
}

type nestedType struct {
	Id     uint      `json:"id"`
	Simple helloType `json:"nested"`
}

func marshal_string_2_json_via_json_Marshal_breaks() {
	// Marshalling strings to json via the function json.Marshal that you use on objects doesn't
	// work since it causes issues with escaped double quotes
	// still, json.Valid() is `true`.
	s := `{"hi":"there"}`
	fmt.Printf("string: %s\n", s)

	j, e := json.Marshal(s)
	if e != nil {
		panic(e)
	}
	fmt.Printf("wrong escaped json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))
}

func marshal_string_2_json() []byte {
	s := `{"hello":"world"}`
	fmt.Printf("string: %s\n", s)
	b := []byte(s) // necessary for strings to convert to []byte; json.Marshal() doesn't work on strings
	fmt.Printf("bytes message: %+v\n", b)
	// the following works, too:
	// r := json.RawMessage(s)
	// b, e := r.MarshalJSON()
	fmt.Printf("json is valid: %t\n", json.Valid(b))

	return b
}

func unmarshal_json_2_string(b []byte) helloType {
	var obj helloType
	e := json.Unmarshal(b, &obj) // just pass in a the simple bytes-array as input
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled json: %+v\n", obj)

	return obj
}

func marshal_object_2_json() []byte {
	obj := helloType{Hello: "World"}
	fmt.Printf("object: %+v\n", obj)
	j, e := json.Marshal(obj)
	if e != nil {
		panic(e)
	}
	fmt.Printf("simple json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))
	return j
}

func unmarshal_json_2_object(b []byte) {
	var obj helloType
	e := json.Unmarshal(b, &obj)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled object: %+v\n", obj)
}

func marshal_nested_type_2_json() []byte {
	obj := nestedType{Id: 23, Simple: helloType{Hello: "World"}}
	fmt.Printf("nested object: %+v\n", obj)

	j, e := json.Marshal(obj)
	if e != nil {
		panic(e)
	}
	fmt.Printf("nested json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))

	return j
}

func unmarshal_nested_json_2_object(b []byte) {
	var nested nestedType
	e := json.Unmarshal(b, &nested)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled nested object: %+v\n", nested)
}

func jsonValid() {
	var s string
	var b []byte

	// json.Valid() on invalid json returns False
	s = `{"hi":"there}`
	b = []byte(s)
	fmt.Printf("json.Valid() on %s: %t\n", b, json.Valid(b))

	s = `{"hi":"there"}`
	b = []byte(s)
	fmt.Printf("json.Valid() on %s: %t\n", b, json.Valid(b))

	// json.Valid() doesn't catch escaping issues
	s = `{"hi":"there"}`
	b, _ = json.Marshal(s)
	fmt.Printf("json.Valid() on %s: %t\n", b, json.Valid(b))
}

func main() {

	fmt.Println("--- 1 marshal string to json via json.Marshal() breaks bc of escaping qoutes ---")
	marshal_string_2_json_via_json_Marshal_breaks()

	fmt.Println("--- 2 marshal string to json ---")
	b := marshal_string_2_json()

	fmt.Println("\n--- 3 unmarshal json to string ---")
	unmarshal_json_2_string(b)

	fmt.Println("\n--- 4 marshal type to json ---")
	obj := marshal_object_2_json()

	fmt.Println("\n--- 5 unmarshal json to type ---")
	unmarshal_json_2_object(obj)

	fmt.Println("\n--- 6 marshal nested type to json ---")
	nested := marshal_nested_type_2_json()

	fmt.Println("\n--- 7 unmarshal json to nested type ---")
	unmarshal_nested_json_2_object(nested)

	fmt.Println("\n--- 8 json.Valid")
	jsonValid()

	fmt.Println("\nEnd.")
}
