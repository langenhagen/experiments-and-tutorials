// Showcase the parsing of timestamps in Golang
package main

import (
	"fmt"
	"time"
)

func main() {

	// from Python `dt.datetime.utcnow().replace(tzinfo=dt.timezone.utc).isoformat()` works
	str := "2021-10-21T15:11:09.154079+00:00"

	// from Python `dt.datetime.utcnow().isoformat()`  doesn't work
	// str := "2021-10-21T14:52:41.378694"

	t, err := time.Parse(time.RFC3339, str)

	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(t)
}
