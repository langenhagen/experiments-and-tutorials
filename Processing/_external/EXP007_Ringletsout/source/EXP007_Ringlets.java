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

public class EXP007_Ringlets extends PApplet {

/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/105410*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */
float R = 30;
float S = 10;
float T = 10;

int N = 5;

float ringX[] = new float[N];
float ringY[] = new float[N];
float ringK[] = new float[N];

public void drawCurl(float x, float y, float r, float s, float t) {
    pushMatrix();
    translate(x, y);
    beginShape();
    vertex(-r, -t);
    bezierVertex(-r, s - t, +r, s - t, +r, -t);
    vertex(+r, +t);
    bezierVertex(+r, s + t, -r, s + t, -r, +t);
    endShape(CLOSE);
    popMatrix();
}

boolean paused = false;

public void mouseClicked() {
    paused = !paused;
}

public void setup() {
    size(400, 400);
    for (int i = 0; i < N; i++) {
        ringX[i] = 0.5f * width;
        ringY[i] = 0.5f * height;
        ringK[i] = i + 1;
    }
}

public void draw() {
    background(0x11, 0x11, 0x22);
    
    stroke(0x11, 0x11, 0x22);
    strokeWeight(2);
    
    if (!paused)
    for (int i = 0; i < N; i++) {
        ringY[i] += 0.2f * (N - i) * (mouseY - ringY[i]) / N;
    }
    
    for (int i = N - 1; i >= 0; i--) {
        fill(0x66, 0x33, 0x33);
        drawCurl(ringX[i], ringY[i], R * ringK[i], -S * ringK[i], T);
    }
    
    for (int i = 0; i < N; i++) {
        fill(0x44, 0x88, 0x88);
        drawCurl(ringX[i], ringY[i], R * ringK[i], +S * ringK[i], T);
    }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "EXP007_Ringlets" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
