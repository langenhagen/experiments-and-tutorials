// taken from http://www.creativecoding.org/example/audio:frequenz-gruppen-ermitteln

import ddf.minim.*;
import ddf.minim.analysis.*;
  
Minim minim;
AudioInput input;
FFT fft;
 
float maxSpec = 0;
 
void setup () {
  size(800, 600);
  noStroke ();
   
  // Audiotool-Box und Mikrofoneingang erstellen 
  // und einrichten. Die '256' bestimmen dabei die 
  // spaetere Aufloesung des Spektrums.
  minim = new Minim (this);
  input = minim.getLineIn (Minim.STEREO, 256);
  // FFT-Instanz fuer die Spektrumsanalyse
  fft = new FFT (input.bufferSize (), 
                 input.sampleRate ());
}
  
void draw () {
  background (0);
   
  float g = 0;    // Gruenwert der Fuellfarbe
  float h = 0;    // Hoehe von Rechteck und Linie
  float specStep; // Breite einer horiz. Linie
  float specScale = (float) width / (fft.specSize () - 1);
   
  // Erzeugen der 'Frequenz-Gruppen' (16 Bereich)
  // moegliche Schritte: 2-4-8-16-32-64-128
  float[] group = getGroup (4);
   
  // Zeichnen des detailierten Frequenzspektrums
  noStroke ();
  for (int i = 0; i < fft.specSize (); i++) {
    g = map (fft.getBand (i), 0, maxSpec, 50, 255);
    h = map (fft.getBand (i), 0, maxSpec, 2, height);
    fill (0, g, 0);
    rect (i * specScale, height - h, specScale, h);
  }
   
  // Zeichnen der Gruppen (Linien)
  stroke (255, 255, 0, 200);
  specStep = width / group.length;
  for (int i=0; i < group.length; i++) {
    h = height - map (group[i], 0, maxSpec, 0, height);
    line (i * specStep, h, (i+1) * specStep, h);
  }
}
 
/** 
 * Funktion fasst das vorliegende FFT-Spektrum
 * in eine durch den Parameter 'theGroupNum' 
 * festgelegte Anzahl von gleichgroÃŸen Bereichen
 * zusammen â€“ und gibt deren Mittelwert zurueck.
 */
float[] getGroup (int theGroupNum) {
  fft.forward (input.mix);
   
  // Leeres Array fuer die Gruppen erstellen
  float[] group  = new float[theGroupNum];
  // Das FFT-Spekturm hat eine Stelle mehr 
  // als beim Input definiert. (256->257).
  // Diese wird ignoriert.
  int specLimit  = fft.specSize () - 1;
  // Anzahl der Frequenzbaender pro Gruppe
  int groupSize = specLimit / theGroupNum;
   
  // Alle Gruppen mit einem Startwert fuellen
  for (int i=0; i < group.length; i++) {
    group[i] = 0;
  }
   
  // Fuer jedes FFT-Frequenz-Band
  for (int i=0; i < specLimit; i++) {
    // Maximum?
    if (fft.getBand (i) > maxSpec) {
      maxSpec = fft.getBand (i);
    }
    // Jedes Band einer Gruppe zuweisen
    int index = (int) Math.floor (i / groupSize);
    group[index] += fft.getBand (i);
  }
   
  // Der Wert jeder Gruppe durch die Anzahl
  // der enthaltenen Baender Teilen - >Mittelwert
  for (int i=0; i < group.length; i++) {
    group[i] /= groupSize;
  }
  // Gruppe zurueck geben.
  return group;
}
