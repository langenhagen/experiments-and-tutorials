package greetings

import (
	"errors" /*nicewise, Go, doesn't know exceptions; errors go with the normal control flow*/
	"fmt"

	"math/rand"
	"time"
)

/*
Functions with name with uppercase first letter are public functions,
Functions with name with lowercase first letter are packages private methods.

In Go terms, a capital letter function is an "exported name".


If the functions with names that start with an uppercase letter will be exported to other
packages. If the function name starts with a lowercase letter, it won't be exported to other
packages, but you can call this function within the same package.
*/

// Hello returns a greeting for the named person.
func Hello(name string) (string, error) {

	// If no name was given, return an error with a message.
	if name == "" {
		return "", errors.New("empty name") /*this is how you create an error*/
	}

	// long variable initialization
	// var message string
	// message = fmt.Sprintf("Hi, %v. Welcome!", name)*/

	// short variable initialization
	message := fmt.Sprintf(randomFormat(), name)

	return message, nil
}

/*Every file can have 0 or more `init()` functions to set up the module. The `init()` functions run
after imports and variable declarations are done.

see: https://golang.org/doc/effective_go#init
*/
func init() {
	rand.Seed(time.Now().UnixNano())
}

// randomFormat returns one of a set of greeting messages. The returned
// message is selected at random.
func randomFormat() string {
	// A slice of message formats.
	formats := []string{
		"Hi, %v. Welcome!",
		"Great to see you, %v!",
		"Hail, %v! Well met!",
	}

	// Return a randomly selected message format by specifying
	// a random index for the slice of formats.
	return formats[rand.Intn(len(formats))]
}
