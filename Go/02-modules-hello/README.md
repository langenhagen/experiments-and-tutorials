# Go Modules


based on: https://golang.org/doc/tutorial/create-module

see also: https://www.golangprograms.com/naming-conventions-for-golang-functions.html


Steps:

1. create a module

```bash
mkdir greetings
cd greetings
go mod init example.com/greetings  # create the module "command"
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