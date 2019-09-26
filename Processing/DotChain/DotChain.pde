
String sketchName = "DotChain";

void setup() {
  size(1024, 768, P2D);
  background(0);
  
  frameRate(60);
  
  noiseDetail(64,0.5);
 
  smooth(2);
}

void draw() {
  
  setWinTitleAndFps();
  
  fill(0, 1);
  //rect(0, 0, width, height);
  /**/

  fill(255,255);
  variableEllipseByNoise();
}



void variableEllipseByNoise()
{
  float radius = noise(frameCount) * 100 - 20;//noise(millis() * 0.01) * 100;
  
  float x = noise(0, frameCount * 0.015)   * width * 2 - width  * 0.5;
  float y = noise(0,0, frameCount * 0.015) * height* 2 - height * 0.5;
  
  stroke(radius);
  ellipse(x, y, radius, radius);
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
