# Hello Go
My, Andreas Langenhagen's, first project in the Go Programming Language.

Apparently, `Go` is a very convenient programming language.

Based on: https://golang.org/doc/tutorial/getting-started

1. create the file `go.mod` via:
```bash
go mod init hello
```

`go.mod` is basically like a `requirements.txt` or `setup.py` stuff.

2. write the code

3. run the function `main()` function of the package `main` via:
```bash
go run .
```

4. then add the 3rd party stuff to your code and update the file `go.mod` and create or update the
   file `go.sum` conveniently via:
```bash
go mod tidy
```

`go.sum` is an equivalent to `pipenv.lock` or `poetry.lock` files in python, only. eh.. better xD
Apparently, external packages or maybe stuff that goes into the file `go.mod` with the keyword
`require` will result in having a file `go.sum`.


## Other nice stuff

- run `go help` to list go commands and short help
- `https://pkg.go.dev/` is the 3rd party package repository for Go, similar to PyPi for Python
