/*My first Go program.

based on: https://golang.org/doc/tutorial/getting-started
@author: andreasl
*/
package main

import (
	"fmt"

	"rsc.io/quote"
)

func main() {
	fmt.Println("Hello, World!")
	fmt.Println(quote.Go())
}
