float x, y, r, g, b, radius;

int timer;

 

void setup() {

  size(500, 500);
  background(255);
  noStroke();
  smooth();
}

 

void draw() {

  x = frameCount % width;

  
  if (millis() - timer >= 2000) {
    y = random(height);
    timer = millis();
  }

  
  r = noise(frameCount * 0.01) * 255;
  g = frameCount % 255;
  b = 255 - noise(1 + frameCount * 0.025) * 255;
  

  radius = noise(frameCount * 0.01) * 100;

  color c = color(r, g, b);

  fill(c);

  ellipse(x, y, radius, radius);
}
