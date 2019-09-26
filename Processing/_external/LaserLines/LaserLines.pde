
void setup(){
   colorMode(HSB);
   size(1024,768);
   
   smooth(4);
   
}
void draw(){
  
   fill(0,0,0,15);
   rect(0,0,width,height);
   stroke(255);
   stroke(120,random(255),240);
   
   line(0,random(height),mouseX,mouseY);
   line(mouseX,mouseY,width,random(height));
   
   line(mouseX,mouseY,random(width),0);
   line(mouseX,mouseY,random(width),height);
 
  float a = random(10);
  ellipse(mouseX,mouseY,a,a);
}
