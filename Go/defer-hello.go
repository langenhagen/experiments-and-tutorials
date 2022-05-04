// A defer statement defers the execution of a function until the surrounding function returns.
//
// The deferred call's arguments are evaluated immediately, but the function call is not executed
// until the surrounding function returns.
//
// `defer` doesn't work when calling `os.Exit()`, see https://gobyexample.com/exit
//
// see: https://tour.golang.org/flowcontrol/12
// and: https://go.dev/tour/flowcontrol/13
//
// author: andreasl
package main

import "fmt"

// Prints `hello` then `nice` then `world`.
func main() {
	defer fmt.Println("world") // prints last
	defer fmt.Println("nice") // prints second last

	fmt.Println("hello")
}
