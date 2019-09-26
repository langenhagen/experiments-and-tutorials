/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/124855*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */

final String sketchName = "Jungle Lines Extended";

ArcuateLine arcLine1;
ArcuateLine arcLine2;

int fadeOutFreq = 0;

boolean aliceMode = false;


void setup() {
  
  size(1920, 1080,P2D);
  background(0);
  smooth();
  strokeCap(ROUND);
  
  frameRate(60);
  
  color backColor = color(128);
  color foreColor1 = color(32,255,64);
  
  arcLine1 = new ArcuateLine( 0,0, backColor, foreColor1);
  arcLine2 = new ArcuateLine( 200,300, backColor, foreColor1);
}

void draw() {


  setWinTitleAndFps();
  
  strokeCap(ROUND);
  arcLine1.draw();
  strokeCap(PROJECT);
  arcLine2.draw();
  
  if( fadeOutFreq-- == 0)
  {
    fadeOutFreq = 10;
      
    noStroke();
    fill(0,0,0, 5);
    rect(0, 0, width, height);
  }
  

  
  if( aliceMode)
  {
    filter(INVERT);
  }
  
  
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
    aliceMode = !aliceMode;
  }
  
  
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

