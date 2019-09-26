public class Paster
{
  ArrayList<BufferElement> buffer = new ArrayList<BufferElement>();

  int maxBufferSize = 10;
  int currentFrame = 0;


  // CONSTRUCTOR

  public Paster( int maxBufferSize)
  {
    this.maxBufferSize = maxBufferSize;
  }


  // METHODS

  void drawFrame()
  {
    drawFrame(currentFrame);
  }

  void drawFrame( int frame)
  {
    image( getFrame(frame),0 , 0);
    currentFrame = frame;
  }

  // return a past frame: 0 is the last frame
  // 1 the frame before 0 etc.
  PGraphics getFrame( int frame)
  {
    int element = min(max( maxBufferSize - frame, 0), buffer.size()-1);

    return buffer.get(element).frame;
  }

  // saves the current frame and its timestamp at the end of the buffer.
  // maybe removes the first buffer elements if the buffer is full.
  void capture()
  {
    currentFrame = 0;

    PGraphics graphics = screen2PGraphics();

    BufferElement element = new BufferElement( graphics, millis());
    buffer.add( element);

    while( buffer.size() > maxBufferSize)
    {
      buffer.remove(0);
    }

  }


  PGraphics screen2PGraphics()
  {
    PGraphics ret = createGraphics( width, height);

    loadPixels();
    ret.loadPixels();

    for( int i = 0; i < pixels.length; ++i)
    {
      ret.pixels[i] = pixels[i];
    }

    ret.updatePixels();

    return ret;
  }


  // copies all pixels from to, given same pixel array size.
  void copyPixels( PGraphics from, PGraphics to)
  {
    from.loadPixels();
    to.loadPixels();

    for( int i = 0; i < from.pixels.length; ++i)
    {
      to.pixels[i] = from.pixels[i];
    }

    to.updatePixels();
  }

  // INTERNAL PAIR CLASS

  class BufferElement
  {
    public PGraphics frame; // the PGraphics Element
    public float     millis;    // The timestamp of the PGraphics Element in milliseconds

    // CONSTRUCTOR

    public BufferElement( PGraphics frame, int millis)
    {
      this.frame = frame;
      this.millis = millis;
    }
  }


}
