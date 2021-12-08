// Showcase the `binding` feature from gin. based on:
// https://github.com/gin-gonic/gin#model-binding-and-validation
//
// Uage examples:
//
// 1. curl --data '{"user":"andi", "pass":"123"}' -X POST 'localhost:8080/login'
//
//    results: {"status":"you are logged in"}
//
// 2. curl --data '{"user":"carl"}' -X POST 'localhost:8080/login'
//
//    results: {"status":"unauthorized"}
//
// 3. curl --data '{"pass":"123"}' -X POST 'localhost:8080/login'
//
//   results: {"error":"Key: 'Login.User' Error:Field validation for 'User' failed on the 'required' tag"}
//
// Binding seems to be even more capable. See:
// https://blog.logrocket.com/gin-binding-in-go-a-tutorial-with-examples/
//
// However, `binding:"required"` would also fail when you give it values like 0, "", false.
// Apparently, one should then rather use a pointer type.
// Consider instead to use the package `validator`:
// https://github.com/go-playground/validator/blob/master/_examples/simple/main.go It seems much
// more capable.
package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// Binding from JSON
type Login struct {
	User string `json:"user" binding:"required"` // binding:"required" will also fire when the string is empty `""`
	// binding:"-" seems to be like not writing it at all
	// see: https://github.com/gin-gonic/gin#:~:text=the%20%27required%27%20tag%22%7D-,Skip%20validate,-When%20running%20the
	Password string `json:"pass" binding:"-"`
}

func main() {
	router := gin.Default()

	router.POST("/login", func(c *gin.Context) {
		var json Login
		if err := c.ShouldBindJSON(&json); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		if json.User != "andi" || json.Password != "123" {
			c.JSON(http.StatusUnauthorized, gin.H{"status": "unauthorized"})
			return
		}

		c.JSON(http.StatusOK, gin.H{"status": "you are logged in"})
	})

	router.Run(":8080")
}
