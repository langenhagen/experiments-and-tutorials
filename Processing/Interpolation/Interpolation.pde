final String sketchName = "Interpolation";

// TODO speedup

PImage image;

int numHorizontalElements = 4;
int numVerticalElements = 6;

void setup() {
  image=loadImage("Ary_Scheffer_-_The_Temptation_of_Christ_(1854).jpg");
  size(image.width, image.height, P3D);
  
  noStroke(); 
}

void draw() {
  setWinTitleAndFps();
  tint(255, 24);
  image(image, 0, 0);
    
  numHorizontalElements = ceil(max(1, mouseX/2.0f));
  numVerticalElements = ceil(max(1, mouseY/2.0f));
 
  //linearInterpolation( numHorizontalElements, numVerticalElements);
  
  // really not much better than linear interpolation, only at very low resulution, but slower...
  bicubicInterpolation( numHorizontalElements, numVerticalElements);  
}

void bicubicInterpolation( int numHorizontalElements, int numVerticalElements) {
  int w = ceil((width)/float(numHorizontalElements));
  int h = ceil((height)/float(numVerticalElements));
  
  double[][] r = new double[4][4];
  double[][] g = new double[4][4];
  double[][] b = new double[4][4];
  
  // central spot: c[1][1] c[2][1]
  //               c[1][2] c[2][2]
  
  for( int x = -1; x<=numHorizontalElements; ++x) {
    for( int y = -1; y<=numVerticalElements; ++y) {
      // derive the four x four x/y-coordinates
      int x0 = x*w-w < 0 ? 0 : x*w-w;
      int x1 = x*w;
      int x2 = x*w+w;
      int x3 = x*w+w+w >= width ? width-1 : x*w+w+w;
      
      int y0 = y*h-h < 0 ? 0 : y*h-h;
      int y1 = y*h;
      int y2 = y*h+h;
      int y3 = y*h+h+h >= height ? height-1 : y*h+h+h;
      
      r[0][0] = red(get(x0, y0));
      r[0][1] = red(get(x0, y1));
      r[0][2] = red(get(x0, y2));
      r[0][3] = red(get(x0, y3));
      r[1][0] = red(get(x1, y0));
      r[1][1] = red(get(x1, y1));
      r[1][2] = red(get(x1, y2));
      r[1][3] = red(get(x1, y3));
      r[2][0] = red(get(x2, y0));
      r[2][1] = red(get(x2, y1));
      r[2][2] = red(get(x2, y2));
      r[2][3] = red(get(x2, y3));
      r[3][0] = red(get(x3, y0));
      r[3][1] = red(get(x3, y1));
      r[3][2] = red(get(x3, y2));
      r[3][3] = red(get(x3, y3));
      
      g[0][0] = green(get(x0, y0));
      g[0][1] = green(get(x0, y1));
      g[0][2] = green(get(x0, y2));
      g[0][3] = green(get(x0, y3));
      g[1][0] = green(get(x1, y0));
      g[1][1] = green(get(x1, y1));
      g[1][2] = green(get(x1, y2));
      g[1][3] = green(get(x1, y3));
      g[2][0] = green(get(x2, y0));
      g[2][1] = green(get(x2, y1));
      g[2][2] = green(get(x2, y2));
      g[2][3] = green(get(x2, y3));
      g[3][0] = green(get(x3, y0));
      g[3][1] = green(get(x3, y1));
      g[3][2] = green(get(x3, y2));
      g[3][3] = green(get(x3, y3));
      
      b[0][0] = blue(get(x0, y0));
      b[0][1] = blue(get(x0, y1));
      b[0][2] = blue(get(x0, y2));
      b[0][3] = blue(get(x0, y3));
      b[1][0] = blue(get(x1, y0));
      b[1][1] = blue(get(x1, y1));
      b[1][2] = blue(get(x1, y2));
      b[1][3] = blue(get(x1, y3));
      b[2][0] = blue(get(x2, y0));
      b[2][1] = blue(get(x2, y1));
      b[2][2] = blue(get(x2, y2));
      b[2][3] = blue(get(x2, y3));
      b[3][0] = blue(get(x3, y0));
      b[3][1] = blue(get(x3, y1));
      b[3][2] = blue(get(x3, y2));
      b[3][3] = blue(get(x3, y3));
      
      CachedBicubicInterpolator redInterpolator   = new CachedBicubicInterpolator();
      CachedBicubicInterpolator greenInterpolator = new CachedBicubicInterpolator();
      CachedBicubicInterpolator blueInterpolator  = new CachedBicubicInterpolator();
      
      redInterpolator.updateCoefficients(r);
      greenInterpolator.updateCoefficients(g);
      blueInterpolator.updateCoefficients(b);
      
      for(double offX=0; offX<w; ++offX) {
        for( double offY=0; offY<h; ++offY) {

          double dblOffx = offX/w;
          double dblOffy = offY/h;
          
          int cr = (int)redInterpolator.getValue(dblOffx, dblOffy);
          int cg = (int)greenInterpolator.getValue(dblOffx, dblOffy);
          int cb = (int)blueInterpolator.getValue(dblOffx, dblOffy);
          
          set((int)(x1+offX), (int)(y1+offY), color(cr,cg,cb));
        }
      }
    }
  } 
}

