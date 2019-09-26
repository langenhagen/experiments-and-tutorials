PImage image;      // Source image


ArrayList <Seeker> seekersArray;
float seekX, seekY;
//worm's length
int maxLength;
final int maxLayers = 10;
//like pixels[], to keep track of how many times a point is reached by a worm
int [] buffer;
int [] limiteDown;
//number of worms at the beginning
int numWorms;
final float inertia = 6;
//brightness of the destination pixel
float maxBrightness;
//minimum brightness threshold
final int seuilBrillanceMini = 0;
//maximum brightness threshold
final int seuilBrillanceMaxi = 200;
//location of the tested point in pixels[]
int locTaX;
int locTaY;

//around a point (worms position), how many pixels will we look at...
int amplitudeTest = 1;
//constrain the acceleration vector into a devMax radius circle
float devMax;
//constrain the speed vector into a vMax radius circle
float vMax = 50;
//stroke's weight (slider) or radius of ellipse used for drawing
float myweight;


void setup() {
  
  image = loadImage("Leily & moi.jpg");
  
  size(image.width, image.height);
  
  image.loadPixels();

  smooth();
  strokeJoin(ROUND);
  
  initialiser();

}


void initialiser() { 
  numWorms=6;
  fill( 10 );
  stroke( 255 );
  background(255);
  buffer=new int[height*width];


  myweight=.2;
  

  
  seekersArray=new ArrayList <Seeker>();

  for (int i=0; i< numWorms; i++) {

    Seeker seeker = new Seeker( new PVector(random(width), random(height)), 
                                new PVector(random(-3, 3), random(-3, 3)),
                                inertia);

    float brightness = brightness(seeker.getImgPixel());
    while( brightness > seuilBrillanceMaxi ||
           brightness < seuilBrillanceMini) {
           
      seeker.position = new PVector( int(random(width)), int(random(height)));
    }
    seekersArray.add(seeker);
  }
  println("init done");
}

void draw() {

  devMax = mouseX / 100.0f;
  myweight = mouseY / 100.0f;
  
  for( int i=0; i<seekersArray.size(); ++i) {
    Seeker s = seekersArray.get(i);
    
    s.draw();
    s.deplace = false;
  }
}

void mousePressed() {
  
  initialiser();
}



public class Seeker {

  public float maxLength = 300;
  
  public PVector position;
  public PVector velocity;
  public float inertia;
  
  // worm's length
  public float length;
  // worm's limite
  public int limiteDown;
  
  public boolean deplace;
  
  // stroke weight
  public float dia;


  // Constructor
  public Seeker(PVector position, PVector velocity, float inertia) {
    this.position = position;
    this.velocity = velocity;
    this.limiteDown = 0;
    this.length = 0;
    this.inertia = random(-2, 2) + inertia;
    this.deplace = false;
  }

  
  void draw() {
    
    
    //aimed direction
    PVector vise=new PVector();

    // for each "seeker" (worm's head)
    // //for each seeker, we gonna test pixels around the seeker's position
    // and calculate their barycenter, loaded by pixels values (0/255
    // dark/light);
    // barycenter's coordinates
    
    // avoid looking for mySeeker.position.x for every pixels
    seekX = position.x;
    seekY = position.y;
    int pixelsPosition = floor(seekX) + floor(seekY) * width;
    int locTestX = floor(seekX);
    int locTestY = floor(seekY);
  
    // barycenter calculation
    for (int i = -amplitudeTest; i < amplitudeTest + 1; i++) {// /rdessin
      for (int j = -amplitudeTest; j < amplitudeTest + 1; j++) {
        locTaX = locTestX + i;
        locTaY = locTestY + j;
        // does the point belongs to the image image?
        if ((locTaX > 0) && (locTaY > 0) && (locTaX < width - 1) && (locTaY < height - 1)) {
          int brightnessTemp = int(brightness(image.pixels[locTaX + width * locTaY]));
          vise.sub(new PVector(i * brightnessTemp, j * brightnessTemp));
        }
      }
    }
    // core of the behaviour of the seeker (http://www.shiffman.net/ see
    // wanderer's code)
  
    vise.normalize();
    vise.mult(100f/inertia);
    velocity.add(new PVector(vise.x, vise.y));
    PVector deviation = velocity.get();
    deviation.normalize();
    deviation.mult(devMax);
    velocity.normalize();
    velocity.mult(vMax);
    position.add(deviation);
    
    // ******************different cases that lead to move the seeker to
    // another random place**************
    // outside window
    // worm's length is increased
    
    float positionBrightness=brightness(getImgPixel());
    
    
    // seeker's moved if worm's too long
    if (++length > maxLength) {
      deplacePoint();
    }
    if( position.x < 1 || 
        position.y < 1 || 
        position.x > width - 1 || 
        position.y > height - 1) {
      
      deplace = true;
      deplacePoint();
      return; // TODO
    }
    // buffer est une copie vide de l'image. on l'augmente pour chaque point
    // parcouru
    // buffer is an empty copy of the image image. It's increased every
    // time a point is reached.
    buffer[pixelsPosition]++;
    // si on est passe plus de n fois on demenage le point
    // If a point is reached n times, seeker is moved
    if (buffer[pixelsPosition] > maxLayers) {
      deplacePoint();
    }
  
    // inside window, limite on and inside value range
    if ( positionBrightness <= seuilBrillanceMaxi && 
         positionBrightness >= seuilBrillanceMini &&
         limiteDown != 0 ) {
      
        limiteDown = limiteDown - 2;
      }
      
    // limite on and outside value range
    if ( positionBrightness > seuilBrillanceMaxi || 
         positionBrightness < seuilBrillanceMini) {
      
      if (limiteDown == 0) {
        limiteDown =2;
      }
      
      limiteDown = limiteDown + 4;
      
      if (limiteDown >= 152 / myweight) {
      
        limiteDown = 0;
        deplacePoint();
      }
    }
    
    // null deviation
    if( deviation.x == 0 &&
        deviation.y == 0) {
          
      limiteDown =0;
      deplacePoint();
    } else {
      maxBrightness = brightness(image.pixels[pixelsPosition]);
    }
    
    // go draw the seeker's shape
    dia = (float) (myweight * (1 - cos(length * PI * 2 / (float) maxLength)));
    float alpha = max(0, round(127 * dia / myweight) - (int) maxBrightness / 2 );
    
    color c = image.get((int)position.x, (int)position.y);
    stroke(c, alpha);
    
    strokeWeight(dia);
    line(seekX, seekY, position.x, position.y);

    
    // from times to times a new worm is created
    if (random(1) > 1 - (255 - maxBrightness) / (500 * seekersArray.size())) {
      seekersArray.add(new Seeker(new PVector(seekX, seekY), new PVector(velocity.x * random(-3, 3), velocity.x
        * random(-3, 3)), inertia));
      numWorms++;
  
    }
  }
  
  void deplacePoint() {
    length =0;
    position = new PVector(random(1, width - 1), random(1, height - 1));
    
    float brightness = brightness(getImgPixel());
    
    while ( brightness > seuilBrillanceMaxi || 
            brightness < seuilBrillanceMini) {

      brightness = brightness(getImgPixel());
      
      position = new PVector(random(0, width), random(0, height));
    }
    seekX = position.x;
    seekY = position.y;
  }
  

  public int getImgPixel() {
    if( position.x >= 0 && position.x < width &&
        position.y >= 0 && position.y < height) {
      return image.pixels[floor(position.x)+floor(position.y)*width];
    } else {
      println("Out of range");
      return 0;
    }
  }
}

