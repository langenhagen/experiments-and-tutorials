# Go Modules


based on a larger tutorial:
    - https://golang.org/doc/tutorial/create-module
    - https://golang.org/doc/tutorial/call-module-code
    - https://golang.org/doc/tutorial/handle-errors
    - https://golang.org/doc/tutorial/random-greeting
    - https://golang.org/doc/tutorial/greetings-multiple-people
    - https://golang.org/doc/tutorial/add-a-test
    - https://golang.org/doc/tutorial/compile-install

see also: https://www.golangprograms.com/naming-conventions-for-golang-functions.html


Steps:

1. create a module

```bash
mkdir greetings
cd greetings
go mod init example.com/greetings  # create the module "greetings"
touch greetings.go
```
Then, implement the file greetings.go

2. create client code

```bash
cd ..
mkdir hello
cd hello
go mod init example.com/hello
touch hello.go
```

3. Point example.com/greetings to its local path
From within subdir `hello/`, call:
```bash
go mod edit -replace=example.com/greetings=../greetings
```

4. Tidy Up
From within subdir `hello/`, call:
```bash
go mod tidy
```

5. Run the stuff
From within subdir `hello/`, call:
```bash
go run .
```

6. Add the test
```bash
cd greetings
touch greetings_test.go   # tests end with `_test.go`
```
Then, implement the file greetings_test.go

7. Run the tests
```bash
cd greetings
go test  # alternatively, call `go test -v`
```

8. Build the program
Create a classical executable via `go build`:
```bash
cd hello
go build
```

Run the program via
```bash
./hello
```


9. Install the proram

Discover the install path:
```bash
go list -f '{{.Target}}'  # something like $HOME/go/bin/hello
```

You can 1) add the install path to your $PATH or 2) change the install path:
1. add the install path to your `$PATH`
  ```bash
  export PATH=$PATH:/path/to/your/install/directory
  ```
OR
2. Change Go's install path to something you already have
  ```bash
  go env -w GOBIN=/path/to/your/bin
  ```

Then simply
```bash
go install
```
Then, you should be able, to call `hello` from anywhere.


Nice:
```bash
go env   # list go environment variables
```
