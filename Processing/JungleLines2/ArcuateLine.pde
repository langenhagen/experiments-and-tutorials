public class ArcuateLine {

  float x0, y0;

  int nextChange = 0;
  float angle = random(TWO_PI);
  float angleIncr = 0.2;
  float widthIncr = 0.3;
  float lengthIncr = 0.3;

  float maxWidth = 40;
  float maxLength = 30;
  float lineLength = maxLength;
  float lineWidth = maxWidth;

  color backColor;
  color foreColor;

  // CONSTRUCTOR

  public ArcuateLine( float x0, float y0, color backColor, color foreColor)
  {
    this.x0 = x0;
    this.y0 = y0;

    this.backColor = backColor;
    this.foreColor = foreColor;
  }


  // METHODS

  public void draw()
  {
    // begin & end points of bg line and foreground line
    float x1 = constrain(x0 + lineLength * cos(angle), -lineWidth, width + lineWidth);
    float y1 = constrain(y0 + lineLength * sin(angle), -lineWidth, height + lineWidth);

    float x2 = constrain(x0 + 4 * cos(angle), -lineWidth, width + lineWidth); 
    float y2 = constrain(y0 + 4 * sin(angle), -lineWidth, height + lineWidth);

    // bg line
    strokeWeight(lineWidth + 4);
    stroke(backColor);
    line(x2, y2, x1, y1);

    // foreground line
    strokeWeight(lineWidth);
    stroke(foreColor);
    line(x0, y0, x1, y1);

    x0 = x1;
    y0 = y1;

    lineWidth -= widthIncr;
    lineLength -= lengthIncr;
    angle += angleIncr;


    // plan next change
    if (0 == nextChange) {
      angleIncr = -angleIncr;
      
      nextChange = int(random(1, 50));
        }
    --nextChange;

    if (lineWidth < 2 || lineWidth > maxWidth) {
      widthIncr = -widthIncr;
    }

    if (lineLength < 5 || lineLength > maxLength) {
      lengthIncr = -lengthIncr;
    }
  }
    
}
