// author: andreasl

// Single-file Go interview refresher. The core Go sections are runnable; the
// Gin / GORM / third-party sections are real, syntax-highlighted code that
// references uninstalled packages, so the file as a whole does NOT compile -
// that is on purpose. Each snippet is valid Go you can copy out on its own.

// -------------------------------------------------------------------------------------------------
// CLI Commands
//
//   go run .                     run current package (or: go run main.go)
//   go build -o app .            compile to a named binary
//   go install                   build + install into $GOBIN
//
//   go mod init example.com/foo  start a module (creates go.mod)
//   go mod tidy                  add missing + drop unused deps
//   go get github.com/x/y@v1.2.3 add/upgrade a dependency
//
//   go test ./...                test all packages recursively
//   go test -run TestName -v     run one test, verbose
//   go test -race                enable the data-race detector
//   go test -cover               report coverage %
//   go test -bench=. -benchmem   run benchmarks + report allocations
//
//   go fmt ./...                 format (wraps gofmt); gofmt -w file.go
//   go vet ./...                 static checks for common mistakes
//   go doc fmt.Println           show docs for a symbol
//   GOOS=linux GOARCH=amd64 go build   cross-compile

package main

import (
	"context"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"net/http"
	"sort"
	"strconv"
	"sync"
	"time"

	// Third-party packages for the reference sections below - not installed, so
	// your editor marks them unresolved. Expected: real highlighted code, not a build.
	"github.com/davecgh/go-spew/spew"
	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"github.com/lib/pq"
	uuid "github.com/satori/go.uuid"
	log "github.com/sirupsen/logrus"
	"github.com/tidwall/gjson"
	"gorm.io/datatypes"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"moul.io/http2curl"
)

// -------------------------------------------------------------------------------------------------
// Strings

func stringsDemo() {
	// Raw string literals use backticks and keep escapes and newlines literal.
	raw := `no \ escapes, "quotes" and
	multiple lines are fine`

	// Interpreted string literals use double quotes and honor escapes.
	escaped := "line 1\nline 2\t(tab)"

	// A string is immutable UTF-8 bytes: index by byte, range by rune.
	s := "héllo"
	_ = len(s) // 6 bytes, not 5 characters
	for i, r := range s {
		_ = i // byte index
		_ = r // rune (Unicode code point)
	}

	// Convert between string, []byte and []rune explicitly.
	bs := []byte(s)
	rs := []rune(s) // len(rs) == 5

	// strconv converts between strings and numbers.
	n, _ := strconv.Atoi("42")
	txt := strconv.Itoa(n)
	f, _ := strconv.ParseFloat("3.14", 64)

	fmt.Println(raw, escaped, bs, rs, txt, f)
}

// -------------------------------------------------------------------------------------------------
// Variables And Constants

func variablesDemo() {
	// Short declaration infers the type; only valid inside functions.
	count := 42

	// Explicit type declaration.
	var name string = "Go"

	// Let the compiler infer the type from the initializer.
	var enabled = true

	// Zero values: 0, "", false, nil. There is no "undefined".
	var (
		i int    // 0
		s string // ""
		b bool   // false
		p *int   // nil
	)

	// Multiple assignment.
	x, y := 1, 2
	x, y = y, x // swap

	// Constants are compile-time; iota auto-increments within a const block.
	const Pi = 3.14
	const (
		A = iota // 0
		B        // 1
		C        // 2
	)
	const KB = 1 << (10 * (iota + 1)) // iota tricks for byte units

	fmt.Println(count, name, enabled, i, s, b, p, x, y, A, B, C, KB, Pi)
}

// -------------------------------------------------------------------------------------------------
// Control Flow

