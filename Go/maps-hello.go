// Showcase maps in go.
package main

import "fmt"

func helloMaps() {
	// m := map[string]int{"foo": 1, "bar": 2} // works, too
	m := make(map[string]int)
	m["foo"] = 7
	m["bar"] = 13

	fmt.Println("m:", m)
	fmt.Println("len(m):", len(m))

	v := m["foo"]
	fmt.Println(`m["foo"]: `, v)

	v, exists := m["foo"]
	fmt.Println(`m["foo"]: `, v)
	fmt.Println(`m["foo"] exists?: `, exists)
}

func deletion() {
	m := map[string]int{"foo": 1, "bar": 2}

	delete(m, "bar")
	fmt.Println("map:", m)

}

func accessNonexistentItems() {
	m := map[string]int{"foo": 1, "bar": 2}

	kZero, exists := m["non_existing"]
	fmt.Println(`m["non_existing"]`, kZero) // zeroed
	fmt.Println("prs:", exists)             // false
}

func main() {
	fmt.Println("--- 1 hello maps ---")
	helloMaps()

	fmt.Println("\n--- 2 deleting elements ---")
	deletion()

	fmt.Println("\n--- 3 accessing non-existent elements ---")
	accessNonexistentItems()
}
