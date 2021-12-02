// Showcase how to return a variable as a pointer in golang
//
// See: https://goinbigdata.com/how-to-correctly-serialize-json-string-in-golang/
// Go program to return the
// pointer from the function
package main

import "fmt"

// returnPointer returns an integer pointer.
func returnPointer() *int {
	myLocalVar := 69 // local var on the heap

	// returning a reference to a local variable is OK. Golang uses garbage collection
	return &myLocalVar
}

// returnPointer returns a Nil integer pointer.
func returnNil() *int {
	return nil
}

func main() {
	fmt.Println("\n--- 1 functions can return local vars as pointers ---")
	i := returnPointer()
	fmt.Printf("i = %d\n", *i)

	fmt.Println("\n--- 2 functions can return nil ---")
	j := returnNil()
	fmt.Printf("j = %#v\n", j)
}
