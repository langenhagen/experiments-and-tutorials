 import processing.core.*;
 import processing.opengl.*;


 public class MyProcessingSketch extends PApplet {
	 
	 
	 public static void main(String args[]) {
		    PApplet.main(new String[] { "--present", "MyProcessingSketch" });	  
	 }

	 
   public void setup() {
     size(200,200);
     background(0);
   }

   public void draw() {
     stroke(255);
     if (mousePressed) {
       line(mouseX,mouseY,pmouseX,pmouseY);
     }
   }
 }