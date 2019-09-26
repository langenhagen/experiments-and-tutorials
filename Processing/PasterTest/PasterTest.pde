// PASTER TEST FAILED: IT IS TOO SLOW (U CAN FEEL THE GARBAGE COLLECTOR WORKING)

final String sketchName = "Paster Test";

Paster p = new Paster( 10000);

void setup() {

  size(800, 600, P3D);



}

void draw() {

  setWinTitleAndFps();


  if( p.currentFrame != 0)
  {
    // do something in the past
    p.drawFrame();
  }
  else
  {
    // the time is now

    background(0);

    float floatSeconds = millis()/ 1000.0f;

    pushMatrix();
    translate( width/2, height/2);
    rotate(floatSeconds);
    ellipse( 0, 0, 100, 20);
    popMatrix();

    p.capture();
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
    println( " => " +sketchName + " " + millis() + " ms [" + (frameRate) + " fps]");
  }
  if( key == 'q')
  {
    p.currentFrame++;
  }
  if( key == 'w')
  {
    p.currentFrame--;
  }


}


void setWinTitleAndFps()
{
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}

