// Showcase the usage of strings in Go.
package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println("--- 1 HasSuffix ---")
	s := "Hello, World!"
	fmt.Printf("s:   %v\n", s)
	suffix := "World"
	fmt.Printf("Has suffix \"%v\":   %v\n", suffix, strings.HasSuffix(s, suffix))
	suffix = "World!"
	fmt.Printf("Has suffix \"%v\":   %v\n", suffix, strings.HasSuffix(s, suffix))
}
