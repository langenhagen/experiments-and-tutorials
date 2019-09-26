////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Klasse LGS zum Arbeiten und Loesen von linearen Gleichungssystemen mittels                             //
// einer Abwandlung des Gauss-Algorithmus                                                                 //
//                                                                                                  			//
// - Funktionen unter Java                                                                                //
//																																																				//
// - benoetigt die Klasse Fraction																																				//
// - es sind nur Gleichungssysteme mit maximal 128 Unbekannten loesbar!																		//
//																																																				//
// Author: Barn																																														//
// Version: 251009                                                                                        //
////////////////////////////////////////////////////////////////////////////////////////////////////////////
package lgs_testing;

import java.util.Vector;

public class LGS{

Vector<Vector<Fraction[]>> lgs;   // Das LGS
byte numOfXs;                      // Anzahl der Unbekannten
Fraction[] vars;                       // Die Werte der Unbekannten

  // Konstruktoren ////////////////////////////////////////////////////////////

  /**
   * Standardkonstruktor
   * erstellt eine 3x3 Einheitsmatrix mit 1 als jeweilige Lösung
   */
  public LGS(){
    numOfXs = 3;
    vars = new Fraction[3];

    Fraction[] l1 = { new Fraction(1), new Fraction(0), new Fraction(0), new Fraction(1) };
    Fraction[] l2 = { new Fraction(0), new Fraction(1), new Fraction(0), new Fraction(1) };
    Fraction[] l3 = { new Fraction(0), new Fraction(0), new Fraction(1), new Fraction(1) };

    lgs = new Vector<Vector<Fraction[]>>();
    lgs.add( new Vector<Fraction[]>()); // Urspruengliche Mat!
    lgs.add( new Vector<Fraction[]>()); // 1. Iterationsschritt
    lgs.add( new Vector<Fraction[]>()); // 2. Iterationsschritt
    lgs.get(0).add(l1);
    lgs.get(0).add(l2);
    lgs.get(0).add(l3);
  }

  /**
   * Konstruktor #2
   * -nimmt einen Vektor von Fraction-Arrays entgegen
   * -die Arrays stellen die Zeilen mit den Faktoren dar (samt Loesungsteil)
   */
  public LGS(Vector<Fraction[]> lgs) {
    numOfXs = (byte)(lgs.size()-1);
    vars = new Fraction[numOfXs];

    this.lgs = new Vector<Vector<Fraction[]>>();
    this.lgs.add(lgs); // erstes Subset einrichten
    
    for(byte i=1; i<numOfXs; i++) // restliche Subsets einrichten
      this.lgs.add( new Vector<Fraction[]>());
  }

  // Getter- / Setter /////////////////////////////////////////////////////////

  // TODO
  
  // Repraesentationsmethoden /////////////////////////////////////////////////

  // TODO
  
  // Arithmetische Helper /////////////////////////////////////////////////////

  /**
   * baut die eine Zeilenstufenform aus dem LGS
   * und stellt den ersten Teil des Gauss-Algorithmus dar
   * -Rueckgabewerte: 0 - ZSF erreicht
   *                  1 - Losung nicht eindeutig
   *                  2 - keine Loesung vorhanden
   */
  private byte buildZSF(){
    deleteZeroLines();

    for( byte istep=0; istep<numOfXs-1; istep++){
      
      sortProper(istep);

      if( isInconsistent(istep))
        return 2; // LGS inkonsistent .....................

      // ZSF vorzeitig erreicht...
      byte cntr = 0;
      for(byte it=0; it< numOfXs; it++)
        if( s(it).size()>0)
          cntr++;
      if( cntr == numOfXs)
        return 0; // ZSF erreicht .........................

      byte ipp = (byte)(istep+1);
      byte numToGet = (byte)(numOfXs - (ipp) - s(ipp).size());

      // Walzen aller moeglichen neuen Gleichungen
      for( short l1=0; l1< s(istep).size(); l1++){
        for( short l2=(short)(l1+1); l2<s(istep).size(); l2++){

          s(ipp).add(eraseVarAt(istep, l1, l2, istep, istep));

          // wenn Ergebnis ne Nullzeile is oder identisch
          // mit vorhandenen Zeilen im selben Subset, loeschen
          if( isZeroLine((short)(s(ipp).size()-1), ipp) ||
              isDuplicate((short)(s(ipp).size()-1), ipp))
            s(ipp).remove(s(ipp).size()-1);
          else
            numToGet--;

          if(numToGet == 0) // fertig mit dieser Iterationsstufe?
            break;
        } // END inner for
        if(numToGet == 0) // fertig mit dieser Iterationsstufe?
          break;
      } // END outer for
    } // END iterationen durch die Steps

    // ZSF erreicht?
    byte cntr = 0;
    for(byte it=0; it< numOfXs; it++)
      if( s(it).size()>0)
        cntr++;
    if( cntr != numOfXs) // unendlch viele Loesungen ......
      return 1;
    return 0; // ZSF erreicht .............................
  }

  /**
   * errechnet nach erreichter ZSF die absoluten Werte
   * der Unbekannten, speichert sie im 'vars'-Array
   * und gibt dieses zurueck
   */
  private Fraction[] getAbsVals(){

    sortProper((byte)0);
    for( byte istep1=0; istep1<numOfXs; istep1++){
      for( byte istep2=(byte)(istep1+1); istep2<numOfXs; istep2++){
        Fraction[] line;
        if( s(istep1).get(0)[istep2].getZ() == 0)
          line = s(istep1).get(0);
        else
          line = eraseVarAt(istep2, (short)0, (short)0, istep1, istep2);

        s(istep1).remove(0);
        s(istep1).add(0, line);
      }
      vars[istep1] = s(istep1).get(0)[s(istep1).get(0).length-1];
    }
    return vars;
  }

