// The AbstractWanderer class (for wandering)
//
// code inspired and adopted from Daniel Shiffman's "The Nature of Code"
// http://natureofcode.com


abstract class AbstractWanderer {

  PVector position;
  PVector velocity;
  PVector acceleration;
  float r;           // ? radius
  float wanderTheta; // ?
  float maxForce;    // Maximum steering force
  float maxSpeed;    // Maximum speed

  AbstractWanderer(float x, float y) {
    acceleration = new PVector(0,0);
    velocity = new PVector(0,0);
    position = new PVector(x,y);
    r = 6;
    wanderTheta = 0;
    maxSpeed = 2;
    maxForce = 0.05;
  }

  void run() {
    update();
    borders();
  }

  // Method to update position
  void update() {
    // Update velocity
    velocity.add(acceleration);
    // Limit speed
    velocity.limit(maxSpeed);
    position.add(velocity);
    // Reset accelertion to 0 each cycle
    acceleration.mult(0);
  }

  void wander() {
    float wanderR = 25;         // Radius for our "wander circle"
    float wanderD = 80;         // Distance for our "wander circle"
    float change = 0.3;
    wanderTheta += random(-change,change);     // Randomly change wander theta

    // Now we have to calculate the new position to steer towards on the wander circle
    PVector circleloc = velocity.get();    // Start with velocity
    circleloc.normalize();            // Normalize to get heading
    circleloc.mult(wanderD);          // Multiply by distance
    circleloc.add(position);               // Make it relative to boid's position
    
    float h = velocity.heading2D();        // We need to know the heading to offset wanderTheta

    PVector circleOffSet = new PVector(wanderR*cos(wanderTheta+h),wanderR*sin(wanderTheta+h));
    PVector target = PVector.add(circleloc,circleOffSet);
    seek(target);

    // Render wandering circle, etc. 
    if (__DEBUG__)
      drawWanderStuff(position,circleloc,target,wanderR);
  }  

  void applyForce(PVector force) {
    // We could add mass here if we want A = F / M
    acceleration.add(force);
  }


  // A method that calculates and applies a steering force towards a target
  // STEER = DESIRED MINUS VELOCITY
  void seek(PVector target) {
    PVector desired = PVector.sub(target,position);  // A vector pointing from the position to the target

    // Normalize desired and scale to maximum speed
    desired.normalize();
    desired.mult(maxSpeed);
    // Steering = Desired minus Velocity
    PVector steer = PVector.sub(desired,velocity);
    steer.limit(maxForce);  // Limit to maximum steering force

    applyForce(steer);
  }

  abstract void display();

  // Wraparound
  void borders() {
    if (position.x < -r)       position.x = width+r;
    if (position.y < -r)       position.y = height+r;
    if (position.x > width+r)  position.x = -r;
    if (position.y > height+r) position.y = -r;
  }
  
  
  // A method just to draw the circle associated with wandering
  void drawWanderStuff(PVector position, PVector circle, PVector target, float rad) {
    stroke(0); 
    noFill();
    ellipseMode(CENTER);
    ellipse(circle.x,circle.y,rad*2,rad*2);
    ellipse(target.x,target.y,4,4);
    line(position.x,position.y,circle.x,circle.y);
    line(circle.x,circle.y,target.x,target.y);
  }
  
}




