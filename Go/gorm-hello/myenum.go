// Define the database model for mmy custom PostgreSQL enum `MyEnum``.
package main

import "database/sql/driver"

type myEnum string

const (
	albatross myEnum = "albatross"
	penguin   myEnum = "penguin"
	seagull   myEnum = "seagull"
)

func (e *myEnum) Scan(value interface{}) error {
	*e = myEnum(value.([]byte))
	return nil
}

func (e myEnum) Value() (driver.Value, error) {
	return string(e), nil
}
