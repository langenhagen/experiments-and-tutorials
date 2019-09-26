/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/61903*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */
import java.util.*;
PGraphics bg;
RingCollection rings;

int nbrRings = 10;
float innerWidth = 30.;
float ringWidth = 10.;
float spacing = 5.;
int innerSegments = 30;
int outerSegments = 70;


void setup() {
  size(400, 400, P2D);
  rings = new RingCollection(nbrRings, width/2, height/2, innerWidth, ringWidth, spacing, innerSegments, outerSegments);
  smooth();
  colorMode(HSB, 360, 1, 1);

  bg = createGraphics(width, height, P2D);
  drawBackground();

  // clear all the rings and force seq10
  for (Ring r:rings.rings) {
    r.clearPattern();
  }
  rings.seq.seq = (LinkedList<SequenceEntry>[]) new LinkedList<?>[80];
  seq10(rings.seq.seq, rings.damping, nbrRings, 80);
}

void draw() {
  background(0);
  image(bg, 0, 0);
  rings.update();
  rings.draw();
  /*if (frameCount%60 == 0) {
    println("" + frameRate);
  }*/
}

// the pattern generator functions may call this when it is time to
// animate a renewal of the rings
void renewRings() {
  rings.isRenewing = true;
}

