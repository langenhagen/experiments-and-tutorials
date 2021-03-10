package main

import (
	"fmt"
	"log"

	"example.com/greetings"
)

func main() {
	log.SetPrefix("greetings: ")
	log.SetFlags(0) // disable printing the time, source file, and line number.

	// message, err := greetings.Hello("")
	message, err := greetings.Hello("Grump")
	if err != nil {
		log.Fatal(err) // log.Fatal stops the program
	}
	fmt.Println(message)
}
