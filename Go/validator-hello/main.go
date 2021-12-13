// Showcase the usage of the 3rd party package `validator`
// https://github.com/go-playground/validator for validation of struct objects.
//
// Code taken from: https://github.com/go-playground/validator/blob/master/_examples/simple/main.go
package main

import (
	"fmt"

	"github.com/go-playground/validator/v10"
)

// User contains user information
type User struct {
	FirstName      string     `validate:"required"`
	LastName       string     `validate:"required"`
	Age            uint8      `validate:"gte=0,lte=130"`
	Email          string     `validate:"required,email"`
	FavouriteColor string     `validate:"iscolor"`       // alias for 'hexcolor|rgb|rgba|hsl|hsla'
	Addresses      []*Address `validate:"required,dive"` // a person can have a home and cottage...
}

// Address houses a users address information
type Address struct {
	Street string `validate:"required"`
	City   string `validate:"required"`
	Planet string `validate:"required"`
	Phone  string `validate:"required"`
}

// use a single instance of Validate, it caches struct info
var validate *validator.Validate

func validateStruct() {
	address := &Address{
		Street: "Eavesdown Docks",
		Planet: "Persphone",
		Phone:  "none",
	}
	user := &User{
		FirstName:      "Badger",
		LastName:       "Smith",
		Age:            135,
		Email:          "Badger.Smith@gmail.com",
		FavouriteColor: "#000-",
		Addresses:      []*Address{address},
	}

	// returns nil or ValidationErrors ( []FieldError )
	err := validate.Struct(user)
	if err != nil {
		// this check is only needed when your code could produce
		// an invalid value for validation such as interface with nil values
		// most including myself do not usually have code like this.
		if _, ok := err.(*validator.InvalidValidationError); !ok {
			fmt.Printf("Error: %v:\n", err)
			// return
		}

		// loop through all validation errors and print some infZZZ
		for _, err := range err.(validator.ValidationErrors) {
			fmt.Printf("Validation Error: %v\n", err)
			fmt.Printf("  Namespace: %s\n", err.Namespace())
			fmt.Printf("  StructNamespace: %s\n", err.StructNamespace())
			fmt.Printf("  Field: %s\n", err.Field())
			fmt.Printf("  StructField: %s\n", err.StructField())
			fmt.Printf("  Tag: %s\n", err.Tag())
			fmt.Printf("  ActualTag: %s\n", err.ActualTag())
			fmt.Printf("  Kind: %s\n", err.Kind())
			fmt.Printf("  Type: %s\n", err.Type())
			fmt.Printf("  Value: %s\n", err.Value())
			fmt.Printf("  Param: %s\n", err.Param())
			fmt.Println()
		}

		// here you can have your own checks and whatnot

		return
	}

	// from here all is good
}

func validateVariable() {

	myEmail := "joeybloggs.gmail.com"

	errs := validate.Var(myEmail, "required,email")
	if errs != nil {
		fmt.Println(errs) // output: Key: "" Error:Field validation for "" failed on the "email" tag
		return
	}

	// email ok, move on
}

func main() {

	validate = validator.New()

	fmt.Println("\n--- 1 structs ---")
	validateStruct()

	fmt.Println("\n--- 2 variables ---")
	validateVariable()
}
