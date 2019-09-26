/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/151539*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */
/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/135544*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */

/*----------------------------------
 Tweak of "Anemone" by oggy. Check link above. Anomone itself is still the original code of oggy. I've added the "food" and the stearing behaviour. Sorry for the messy code.
 
 Copyright by Diana Lange 2014
 Don't use without any permission. Creative Commons: Attribution Non-Commercial.
     
 mail: kontakt@diana-lange.de
 web: diana-lange.de
 facebook: https://www.facebook.com/DianaLangeDesign
 flickr: http://www.flickr.com/photos/dianalange/collections/
 tumblr: http://dianalange.tumblr.com/
 twitter: http://twitter.com/DianaOnTheRoad
 vimeo: https://vimeo.com/dianalange/videos
    
 -----------------------------------*/

final int NB_CILIUM = 25;
final float CELL_RADIUS = 13;
Cilium[] tabCilium = new Cilium[NB_CILIUM];
float R = 324;
float G = 90;
float B = 90;
float Rspeed;
float Gspeed;
float Bspeed;

PVector food;
PVector foodDir;
float foodSpeed;
float foodD;

void setup()
{
  size(600, 600, P3D);
  //frameRate (2);
  colorMode(HSB, 360, 100, 100);
  //noCursor();

  //  strokeCap(ROUND);
  //  strokeJoin(ROUND);
  //smooth();

  food = new PVector (random (width), random (height));
  foodDir = new PVector (0, 0);
  foodSpeed = 3;
  foodD = random (3, 10);

  float angle = random (TWO_PI);
  PVector location = new PVector (random (width), random (height));
  PVector direction = new PVector (cos (angle), sin (angle));

  for (int i = 0; i < NB_CILIUM; i++)
  {
    tabCilium[i] = new Cilium(CELL_RADIUS, i * TWO_PI / NB_CILIUM, location, direction, 0.05);
  }
}

void draw()
{
  noStroke();
  background(0,0, 90);

  food.x += foodDir.x * foodSpeed;
  food.y += foodDir.y * foodSpeed;

  float noiseVal = noise (food.x / 70, food.y / 70);

  noiseVal = map (noiseVal, 0, 1, 0, TWO_PI);

  foodDir.x = cos (noiseVal);
  foodDir.y = sin (noiseVal);

  if (food.x < 0) food.x = width-1;
  else if (food.x >= width) food.x = 0;

  if (food.y < 0) food.y = height-1;
  else if (food.y >= height) food.y = 0;


  if (frameCount % 100 == 0) noiseSeed ((int) random (10000));
  if (foodD <= 0.3)
  {
    foodD = random (6, 25);

    int dice = (int) random (3);

    float x = 0;

    if (dice == 0) x = -foodD;
    else if (dice == 1) x = width+foodD;
    else x = random (width);

    float y = 0;
    if (dice == 3) y= (int) random (2) == 0 ? -foodD : height+ foodD;
    else y = random (height);


    food.x = x;
    food.y = y;

    noiseSeed ((int) random (10000));
    foodSpeed = random (1, 5);
  }

  if (PVector.dist (tabCilium[0].location, food) <= CELL_RADIUS) foodD*= 0.9;

  /*

   Rspeed = ((R += Rspeed) > 255 || (R < 0)) ? -Rspeed : Rspeed;
   Gspeed = ((G += Gspeed) > 255 || (G < 0)) ? -Gspeed : Gspeed;
   Bspeed = ((B += Bspeed) > 255 || (B < 0)) ? -Bspeed : Bspeed;
   */

  R = R*0.9 + 0.1* map (tabCilium[0].speed, 0.1, 0.65, 324, 360) ;
  G = G*0.9 + 0.1*map (tabCilium[0].speed, 0.1, 0.65, 50, 100) ;
  // println (tabCilium[0].speed);
  B = B*0.9 + 0.1* map (tabCilium[0].speed, 0.1, 0.65, 20, 100) ;

  for (int i = 0; i < NB_CILIUM; i++)
  {
    tabCilium[i].process(food.x, food.y);
  }
  for (int i = tabCilium[0].NB_POINTS-1; i >= 0; i--)//draw the tips first
  {
    for (int j = 0; j < NB_CILIUM; j++)
    {
      tabCilium[j].render(i);
    }
  }

  fill (R, G, B);
  stroke (0, 0, 90);
  strokeWeight (3);
  ellipse (food.x, food.y, foodD, foodD);

}


