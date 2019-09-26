/**
 * Mirror Dither 512
 *
 * by Windell Oskay
 *
 * Based on Mirror 2 by Daniel Shiffman. 
 * and adapted to perform Atkinson Dithering.
 *
 * For more about Atkinson dithering, see: http://verlagmartinkoch.at/software/dither/index.html
 * 
 * Sized at 512 x 342, with rounded corners, like an original Macintosh screen.
 */

import processing.video.*;

// Number of columns and rows in our system
int cols = 512;
int rows = 342; 

int rawVideoRows = round(cols*.75);


int borderWidth = 45;

// Variable for capture device
Movie video;

int mainwidth  = cols + 2 * borderWidth;
int mainheight = rows + 2 * borderWidth;

int[] GrayArray;
int GrayArrayLength;

void setup() {

  frameRate(30);

  //  size(mainwidth, mainheight, P2D);  // Faster
  size(mainwidth, mainheight, JAVA2D);  // More accurate, in general


  colorMode(RGB);

  // Uses the default video input, see the reference if this causes an error
  video = new Movie(this, "The Creation.mp4"); // must be mov

  video.play();
  video.volume(0);
  noSmooth();
  background(0);
}


void draw() { 

  float brightTot;
  int pixelCt;
  color c2;
  int idx = 0;

  if (video.available()) {
    video.read();
    video.loadPixels();

    GrayArrayLength = cols * rawVideoRows;
    int[] GrayArray = new int[GrayArrayLength];

    for (int n = 0; n < GrayArrayLength; n++)
    {
      GrayArray[n] = 0;
    } 

    // Black background:
    background(0);

    // White rectangle, rounded corners:
    fill(255);
    noStroke(); 
    rect(borderWidth, borderWidth, cols, rows, 7);   // Last digit is rounded corners

    noFill(); 
    stroke(0);
    strokeWeight(1);




    int vOffset = floor ((rawVideoRows - rows) / 2);
    int lastRow = (rawVideoRows - vOffset);
    int yBorderTot = borderWidth - vOffset;

    // Begin loop for columns
    for (int i = 0; i < cols;i++) {
      // Begin loop for rows
      for (int j = vOffset; j < lastRow; j++) {


        // Where are we, pixel-wise?
        int x = i;
        int y = j;

        int loc = (video.width - x - 1) + y*video.width; // Reversing x to mirror the image

        pixelCt = 0;
        brightTot = 0;

        float brightTemp;

        c2 = video.pixels[loc];
        brightTemp = brightness(c2);

        // Brightness correction curve:
        brightTemp =  sqrt(255) * sqrt (brightTemp);

        if (brightTemp > 255) 
          brightTemp = 255;

        if (brightTemp < 0)
          brightTemp = 0;

        int darkness = 255 - floor(brightTemp);

        idx = (j)*cols + (i);        

        darkness += GrayArray[idx];

        if ( darkness >= 128) {

          //          rect(x + borderWidth, y + borderWidth - vOffset, 1, 1);  // If using P2D
          point(x + borderWidth, y + yBorderTot);  // For use with JAVA2D only

          darkness -= 128;
        } 

        int darkn8 = round(darkness / 8);

        // Atkinson dithering algorithm:  http://verlagmartinkoch.at/software/dither/index.html          
        // Distribute error as follows:
        //     [ ]  1/8  1/8
        //1/8  1/8  1/8
        //     1/8 

          if ((idx + 1) < GrayArrayLength)
          GrayArray[idx + 1] += darkn8;
        if ((idx + 2) < GrayArrayLength)
          GrayArray[idx + 2] += darkn8;
        if ((idx + cols - 1) < GrayArrayLength)
          GrayArray[idx + cols - 1] += darkn8;
        if ((idx + cols) < GrayArrayLength)
          GrayArray[idx + cols] += darkn8;
        if ((idx + cols + 1) < GrayArrayLength)
          GrayArray[idx + cols + 1 ] += darkn8;
        if ((idx + 2 * cols) < GrayArrayLength)
          GrayArray[idx + 2 * cols] += darkn8;
      }
    }
  }
  else
    println("Video Err.");
}

