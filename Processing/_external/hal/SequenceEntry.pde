class SequenceEntry {
  int ringIndex;
  float maxSpin;
  float minSpin;
  
  SequenceEntry(int index, float maxSpin, float minSpin) {
    this.ringIndex = index;
    this.maxSpin = maxSpin;
    this.minSpin = minSpin;
  }
}
