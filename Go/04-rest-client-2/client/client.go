package client

import (
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

type getResponse struct {
	Code int `json:"code"` // the `json:"code"` part defines which fields of a json object to parse when creating the struct
	Data getResponseData
}

type getResponseData struct {
	Args struct {
		Foo string `json:"foo"`
	} `json:"args"`
	Headers struct {
		Accept       string `json:"Accept"`
		Host         string `json:"Host"`
		UserAgent    string `json:"User-Agent"`
		XAmznTraceID string `json:"X-Amzn-Trace-Id"`
	} `json:"headers"`
	Origin string `json:"origin"`
	URL    string `json:"url"`
}

// curl -X GET 'https://httpbin.org/get?foo=bar'
// {
//  "args": {
//    "fpp": "bar"
//  },
//  "headers": {
//    "Accept": "*/*",
//    "Host": "httpbin.org",
//    "User-Agent": "curl/7.68.0",
//    "X-Amzn-Trace-Id": "Root=1-605a5ed2-63c5e92103445a5c211a776a"
//  },
//  "origin": "91.66.9.159",
//  "url": "https://httpbin.org/get?fpp=bar"
//}
func (c *Client) Get(ctx context.Context, querystring string) (*getResponse, error) {
	url := fmt.Sprintf(c.BaseURL + "/get" + "?" + querystring)
	req, err := http.NewRequestWithContext(ctx, "GET", url, nil)
	if err != nil {
		return nil, err
	}

	res := getResponse{}
	if err := c.sendRequest(req, &res); err != nil {
		return nil, err
	}

	return &res, nil
}

func (c *Client) sendRequest(req *http.Request, result *getResponse) error {
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

// curl -H "Content-Type: application/json" -X POST --data '{"username":"xyz","password":"xyz"}' https://httpbin.org/post
//{
//  "args": {},
//  "data": "{\"username\":\"xyz\",\"password\":\"xyz\"}",
//  "files": {},
//  "form": {},
//  "headers": {
//    "Accept": "*/*",
//    "Content-Length": "35",
//    "Content-Type": "application/json",
//    "Host": "httpbin.org",
//    "User-Agent": "curl/7.68.0",
//    "X-Amzn-Trace-Id": "Root=1-605a60a4-089d11174f578f0d48080b71"
//  },
//  "json": {
//    "password": "xyz",
//    "username": "xyz"
//  },
//  "origin": "91.66.9.159",
//  "url": "https://httpbin.org/post"
//}
// func (s *Client) Post(todo *Todo) error {
// }
