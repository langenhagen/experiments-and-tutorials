// Showcase the go standard library package `time`.
package main

import (
	"fmt"
	"time"
)

func createTimes() {
	now := time.Now()
	fmt.Printf("now:  %+v\n", now)

	// You can build a time struct by providing the year, month, day, etc. Times are always
	// associated with a Location, i.e. time zone.
	t := time.Date(2022, 01, 02, 18, 34, 58, 651387237, time.UTC)
	fmt.Printf("time: %+v\n", t)
}

// Parsing datetime in Golang is a bit uncanny, You have to provide a kind-of magic string to
// specify the format datetime format
func parseFromString() {
	// from Python `dt.datetime.utcnow().replace(tzinfo=dt.timezone.utc).isoformat()` works
	str := "2021-10-21T15:11:09.154079+00:00"

	// from Python `dt.datetime.utcnow().isoformat()`  doesn't work
	// str := "2021-10-21T14:52:41.378694"

	t, e := time.Parse(time.RFC3339, str)
	if e != nil {
		fmt.Println(e)
	}
	fmt.Println(t)
}

func convertToString() {
	t := time.Date(2021, 12, 23, 12, 03, 58, 0, time.Local)
	fmt.Printf("time:   %+v\n", t)

	s := t.String()
	fmt.Printf("string: %s\n", s)
}

func sleep() {
	fmt.Println("Going to sleep for 2 seconds")
	time.Sleep(2 * time.Second)
	fmt.Println("And waking up.")
}

func main() {
	fmt.Println("--- 1 create times ---")
	createTimes()

	fmt.Println("\n--- 2 string to time ---")
	parseFromString()

	fmt.Println("\n--- 3 time to string ---")
	convertToString()

	fmt.Println("\n--- 4 time.Sleep() ---")
	sleep()

}
