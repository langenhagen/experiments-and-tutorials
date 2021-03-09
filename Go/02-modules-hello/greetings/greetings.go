package greetings

import "fmt"

/*
Functions with name with uppercase first letter are public functions,
Functions with name with lowercase first letter are packages private methods.

In Go terms, a capital letter function is an "exported name".


If the functions with names that start with an uppercase letter will be exported to other
packages. If the function name starts with a lowercase letter, it won't be exported to other
packages, but you can call this function within the same package.
*/

// Hello returns a greeting for the named person.
func Hello(name string) string {

	// long variable initialization
	/*var message string
	  message = fmt.Sprintf("Hi, %v. Welcome!", name)*/

	// short variable initialization
	message := fmt.Sprintf("Hi, %v. Welcome!", name)

	return message
}
