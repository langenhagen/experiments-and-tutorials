# gRPC Hello

- Quickstart: https://grpc.io/docs/languages/python/quickstart/
- Introduction: https://grpc.io/docs/what-is-grpc/introduction/
- Concepts: https://grpc.io/docs/what-is-grpc/core-concepts/

- Performance best practices: https://grpc.io/docs/guides/performance/

Key Points from the Introduction:

`gRPC` can use `protocol` buffers as both its Interface Definition Language (IDL) and as its
underlying message interchange format.

By default, `gRPC` uses `Protocol Buffers`, Google’s open source mechanism for serializing
structured data (although it can be used with other data formats such as `JSON`).

---



## Tooling

There is a protobuf linter:
```bash
go install github.com/yoheimuta/protolint/cmd/protolint@latest
```
