// Define a custom enum-like to match the custom PostgreSQL enum `MyEnum``.
package main

type myEnum string

const (
	albatross myEnum = "albatross"
	penguin   myEnum = "penguin"
	seagull   myEnum = "seagull"
)
