// Showcase the variadic functions in Golang
package main

import (
	"fmt"
	"strings"
)

func join(delimiter string, element ...string) string {
	return strings.Join(element, delimiter)
}

func variadicFunctions() {

	s := join(" ", "Hallo")
	fmt.Printf("join(\" \", \"Hallo\") = %s\n", s)

	s = join("Hallo", "Andi")
	fmt.Printf("join(\" \", \"Hallo\", \"Andi\") = %s\n", s)
}

func variadicFunctionsWithSlices() {

	ss := []string{"Andi", "Mandi", "Sugar", "Candy"}

	s := join(" ", ss...) // similar to tuple unpacking in Python
	fmt.Printf("join(\" \", ss...) = %s\n", s)
}

func main() {
	fmt.Println("--- 1 hello variadic functions ---")
	variadicFunctions()

	fmt.Println("\n--- 2 variadic functions with slice arguments ---")
	variadicFunctionsWithSlices()
}
