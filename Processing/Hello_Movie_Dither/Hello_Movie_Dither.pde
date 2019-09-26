import processing.video.*;

String sketchName = "Hello Random Movie Dither";

Movie movie;

PShader ditherShader;


void setup() {
  size(640, 480, P2D);
  
  frameRate(300);
  
  noSmooth();
  
  movie = new Movie(this, "CLN_at_night.mp4");
  //movie = new Movie(this, "The Creation.mp4");
  movie.play();
  movie.volume(0);
  
  
  
  ditherShader = loadShader("randomdither_frag.glsl");
  
  shader(ditherShader);
  
}

void draw() {
  
  setWinTitleAndFps();
  
  if( frameCount % 3 == 0)
  {
    ditherShader.set("random", random(1));
  }
  
  println((float)(mouseY - height/2)/height);
  ditherShader.set("offset", (float)(mouseY - height/2)/height);
  
  image(movie, 0, 0, width, height);
  
  
}

void movieEvent(Movie m) {
  m.read();
}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
