# gRPC Hello Basics

Based on the basics tutorial: https://grpc.io/docs/languages/python/basics/


```bash
scripts/setup.sh
scripts/generate_grpc_code.py
```

The script `generate_grpc_code`.py generates:
- classes for the messages defined in `route_guide.proto`
- classes for the service defined in `route_guide.proto`
- `RouteGuideStub`, is stub for clients
- `RouteGuideServicer`, in `route_guide_pb2_grpc` interface for the service
- a function `add_RouteGuideServicer_to_server()` to add a` RouteGuideServicer` to a `grpc.Server`

---

## RPC Types
There are 4 different RPC types:
1. Simple RPC
2. Request-streaming RPC
3. Response-streaming RPC
4. Bidirectional streaming RPC

### Simple RPC
A synchronous call to the simple RPC is similar to calling a local method.

An asynchronous call is rather like calling a local method asynchronously in a thread pool.

```python
my_response = stub.MyRPC(my_arg)

# or

my_response_future = stub.MyRPC.future(my_arg)
# ...
response = my_response_future.result()
```

### Request-streaming RPC
Dimilar to passing an iterator to a local method. Can be called synchronously or asynchronously.


### Response-streaming RPC
Similar to working with sequence types as return objects:

```python
for feature in stub.ListFeatures(rectangle):
    ...
```

### Bidirectional streaming RPC
A combination of the request-streaming and response-streaming semantics.


---

## Notes
The `2` in `pb2` indicates that the generated code is following Protocol Buffers Python API version
2. It has no relation to the Protocol Buffers Language version, which is the one indicated by
`syntax = "proto3"` or `syntax = "proto2"` in a `.proto` file.

---

`protoc` may be a bitch when it comes to relative imports, i.e. when you want to generate your stubs
into a subfolder other than the project root. I found and incorporated a solution in
`generate_grpc_code.py`. Found here:
https://github.com/protocolbuffers/protobuf/issues/1491#issuecomment-1279336064
