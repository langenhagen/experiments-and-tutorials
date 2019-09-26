import processing.core.*;  

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class RecursiveTree extends PApplet {

float curlx = 0;
float curly = 0;
float f = sqrt(2)/2.f;
float deley = 10;
float growth = 0;
float growthTarget = 0;


public void setup()
{
  size(1200,900,P2D);
  //smooth();
  addMouseWheelListener(new java.awt.event.MouseWheelListener() { 
    public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) { 
      mouseWheel(evt.getWheelRotation());
  }});
}
public void draw()
{
  background(250);
  stroke(0);
  curlx += (radians(360.f/height*mouseX)-curlx)/deley;
  curly += (radians(360.f/height*mouseY)-curly)/deley;
  translate(width/2,height/3*2);
  line(0,0,0,height/2);
  branch(height/4.f,17);
  growth += (growthTarget/10-growth+1.f)/deley;
}

public void mouseWheel(int delta)
{
  growthTarget += delta;
}

public void branch(float len,int num)
{
  len *= f;
  num -= 1;
  if((len > 1) && (num > 0))
  {
    pushMatrix();
    rotate(curlx);
    line(0,0,0,-len);
    translate(0,-len);
    branch(len,num);
    popMatrix();
    
//    pushMatrix();
//    line(0,0,0,-len);
//    translate(0,-len);
//    branch(len);
//    popMatrix();
    len *= growth;
    pushMatrix();
    rotate(curlx-curly);
    line(0,0,0,-len);
    translate(0,-len);
    branch(len,num);
    popMatrix();
    //len /= growth;
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "RecursiveTree" });
  }
}
