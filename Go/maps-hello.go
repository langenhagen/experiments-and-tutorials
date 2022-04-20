// Showcase maps in go.
package main

import "fmt"

func helloMaps() {
	m := make(map[string]int)

	m["k1"] = 7
	m["k2"] = 13

	fmt.Println("m:", m)
	fmt.Println("len(m):", len(m))

	// normal access
	v1 := m["k1"]
	fmt.Println(`m["k1"]: `, v1)

	v1, exists := m["k1"]
	fmt.Println(`m["k1"]: `, v1)
	fmt.Println(`m["k1"] exists?: `, exists)

	// deletion
	delete(m, "k2")
	fmt.Println("map:", m)

	// accessing non-existent stuff
	kZero, exists := m["non_existing"]
	fmt.Println(`m["non_existing"]`, kZero) // zeroed
	fmt.Println("prs:", exists)             // false

	// Inline creation
	fmt.Println("map:", map[string]int{"foo": 1, "bar": 2})
}

func main() {
	fmt.Println("--- 1 hello maps ---")
	helloMaps()
}
