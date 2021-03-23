package facest

import (
	"bytes"
	"io"
	"mime/multipart"
	"net/http"
)

// TrainRes .
type TrainRes struct {
	FaceToken  string `json:"face_token"`
	ImageToken string `json:"image_token"`
	ImageURL   string `json:"image_url"`
}

// Train the model by uploading face image and tagging it with something unique to your app (face_id)
func (c *Client) Train(image io.Reader, faceID string) (*TrainRes, error) {
	var buf bytes.Buffer
	w := multipart.NewWriter(&buf)
	w.WriteField("face_id", faceID)
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

	req, err := http.NewRequest("POST", c.baseURL+"/train", &buf)
	if err != nil {
		return nil, err
	}

	req.Header.Set("Content-Type", w.FormDataContentType())

	res := TrainRes{}
	if err := c.sendRequest(req, &res); err != nil {
		return nil, err
	}

	return &res, nil
}
