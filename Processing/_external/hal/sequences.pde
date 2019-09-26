// some functions for generating specific types of sequences and damping values
// 
// void seq#(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength)



// add a stop entry for all rings at time t
void stopAll(LinkedList<SequenceEntry>[] seq, int nbrRings, int t) {
  seq[t] = new LinkedList<SequenceEntry>();
  for (int i = 0; i < nbrRings; i++) {
    SequenceEntry seqEntry = new SequenceEntry(i, 0, 0);
    seq[t].add(seqEntry);
  }
}

//cascade start stop inwards
void seq0(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  for (int i = nbrRings-1; i >= 0; i--) {
    damping[i] = 1;
    int dir = (random(0, 1) < 0.5 ? 1 : -1);
    int time = (nbrRings-1-i)*seqLength/(nbrRings+1);
    SequenceEntry seqEntry = new SequenceEntry(i, 0.1*dir, 0.1*dir);
    seq[time] = new LinkedList<SequenceEntry>();
    seq[time].add(seqEntry);
    if (i != nbrRings-1) {
      SequenceEntry seqEntry2 = new SequenceEntry(i+1, 0, 0);
      seq[time].add(seqEntry2);
    }
  }
  stopAll(seq, nbrRings, seqLength-1);
}

//cascade start stop outwards
void seq1(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = 1;
    int dir = (random(0, 1) < 0.5 ? 1 : -1);
    int time = i*seqLength/(nbrRings+1);
    SequenceEntry seqEntry = new SequenceEntry(i, 0.3*dir, 0.1*dir);
    seq[time] = new LinkedList<SequenceEntry>();
    seq[time].add(seqEntry);
    if (i != 0) {
      SequenceEntry seqEntry2 = new SequenceEntry(i-1, 0, 0);
      seq[time].add(seqEntry2);
    }
  }
  stopAll(seq, nbrRings, seqLength-1);
}

// random vel, damping 1
void seq2(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  seq[0] = new LinkedList<SequenceEntry>();
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = random(0.95, 1);
    int dir = (random(0, 1) < 0.5 ? 1 : -1);
    SequenceEntry seqEntry = new SequenceEntry(i, 0.1 * dir, 0.04 * dir);
    seq[0].add(seqEntry);
  }
}

// high damping, random vels to random rings at random times
void seq3(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  int nbrImpulses = 20;
  for (int i = 0; i <nbrImpulses; i++) {
    int time = int(random(0, seqLength));
    if (seq[time] == null) {
      seq[time] = new LinkedList<SequenceEntry>();
    }
    int dir = (random(0, 1) < 0.5 ? 1 : -1);
    seq[time].add(new SequenceEntry(int(random(0, nbrRings)), 0.1*dir, 0.1*dir));
  }

  for (int i = 0; i<nbrRings; i++) {
    damping[i] = 0.9;
  }
}


//cascade start same dir outwards
void seq4(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  stopAll(seq, nbrRings, 0);
  int dir = (random(0, 1) < 0.5 ? 1 : -1);
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = 1;
    int time = i*seqLength/(nbrRings+1);
    SequenceEntry seqEntry = new SequenceEntry(i, 0.1*dir, 0.04*dir);
    if (seq[time] == null) {
      seq[time] = new LinkedList<SequenceEntry>();
    }
    seq[time].add(seqEntry);
  }
}


//cascade start same dir inwards
void seq5(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  stopAll(seq, nbrRings, 0);
  int dir = (random(0, 1) < 0.5 ? 1 : -1);
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = 1;
    int time = i*seqLength/(nbrRings+1);
    SequenceEntry seqEntry = new SequenceEntry(nbrRings-1-i, 0.1*dir, 0.04*dir);
    if (seq[time] == null) {
      seq[time] = new LinkedList<SequenceEntry>();
    }
    seq[time].add(seqEntry);
  }
}

// all start synched, inwards damping decay
void seq6(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  int dir = (random(0, 1) < 0.5 ? 1 : -1);
  seq[0] = new LinkedList<SequenceEntry>();
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = map(i, 0, nbrRings-1, 0.93, 0.98);
    SequenceEntry seqEntry = new SequenceEntry(i, 0.1*dir, 0.1*dir);
    seq[0].add(seqEntry);
  }
}

// all start synched,outwards damping decay
void seq7(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  int dir = (random(0, 1) < 0.5 ? 1 : -1);
  seq[0] = new LinkedList<SequenceEntry>();
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = map(i, 0, nbrRings-1, 0.98, 0.93);
    SequenceEntry seqEntry = new SequenceEntry(i, 0.1*dir, 0.1*dir);
    seq[0].add(seqEntry);
  }
}

// inner half start then stop and outer half starts
void seq8(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = 1;
  }
  int dir = (random(0, 1) < 0.5 ? 1 : -1);
  stopAll(seq, nbrRings, 0);
  for (int i = 0; i < nbrRings/2; i++) {
    seq[0].add(new SequenceEntry(i, 0.1*dir, 0.05*dir));
  }
  stopAll(seq, nbrRings, seqLength/2);  
  dir = (random(0, 1) < 0.5 ? 1 : -1);
  for (int i = nbrRings/2; i < nbrRings; i++) {
    seq[seqLength/2].add(new SequenceEntry(i, 0.1*dir, 0.05*dir));
  }
}

// even start then stop and odd starts
void seq9(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  for (int i = 0; i < nbrRings; i++) {
    damping[i] = 1;
  }
  int dir = (random(0, 1) < 0.5 ? 1 : -1);
  stopAll(seq, nbrRings, 0);
  for (int i = 0; i < nbrRings; i+=2) {
    seq[0].add(new SequenceEntry(i, 0.2*dir, 0.1*dir));
  }
  stopAll(seq, nbrRings, seqLength/2);  
  dir = (random(0, 1) < 0.5 ? 1 : -1);
  for (int i = 1; i < nbrRings; i+=2) {
    seq[seqLength/2].add(new SequenceEntry(i, 0.2*dir, 0.1*dir));
  }
}

// all stop, renew rings
void seq10(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  stopAll(seq, nbrRings, 0); 
  renewRings();
}

// all start same dir, then stop at random time
void seq11(LinkedList<SequenceEntry>[] seq, float [] damping, int nbrRings, int seqLength) {
  int dir = (random(0, 1) < 0.5 ? 1 : -1);
  seq[0] = new LinkedList<SequenceEntry>();
  for (int i = 0; i < nbrRings; i++) {
    seq[0].add(new SequenceEntry(i, 0.1*dir, 0.05*dir));
    damping[i] = 1;
  }
  for (int i = 0; i < nbrRings; i++) {
    int time = int(random(seqLength));
    if (seq[time] == null) {
      seq[time] = new LinkedList<SequenceEntry>();
    }
    seq[time].add(new SequenceEntry(i, 0, 0));
  }
}

