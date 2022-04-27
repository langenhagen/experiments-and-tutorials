// Showcase structs in go.
package main

import "fmt"

type myStruct struct {
	Hello int
	World string
}

type embeddedAnonymousStruct struct {
	myStruct
	other string
}

func helloStructs() {
	var o myStruct
	o = myStruct{} // initializes an object with Hello=0 and World=""
	fmt.Printf("MyStruct{} via %%v: %v\n", o)
	fmt.Printf("MyStruct{} via %%+v: %+v\n", o) // more detailed
	fmt.Printf("MyStruct{} via %%#v: %#v\n\n", o)  // most detailed

	o = myStruct{World: "DisneyWorld"} // initializes an object with Hello=0
	fmt.Printf("MyStruct{World:\"DisneyWorld\"}: %+v\n", o)
}

// Fields and methods as embedded/anonymous fields are also called "promoted"
func embeddedAnonymousStructs() {
	var o embeddedAnonymousStruct
	o = embeddedAnonymousStruct{}
	fmt.Printf("embeddedAnonymousStruct{} via %%#v: %#v\n\n", o)

	o = embeddedAnonymousStruct{myStruct: myStruct{World: "Cybertron"}, other: "foo"}
	fmt.Printf("%#v\n", o)
}

func main() {
	fmt.Println("--- 1 hello structs ---")
	helloStructs()

	fmt.Println("\n--- 2 embedded anonymous structs ---")
	embeddedAnonymousStructs()
}
