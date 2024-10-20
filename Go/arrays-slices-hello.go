// Showcase the usage of arrays and slices in Go.
package main

import (
	"fmt"
	"strings"
)

func array() {
	a := [6]int{2, 3, 5, 7, 11, 13}
	fmt.Printf("Array via %%v: %v\n", a)   // [2 3 5 7 11 13]
	fmt.Printf("Array via %%+v: %+v\n", a) // [2 3 5 7 11 13]
	fmt.Printf("Array via %%+v: %#v\n", a) // [6]int{2, 3, 5, 7, 11, 13}

	s := strings.Trim(strings.Join(strings.Fields(fmt.Sprint(a)), ", "), "[]")
	fmt.Printf("Array to string via custom code: %s\n", s) // 2, 3, 5, 7, 11, 13
}

func slice() {
	s := []int{2, 3, 5, 7, 11, 13}
	fmt.Printf("Slice via %%v: %v\n", s)   // [2 3 5 7 11 13]
	fmt.Printf("Slice via %%+v: %+v\n", s) // [2 3 5 7 11 13]
	fmt.Printf("Slice via %%+v: %#v\n", s) // [6]int{2, 3, 5, 7, 11, 13}
}

func main() {
	fmt.Println("--- 1 arrays ---")
	array()

	fmt.Println("\n--- 2 slices ---")
	slice()
}