func controlFlowDemo() {
	// if may carry an init statement scoped to the if/else.
	if v := 10; v > 5 {
		fmt.Println("big")
	} else if v == 5 {
		fmt.Println("five")
	}

	// for is the only loop keyword: classic, while-style, and infinite.
	for i := 0; i < 3; i++ {
	}
	n := 3
	for n > 0 {
		n--
	}
	for {
		break
	}

	// range iterates slices, maps, strings and channels.
	for idx, val := range []string{"a", "b"} {
		_ = idx
		_ = val
	}

	// switch needs no break and does not fall through by default.
	switch n {
	case 0, 1:
		fmt.Println("small")
	case 2:
		fallthrough // explicit continue into the next case
	default:
		fmt.Println("other")
	}

	// A conditionless switch reads like an if/else chain.
	switch {
	case n < 0:
	default:
	}

	// Labels let break/continue target an outer loop.
outer:
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if i*j > 2 {
				break outer
			}
		}
	}
}

// -------------------------------------------------------------------------------------------------
// Functions

// Functions can return multiple values, including named results.
func divmod(a, b int) (q, r int) {
	q = a / b
	r = a % b
	return // naked return uses the named results
}

// Variadic parameters collect trailing args into a slice.
func sum(nums ...int) int {
	total := 0
	for _, n := range nums {
		total += n
	}
	return total // call: sum(1, 2, 3) or sum(slice...)
}

// Closures capture surrounding variables by reference.
func counter() func() int {
	i := 0
	return func() int { i++; return i }
}

// defer runs on function exit in LIFO order; its args are evaluated immediately.
func deferDemo() {
	defer fmt.Println("runs last")
	defer fmt.Println("runs first")
	i := 0
	defer fmt.Println("captured now:", i) // prints 0, not 10
	i = 10
	_ = i
}

// -------------------------------------------------------------------------------------------------
// Composite Types

func compositeDemo() {
	// Arrays are fixed-size value types (copied on assignment).
	arr := [...]int{1, 2, 3} // compiler counts the length

	// Slices are dynamic views over a backing array (len + cap).
	s := make([]int, 0, 4)
	s = append(s, 1, 2, 3)
	s = append(s, arr[:]...) // spread another slice

	// Slicing shares the backing array; copy() detaches it.
	sub := s[1:3]
	sub[0] = 99 // also mutates s[1]
	cp := make([]int, len(s))
	copy(cp, s)

	// Maps are reference types; a missing key returns the zero value.
	m := map[string]int{"a": 1}
	m["b"] = 2
	v, ok := m["c"] // comma-ok: v == 0, ok == false
	delete(m, "a")
	// A nil map reads fine but panics on write, so make() before writing.

	// Structs group fields; embedding gives composition and promotion.
	type Address struct{ City string }
	type User struct {
		Name    string
		Address          // embedded: u.City is promoted
		Tags    []string `json:"tags,omitempty"` // struct tag
	}
	u := User{Name: "Ada", Address: Address{City: "London"}}
	u.City = "Paris"
	pu := &u // pointer field access auto-dereferences (no ->)

	// Pointers pass by reference; Go has no pointer arithmetic.
	x := 5
	ptr := &x
	*ptr = 10

	fmt.Println(sub, cp, v, ok, pu.Name, u.City, x)
}

// -------------------------------------------------------------------------------------------------
// Interfaces And Methods

// An interface is a set of method signatures.
type Shape interface {
	Area() float64
}

type Rect struct{ W, H float64 }

// A value receiver works on both Rect and *Rect.
func (r Rect) Area() float64 { return r.W * r.H }

// A pointer receiver is required to mutate the receiver.
func (r *Rect) Scale(f float64) { r.W *= f; r.H *= f }

// Implementing String() satisfies fmt.Stringer for custom formatting.
func (r Rect) String() string { return fmt.Sprintf("Rect(%gx%g)", r.W, r.H) }

