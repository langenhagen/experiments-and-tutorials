// The sequence class is the star of this sketch.
// It consists of a timeline and a set of damping values for each ring.
// The timeline (seq) is an array with a list of events at each time index 
// from 0 to seqLength-1.
// Each event contains the index of the ring to which it applies plus two
// floats which specify the impulse that is applied to the ring at that
// time index.

import java.util.LinkedList;

class Sequence {
  int nbrRings;             //number of rings
  int seqLength;
  float[] damping;
  LinkedList<SequenceEntry>[] seq;

  Sequence(int n) {
    this.nbrRings=n;
    damping = new float[nbrRings];
  }

  void createSequence() {
    seqLength = 80;
    seq = (LinkedList<SequenceEntry>[]) new LinkedList<?>[seqLength];
    int index = int(random(0, 12));
    //index = 11;
    switch (index) {
      case 0: seq0(seq, damping, nbrRings, seqLength); 
      break;
    case 1: 
      seq1(seq, damping, nbrRings, seqLength); 
      break;
    case 2: 
      seq2(seq, damping, nbrRings, seqLength); 
      break;
    case 3: 
      seq3(seq, damping, nbrRings, seqLength); 
      break;
    case 4: 
      seq4(seq, damping, nbrRings, seqLength); 
      break;
    case 5: 
      seq5(seq, damping, nbrRings, seqLength); 
      break;
    case 6: 
      seq6(seq, damping, nbrRings, seqLength); 
      break;
    case 7: 
      seq7(seq, damping, nbrRings, seqLength); 
      break;
    case 8: 
      seq8(seq, damping, nbrRings, seqLength); 
      break;
    case 9: 
      seq9(seq, damping, nbrRings, seqLength); 
      break;
    case 10: 
      seq10(seq, damping, nbrRings, seqLength); 
      break;
    case 11: 
      seq11(seq, damping, nbrRings, seqLength); 
      break;
    }
  }

  LinkedList<SequenceEntry> getEntries(int idx) {
    return seq[idx];
  }
}

