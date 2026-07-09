// A terse Golang Chear Sheet
//
// CLI:  go run .          go mod init x / go mod tidy    go get github.com/x/y@v1.2.3
//       go test ./...     go test -run TestX -v          go test -race     go test -cover
//       gofmt -w f.go     go vet ./...
//
// author: andreasl

package main

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"
	"net/http/httptest"
	"strconv"
	"strings"
	"sync"
	"testing"

	"github.com/gin-gonic/gin"
)

type User struct {
	Name string `json:"name" binding:"required,max=32"` // required + length rule; eval'ed by ShouldBind
	Age  int    `json:"age" binding:"gte=16"`           // gt/gte/lt/lte eq/ne, len, min/max, oneof=a b c
}

func router() *gin.Engine {
	r := gin.Default() // Logger + Recovery middleware
	r.GET("/users/:id", func(c *gin.Context) {
		id := c.Param("id")           // path param  /users/7 -> "7"
		q := c.DefaultQuery("v", "1") // query param ?v=2, else default
		c.JSON(http.StatusOK, gin.H{"id": id, "v": q})
	})
	r.POST("/users", func(c *gin.Context) {
		var u User
		if err := c.ShouldBindJSON(&u); err != nil { // decodes body + runs binding tags; the one error case
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusCreated, u)
	})
	return r
}

func main() { router().Run(":8080") } // wraps http.ListenAndServe

// Test (lives in *_test.go). Serve requests into a recorder, assert the code.
func TestPostUsers(t *testing.T) {
	gin.SetMode(gin.TestMode)
	cases := []struct {
		body string
		want int
	}{
		{`{"name":"Ada","age":23}`, 201},
		{`{"name":"Ada","age":15}`, 400}, // fails binding -> ShouldBindJSON errors
	}
	for _, tc := range cases {
		req, _ := http.NewRequest("POST", "/users", strings.NewReader(tc.body))
		req.Header.Set("Content-Type", "application/json")
		w := httptest.NewRecorder()
		router().ServeHTTP(w, req)
		if w.Code != tc.want {
			t.Errorf("body %s: got %d want %d", tc.body, w.Code, tc.want)
		}
	}
}

var errNotFound = errors.New("not found")

const pi = 3.14

func quickRef() {
	b, _ := json.Marshal(User{Name: "Ada", Age: 23}) // JSON: marshal a value; unmarshal into a pointer
	var u User
	_ = json.Unmarshal(b, &u)

	n, _ := strconv.Atoi("42") // strconv: string <-> number
	s := strconv.Itoa(n)
	f, _ := strconv.ParseFloat("3.14", 64)

	xs := append(make([]int, 0, 4), 1, 2, 3) // slices grow with append; copy() detaches from the shared backing array
	cp := make([]int, len(xs))
	copy(cp, xs[1:3])

	m := map[string]int{"a": 1} // map;   reading/deleting from an empty aka nil map is ok but panics on write
	v, ok := m["b"]             // comma-ok read; v==0, ok==false when key absent
	delete(m, "a")              // remove a key

	str := "héllo"    // strings: len is bytes (6 here, not 5); range yields runes
	rs := []rune(str) // len 5

	err := fmt.Errorf("load: %w", errNotFound) // errors: wrap with %w, match with errors.Is / extract type with errors.As
	_ = errors.Is(err, errNotFound)

	var wg sync.WaitGroup // goroutines + buffered channel + WaitGroup
	ch := make(chan int, 3)
	for i := 0; i < 3; i++ {
		wg.Add(1)
		go func(i int) { defer wg.Done(); ch <- i * 2 }(i)
	}
	wg.Wait()
	close(ch)
	fmt.Println(s, f, cp, v, ok, rs, u)
}