func interfaceDemo() {
	// Interface satisfaction is implicit: no "implements" keyword.
	var sh Shape = Rect{2, 3}
	fmt.Println(sh.Area())

	// any (alias for interface{}) holds a value of any type.
	var anything any = "hello"

	// A type assertion with comma-ok avoids a panic on mismatch.
	if str, ok := anything.(string); ok {
		fmt.Println(str)
	}

	// A type switch branches on the dynamic type.
	switch val := anything.(type) {
	case int:
		fmt.Println("int", val)
	case string:
		fmt.Println("string", val)
	default:
		fmt.Println("other")
	}

	// sort.Slice sorts in place with a less() closure.
	nums := []int{3, 1, 2}
	sort.Slice(nums, func(i, j int) bool { return nums[i] < nums[j] })

	// io.Reader / io.Writer are the most-used stdlib interfaces.
	var _ io.Reader
	var _ io.Writer
}

// -------------------------------------------------------------------------------------------------
// Generics

// A constraint is an interface listing allowed underlying types (~ = underlying).
// Also built-in: `any` (no constraint) and `comparable` (enables == and map keys).
type Number interface {
	~int | ~int64 | ~float64
}

// Type parameters (Go 1.18+) make one function work over many types.
func Sum[T Number](nums []T) T {
	var total T
	for _, n := range nums {
		total += n
	}
	return total
}

// Structs can be generic too.
type Stack[T any] struct{ items []T }

func (s *Stack[T]) Push(v T) { s.items = append(s.items, v) }
func (s *Stack[T]) Pop() (T, bool) {
	var zero T
	if len(s.items) == 0 {
		return zero, false
	}
	v := s.items[len(s.items)-1]
	s.items = s.items[:len(s.items)-1]
	return v, true
}

// -------------------------------------------------------------------------------------------------
// Errors

// A sentinel error is a package-level value compared with errors.Is.
var ErrNotFound = errors.New("not found")

// A custom error type carries data and is extracted with errors.As.
type ValidationError struct {
	Field string
	Msg   string
}

func (e *ValidationError) Error() string {
	return fmt.Sprintf("%s: %s", e.Field, e.Msg)
}

func findUser(id int) (string, error) {
	if id <= 0 {
		return "", &ValidationError{Field: "id", Msg: "must be positive"}
	}
	if id > 100 {
		// %w wraps an error, preserving the chain for Is/As.
		return "", fmt.Errorf("lookup id=%d: %w", id, ErrNotFound)
	}
	return "Ada", nil
}

func errorsDemo() {
	_, err := findUser(200)
	if err != nil {
		// errors.Is matches a wrapped sentinel anywhere in the chain.
		if errors.Is(err, ErrNotFound) {
			fmt.Println("no such user")
		}
		// errors.As extracts a concrete type from the chain.
		var ve *ValidationError
		if errors.As(err, &ve) {
			fmt.Println("bad field:", ve.Field)
		}
	}

	// panic/recover is for truly exceptional cases, not normal control flow.
	defer func() {
		if r := recover(); r != nil {
			fmt.Println("recovered:", r)
		}
	}()
}

// -------------------------------------------------------------------------------------------------
// Concurrency

func concurrencyDemo() {
	// A goroutine is a cheap runtime-scheduled thread started with `go`.
	go fmt.Println("async")

	// Channels are typed conduits; unbuffered means a synchronous handoff.
	ch := make(chan int)
	go func() {
		ch <- 42  // send
		close(ch) // the sender closes, never the receiver
	}()
	v, ok := <-ch // ok == false once closed and drained
	fmt.Println(v, ok)

	// range over a channel drains it until it is closed.
	buf := make(chan int, 3)
	go func() {
		for i := 0; i < 3; i++ {
			buf <- i
		}
		close(buf)
	}()
	for x := range buf {
		_ = x
	}

	// select waits on multiple channel operations.
	c1 := make(chan int)
	select {
	case x := <-c1:
		fmt.Println(x)
	case <-time.After(time.Millisecond):
		fmt.Println("timeout")
	default:
		fmt.Println("nothing ready") // non-blocking branch
	}

	// WaitGroup waits for a set of goroutines to finish.
	var wg sync.WaitGroup
	for i := 0; i < 3; i++ {
		wg.Add(1)
		go func(id int) {
			defer wg.Done()
			_ = id
		}(i) // pass loop var as an arg to be safe on any Go version
	}
	wg.Wait()

	// Mutex guards shared state; sync.Once runs a func exactly once.
	var mu sync.Mutex
	mu.Lock()
	mu.Unlock()
	var once sync.Once
	once.Do(func() {})
	// Always test concurrent code with:  go test -race
}

