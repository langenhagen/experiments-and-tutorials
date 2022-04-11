// Showcase the usage of satori UUIDs.
// There also exists google UUIDs. Google UUIDs seem to be more common.
package main

import (
	"fmt"

	uuid "github.com/satori/go.uuid"
)

func fromString() {
	s := "11111111-1111-4111-8111-111111111111"
	fmt.Printf("string: \"%s\"\n", s)
	u, e := uuid.FromString(s)
	if e != nil {
		panic(e)
	}
	fmt.Printf("UUID: %v\n", u)
}

func fromEmptyString() {
	s := ""
	fmt.Printf("string: \"%s\"\n", s)
	_, e := uuid.FromString(s)
	if e != nil {
		fmt.Printf("Error: UUID from empty string does not work; error: %v\n", e)
		return
	}
	fmt.Printf("¡This line should be unreachable!")
}

func fromBrokenString() {
	s := "not a UUID"
	fmt.Printf("string: \"%s\"\n", s)
	_, e := uuid.FromString(s)
	if e != nil {
		fmt.Printf("Error: UUID from broken string does not work; error: %v\n", e)
		return
	}
	fmt.Printf("¡This line should be unreachable!")
}

func fromInvalidUUID4String() {
	s := "11111111-1111-5111-c111-111111111111"
	fmt.Printf("string: \"%s\"\n", s)
	u, e := uuid.FromString(s)
	if e != nil {
		panic(e)
	}
	fmt.Printf("UUID: %v\n", u)
}

func toString() {
	u := uuid.NewV4()
	s := fmt.Sprint(u)
	fmt.Printf("string: %s\n", s)
}

func main() {
	fmt.Println("\n--- 1 UUIDs from string ---")
	fromString()

	fmt.Println("\n--- 2 UUIDs from empty string fails ---")
	fromEmptyString()

	fmt.Println("\n--- 3 UUIDs from broken string fails ---")
	fromBrokenString()

	fmt.Println("\n--- 4 UUIDs from invalid uuid4 works ---")
	fromInvalidUUID4String()

	fmt.Println("\n--- 5 UUID to string ---")
	toString()
}
