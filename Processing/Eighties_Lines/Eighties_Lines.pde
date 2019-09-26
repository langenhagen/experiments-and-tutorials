String sketchName = "80s Lines";



void setup() {
  size(1024, 768, P3D);
  frameRate(30);

  fill(0, 0);
  noSmooth();
  hint(DISABLE_DEPTH_TEST);


}

void draw() {

  setWinTitleAndFps();

  background(0);

  loadPixels();

  for( int i = 0; i < width; ++i)
  {
    for( int j = 0; j < height; ++j)
    {

      int col = i+j;
      int row = j*j;
      int index = row * j + col;

      if(index > 0 && index < width*height) {

        pixels[ row*j + col] = color( noise( frameCount + i*j) * 255,
                                      noise( 0, frameCount + j*j) * 255,
                                      noise( 0,0, frameCount + i*i) * 255,
                                      255);
      }
    }
  }
  updatePixels();

}


void setWinTitleAndFps()
{
  if( frameCount % 60 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

