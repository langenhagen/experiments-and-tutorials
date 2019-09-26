String sketchName = "Hello Linear Blur";

PImage img;

PShader horizontalBlur;
PShader verticalBlur;

void setup() {
  
  img = loadImage("anna lee fisher.jpg");
  
  size(800, 600, P3D);
  
  frameRate(600);

 
  horizontalBlur  = loadShader( "linear_blur_horizontal_frag.glsl");
  verticalBlur    = loadShader( "linear_blur_vertical_frag.glsl");
  
  
  horizontalBlur.set("offsetPerPixel", 1.0/img.width);
  verticalBlur.set("offsetPerPixel", 1.0/img.height);
}

void draw() {
  
  setWinTitleAndFps();

  horizontalBlur.set("radius", (int)(mouseX * 0.05f));
  verticalBlur.set("radius", (int)(mouseY * 0.05f) );
  
  image( img, 0, 0, width, height);
  
  filter(horizontalBlur);
  filter(verticalBlur);
  
}


void keyPressed() {
 
  
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
  if( key == 'f')
  {
    println( "Filter Size: " + (2 *( mouseX / 20) + 1) + " x " + (2 *( mouseY / 20) + 1) + " pixels @ " + frameRate + " fps.");
  }
  
  
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

