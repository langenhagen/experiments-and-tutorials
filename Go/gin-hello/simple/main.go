// Gin-hello showcases the usage of the web framework `gin`.
// See: https://github.com/gin-gonic/gin#quick-start
//
// ping the endpoint e.g. via:
//
//   curl 'localhost:8080/ping'
//
package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})
	r.Run() // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