void linearInterpolation( int numHorizontalElements, int numVerticalElements) {

  int w = ceil((width)/float(numHorizontalElements));
  int h = ceil((height)/float(numVerticalElements));
  
  for( int x = 0; x<numHorizontalElements; ++x) {
    for( int y = 0; y<numVerticalElements; ++y) {
      
      
      color c1 = get(x*w, y*h);     //upper left
      color c2 = get(x*w+w, y*h);   //upper right
      color c4 = get(x*w+w, y*h+h); //lower left
      color c3 = get(x*w, y*h+h);   //lower right
      
      for(int offX=0; offX<w; ++offX) {
        for( int offY=0; offY<h; ++offY) {
          
          float influence1 = 1-(offX/w - offY/h);
          float influence2 = offX/w;


          
          // linear interpolation
          float r = 1.0/(w*h) * ( red(c1)*(w-offX)*(h-offY) +
                                  red(c2)*(offX)*( h-offY) +
                                  red(c3)*(w-offX)*(offY) +
                                  red(c4)*(offX)*(offY));
          float g = 1.0/(w*h) * ( green(c1)*(w-offX)*(h-offY) +
                                  green(c2)*(offX)*( h-offY) +
                                  green(c3)*(w-offX)*(offY) +
                                  green(c4)*(offX)*(offY));
          float b = 1.0/(w*h) * ( blue(c1)*(w-offX)*(h-offY) +
                                  blue(c2)*(offX)*( h-offY) +
                                  blue(c3)*(w-offX)*(offY) +
                                  blue(c4)*(offX)*(offY));
          
          
          set(x*w+offX, y*h+offY, color((int)r,(int)g,(int)b ));
        }
      }
      
    }
  }
}



void keyPressed() {
  
  // beware of the difference between key and keyCode
  if( keyCode == 123) {
    // F12
    save("result_" + timestamp() + ".png");
  }
  if( key == 'p') {
    // PRINT SOMETHING
    println( " => " +sketchName + " " + millis() + " ms [" + (frameRate) + " fps]");
  }
  
  //println("(int)key: " + (int)key + "\tkey: " + key + "\tkeyCode: " + keyCode);
}


// STANDARD HELPER FUNCTIONS //////////////////////////////////////////////////////////////////////

String timestamp() {
  return year() + nf(month(), 2) + nf(day(), 2) + "-"  + nf(hour(), 2) + nf(minute(), 2) + nf(second(), 2);
}


void setWinTitleAndFps() {
  if( frameCount % 100 == 0)
    frame.setTitle( sketchName + " [" + (frameRate) + " fps]");
}
