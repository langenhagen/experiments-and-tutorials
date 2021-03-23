package facest

import (
	"bytes"
	"io"
	"mime/multipart"
	"net/http"
)

// DetectRes .
type DetectRes struct {
	Count int            `json:"count"`
	Faces []DetectedFace `json:"faces"`
}

// DetectedFace .
type DetectedFace struct {
	Rectangle Rectangle `json:"rectangle"`
}

// Detect faces within a given image.
func (c *Client) Detect(image io.Reader) (*DetectRes, error) {
	var buf bytes.Buffer
	w := multipart.NewWriter(&buf)
	fw, err := w.CreateFormFile("image", "image.jpg")
	if err != nil {
		return nil, err
	}
	if _, err = io.Copy(fw, image); err != nil {
		return nil, err
	}
	if err = w.Close(); err != nil {
		return nil, err
	}

	req, err := http.NewRequest("POST", c.baseURL+"/detect", &buf)
	if err != nil {
		return nil, err
	}

	req.Header.Set("Content-Type", w.FormDataContentType())

	res := DetectRes{}
	if err := c.sendRequest(req, &res); err != nil {
		return nil, err
	}

	return &res, nil
}
