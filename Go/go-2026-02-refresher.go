// go-2026-refresher.go
//
// Intent
//   - Provide a single-file, interview-focused Go refresher you can skim and run.
//   - Cover common topics in one pass: structs/methods, errors, JSON, generics,
//     interfaces, slices/maps, and goroutines/channels/context.
//   - Keep each section small, executable, and easy to discuss out loud.
//
// How To Use
//
//	go run go-2026-refresher.go
package main

import (
	"context"
	"encoding/json"
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"sort"
	"strconv"
	"strings"
	"sync"
	"time"
)

///////////////////////////////////////////////////////////////////////////////
// PROGRAM STYLE NOTE
// This file is intentionally written like a step-by-step script.
// main() runs numbered sections in sequence.
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// GO SYNTAX QUICK MAP (PYTHON/C++ TO GO)
// - var x T = ...     -> explicit variable declaration
// - x := ...          -> short declaration with inferred type
// - func (...) ...    -> function declaration
// - *T / &x           -> pointer type / address-of value
// - if err != nil     -> explicit error handling style
// - defer f()         -> run f when current function returns
// - go f()            -> launch goroutine
// - ch <- x / <-ch    -> send to channel / receive from channel
// - any / comparable  -> built-in generic constraints
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// FEATURE TIMELINE (WHAT THIS FILE USES)
// Go 1.13: errors.Is / errors.As
// Go 1.18: generics
// Go 1.20: errors.Join
// Go 1.x: goroutines/channels/defer/interfaces/json/context/embedding
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// SMALL OUTPUT HELPER
///////////////////////////////////////////////////////////////////////////////

func printSection(title string) {
	fmt.Printf("\n--- %s ---\n\n", title)
}

///////////////////////////////////////////////////////////////////////////////
// CORE TYPES: METHODS, EMBEDDING, POINTER RECEIVERS
///////////////////////////////////////////////////////////////////////////////

type Audit struct {
	CreatedAt time.Time
}

type Task struct {
	Audit
	Name     string            `json:"name"`
	Priority int               `json:"priority"`
	Group    string            `json:"group"`
	Tags     []string          `json:"tags,omitempty"`
	Note     *string           `json:"note,omitempty"`
	Meta     map[string]string `json:"meta,omitempty"`
}

func (t Task) Lane() string {
	switch {
	case t.Priority >= 6:
		return "pager"
	case t.Priority >= 5:
		return "critical"
	case t.Priority >= 4:
		return "high"
	case t.Priority >= 3:
		return "medium"
	case t.Priority >= 1:
		return "low"
	default:
		return "none"
	}
}

func (t *Task) Bump(delta int) {
	t.Priority += delta
}

///////////////////////////////////////////////////////////////////////////////
// INTERFACES + GENERICS
///////////////////////////////////////////////////////////////////////////////

type Prioritized interface {
	GetPriority() int
}

func (t Task) GetPriority() int {
	return t.Priority
}

func Sum[T ~int | ~int64 | ~float64](values []T) T {
	var total T
	for _, v := range values {
		total += v
	}
	return total
}

func MaxPriority[T Prioritized](items []T) int {
	if len(items) == 0 {
		return 0
	}
	max := items[0].GetPriority()
	for _, item := range items[1:] {
		if p := item.GetPriority(); p > max {
			max = p
		}
	}
	return max
}

///////////////////////////////////////////////////////////////////////////////
// PARSING + HELPERS
///////////////////////////////////////////////////////////////////////////////

var ErrBadRecord = errors.New("bad record")

