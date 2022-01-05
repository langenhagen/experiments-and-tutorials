// Gin-hello showcases the usage of the web framework `gin`.
// See: https://github.com/gin-gonic/gin#quick-start
//
// And for streaming responses:
// https://stackoverflow.com/questions/44825244/how-to-write-a-stream-api-using-gin-gonic-server-in-golang-tried-c-stream-didnt
//
// ping the endpoint e.g. via:
//
//   curl 'localhost:8080/ping'
//
package main

import (
	"bytes"
	"fmt"
	"io"
	"time"

	"github.com/gin-gonic/gin"
)

func ping(c *gin.Context) {
	c.JSON(200, gin.H{"message": "pong"})
}

func stream(c *gin.Context) {
	stream := make(chan int, 10)
	go func() {
		defer close(stream)
		for i := 0; i < 5; i++ {
			stream <- i
			time.Sleep(time.Second * 1)
		}
	}()
	c.Stream(func(w io.Writer) bool {
		msg, ok := <-stream
		if !ok {
			fmt.Println("stream end reached")
			return false
		}
		buf := bytes.NewBufferString(fmt.Sprintf("%d", msg))
		// apparently, the use of `...` below is like tuple unpacking in Python.
		// makes several byte params out of a []byte
		c.Writer.Write(append(buf.Bytes(), []byte("\n")...))

		// c.SSEvent("message", msg)  // that is also something
		return true
	})
}

// something similar to `stream()`
func streamWithSSEvent(c *gin.Context) {
	stream := make(chan int, 10)
	go func() {
		defer close(stream)
		for i := 0; i < 5; i++ {
			stream <- i
			time.Sleep(time.Second * 1)
		}
	}()
	c.Stream(func(w io.Writer) bool {
		msg, ok := <-stream
		if !ok {
			fmt.Println("stream end reached")
			return false
		}
		c.SSEvent("message", msg) // that is also something
		return true
	})
}

func main() {
	r := gin.Default()
	r.GET("/ping", ping)
	r.GET("/stream", stream)
	r.GET("/ssevent", streamWithSSEvent)

	r.Run() // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
