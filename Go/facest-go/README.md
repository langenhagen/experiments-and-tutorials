from https://github.com/facest/facest-go
I took it on 2021-03-20

[![Build Status](https://travis-ci.org/facest/facest-go.svg?branch=master)](https://travis-ci.org/facest/facest-go) [![Go Report Card](https://goreportcard.com/badge/facest/facest-go)](https://goreportcard.com/report/facest/facest-go)
[![Godoc](http://img.shields.io/badge/godoc-reference-blue.svg?style=flat)](https://godoc.org/github.com/facest/facest-go)

# facest-go

[Facest.io](https://facest.io) Go client.

## Install

```
go get -u github.com/facest/facest-go
```

## Usage

```go
import "github.com/facest/facest-go"

// Replace API_KEY with your real key
client := facest.NewClient("API_KEY")
```

## Testing

Run integration tests with real API Key.

```
export FACEST_INTEGRATION_API_KEY=
go test -v -tags=integration
```