// context carries cancellation, deadlines and request-scoped values.
func contextDemo() {
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel() // always cancel to release resources

	select {
	case <-ctx.Done():
		fmt.Println("cancelled:", ctx.Err()) // Canceled or DeadlineExceeded
	case <-time.After(time.Millisecond):
		fmt.Println("done")
	}
}

// A worker pool has fixed goroutines consuming a jobs channel.
func workerPool() {
	jobs := make(chan int, 10)
	results := make(chan int, 10)
	var wg sync.WaitGroup

	for w := 0; w < 3; w++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			for j := range jobs {
				results <- j * 2
			}
		}()
	}
	for i := 0; i < 5; i++ {
		jobs <- i
	}
	close(jobs)
	go func() { wg.Wait(); close(results) }()
	for r := range results {
		_ = r
	}
}

// -------------------------------------------------------------------------------------------------
// net/http And encoding/json

// Struct tags control JSON field names and behavior.
type Payload struct {
	Name  string `json:"name"`
	Email string `json:"email,omitempty"` // omit when empty
	Age   int    `json:"-"`               // never serialized
}

func jsonDemo() {
	// Marshal turns a value into JSON bytes.
	data, _ := json.Marshal(Payload{Name: "Ada", Email: "a@b.c"})

	// Unmarshal fills a value; pass a pointer.
	var p Payload
	_ = json.Unmarshal([]byte(`{"name":"Bob"}`), &p)

	// Arbitrary JSON decodes into map[string]any.
	var generic map[string]any
	_ = json.Unmarshal(data, &generic)

	fmt.Println(string(data), p, generic)
}

func httpServerDemo() {
	mux := http.NewServeMux()

	// A handler receives the response writer and the request.
	mux.HandleFunc("/hello", func(w http.ResponseWriter, r *http.Request) {
		name := r.URL.Query().Get("name") // query parameter
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(map[string]string{"hello": name})
	})

	// Decode a JSON request body straight from the stream.
	mux.HandleFunc("/users", func(w http.ResponseWriter, r *http.Request) {
		var p Payload
		if err := json.NewDecoder(r.Body).Decode(&p); err != nil {
			http.Error(w, err.Error(), http.StatusBadRequest)
			return
		}
	})

	srv := &http.Server{Addr: ":8080", Handler: mux, ReadTimeout: 5 * time.Second}
	_ = srv.ListenAndServe()
}

func httpClientDemo() {
	// Always set a timeout and close the response body.
	client := &http.Client{Timeout: 10 * time.Second}
	resp, err := client.Get("https://example.com")
	if err != nil {
		return
	}
	defer resp.Body.Close()
	body, _ := io.ReadAll(resp.Body)
	_ = body
}

// -------------------------------------------------------------------------------------------------
// Testing  (lives in *_test.go, package foo; kept commented so it does not
// clash with this file's package main)
/*
// Table-driven tests are the idiomatic Go pattern.
func TestSum(t *testing.T) {
	tests := []struct {
		in   []int
		want int
	}{
		{nil, 0},
		{[]int{1, 2, 3}, 6},
	}
	for _, tc := range tests {
		t.Run("", func(t *testing.T) { // subtest: isolated + named
			if got := Sum(tc.in); got != tc.want {
				t.Errorf("Sum(%v) = %d, want %d", tc.in, got, tc.want)
			}
		})
	}
}

func BenchmarkSum(b *testing.B) { for i := 0; i < b.N; i++ { Sum([]int{1, 2, 3}) } } // b.N auto-tuned

// httptest exercises handlers without a network: httptest.NewRecorder() + NewRequest(...).
// testify (github.com/stretchr/testify): assert.Equal (continues) / require.NoError (aborts).
*/

