String sketchName = "Hello World AKA Pointillism";

PImage img;


void setup() {
  
  img = loadImage("[CLN] flowerbeauty_clrshft200_round_psbrightup.png");
  
  size(img.width, img.height);

  

  imageMode(CENTER);
  noStroke();
  background(0);
  
  frameRate(26000);
}

void draw() { 
  
  setWinTitleAndFps();
  
  float pointillize = random(1,10);   //map(mouseX, 0, width, smallPoint, largePoint);
  int x = int(random(img.width));
  int y = int(random(img.height));
  color pix = img.get(x, y);
  fill(pix, 128);
  ellipse(x, y, pointillize, pointillize);
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
