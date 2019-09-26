final String sketchName = "Metaballs";

// something like a debug macro, works well whithin the whole project.
boolean __DEBUG__ = false;

int width = 800;
int height = 600;
int halfWidth = width/2;
int halfHeight = height/2;

int p1x = 300;
int p1y = 200;
int p1r = 130;

int p2x = 500;
int p2y = 300;
int p2r = 70;

void setup() {
  
  size(800, 600, P3D);

}

void draw() {
 
  setWinTitleAndFps();
  
  fill(0,0,0);
  rect(0,0,width,height);
  
  fill(255,255,255);
  
  ellipse(p1x, p1y, 3, 3);
  ellipse(p2x, p2y, 3, 3);
  
  
  for(int x = 0; x<width; ++x) {
    for( int y = 0; y < height; ++y) {
      
      float d1 = dist( p1x, p1y, x, y);
      float d2 = dist( p2x, p2y, x, y);

      /*      
      if( d1 < p1r) {
        stroke(255,0,255);
        point(x,y);
      }
      
      if( d2 < p2r) {
        stroke(255,0,0);
        point(x,y);
      }
      /**/
      
      float s=2;
      
      if( pow(pow((d1-p1r),s) * pow((d2-p2r),s), 1/(s)) < 8*mouseY) {
        stroke(255,255,255);
        point(x,y);
      }
      
    }
  }
  

}

float dist( int x1, int y1, int x2, int y2) {
  return sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
}


void keyPressed() {
  
  // beware of the difference between key and keyCode
  if( keyCode == 123) {
    // F12
    save("result_" + timestamp() + ".png");
  }
  if( key == 'p') {
    // PRINT SOMETHING
    println( " => " +sketchName + " " + millis() + " ms [" + (frameRate) + " fps]");
  }
  
  //println("(int)key: " + (int)key + "\tkey: " + key + "\tkeyCode: " + keyCode);
}


// STANDARD HELPER FUNCTIONS //////////////////////////////////////////////////////////////////////

String timestamp() {
  return year() + nf(month(), 2) + nf(day(), 2) + "-"  + nf(hour(), 2) + nf(minute(), 2) + nf(second(), 2);
}


void setWinTitleAndFps() {
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
