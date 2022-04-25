// Showcase the lightweight threads called goroutines in Go.
//
// see: https://gobyexample.com/goroutines
package main

import (
	"fmt"
	"sync"
	"time"
)

// Prints numbers from 1-3 along with the passed string.
func foo(s string, wg *sync.WaitGroup) {
	defer wg.Done()

	for i := 1; i <= 3; i++ {
		time.Sleep(100 * time.Millisecond)
		fmt.Println(s, ": ", i)
	}
}

func helloGoroutines() {
	// Starting two goroutines

	var wg sync.WaitGroup

	wg.Add(2) // tell the waitgroup that there will be 2 more goroutines to wait for
	go foo("1st goroutine", &wg)
	go foo("2nd goroutine", &wg)

	wg.Wait()

	fmt.Println("All goroutines done")
}

func main() {
	fmt.Println("--- 1 simple Goroutines ---")
	helloGoroutines()
}
