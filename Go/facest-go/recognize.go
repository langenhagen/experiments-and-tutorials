package facest

import (
	"bytes"
	"io"
	"mime/multipart"
	"net/http"
)

// RecognizeRes .
type RecognizeRes struct {
	Count int              `json:"count"`
	Faces []RecognizedFace `json:"faces"`
}

// RecognizedFace .
type RecognizedFace struct {
	Rectangle  Rectangle `json:"rectangle"`
	FaceHash   string    `json:"face_hash"`
	FaceID     string    `json:"face_id"`
	Confidence float64   `json:"confidence"`
	URL        string    `json:"image_url"`
}

// Recognize faces within a givin image
func (c *Client) Recognize(image io.Reader) (*RecognizeRes, error) {
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

	req, err := http.NewRequest("POST", c.baseURL+"/recognize", &buf)
	if err != nil {
		return nil, err
	}

	req.Header.Set("Content-Type", w.FormDataContentType())

	res := RecognizeRes{}
	if err := c.sendRequest(req, &res); err != nil {
		return nil, err
	}

	return &res, nil
}
