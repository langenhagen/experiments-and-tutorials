// Showcase the usage of strings in Go.
package main

import (
	"fmt"
	"strings"
)

func hasSuffix() {
	s := "Hello, World!"
	fmt.Printf("s:   %v\n", s)
	suffix := "World"
	fmt.Printf("Has suffix \"%v\":   %v\n", suffix, strings.HasSuffix(s, suffix))
	suffix = "World!"
	fmt.Printf("Has suffix \"%v\":   %v\n", suffix, strings.HasSuffix(s, suffix))
}

func convertByteSlicesToAndFromString() {
	s := `Ima string`
	b := []byte(s)
	t := string(b)

	fmt.Printf("String s: %s\n", s)
	fmt.Printf("[]byte: %+v\n", b)
	fmt.Printf("String t: %s\n", t)
}

func main() {
	fmt.Println("--- 1 HasSuffix ---")
	hasSuffix()

	fmt.Println("\n--- 2 byte[] and string conversion is trivial ---")
	convertByteSlicesToAndFromString()
}
