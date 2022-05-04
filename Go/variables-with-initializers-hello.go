// Taken from https://tour.golang.org/basics/9.
// author: andreasl
// run via:
//   go run variables-with-initializers.go

package main

import "fmt"

var i, j int = 1, 2

func main() {
	var c, python, java = true, false, "no!" // works!
	fmt.Println(i, j, c, python, java)
}