func parseLine(line string) (Task, error) {
	parts := strings.Split(line, ",")
	if len(parts) != 3 {
		return Task{}, fmt.Errorf("%w: expected 3 fields", ErrBadRecord)
	}

	priority, err := strconv.Atoi(strings.TrimSpace(parts[1]))
	if err != nil {
		return Task{}, fmt.Errorf("%w: priority parse: %v", ErrBadRecord, err)
	}

	t := Task{
		Audit:    Audit{CreatedAt: time.Now()},
		Name:     strings.TrimSpace(parts[0]),
		Priority: priority,
		Group:    strings.TrimSpace(parts[2]),
		Meta:     map[string]string{"source": "csv"},
	}

	if strings.HasPrefix(t.Name, "build") {
		note := "hot path"
		t.Note = &note
	}

	return t, nil
}

func buildDemoTasks(lines []string) ([]Task, error) {
	tasks := make([]Task, 0, len(lines))
	var allErr error

	for _, line := range lines {
		t, err := parseLine(line)
		if err != nil {
			allErr = errors.Join(allErr, fmt.Errorf("line %q: %w", line, err))
			continue
		}
		tasks = append(tasks, t)
	}

	return tasks, allErr
}

func groupBy(items []Task) map[string][]Task {
	groups := make(map[string][]Task)
	for _, item := range items {
		groups[item.Group] = append(groups[item.Group], item)
	}
	return groups
}