// -------------------------------------------------------------------------------------------------
// Gin  (github.com/gin-gonic/gin)

// binding tags reuse the validator engine for request validation.
type CreateUser struct {
	Name  string `json:"name" binding:"required"`
	Email string `json:"email" binding:"required,email"`
}

func ginRouter() *gin.Engine {
	r := gin.Default() // Logger + Recovery middleware included

	// Custom middleware runs around downstream handlers.
	r.Use(func(c *gin.Context) {
		c.Next() // or c.Abort() / c.AbortWithStatusJSON(...) to stop
	})

	// Path params use :name; read query params with c.Query.
	r.GET("/users/:id", func(c *gin.Context) {
		id := c.Param("id")
		c.JSON(http.StatusOK, gin.H{"id": id})
	})

	// ShouldBindJSON decodes the body and runs binding validation.
	r.POST("/users", func(c *gin.Context) {
		var body CreateUser
		if err := c.ShouldBindJSON(&body); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusCreated, body)
	})

	// Route groups share a prefix and middleware.
	api := r.Group("/api/v1")
	api.GET("/me", func(c *gin.Context) {})
	return r
}

// In a real program this is main(); renamed to avoid a second main() here.
func ginServe() { ginRouter().Run(":8080") } // r.Run wraps http.ListenAndServe

// Test a handler by serving into a recorder:
//   w := httptest.NewRecorder()
//   req, _ := http.NewRequest("GET", "/users/1", nil)
//   ginRouter().ServeHTTP(w, req); assert.Equal(t, 200, w.Code)

// -------------------------------------------------------------------------------------------------
// GORM  (gorm.io/gorm + a driver, e.g. gorm.io/driver/postgres)

// gorm.Model embeds ID, CreatedAt, UpdatedAt, DeletedAt (soft delete).
type GormUser struct {
	gorm.Model
	Name  string `gorm:"size:100;not null"`
	Email string `gorm:"uniqueIndex"`
	Posts []Post // has-many association
}

type Post struct {
	gorm.Model
	Title  string
	UserID uint // belongs-to foreign key (convention: <Owner>ID)
}

func gormRun() {
	dsn := "host=localhost user=u password=p dbname=d port=5432"
	db, _ := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	db.AutoMigrate(&GormUser{}, &Post{}) // create/alter tables from structs

	// Create.
	u := GormUser{Name: "Ada", Email: "a@b.c"}
	db.Create(&u) // u.ID is populated afterwards

	// Read: First by primary key or condition (? args are escaped).
	var first GormUser
	db.First(&first, 1)
	var users []GormUser
	db.Where("name LIKE ?", "A%").Find(&users)

	// Update: struct skips zero fields; map includes them.
	db.Model(&u).Updates(GormUser{Name: "Ada L."})
	db.Model(&u).Updates(map[string]any{"name": "X"})

	// Delete is a soft delete because gorm.Model has DeletedAt.
	db.Delete(&u, 1)
	db.Unscoped().Delete(&u, 1) // hard delete

	// Preload eager-loads an association; Transaction wraps a unit of work.
	db.Preload("Posts").First(&first, 1)
	db.Transaction(func(tx *gorm.DB) error {
		return tx.Create(&Post{Title: "t"}).Error // non-nil error rolls back
	})

	// Raw SQL escape hatch.
	var count int64
	db.Raw("SELECT count(*) FROM users").Scan(&count)
}

// -------------------------------------------------------------------------------------------------
// Other Repo Libraries

