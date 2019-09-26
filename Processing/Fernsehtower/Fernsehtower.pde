String sketchName = "FernsehtowR";

PImage img;

float towerScale = 0.8f;
int ellipseRadius = 0;
int maxRadius, increment = 1;

void setup() {
  size(640, 480);
  stroke(255);
  frameRate(60);
  

  img = loadImage("Fernsehturm_Silhouette_141_480_inv.png");
  
  maxRadius = (int)random(255);
  
  background(0);
} 

void draw() {
  
  setWinTitleAndFps();
  
  // bg overdraw
  fill(0,0,0,(float)mouseY/height*255);
  rect(0, 0, width, height);
  
  
  // image
  image(img,100, height-img.height*towerScale, img.width*towerScale, img.height*towerScale);
  
  // ellipse
  ellipseRadius = (increment + ellipseRadius) % maxRadius;
  if( ellipseRadius == 0)
  {
    maxRadius = (int)random(600) + 1;
    increment = (int)random(4) + 1;
  }
  
  fill(0,0,0,0);
  ellipse(155, 256, ellipseRadius, ellipseRadius);
  
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