func prettyJSON(v any) string {
	b, err := json.MarshalIndent(v, "", "  ")
	if err != nil {
		return fmt.Sprintf("marshal error: %v", err)
	}
	return string(b)
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 1: BASICS, VALUES VS POINTERS, SLICES, MAPS
///////////////////////////////////////////////////////////////////////////////

func section1Basics() {
	printSection("1 Basics, Values, Slices, Maps")

	const demoCount = 6
	fmt.Println("demo lines configured:", demoCount)

	lines := []string{
		"build-core,5,core",
		"test-smoke,3,qa",
		"package,4,ops",
		"deploy,2,ops",
		"bench,6,core",
		"broken_line",
	}

	tasks, err := buildDemoTasks(lines)
	if err != nil {
		fmt.Println("parse issues encountered:", err)
	}

	if len(tasks) == 0 {
		fmt.Println("no valid tasks")
		return
	}

	copyValue := tasks[0]
	copyValue.Bump(100)
	fmt.Printf("value copy bump: original=%d copy=%d\n", tasks[0].Priority, copyValue.Priority)

	tasks[0].Bump(1)
	fmt.Println("pointer-receiver update in slice:", tasks[0].Priority)

	sort.Slice(tasks, func(i, j int) bool {
		return tasks[i].Priority > tasks[j].Priority
	})

	groups := groupBy(tasks)
	fmt.Println("tasks parsed:", len(tasks), "groups:", len(groups))

	for group, bucket := range groups {
		fmt.Printf("group=%s count=%d\n", group, len(bucket))
	}
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 2: CONTROL FLOW, DEFER, ERROR WRAPPING
///////////////////////////////////////////////////////////////////////////////

func openAndDescribe(path string) (string, error) {
	f, err := os.Open(path)
	if err != nil {
		return "", fmt.Errorf("open %s: %w", path, err)
	}
	defer f.Close()

	info, err := f.Stat()
	if err != nil {
		return "", fmt.Errorf("stat %s: %w", path, err)
	}

	return fmt.Sprintf("name=%s size=%d", info.Name(), info.Size()), nil
}

func section2ErrorsAndDefer() {
	printSection("2 Control Flow, Defer, Errors")

	_, err := openAndDescribe("definitely_missing.file")
	if err != nil {
		fmt.Println("wrapped error:", err)
		if errors.Is(err, os.ErrNotExist) {
			fmt.Println("errors.Is: file does not exist")
		}
	}

	_, parseErr := parseLine("oops")
	if parseErr != nil && errors.Is(parseErr, ErrBadRecord) {
		fmt.Println("custom sentinel match via errors.Is")
	}
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 3: INTERFACES + GENERICS
///////////////////////////////////////////////////////////////////////////////

func section3InterfacesAndGenerics() {
	printSection("3 Interfaces And Generics")

	tasks := []Task{
		{Name: "a", Priority: 2},
		{Name: "b", Priority: 7},
		{Name: "c", Priority: 4},
	}

	max := MaxPriority(tasks)
	fmt.Println("max priority:", max)

	ints := []int{1, 2, 3, 4, 5}
	floats := []float64{1.5, 2.5, 3.0}
	fmt.Println("sum ints:", Sum(ints), "sum floats:", Sum(floats))
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 4: STRUCT TAGS + JSON + OPTIONAL FIELDS
///////////////////////////////////////////////////////////////////////////////

func section4JSON() {
	printSection("4 Struct Tags, JSON, Optional Fields")

	note := "watch latency"
	t := Task{
		Audit:    Audit{CreatedAt: time.Now()},
		Name:     "serve-api",
		Priority: 5,
		Group:    "core",
		Tags:     []string{"http", "prod"},
		Note:     &note,
		Meta:     map[string]string{"owner": "platform"},
	}

	encoded := prettyJSON(t)
	fmt.Println(encoded)

	var decoded Task
	if err := json.Unmarshal([]byte(encoded), &decoded); err != nil {
		fmt.Println("unmarshal error:", err)
		return
	}

	fmt.Printf("decoded name=%s lane=%s created=%s\n", decoded.Name, decoded.Lane(), decoded.CreatedAt.Format(time.RFC3339))
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 5: CONCURRENCY (GOROUTINES, CHANNELS, CONTEXT)
///////////////////////////////////////////////////////////////////////////////

func producer(ctx context.Context, out chan<- int, n int) {
	defer close(out)
	for i := 1; i <= n; i++ {
		select {
		case <-ctx.Done():
			return
		case out <- i:
		}
	}
}

func worker(ctx context.Context, in <-chan int, out chan<- int, wg *sync.WaitGroup) {
	defer wg.Done()
	for {
		select {
		case <-ctx.Done():
			return
		case v, ok := <-in:
			if !ok {
				return
			}
			out <- v * v
		}
	}
}

func section5Concurrency() {
	printSection("5 Concurrency: Goroutines, Channels, Context")

	ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
	defer cancel()

	nums := make(chan int)
	squares := make(chan int)

	go producer(ctx, nums, 5)

	var wg sync.WaitGroup
	wg.Add(1)
	go worker(ctx, nums, squares, &wg)

	go func() {
		wg.Wait()
		close(squares)
	}()

	total := 0
	for s := range squares {
		total += s
	}

	fmt.Println("sum of squares 1..5:", total)
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 6: PATH/FILESYSTEM BASICS
///////////////////////////////////////////////////////////////////////////////

func section6Filesystem() {
	printSection("6 Filesystem Basics")

	cwd, err := os.Getwd()
	if err != nil {
		fmt.Println("getwd error:", err)
		return
	}

	base := filepath.Base(cwd)
	joined := filepath.Join(cwd, "tmp", "demo.txt")
	fmt.Println("cwd base:", base)
	fmt.Println("joined path:", joined)
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 7: COMMON INTERVIEW TOPICS CHECKLIST
///////////////////////////////////////////////////////////////////////////////

func section7InterviewChecklist() {
	printSection("7 Interview Checklist")

	items := []string{
		"values vs pointers",
		"slices capacity and append behavior",
		"maps zero values and key existence idiom",
		"interfaces and method sets",
		"goroutine leaks, cancellation, and channel ownership",
		"error wrapping with %w and errors.Is",
		"JSON tags and omitempty",
		"race detector: go test -race",
	}

	for i, item := range items {
		fmt.Printf("%d) %s\n", i+1, item)
	}
}

func main() {
	start := time.Now()
	fmt.Println("Dense Go refresher, organized as numbered sections.")

	section1Basics()
	section2ErrorsAndDefer()
	section3InterfacesAndGenerics()
	section4JSON()
	section5Concurrency()
	section6Filesystem()
	section7InterviewChecklist()

	fmt.Printf("\nRound-trip complete in %s.\n", time.Since(start).Round(time.Microsecond))
}
