
PFont font;


void setup() {
  size(1024,768, P2D);
  
  background(0);
  
  char[] fontset = {'C', 'L', 'N'};
  font = createFont("Arial Black", height, true, fontset);
  textFont(font);
  
  
}
 
 
void draw() {

  println("!");
  
  fill( 255,0,0, 10);
  rect( 0, 0, width, height);
  
  fill( 255);
  textSize( mouseY);
  text("CLN", 50, 300);
  

}
