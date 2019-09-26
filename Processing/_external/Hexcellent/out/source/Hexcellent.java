import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Hexcellent extends PApplet {

/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/189138*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */
/*
 Inspiration from bees&bombs
  
 still not totally perfect, but close enough.
  
*/
int numBaseSides = 6;
int sideSize = 100;
float s = 0;
float totalScale = 1.0f;
float timeScale = 0.8f;
float diff = 0.05f;
 
float triangleHeight = sqrt( pow(sideSize, 2)- pow((sideSize/2.0f), 2));
   
public void setup() {
  size(500, 500);
  fill(0); 
}
 
public void draw() {
  background(100);
  translate(width/2, height/2);
  scale(totalScale);
  rotate(PI/2);
   
  drawBase();
  drawExternalTriangles();
 
  totalScale -= 0.004f * timeScale;
  s += 0.02f * timeScale;
 
  if (totalScale <= 0.5f) {
    totalScale = 1;
    s = 0;
  }
}
 
public void drawExternalTriangles() {
  int triIndex = 0;
 
  for (float i = 0; i < 6; i++) {
    pushMatrix();
 
    rotate(i * TWO_PI/6.0f);
    translate(0, -triangleHeight*1.5f);
 
    // left
    pushMatrix();
    translate(-sideSize/2, 0);
    float s1 = min(1, max(s - (triIndex * diff), 0));
    scale(s1);
    rotate(PI);
    triangle(sideSize/2, triangleHeight/2.0f, -sideSize/2, triangleHeight/2.0f, 0, -triangleHeight/2.0f);
    popMatrix();
    triIndex++;
 
    // center
    pushMatrix();
    float s2 = min(1, max(s - (triIndex * diff), 0));
    scale(s2);
    triangle(sideSize/2, triangleHeight/2.0f, -sideSize/2, triangleHeight/2.0f, 0, -triangleHeight/2.0f);
    popMatrix();
    triIndex++;
 
    // right
    pushMatrix();
    translate(sideSize/2, 0);
    float s3 = min(1, max(s - (triIndex * diff), 0));
    scale(s3);
    rotate(PI);
    triangle(sideSize/2, triangleHeight/2.0f, -sideSize/2, triangleHeight/2.0f, 0, -triangleHeight/2.0f);
    popMatrix();
    triIndex++;
 
    popMatrix();
  }
}
 
public void drawBase() {
  for (float i = 0; i <= numBaseSides; i++) {
    pushMatrix();
    rotate(i * (TWO_PI/numBaseSides));
    triangle(sideSize/2, triangleHeight, -sideSize/2, triangleHeight, 0, 0);
    popMatrix();
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Hexcellent" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
