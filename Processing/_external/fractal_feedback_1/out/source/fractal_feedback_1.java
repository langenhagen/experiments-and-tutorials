import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.core.*; 
import processing.xml.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class fractal_feedback_1 extends PApplet {

/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/8221*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */

/////////////////////////////
//                         //
//   fractal feedback 1    //
//                         //
/////////////////////////////

// (c) Martin Schneider 2010


// constants
final int nmax = 2, hues = 12, r=4, w = 400;

// parameters
boolean mixmode = true;
boolean darkmode;
int colormode;
float cmix;


public void setup() {
  size(w, w);
  colorMode(HSB, hues, 1, nmax); 
}


public void draw() {
  
  // get colors
  int bg = darkmode ? 0xff000000 : 0xffffffff;
  int fg = darkmode ? 0xffffffff : 0xff000000;
  float cmix = mixmode ? 3/4f : 1f;
  int hue = colormode % hues;
  
  // map mouse coordinates to complex space
  float mx = map(mouseX, 0, w, -r, r);
  float my = map(mouseY, 0, w, -r, r);
  
  // transform radius
  float r2 = r * (6 + 3 * sin(frameCount*.05f));
  
  // prepare the pixel array
  loadPixels();
  int[] p= new int[w*w];

  // iterate over the pixel array
  for(int x=0; x<width; x++)
    for(int y=0; y<width; y++) {
      
      // map screen space to complex space
      float re = map(x, 0, w, -r, r);
      float im = map(y, 0, w, -r, r);
      
      // get julia set color
      int n=0;
      float rere=re*re;
      float imim=im*im;
      for(;n<nmax;n++) {
        re = re * (rere-3*imim) + mx;
        im = im * (3*rere-imim) + my;
        rere = re*re;
        imim = im*im;
        if(rere+imim > 16) break;
      }

      // map complex space back to screen space
      float x1 = map(re, -r2, r2, 0, w);
      float y1 = map(im, -r2, r2, 0, w);
      
      // calculate new color from julia set color + feedback
      int cnew = color(hue, 1, n);
      int cfeedback = get(x1, y1, bg);
      p[x + y*w] = lerpColor(lerpColor(cnew, cfeedback, cmix), fg, .1f);
    }
  
  // show the image
  arrayCopy(p, pixels);
  updatePixels();
}


// get pixel with subsampling

public int get(float px, float py, int bg) {
  int x = floor(px), y = floor(py);
  if(mag(x-w/2, y-w/2) >= w/2-1) return bg;
  //if (x < 0 || y < 0 || x >= w - 1 || y >= w - 1) return bg;
  float dx = px - x;
  float dy = py - y; 
  int p0 = x + y * width;
  int p1 = p0 + width;
  return lerpColor(
    lerpColor(pixels[p0], pixels[p0+1], dx), 
    lerpColor(pixels[p1], pixels[p1+1], dx),
    dy);
} 
 

// user interaction

public void keyPressed() {
  switch(key) {
    case 'c' : colormode++ ; break;
    case 'd' : darkmode = !darkmode; break;
    case 'm' : mixmode = !mixmode; break;
  }
}



  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "fractal_feedback_1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
