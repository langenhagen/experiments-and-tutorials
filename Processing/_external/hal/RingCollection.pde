

class RingCollection {
  ArrayList<Ring> rings; 
  int nbrRings;
  float x, y;
  int counter = 0;
  float[] damping;
  Sequence seq;
  LinkedList<SequenceEntry> currSeqEntry;
  RingRenewer renewer;
  boolean isRenewing = false;

  RingCollection(int nbr, float x, float y, float startRadius, float ringWidth, float spacing, int iNumSegments, int oNumSegments) {
    this.nbrRings = nbr;
    this.x=x;
    this.y=y;
    seq = new Sequence(nbrRings);
    seq.createSequence();
    createRings(startRadius, ringWidth, spacing, iNumSegments, oNumSegments);
    renewer = new RingRenewer(rings);
  }

  void createRings(float sr, float rw, float space, int iNbr, int oNbr) {
    rings = new ArrayList<Ring>();
    for (int i = 0; i < nbrRings; i++) {
      rings.add(new Ring(x, y, 
      sr + (i+1)*rw + i*space, 
      sr + i*(rw+space), 
      int(i/(nbrRings+1.)*(oNbr-iNbr) + iNbr)));
    }
  }

  void draw() {
    for (Ring r : rings) {
      r.draw();
    }
  }

  void update() {
    currSeqEntry = seq.getEntries(counter);
    if (currSeqEntry != null) {
      for (SequenceEntry se : currSeqEntry) {
        rings.get(se.ringIndex).dtheta = random(se.minSpin, se.maxSpin);
      }
    }
    int j = 0;
    for (Ring r : rings) {
      r.dtheta *= seq.damping[j];
      r.update();
      j++;
    }
    if (isRenewing) {
      
      renewer.renew(counter, seq.seqLength);
    }
    counter++;
    if (counter == seq.seqLength) {
      isRenewing = false;
      renewer.reset();
      seq.createSequence();
      counter = 0;
    }
  }
}

