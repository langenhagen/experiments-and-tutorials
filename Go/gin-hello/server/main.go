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
	"os"
	"strconv"
	"time"

	"github.com/gin-gonic/gin"
)

func ping(c *gin.Context) {
	c.JSON(200, gin.H{"message": "pong"})
}

// Stream some numbers to the client.
func stream(c *gin.Context) {
	ch := make(chan int, 10)
	go func() {
		defer close(ch)
		for i := 0; i < 5; i++ {
			ch <- i
			time.Sleep(time.Second * 1)
		}
	}()
	c.Stream(func(w io.Writer) bool {
		msg, ok := <-ch
		if !ok {
			fmt.Println("stream end reached")
			return false
		}
		buf := bytes.NewBufferString(strconv.Itoa(msg))
		// apparently, the use of `...` below is like tuple unpacking in Python.
		// makes several byte params out of a []byte
		c.Writer.Write(append(buf.Bytes(), []byte("\n")...))

		return true
	})
}

// Stream some numbers to the client with SSEvents that apparently can send events.
func streamWithSSEvent(c *gin.Context) {
	ch := make(chan int, 10)
	go func() {
		defer close(ch)
		for i := 0; i < 5; i++ {
			ch <- i
			time.Sleep(time.Second * 1)
		}
	}()
	c.Stream(func(w io.Writer) bool {
		msg, ok := <-ch
		if !ok {
			fmt.Println("stream end reached")
			return false
		}
		c.SSEvent("message", msg)
		return true
	})
}

// Stream a file to the client.
func fileStream(c *gin.Context) {
	wd, err := os.Getwd()
	if err != nil {
		panic(err)
	}
	fmt.Print(wd)
	c.File(wd + "/file.txt")
}

func main() {
	r := gin.Default()
	r.GET("/ping", ping)
	r.GET("/stream", stream)
	r.GET("/ssevent", streamWithSSEvent)
	r.GET("/filestream", fileStream)

	r.Run() // listen and serve on 0.0.0.0:8080
}