// go-playground/validator/v10 validates structs via `validate` tags.
type SignUp struct {
	Name  string `validate:"required"`
	Email string `validate:"required,email"`
	Age   uint8  `validate:"gte=0,lte=130"`
}

// lib/pq exposes Postgres-specific column types, e.g. text[] arrays in GORM.
type Robot struct {
	Roles pq.StringArray `gorm:"type:varchar(50)[]"`
}

// gorm.io/datatypes adds JSON (and other) column types to GORM.
type Box struct {
	Metadata datatypes.JSON
}

// Reuse a single validator instance; it caches struct info.
var validate = validator.New()

func thirdPartyDemos() {
	// validator returns nil or a validator.ValidationErrors slice.
	err := validate.Struct(SignUp{})

	// tidwall/gjson reads values from JSON without a struct, via dot paths.
	s := `{"nested":{"name":"x"},"values":[1,2]}`
	gjson.Get(s, "nested.name").String()
	gjson.Get(s, "values.1").Int()          // index into an array
	gjson.Get(s, "friends.#(age>18)#.name") // filter + collect

	// satori/go.uuid generates and parses UUIDs (google/uuid is the alternative).
	id := uuid.NewV4() // satori: no error return
	parsed, _ := uuid.FromString("11111111-1111-4111-8111-111111111111")
	_ = parsed
	// google/uuid: uuid.New(), uuid.Parse(s)

	// davecgh/go-spew deep-prints values for debugging.
	spew.Dump(id)

	// sirupsen/logrus is structured leveled logging.
	log.WithFields(log.Fields{"id": 7}).Info("logged in")
	log.WithError(err).Error("failed") // Debug/Info/Warn/Error/Fatal

	// lib/pq array predicate (column types shown in the structs above).
	var db *gorm.DB
	db.Where("? = ANY(roles)", "admin")

	// moul.io/http2curl turns an *http.Request into a copy-pasteable curl string.
	req, _ := http.NewRequest("GET", "https://example.com", nil)
	cmd, _ := http2curl.GetCurlCommand(req)
	_ = cmd

	// jinzhu/gorm is the LEGACY (pre-gorm.io) API:
	//   db.CreateTable(&X{}); db.DropTable(&X{}); dialects/postgres.Jsonb
	// Modern code uses gorm.io/gorm. The two APIs differ; don't mix them.

	// Recognition-only (common in larger services, not needed at cheatsheet depth):
	//   go.opentelemetry.io/otel   tracing/metrics; otelgin + otelhttp middleware
	//   prometheus/client_golang   promhttp.Handler() on /metrics
	//   aws-sdk-go-v2/service/s3   s3.NewFromConfig(cfg); manager Uploader
	//   coreos/go-oidc + x/oauth2  OIDC login + ID-token verification
	//   tkanos/gonfig              gonfig.GetConf("config.json", &cfg)
}

// -------------------------------------------------------------------------------------------------
// Gotchas  (fast scan)
//
//   - Loop var capture: Go 1.22+ gives each iteration a fresh var; older Go
//     shared it, so closures/goroutines saw the final value. When unsure, pass
//     it as an arg or shadow with `v := v`.
//   - nil slice vs empty: both len 0; JSON encodes nil as null, []T{} as [].
//   - nil map: reading is fine, writing panics -> make() before writing.
//   - An interface holding a nil pointer is NOT == nil (it has a type).
//   - defer in a loop stacks until the function returns; wrap the body in a func.
//   - `:=` in an inner scope shadows the outer var (classic err-shadow bug).
//   - Unbuffered channel with no counterpart deadlocks (fatal).
//   - Slicing shares the backing array; copy() to detach.
//   - Map iteration order is randomized; never rely on it.

// Reference file: the *Demo funcs are meant to be read/copied, not run. Every
// section function stands alone; main just needs one call to be a valid entry.
func main() {
	stringsDemo()
}
