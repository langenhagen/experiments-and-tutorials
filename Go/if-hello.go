// Showcase the usage of the if-statement in Go.
package main

import "fmt"

func main() {
	i := true
	if i {
		fmt.Printf("i is truthy\n")
	}

    i = false
	if i {
		fmt.Printf("i is truthy, but should not be\n")
	}

}
