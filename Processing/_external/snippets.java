//////////////////////////////////////////////////////////////////////////////////////
// filter and load files from a dir

String[] filenames;
String fullPath = "C:/WINDOWS/Web/Wallpaper"; // use forward slashes
 
void setup() {
  filenames = loadFilenames(fullPath);
  println(filenames);
  exit();
}
 
String[] loadFilenames(String path) {
  File folder = new File(path);
  FilenameFilter filenameFilter = new FilenameFilter() {
    public boolean accept(File dir, String name) {
      return name.toLowerCase().endsWith(".jpg"); // change this to any extension you want
    }
  };
  return folder.list(filenameFilter);
}

//////////////////////////////////////////////////////////////////////////////////////
// print timestamps
String timestamp = year() + nf(month(),2) + nf(day(),2) + "-"  + nf(hour(),2) + nf(minute(),2) + nf(second(),2);


//////////////////////////////////////////////////////////////////////////////////////
// text input


String myText = "Type something";
 
void setup() {
  size(500, 500);
  textAlign(CENTER, CENTER);
  textSize(30);
  fill(0);
}
 
void draw() {
  background(255);
  text(myText, 0, 0, width, height);
}
 
void keyPressed() {
  if (keyCode == BACKSPACE) {
    if (myText.length() > 0) {
      myText = myText.substring(0, myText.length()-1);
    }
  } else if (keyCode == DELETE) {
    myText = "";
  } else if (keyCode != SHIFT && keyCode != CONTROL && keyCode != ALT) {
    myText = myText + key;
  }
}

//////////////////////////////////////////////////////////////////////////////////////
// check if mouse is over circle or rectangle

boolean mouseOverCircle(int x, int y, float diameter) {
  return (dist(mouseX, mouseY, x, y) < diameter*0.5);
}
 
boolean mouseOverRect(int x, int y, int w, int h) {
  return (mouseX >= x && mouseX <= x+w && mouseY >= y && mouseY <= y+h);
}

//////////////////////////////////////////////////////////////////////////////////////
// save high-resolution output

void saveHiRes(int scaleFactor) {
  PGraphics hires = createGraphics(width*scaleFactor, height*scaleFactor, P2D);
  beginRecord(hires);
  hires.scale(scaleFactor);
  draw();
  endRecord();
  hires.save("hires.png");
}

//////////////////////////////////////////////////////////////////////////////////////
// a fast circle
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



//////////////////////////////////////////////////////////////////////////////////////
// transparent bgOverdraw
void bgOverdraw( int alpha) {
  fill(0,0,0, alpha);
  rect(0, 0, width, height);
}