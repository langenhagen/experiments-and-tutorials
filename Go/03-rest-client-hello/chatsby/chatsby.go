// showcase a presumable HTTP client struct. Taugt wenig, because the struct User without
// definition.
//
//Taken from:
// https://medium.com/@marcus.olsson/writing-a-go-client-for-your-restful-api-c193a2f4998c author:
// andreasl
package chatsby

import (
	"encoding/json"
	"net/http"
	"net/url"
)

type Client struct {
	BaseURL   *url.URL
	UserAgent string

	httpClient *http.Client
}

func (c *Client) ListUsers() ([]User, error) {
	rel := &url.URL{Path: "/users"}
	u := c.BaseURL.ResolveReference(rel)
	req, err := http.NewRequest("GET", u.String(), nil)
	if err != nil {
		return nil, err
	}
	req.Header.Set("Accept", "application/json")
	req.Header.Set("User-Agent", c.UserAgent)

	resp, err := c.httpClient.Do(req)
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()
	var users []User
	err = json.NewDecoder(resp.Body).Decode(&users)
	return users, err
}
