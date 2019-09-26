
String sketchName = "Atom";

float halfWidth, halfHeight;

float reciprocalPI = 1 / PI; // use this for funky rounding-induced artifacts ;))

void setup() {
  size(1024, 768, P3D);
  frameRate(7);
  //smooth(2);
  ellipseMode(CENTER);
  strokeWeight(3);
  stroke(255,255);
  fill(0, 0);

  hint(DISABLE_DEPTH_TEST);

  halfWidth = width/2;
  halfHeight = height/2;

}

void draw() {



  //setWinTitleAndFps();
  background(0);


  float maxNum = frameCount % 200;//(mouseY/10.0f);
  //float absYScale = 1.0f;//(float)mouseX/width;
  float angleStep = reciprocalPI * maxNum;



  for( int i = 0; i <  maxNum; ++i)
  {
    stroke( 128 + noise((frameCount + i) * 0.02) * 128,
            128 + noise(0, (frameCount + i) * 0.02) * 128,
            128 + noise(0,0, (frameCount + i) * 0.02) * 128,
            255);

    rotatedEllipse( angleStep * i, halfWidth, halfHeight, 400, 100, 1);
  }

}



void rotatedEllipse( float radianAngle, float x, float y, float radiusX, float radiusY, float absYScale)
{
  pushMatrix();

  translate(x, y);
  scale(1, absYScale);
  rotate(radianAngle);

  ellipse(0, 0, radiusX, radiusY);

  popMatrix();
}


void setWinTitleAndFps()
{
  if( frameCount % 60 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
