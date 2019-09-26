
String sketchName = "TextureChain";

PImage img;

PShader blur_horizontal;
PShader blur_vertical;

void setup() {
  size(1024, 768, P2D);
  background(50, 220, 50);
  
  frameRate(20);

  noiseDetail(64,0.5);
  
  smooth();
  
  blur = loadShader("blur.glsl");
  float offset = 1.0000;
  blur.set( "texOffset", offset, 0);
  
  img = loadImage("flower2.png");

  
}

void draw() {
  
  setWinTitleAndFps();
  
  
  
  tint( 255, 255, 255, 255);
  filter(blur);
  
  

  variableTextureByNoise();
  
}



void variableTextureByNoise()
{
  noStroke();
  
  float halfScale = noise(frameCount) * 100 - 20;
  
  float x = random(width);//noise(0,   frameCount * 0.03) * width * 2 - width  * 0.5;
  float y = random(height);//noise(0,0, frameCount * 0.03) * height* 2 - height * 0.5;

  tint( 150 + noise( 100000 + frameCount * 0.3) * 128, 
        150 + noise( 200000 + frameCount * 0.3) * 128,
        150 + noise( 300000 + frameCount * 0.3) * 128);
        
        
  pushMatrix();
  
  translate(x, y);
  rotate(random(PI*2));
  
  beginShape();
  texture(img);
  vertex(-halfScale, -halfScale, 0,         0);
  vertex(+halfScale, -halfScale, img.width, 0);
  vertex(+halfScale, +halfScale, img.width, img.height);
  vertex(-halfScale, +halfScale, 0,         img.height);
  endShape();
  
  popMatrix();
  
  
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

void keyPressed() {

  if( key == 10)
  {
    save("result.png");
  }
  
}
