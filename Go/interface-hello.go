// Showcase the usage of interfaces.
package main

import (
	"fmt"
)

// Interface names usually end with -er, e.g. `Stringer`.
type myInterfacer interface {
	MyFunction() int
}

type myStructThatDoesNotImplementMyInterfacer struct{}

type myStruct struct {
	A int
	B int
}

func (s myStruct) MyFunction() int {
	return s.A + s.B
}

// Showcase how we can have variables of type of an interface and assign them to a struct that
// implements the according interface.
func interfacesAndStructs() {

	var v, p myInterfacer
	// i = &myStructThatDoesNotImplementMyInterfacer{} // doesn't work

	fmt.Printf("v is type %T; p is type %T\n", v, p)

	v = myStruct{1, 2}
	fmt.Printf("v is type %T; v.MyFunction() = %d\n", v, v.MyFunction())

	p = &myStruct{3, 4}
	fmt.Printf("p is type %T; p.MyFunction() = %d\n", p, p.MyFunction())
}

func main() {
	fmt.Println("--- 1 interfaces and structs ---")
	interfacesAndStructs()
}
