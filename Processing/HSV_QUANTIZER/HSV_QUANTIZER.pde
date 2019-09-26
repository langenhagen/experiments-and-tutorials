String sketchName = "HSV QUANTIZER";

PGraphics canvas, canvas2;

PImage img;

PShader rgb2hsvShader;
PShader hsv2rgbShader;
PShader quantizeShader;

void setup() {
  
  size(800, 600, P2D);
  
  frameRate(600);
  
  canvas  = createGraphics(width, height, P2D);
  canvas2 = createGraphics(width, height, P2D);
 
  rgb2hsvShader  = loadShader( "rgb2hsv_frag.glsl");
  hsv2rgbShader  = loadShader( "hsv2rgb_frag.glsl");
  quantizeShader = loadShader( "quantize_frag.glsl");

  
  img = loadImage("t.png");
  
}

void draw() {
 
  setWinTitleAndFps();
  
  quantizeShader.set( "multiplier", mouseY/15 + 1);
  
  canvas.beginDraw();
  
  canvas.image( img, 0,0 ,width, height);
  
  canvas.filter(rgb2hsvShader);
  canvas.filter(quantizeShader);
  canvas.filter(hsv2rgbShader);
  
  canvas.endDraw();
  
  
  canvas2.beginDraw();
    
  canvas2.image( img, 0,0 ,width, height);
  
  canvas2.filter(quantizeShader);
  
  canvas2.endDraw();
  
  image(canvas , 0, 0, width, height);
  image(canvas2, 0, 0, mouseX, height, 0, 0, (int)map(mouseX, 0, width, 0, canvas2.width), canvas2.height);
  
  
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

