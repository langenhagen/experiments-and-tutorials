final String sketchName = "Hello Noise 2";

void setup() {
  
  size(800, 600, P3D);
  
}

void draw() {
 
  setWinTitleAndFps();
  
  int lod = (int)((float)mouseX/width*10);
  float falloff = (float)mouseY/height;
  noiseDetail(lod, falloff);
  
  for( int i = 0; i < width; ++i)
    for( int j = 0; j < height; ++j)
    {
      float v = noise( i*0.05f, j*0.05f, frameCount*0)-0.5f;
      color c;
      
      //print(v + "    ");
      
      if( v > 0)
        c = color(abs(v) * 255  ,0,0);
      else
        c = color(0,abs(v) * 255,0);
      
      stroke(c);
      point(i,j);
    }
  
    println("lod: " + lod + " falloff: " + falloff);
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
