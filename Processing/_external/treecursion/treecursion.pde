/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/8941*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */
final String sketchName = "Treecursion";

float curlx = 0; 
float curly = 0; 
float f = sqrt(2)/2.; 
float deley = 10; 
float growth = 0; 
float growthTarget = -2; 
 
 
void setup() 
{ 
  size(1920,1080,P3D); 
  //smooth(); 
  addMouseWheelListener(new java.awt.event.MouseWheelListener() {  
    public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {  
      mouseWheel(evt.getWheelRotation()); 
  }});
  
  background(0);
  stroke(255);
  //fill(0,40);
} 
void draw() 
{ 
  setWinTitleAndFps();
  
  background(0);
  //rect(0,0,width,height);
  
  float speed = ( 0.5 * noise(0,0,millis()*0.00001) + 0.5  )*0.0001;
  float mouseX = -width + noise(millis()*speed)*width*3;
  float mouseY = -height + noise(0, millis()*speed)*height*3;
  
  curlx += (radians(360./height*mouseX)-curlx)/deley; 
  curly += (radians(360./height*mouseY)-curly)/deley; 
  translate(width/2,height/3*2); 
  line(0,0,0,height/2); 
  branch(height/4.,17); 
  growth += (growthTarget/10-growth+1.)/deley; 
} 
 
void mouseWheel(int delta) 
{ 
  growthTarget += delta; 
} 
 
void branch(float len,int num) 
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

void keyPressed() {
 
  if( key == 10)
  {
    // ENTER
    String timestamp = year() + nf(month(),2) + nf(day(),2) + "-"  + nf(hour(),2) + nf(minute(),2) + nf(second(),2);
    save("result_" + timestamp + ".png");
  }
  if( key == 'p')
  {
    // PRINT SOMETHING
    println( " => " +sketchName + " [" + (frameRate) + " fps]");
  }
  
  
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

