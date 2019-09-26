String sketchName = "Hello Shaders";

PImage image;
PShape can;
float angle;

PShader activeShader;

void setup() {
  size(1024, 768, P3D);
  
  frameRate(60);
  
  image = loadImage("mmexample.jpg");
  can = createCan(100, 200, 32, image);
  
  // Simple shader
  //activeShader = loadShader("color_frag.glsl", "color_vert.glsl");
  
  // Blur shader
  activeShader   = loadShader("texfrag.glsl", "texvert.glsl");
  
  /**/
  
  // Median 3x3
  //activeShader  = loadShader("median3x3_frag.glsl");
  
  
  shader(activeShader);
}

void draw() {    
  
  setWinTitleAndFps();
  
  background(0);
    
  // draw can 
  translate(width/2, height/2);
  scale(2);
  rotateY(angle);  
  shape(can);  
  angle += 0.01;
  /**/
}

PShape createCan(float r, float h, int detail, PImage tex) {
  textureMode(NORMAL);
  PShape sh = createShape();
  sh.beginShape(QUAD_STRIP);
  //sh.fill(128, 0, 0);
  sh.texture(tex);
  sh.noStroke();
  for (int i = 0; i <= detail; i++) {
    float angle = TWO_PI / detail;
    float x = sin(i * angle);
    float z = cos(i * angle);
    float u = float(i) / detail;
    sh.normal(x, 0, z);
    sh.vertex(x * r, -h/2, z * r, u, 0);
    sh.vertex(x * r, +h/2, z * r, u, 1);    
  }
  sh.endShape(); 
  return sh;
}

void keyPressed() {
  int keyIndex = -1;
    
  if( key == 10)
  {
    // ENTER
    String timestamp = year() + nf(month(),2) + nf(day(),2) + "-"  + nf(hour(),2) + nf(minute(),2) + nf(second(),2);
    save("result_" + timestamp + ".png");
  }
  if( key == 'p')
  {
    // PRINT SOMETHING
    println( " => " +sketchName + " [" + (frameRate) + " fps]");
  } 
}

void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
