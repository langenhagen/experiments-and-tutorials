package main

import (
	"fmt"
	"log"

	"example.com/greetings"
)

func main() {
	log.SetPrefix("greetings: ")
	log.SetFlags(0) // disable printing the time, source file, and line number.

	names := []string{"Gladys", "Samantha", "Darrin"} /*A slice of names.*/

	// message, err := greetings.Hello("")
	// message, err := greetings.Hello("Grump")
	messages, err := greetings.Hellos(names)

	if err != nil {
		log.Fatal(err) // log.Fatal stops the program
	}
	// fmt.Println(message)
	fmt.Println(messages)
}
