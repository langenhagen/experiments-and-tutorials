// Showcase how to return a variable as a pointer in golang
//
// See: https://goinbigdata.com/how-to-correctly-serialize-json-string-in-golang/
// Go program to return the
// pointer from the function
package main

import "fmt"

// defining function has integer pointer as return type
func returnPointer() *int {

	// local var on the heap
	myLocalVar := 69

	// returning a reference to a local variable is OK.
	return &myLocalVar
}

func main() {

	fmt.Println("--- 1 functions can return local vars as pointers ---")
	n := returnPointer()

	fmt.Println("Value of n is: ", *n)
}
