void drawBackground() {
  //g = imgBackground;
  bg.beginDraw();
  bg.background(0);
  bg.colorMode(HSB, 360, 1, 1);
  bg.noStroke();
  bg.fill(0, 1, 1);
  bg.ellipse(width/2, height/2, width/1.7, height/1.7);  
  bg.filter(BLUR, 50);

  /*bg.fill(10, 0.7, 1);
   bg.ellipse(width/2, height/2, width/2.3, height/2.3);  
   bg.filter(BLUR, 20);*/

  bg.fill(30, 0.6, 1);
  bg.ellipse(width/2, height/2, width/8, height/8);  
  bg.filter(BLUR, 10);

  bg.fill(30, 0.2, 1);
  bg.ellipse(width/2, height/2, width/20, height/20);  
  bg.filter(BLUR, 3);
  bg.endDraw();
}

