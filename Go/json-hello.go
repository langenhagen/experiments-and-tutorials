// Showcase how to construct and work with nested json objects.
// In colang, json is of type `[]byte`.
//
// json.Marshal escapes strings while serializing it.
// See: https://goinbigdata.com/how-to-correctly-serialize-json-string-in-golang/
//
// See also: https://www.sohamkamani.com/golang/json/
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
	Hello    string `json:"hello"` // `Hello` in the struct translates to `hello` in JSON
	Verbatim string // `Verbatim` in the struct translates to `Verbatim` in JSON
}

type nestedType struct {
	Id     uint      `json:"id"`
	Simple helloType `json:"nested"`
}

type complexType struct {
	Id      int        `json:"id"`
	Num     bool       `json:"bool"`
	Simple  helloType  `json:"nested"`
	Pointer *helloType `json:"pointer"`
}

func marshalStringToJSONViaJSONMarshalBreaks() {
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

func stringAndJSON() {
	s := `{"hello":"world"}`
	fmt.Printf("string: %s\n", s)
	b := []byte(s) // necessary for strings to convert to []byte; json.Marshal() doesn't work on strings
	fmt.Printf("bytes message: %+v\n", b)
	// the following works, too:
	// r := json.RawMessage(s)
	// b, e := r.MarshalJSON()
	fmt.Printf("json is valid: %t\n", json.Valid(b))

	var res helloType
	e := json.Unmarshal(b, &res) // just pass in a the simple bytes-array as input
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled json: %+v\n", res)
}

func objectAndJSON() {
	obj := helloType{Hello: "World", Verbatim: "moo"}
	fmt.Printf("object: %+v\n", obj)
	j, e := json.Marshal(obj)
	if e != nil {
		panic(e)
	}
	fmt.Printf("json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))

	var res helloType
	e = json.Unmarshal(j, &res)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled object: %+v\n", res)
}

func nestedAndJSON() {
	obj := nestedType{Id: 23, Simple: helloType{Hello: "World"}}
	fmt.Printf("nested object: %+v\n", obj)

	j, e := json.Marshal(obj)
	if e != nil {
		panic(e)
	}
	fmt.Printf("nested json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))

	var res nestedType
	e = json.Unmarshal(j, &res)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled nested object: %+v\n", res)
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

type typeWithOmittables struct {
	// `OmittableField` translates to `field`; marshalling to JSON will omit the field if its empty
	OmittableField int `json:"field,omitempty"`
}

func omitempty() {
	obj := typeWithOmittables{} // omits field
	fmt.Printf("object: %+v\n", obj)
	j, e := json.Marshal(obj)
	if e != nil {
		panic(e)
	}
	fmt.Printf("json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))

	var res typeWithOmittables
	e = json.Unmarshal(j, &res)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled object: %+v\n", res)
}

func empty() {
	s := `{}`
	b := []byte(s)
	fmt.Printf("bytes message: %+v\n", b)
	var res complexType
	e := json.Unmarshal(b, &res)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled object: %+v\n", res)
}

func arrays() {
	objs := []helloType{
		{Hello: "Aloha!"},
		{Hello: "Ahoi!"},
		{Hello: "Bonjour!"},
	}

	fmt.Printf("object: %+v\n", objs)
	j, e := json.Marshal(objs)
	if e != nil {
		panic(e)
	}
	fmt.Printf("json: %s\n", j)
	fmt.Printf("json is valid: %t\n", json.Valid(j))

	var res []helloType
	e = json.Unmarshal(j, &res)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled object: %+v\n", res)
}

// Showcase how to unmarshal JSON data whose context you don't know at compile time.
func unstructuredData() {

	// The keys need to be strings, the values can be any serializable value
	objs := map[string]interface{}{
		"birdSounds": map[string]string{
			"pigeon": "coo",
			"eagle":  "squak",
		},
		"total birds": 2,
	}

	fmt.Printf("object: %+v\n", objs)
	j, e := json.Marshal(objs)
	if e != nil {
		panic(e)
	}
	fmt.Printf("json: %s\n", j)

	var res map[string]interface{}
	e = json.Unmarshal(j, &res)
	if e != nil {
		panic(e)
	}
	fmt.Printf("unmarshalled object: %+v\n", res)
}

func main() {
	fmt.Println("--- 1 marshal string to json via json.Marshal() breaks bc of escaping qoutes ---")
	marshalStringToJSONViaJSONMarshalBreaks()

	fmt.Println("--- 2 string - json ---")
	stringAndJSON()

	fmt.Println("\n--- 3 type - json ---")
	objectAndJSON()

	fmt.Println("\n--- 4 nested type - json ---")
	nestedAndJSON()

	fmt.Println("\n--- 5 json.Valid ---")
	jsonValid()

	fmt.Println("\n--- 6 type with omittable field - json ---")
	omitempty()

	fmt.Println("\n--- 7 unmarshal empty json to a complex type ---")
	empty()

	fmt.Println("\n--- 8 arrays ---")
	arrays()

	fmt.Println("\n--- 8 unstructured data ---")
	unstructuredData()

	fmt.Println("\nEnd.")
}
