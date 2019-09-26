int halfWidth;
int halfHeight;

void setup() {
  size(1024, 600);
  stroke(255);
  frameRate(1000);
  
  halfWidth = width/2;
  halfHeight = height/2;
  
  background(0);
  
} 

void draw() {
  
  fill(0, 100);
  rect(0, 0, width, height);
  
  
  float amplitude = map(height - mouseY, 0, height, 0, 10);
  
  float rand = 0;
  for  (int i = 0; i < halfWidth; i++)
  {
    point( i,         halfHeight + random(-rand, rand));
    point( width - i, halfHeight + random(-rand, rand));
    rand += random(-amplitude, amplitude);
  }
  
  // center point
  point( halfWidth, halfHeight + random(-rand, rand));
  
}
