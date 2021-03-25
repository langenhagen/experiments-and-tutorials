package client

import (
	"bytes"
	"context"
	"encoding/json"
	"fmt"
	"net/http"
	"time"
)

const (
	BaseURLV1 = "https://httpbin.org"
)

type Client struct {
	BaseURL    string
	HTTPClient *http.Client
}

func NewClient() *Client {
	return &Client{
		BaseURL: BaseURLV1,
		HTTPClient: &http.Client{
			Timeout: time.Minute,
		},
	}
}

type response struct {
	Code int
	Data interface{} // A generic response. I suspect its best to only use it if necessary.
}

// example of a more fleshed out response, if possible, I believe such might be better
// type getResponseData struct {
// 	Args struct {
// 		Foo string `json:"foo"`
// 	} `json:"args"`
// 	Headers struct {
// 		Accept       string `json:"Accept"`
// 		Host         string `json:"Host"`
// 		UserAgent    string `json:"User-Agent"`
// 		XAmznTraceID string `json:"X-Amzn-Trace-Id"`
// 	} `json:"headers"`
// 	Origin string `json:"origin"`
// 	URL    string `json:"url"`
// }

// The following function basically does what the following `curl` call may do:
// curl -X GET 'https://httpbin.org/get?foo=bar'
// Yields:
// {
//   "args": {
//     "fpp": "bar"
//   },
//   "headers": {
//     "Accept": "*/*",
//     "Host": "httpbin.org",
//     "User-Agent": "curl/7.68.0",
//     "X-Amzn-Trace-Id": "Root=1-605a5ed2-63c5e92103445a5c211a776a"
//   },
//   "origin": "91.66.9.159",
//   "url": "https://httpbin.org/get?fpp=bar"
// }
func (c *Client) Get(ctx context.Context, querystring string) (*response, error) {
	url := fmt.Sprintf(c.BaseURL + "/get" + "?" + querystring)
	req, err := http.NewRequestWithContext(ctx, "GET", url, nil)
	if err != nil {
		return nil, err
	}

	res := response{}
	if err := c.sendRequest(req, &res); err != nil {
		return nil, err
	}

	return &res, nil
}

func (c *Client) sendRequest(req *http.Request, result *response) error {
	req.Header.Set("Content-Type", "application/json; charset=utf-8")
	req.Header.Set("Accept", "application/json; charset=utf-8")

	res, err := c.HTTPClient.Do(req)
	if err != nil {
		return err
	}

	defer res.Body.Close()

	if res.StatusCode < http.StatusOK || res.StatusCode >= http.StatusBadRequest {
		return fmt.Errorf("Error: status code: %d", res.StatusCode)
	}

	if err = json.NewDecoder(res.Body).Decode(&result.Data); err != nil {
		return err
	}
	result.Code = res.StatusCode

	return nil
}

// Like the function `Get()`, the following function basically does what the following `curl` call
// may do:
// curl -H "Content-Type: application/json" -X POST --data '{"username":"xyz","password":"xyz"}' \
//   https://httpbin.org/post
// Yields:
// {
//   "args": {},
//   "data": "{\"username\":\"xyz\",\"password\":\"xyz\"}",
//   "files": {},
//   "form": {},
//   "headers": {
//     "Accept": "*/*",
//     "Content-Length": "35",
//     "Content-Type": "application/json",
//     "Host": "httpbin.org",
//     "User-Agent": "curl/7.68.0",
//     "X-Amzn-Trace-Id": "Root=1-605a60a4-089d11174f578f0d48080b71"
//   },
//   "json": {
//     "password": "xyz",
//     "username": "xyz"
//   },
//   "origin": "91.66.9.159",
//   "url": "https://httpbin.org/post"
// }
func (c *Client) Post(ctx context.Context, payload string, querystring string) (*response, error) {
	url := fmt.Sprintf(c.BaseURL + "/post" + "?" + querystring)

	jsonStr := []byte(payload)

	req, err := http.NewRequestWithContext(ctx, "POST", url, bytes.NewBuffer((jsonStr)))
	if err != nil {
		return nil, err
	}

	res := response{}
	if err := c.sendRequest(req, &res); err != nil {
		return nil, err
	}

	return &res, nil
}
