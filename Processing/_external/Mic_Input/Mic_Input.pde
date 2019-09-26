// found here: http://matthewbrown.net.au/programming/minim-and-beat-detection/

import ddf.minim.*;
 
float x;
float y;
Minim minim;
AudioInput input;
 
void setup () {
  // Sketch einstellen
  size (1024, 800);
  frameRate(60);
  smooth();
  stroke (255, 25);
  noFill ();
   
  // Startposition festlegen
  x = 0;
  y = 20;
  
  // Audiotoolkit anlegen
  minim = new Minim (this);
  input = minim.getLineIn (Minim.STEREO, 512);
  background (0);
}
 
void draw () {
  // Kreisgroesse Abhaengig von Lautstaerke
  float dim = input.mix.level () * width;

  // Kreis x-Position verschieben
  x += input.mix.level () * 20;
  // Kreis zeichnen
  ellipse (x, y, dim, dim);
   
  if (x > width) {
    x = 0;
    y += 20;
  }
}
