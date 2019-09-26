/* -------------------------------------------------------------------------------------------------
Was geht

@author: barn
@version: 160812
------------------------------------------------------------------------------------------------- */

final String sketchName = "Sketch Name";
final boolean __DEBUG__ = false;          // debug macro switch


int width = 800;
int height = 600;
int halfWidth = width/2;
int halfHeight = height/2;

// -------------------------------------------------------------------------------------------------

void settings() {
  size(width, height, P3D);
}

// -------------------------------------------------------------------------------------------------

void setup() {

  background(255, 0, 0); // jawas

}

// -------------------------------------------------------------------------------------------------

void draw() {
  setWinTitleAndFps();

  background(0, noise(second())*255, 0);


}

// -------------------------------------------------------------------------------------------------
// Standard Helpers

void keyPressed() {
  // beware of the difference between key and keyCode

   //println("(int)key: " + (int)key + "\tkey: " + key + "\tkeyCode: " + keyCode);

  if( keyCode == 108 /*F12 (on Win: 123, on Mac: 108)*/) {
    String fname = "result_" + timestamp() + ".png";
    l( "saving " + fname);
    save(fname);
  }
  else if( key == 'p') {
    // print something
    println( " => " +sketchName + " " + millis() + " ms [" + (frameRate) + " fps]");
  }

}

// -------------------------------------------------------------------------------------------------

/// Retrieves a neat timestamp
String timestamp() {
  return year() + nf(month(), 2) + nf(day(), 2) + "-" +
         nf(hour(), 2) + nf(minute(), 2) + nf(second(), 2);
}

// -------------------------------------------------------------------------------------------------

/// Sets window title w/ Sketch name and frames per second
void setWinTitleAndFps() {
  if( frameCount % 100 == 0)
    surface.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

// -------------------------------------------------------------------------------------------------

/// The simples logger you can imagine
void l( Object s ) { println(s); }