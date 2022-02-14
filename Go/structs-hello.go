// Showcase structs in go.
package main

import "fmt"

type MyStruct struct {
	Hello int
	World string
}

func main() {
	var o MyStruct
	o = MyStruct{} // initializes an object with Hello=0 and World=""
	fmt.Printf("MyStruct{} via %%v: %v\n", o)
	fmt.Printf("MyStruct{} via %%+v: %+v\n", o)
	fmt.Printf("MyStruct{} via %%#v: %#v\n", o)

	o = MyStruct{World: "DisneyWorld"} // initializes an object with Hello=0
	fmt.Printf("MyStruct{World:\"DisneyWorld\": %+v\n", o)
}
