/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/143842*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */
//Raven Kwok aka Guo, Ruiwen
//ravenkwok.com
//vimeo.com/ravenkwok
//flickr.com/photos/ravenkwok

final String sketchName = "noise Turbulence Remastered 01";

ArrayList<Particle> pts;
boolean onPressed, showInstruction;
PFont f;

void setup() {
  size(720, 720, P2D);
  smooth();
  frameRate(3000);
  colorMode(HSB);
  rectMode(CENTER);

  pts = new ArrayList<Particle>();

  showInstruction = true;
  f = createFont("Calibri", 24, true);

  background(255);
}

void draw() {
  
  setWinTitleAndFps();
  
  if (showInstruction) {
    background(255);
    fill(128);
    textAlign(CENTER, CENTER);
    textFont(f);
    textLeading(36);
    text("Drag and draw." + "\n" +
      "Press 'c' to clear the stage." + "\n"
      , width*0.5, height*0.5);
  }

  if (onPressed) {
    for (int i=0;i<10;i++) {
      Particle newP = new Particle(mouseX, mouseY, i+pts.size(), i+pts.size());
      pts.add(newP);
    }
  }

  for (int i=0; i<pts.size(); i++) {
    Particle p = pts.get(i);
    p.update();
    p.display();
  }

  for (int i=pts.size()-1; i>-1; i--) {
    Particle p = pts.get(i);
    if (p.dead) {
      pts.remove(i);
    }
  }
}

void mousePressed() {
  onPressed = true;
  if (showInstruction) {
    background(255);
    showInstruction = false;
  }
}

void mouseReleased() {
  onPressed = false;
}

void keyPressed() {
  if (key == 'c') {
    for (int i=pts.size()-1; i>-1; i--) {
      Particle p = pts.get(i);
      pts.remove(i);
    }
    background(255);
  }
}

class Particle{
  PVector loc, vel, acc;
  int lifeSpan, passedLife;
  boolean dead;
  float alpha, weight, weightRange, decay, xOffset, yOffset;
  color c;
  
  Particle(float x, float y, float xOffset, float yOffset){
    loc = new PVector(x,y);
    
    float randDegrees = random(360);
    vel = new PVector(cos(radians(randDegrees)), sin(radians(randDegrees)));
    vel.mult(random(5));
    
    acc = new PVector(0,0);
    lifeSpan = int(random(30, 90));
    decay = random(0.75, 0.9);
    c = color(random(255),random(255),255);
    weightRange = random(3,50);
    
    this.xOffset = xOffset;
    this.yOffset = yOffset;
  }
  
  void update(){
    if(passedLife>=lifeSpan){
      dead = true;
    }else{
      passedLife++;
    }
    
    alpha = float(lifeSpan-passedLife)/lifeSpan * 70+50;
    weight = float(lifeSpan-passedLife)/lifeSpan * weightRange;
    
    acc.set(0,0);
    
    float rn = (noise((loc.x+frameCount+xOffset)*0.01, (loc.y+frameCount+yOffset)*0.01)-0.5)*4*PI;
    float mag = noise((loc.y+frameCount)*0.01, (loc.x+frameCount)*0.01);
    PVector dir = new PVector(cos(rn),sin(rn));
    acc.add(dir);
    acc.mult(mag);
    
    float randDegrees = random(360);
    PVector randV = new PVector(cos(radians(randDegrees)), sin(radians(randDegrees)));
    randV.mult(0.5);
    acc.add(randV);
    
    vel.add(acc);
    vel.mult(decay);
    vel.limit(3);
    loc.add(vel);
  }
  
  void display(){
    
    /* only as fast as the slower ellipse... 
    fill(c);
    stroke(0,alpha);
    strokeWeight(0.75);
    circle( loc.x, loc.y, 2*weight);
    /**/
    
    // a fairly faster circle 
    {
      // outline
      strokeWeight(weight+1.5);
      stroke(0, alpha);
      point(loc.x, loc.y);

      // filling
      strokeWeight(weight);
      stroke(c);
      point(loc.x, loc.y);
    }
    /**/
    
    
    //slower ellipse
    /*strokeWeight(0.75);
    stroke(0, alpha);
    fill(c);
    ellipse( loc.x, loc.y, weight, weight);/**/
  }
}

void circle( float posX, float posY, float diameter) {
   
    strokeWeight(diameter + (g.strokeWeight)*2);
    stroke(g.strokeColor);
    point(posX, posY);
    
    strokeWeight(diameter);
    stroke(g.fillColor);
    point(posX, posY);  
}

void setWinTitleAndFps() {
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
