
String sketchName = "Hello Shaders 2 - Pixel-Wise";

PShader blur;

PImage img;

void setup() {
  

  img = loadImage( "anna lee fisher.jpg");
  //img = loadImage( "mycln.png");
  
  size(img.width, img.height, P3D);
  

  
  blur = loadShader("blur_simple_frag.glsl");
  blur.set("offsetPerPixelX", 1.0/img.width);
  blur.set("offsetPerPixelY", 1.0/img.height); 
  shader(blur);
}

void draw() {
 
  setWinTitleAndFps();
  
  blur.set("filterRadius", mouseX / 100);
  
  image( img, 0,0 ,width, height);
  
  
  
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
    // for blur_simple_frag
    println( "Filter Radius: " + mouseX / 100 + " aka " + (2 *( mouseX / 100) + 1) + " x " + (2 *( mouseX / 100) + 1) + " pixels @ " + frameRate + " fps.");
  }
  
  
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

