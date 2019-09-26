final String sketchName = "Hello Vertex Displacement";

int width = 800;
int height = 600;

float sphereRadius = min( width*0.3f, height*0.3f);

PShader shader;

void setup() {
  
  size(width, height, P3D);
  
  
  //noStroke();
  stroke(255, 50);
  
  //noLights();
  //lights();
  
  shader = loadShader("color_frag.glsl", "perlin_noise_displacement_vert.glsl");
  shader(shader);
  
  
  
}

void draw() {
 
  setWinTitleAndFps();

  background(0);
  
  
  
  translate(width*0.5f, height*0.5f, 0);
  rotateX(mouseY * 0.05f);
  rotateY(mouseX * 0.05f);
  
  /*
  directionalLight(200, 200, 200, 0, 0, -1);
  ambientLight(240, 240, 240);
  /**/
  
  fill(mouseX * 2, 0, 160);
  sphereDetail(mouseX / 4);
  sphere( sphereRadius);
}



void keyPressed() {
 
  if( key == 10)
  {
    // ENTER
    save("result_" + timestamp() + ".png");
  }
  if( key == 'p')
  {
    // PRINT SOMETHING
    println( " => " +sketchName + " " + millis() + " ms [" + (frameRate) + " fps]");
  }
  
  
}

String timestamp() {
  return year() + nf(month(), 2) + nf(day(), 2) + "-"  + nf(hour(), 2) + nf(minute(), 2) + nf(second(), 2);
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

