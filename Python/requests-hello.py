import json
import requests

r = requests.post("https://httpbin.org/post", data={"key": "value"})

print(f"Type: {type(r.json())}")
print(r.json())

json.loads(r.json())

s = requests.Session()