  /**
   * eliminiert eine Variable durch Multiplikation & Addition zweier
   * Lines und gibt das Ergebnis zurueck
   * -bei ungueltigen Parametern wird 'null' zurueck gegeben
   * -schlaegt fehl, sollte eine der beiden Lines an der
   *  uebergebenen Position gleich Null sein!
   */
  private Fraction[] eraseVarAt
    ( byte pos, short line1, short line2, byte subset1, byte subset2){
    // Fehler abfangen
    if( pos>=numOfXs || line1>=s(subset1).size() || line2>=s(subset2).size())
      return null;

    Fraction[] ret = new Fraction[ s(subset1).get(0).length];

    // Faktor getten --------------------------
    Fraction v1 = s(subset1).get(line1)[pos];
    Fraction v2 = s(subset2).get(line2)[pos];
    Fraction f = v2.divide(v1).reduce();

    ret = multiplyLine(line1, subset1, f.negative());

    // Addition durchfuerhen, Stelle ist danach eliminiert
    for(int i= ret.length-1; i>=0; i--)
      ret[i]= ret[i].add( s(subset2).get(line2)[i]);

    return ret;
  }

  /**
   * multipliziert eine Line in einer entsprechenden Iterationsstufe
   * mit einer Fraction und gibt das Ergebnis zurueck
   */
  private Fraction[] multiplyLine( short line, byte subset, Fraction f){
      Fraction[] ret = new Fraction[ s(subset).get(0).length];

      for(int i=s(subset).get(0).length-1; i>=0; i--)
        ret[i]= s(subset).get(line)[i].multiply( f);

      return ret;
    }

  /**
   * packt alle existierenden Zeilen aller Subsets
   * ab dem Uebergebenen in ihre jeweils
   * adaequaten Iterationsstufen
   * -Achtung: sollte erst ausgefuehrt werden, wenn
   * keine ZeroLines existieren & isInconsistent ausgefuehrt wurde!
   */
  private void sortProper( byte subset){
    for(; subset<numOfXs; subset++)
      for(short i=0; i< s(subset).size(); i++){
        Fraction[] tmp = s(subset).get(i);
        if( tmp[subset].getN() == '0'){
          s(subset).remove(i);
          s((byte)(subset+1)).add(tmp);
        }
      }
  }

  // weitere Helper ///////////////////////////////////////////////////////////
  
  /**
   * gettet das n-te Subset
   */
  private Vector<Fraction[]> s( byte n){ return lgs.get(n);}

  /**
   * gibt an, ob es innerhalb des angegebenen Subsets
   * widerspruechliche Zeilen gibt. Wenn ja, dann hat das LGS
   * keine Loesung
   * -schlaegt fehl, wenn in einem der zu bearbeitenden Subsets
   *  eine Line enthalten ist, die nicht in dieses Subset gehoert!
   */
  private boolean isInconsistent( byte subset){

    for(short i=0; i<s(subset).size(); i++)       // itert durch die Lines
      for( short j=(short)(i+1); j<s(subset).size(); j++){   // itert durch die Lines2

        byte cntr = 0;
        Fraction[] tmp = eraseVarAt(subset, i, j, subset, subset);
        for( byte k=0; k<tmp.length; k++)         // itert durch die tmpLine
          if( tmp[k].getN() != 0)
            cntr++;
        if( tmp[tmp.length-1].getN() != 0 && cntr == 1) // letzte Stelle nich 0
          return true;
      }
    return false;
  }

  /**
   * gibt an, ob die uebergebene Zeile nur mit Nullen besetzt ist
   */
  private boolean isZeroLine( short line, byte subset){
    for( int i=s(subset).get(0).length-1; i>=0 ; i--)
      if( s(subset).get(line)[i].getZ() != 0)
        return false;
    return true;
  }

  /**
   * loescht alle '0'-Zeilen aus allen Subsets
   */
  private void deleteZeroLines(){
    for(byte sub=(byte)(numOfXs-1); sub>=0; sub--)  //Iter durch alle Subsets
      for( short l= (short)(s(sub).size()-1); l>=0; l--)  //Iter durch die Lines
        if( isZeroLine(l, sub))                 //Wenn Zeroline, dann delete
          s(sub).remove(l);
  }

  /**
   * gibt an, ob die uebergebene Line ein Duplikat einer
   * Vorhandenen des selben Subsets ist
   */
  public boolean isDuplicate( short line, byte subset){
    boolean ret = false;

    for( short i=0; i<s(subset).size(); i++){

      byte posX = subset;
      while( s(subset).get(line)[posX].getZ() == 0 ||
             s(subset).get(i)[posX].getZ() == 0)
        posX++;
      if( posX == (byte)numOfXs)
        continue;

      Fraction[] tmp = eraseVarAt(posX, line, i, subset, subset);

      s(subset).add(tmp);
      if(isZeroLine((short)(s(subset).lastIndexOf(tmp)), subset))
        ret = true;
      s(subset).remove(s(subset).size()-1);
    }
    return ret;
  }
  
} // ENDE LGS-Klasse