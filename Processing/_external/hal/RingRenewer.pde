class RingRenewer {
  ArrayList<Ring> rings; 
  int keyFrame = 20;    //by resetting it to 20, there is a delay between stopping and renewing
  int keyIndex = 0;
  int nbrRings;

  RingRenewer( ArrayList<Ring> rings) {
    this.rings=rings;
    nbrRings = rings.size();
  }

  // for each ring there are three stages
  // * the ring's entire pattern is set to false
  // * the ring's pattern is renewed, saturation is set to 0
  // * the saturation is set to the default value, new hue is picked
  // number of key frames are nbrRings + 2
  void renew(int count, int seqLength) {
    if (count == keyFrame) {
      //boundary cases in the beginning
      if (keyIndex == 0) {
        rings.get(keyIndex).clearPattern();
        
      } 
      else if (keyIndex == 1) {
        rings.get(keyIndex).clearPattern();
        rings.get(keyIndex-1).generatePattern();
        rings.get(keyIndex-1).sat = 0;
      } 
      else if (keyIndex >= 2 && keyIndex <= nbrRings-1) {
        rings.get(keyIndex).clearPattern();
        
        rings.get(keyIndex-1).generatePattern();
        rings.get(keyIndex-1).sat = 0;
        
        rings.get(keyIndex-2).sat = 1;
        rings.get(keyIndex-2).pickHue();
      }
      else if (keyIndex == nbrRings) {
        rings.get(keyIndex-1).generatePattern();
        rings.get(keyIndex-1).sat = 0;
        
        rings.get(keyIndex-2).sat = 1;
        rings.get(keyIndex-2).pickHue();
      }
      else if (keyIndex == nbrRings + 1) {
        rings.get(keyIndex-2).sat = 1;
        rings.get(keyIndex-2).pickHue();
      }



      // update next keyframe
      keyIndex++;
      keyFrame = int(keyIndex*seqLength/(nbrRings+2.)/4)+20;
      // now, there is a lot of potential for bugs in this.
      // by adding a constant to the keyFrame calculation, we run the risk of 
      // a new sequence being selected before all the rings have been renewed.
      // another problem might be if the renewal animation is so quick that
      // two keyFrames become equal, so that some steps are missed in the
      // animation. with the current default seqLength, this works, but if 
      // there are problems, this class deserves a closer look.
    }
  }
  
  void reset() {
    keyFrame=20;
    keyIndex=0;
  }
}

