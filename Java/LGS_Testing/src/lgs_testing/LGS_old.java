//////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Klasse LGS_old zum Arbeiten und Loesen von linearen Gleichungssystemen                                       //
// - Funktionen unter Java                                                                                  //
//																																																					//
// - benoetigt die Klasse Fraction																																					//
//																																																					//
//																																																					//
// Author: Barn																																															//
// Version: 211009                                                                                          //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
package lgs_testing;

import java.util.Vector;

public class LGS_old{

  Vector<Fraction[]> lgs;   // Das LGS_old
  short numOfXs;            // Anzahl der Unbekannten
  short linesOfLGS;         // Anzahl der urspruenglichen Zeilen

  // Konstruktoren ////////////////////////////////////////////////////////////

  /**
   * Standardkonstruktor
   * erstellt eine 3x3 Einheitsmatrix mit 1 als jeweilige Lösung
   */
  public LGS_old() {
    numOfXs = 3;
    linesOfLGS = 3;

    Fraction[] l1 = { new Fraction(1), new Fraction(0), new Fraction(0), new Fraction(1) };
    Fraction[] l2 = { new Fraction(0), new Fraction(1), new Fraction(0), new Fraction(1) };
    Fraction[] l3 = { new Fraction(0), new Fraction(0), new Fraction(1), new Fraction(1) };

    lgs = new Vector<Fraction[]>();
    lgs.add(l1);
    lgs.add(l2);
    lgs.add(l3);
    }

  /**
   * Konstruktor #2
   * -nimmt eine ArrayList von Fraction-Arrays entgegen, außerdem die
   *  Anzahl der gesuchten Parameter
   * -die Arrays stellen die Zeilen mit den Faktoren dar (samt Loesungsteil)
   * -sollte die angegebene Anzahl der Variablen kleiner oder gleich 0 sein,
   *  so wird die Anzahl auf die Groesse des LGS_old-1 gesetzt
   */
  public LGS_old(short numOfVars, Vector<Fraction[]> lgs) {
    this.lgs = lgs;
    numOfXs = numOfVars;
    linesOfLGS = (short)lgs.size();
    if( numOfVars <=0)
      numOfXs = (short)(lgs.size()-1);
  }

  // Getter / Setter //////////////////////////////////////////////////////////

  /** gibt ein Element (einen Wert aus der Matrix) aus dem LGS_old zurueck*/
  public Fraction getElem( int line, int col) { return lgs.get(line)[col];}

  /** gibt die i-te Zeile wieder */
  public Fraction[] getLine( int i){ return lgs.get(i);}

  /** gibt die i-te Spalte wieder */
  public Fraction[] getCol( int i){
    Fraction[] ret = new Fraction[lgs.size()];
    for(int j=0; j<lgs.size(); j++)
      ret[j] = lgs.get(j)[i];
    return ret;
  }

  /** das LGS_old wieder */
  public Vector<Fraction[]> getLGS(){
    Vector<Fraction[]> ret = new Vector<Fraction[]>();
    for(int i=0; i<linesOfLGS; i++)
      ret.add(lgs.get(i));
    return ret;
  }

  /** gibt alle bisherigen Gleichungen wieder */
  public Vector<Fraction[]> getAllEquations(){ return lgs;}

  /** gibt die Anzahl der Unbekannten wieder */
  public short getNumOfVars(){ return numOfXs;}
  
  /** gibt die Anzahl der LGS_old-Zeilen wieder */
  public short getNumOfLines(){ return linesOfLGS;}

  /** gibt die Anzahl der gemachten Gleichungen wieder */
  public int getNumOfAllEquations(){ return lgs.size();}

  /**
   * -nimmt einen Vektor von Fraction-Arrays entgegen, außerdem die
   *  Anzahl der gesuchten Parameter
   * -die Arrays stellen die Zeilen mit den Faktoren dar (samt Loesungsteil)
   * -sollte die angegebene Anzahl der Variablen kleiner oder gleich 0 sein,
   *  so wird die Anzahl auf die 'Groesse des LGS_old'-1 gesetzt
   */
  public void setupLGS(short numOfVars, Vector<Fraction[]> lgs){
    this.lgs = lgs;
    numOfXs = numOfVars;
    linesOfLGS = (short)lgs.size();
    if( numOfVars <=0)
      numOfXs = (short)(lgs.size()-1);
  }

  // Simple Arithmetische Methoden ////////////////////////////////////////////
  /**
   * Addiert zwei Zeilen miteinander, traegt das Ergebnis in eine
   * neue Zeile in der Gleichunsgsliste ein und
   * gibt das Ergebnis zurueck
   */
  public Fraction[] addLines( int line1, int line2){
    Fraction[] ret = new Fraction[lgs.get(0).length];
    for(int i=lgs.get(0).length-1; i>=0; i--){
      ret[i]= new Fraction(lgs.get(line1)[i]);
      ret[i].add(lgs.get(line2)[i]);
    }
    System.out.println("Line #" + lgs.size() + " = Line #" + line1 + " + Line #" + line2);
    lgs.add(ret);
    return ret;
  }

  /**
   * Multipliziert eine Zeile mit einem uebergebenen Bruch,
   * traegt das Ergebnis in eine neue Zeile in der Gleichungsliste
   * und gibt das Ergebnis zurueck
   */
  public Fraction[] multiplyLine( int line, Fraction f){
    Fraction[] ret = new Fraction[lgs.get(0).length];

    for(int i=lgs.get(0).length-1; i>=0; i--)
      ret[i]= lgs.get(line)[i].multiply( f);

    System.out.println("Line #" + lgs.size() + " = " + f.toString() + " * Line #" + line);
    lgs.add(ret);
    return ret;
  }

  /**
   * Dividiert eine Zeile mit einem uebergebenen Bruch,
   * traegt das Ergebnis in eine neue Zeile in der Gleichungsliste
   * und gibt das Ergebnis zurueck
   */
  public Fraction[] divideLine( int line, Fraction f){
    Fraction[] ret = new Fraction[lgs.get(0).length];
    for(int i=lgs.get(0).length-1; i>=0; i--){
      ret[i]= new Fraction(lgs.get(line)[i]);
      ret[i].divide(f);
    }
    System.out.println("Line #" + lgs.size() + " = " + f.toString() + " / Line #" + line);
    lgs.add(ret);
    return ret;
  }

  /**
   * Negiert eine Zeile, traegt das Ergebnis in eine neue Zeile
   * in der Gleichungsliste und gibt es zurueck
   */
  public Fraction[] negativeLine( int line){
    Fraction[] ret = new Fraction[lgs.get(0).length];
    for(int i=lgs.get(0).length-1; i>=0; i--){
      ret[i]= new Fraction(lgs.get(line)[i]);
      ret[i].negative();
    }
    System.out.println("Line #" + lgs.size() + " = - Line #" + line);
    lgs.add(ret);
    return ret;
  }

  // Komplexe arithmetische Methoden //////////////////////////////////////////
  
  /**
   * bringt zwei uebergebene Zeilen an der Stelle x auf den gleichen(negativen)
   * Faktor, indem die erste uebergebene Zeile mit dem entsprechenden Faktor
   * negiert multipliziert wird, sodass man durch Addition die Variable
   * an dieser Stelle x eliminieren kann
   * -gibt die multiplizierte Zeile in einem
   *  Fraction-Array zurueck und schreibt dieses zudem in den Gleichungsvektor
   * -sollte die uebergebene Position nicht Faktor einer Variable sein,
   *  wird 'null' zurueck gegeben
   */
  public Fraction[] matchFactorsAt( short pos, int line1, int line2){
    if( pos >= numOfXs)
      return null;

    Fraction v1 = new Fraction(lgs.get(line1)[pos]);
    Fraction v2 = new Fraction(lgs.get(line2)[pos]);

    Fraction f = v2.divide(v1).reduce();  // Faktor, mit dem line1 gemultet wird
    
    return multiplyLine(line1, f.negative());
  }

  /**
   * eliminiert eine Variable an einer uebergebenen Position, indem zwei Zeilen
   * so miteinander addiert und multipliziert werden, dass der Faktor der
   * betreffenden Variable '0' wird, schreibt das Ergebnis
   * in die Gleichunstabelle und gibt es zurueck.
   * -sollte die uebergebene Position nicht Faktor einer Variable sein,
   *  wird 'null' zurueck gegeben
   */
  public Fraction[] eraseVarAt( short pos, int line1, int line2){
    if( pos >= numOfXs)
      return null;

    matchFactorsAt(pos, line1, line2);
    Fraction[] ret = addLines( last(), line2);
    lgs.remove(last()-1);
    return ret;
  }

  // Repraesentationsmethoden /////////////////////////////////////////////////

  /** gibt die korrekte String-Repraesentation des LGS_old zurueck */
  @Override
  public String toString(){
    String ret = "";
    if( lgs.get(0).length-1 > numOfXs)
      for( int i=0; i<linesOfLGS; i++)
        ret = ret + lts2(i);
    else
      for( int i=0; i<linesOfLGS; i++)
        ret = ret + lts1(i);
    return ret;
  }

  /**
   * gibt die korrekte String-Repraesentation aller Gleichungen zurueck
   */
  public String allToString(){
    String ret = "";
    if( lgs.get(0).length-1 > numOfXs)
      for( int i=0; i<lgs.size(); i++)
        ret = ret + lts2(i);
    else
      for( int i=0; i<lgs.size(); i++)
        ret = ret + lts1(i);
    return ret;
  }

  /** gibt die korrekte String-Repraesentation einer Zeile zurueck */
  public String lineToString( int line){
    if( lgs.get(0).length-1 > numOfXs)
      return lts2(line);
    else
      return lts1(line);
  }

  // Hilfsfunktionen //////////////////////////////////////////////////////////

  /**
   * Fuellt das LGS_old mit Gleichungen, in denen 1 bis n Variablen eliminiert sind,
   * sodass man implizit eine Dreiecksmatrix gewinnt.
   * Erster Schritt großer Schritt beim Loesen des LGS_old.
   */
  private void fillEquations(){

    if( !isComplete()){
      // VERKACKT
      System.out.print( "Offenbar existieren unendlich viele Loesungen\n"
                       +"Starte Suche Nach Parametern.");
    }

    // x: Iterationsschritt und Nummer der 
    // zu eliminierenden Variable
    for( short x=0; x < numOfXs-1; x++){

      Vector<Integer> proper = getProperLines(x);

      // Propere Lines zuerst einfuegen
      for(int i=0; i<proper.size(); i++)
        lgs.add( lgs.get(proper.get(i)));

      // Fuegt je nach Iterationschritt neue Zeilen durch Variableneliminierung
      // zum Gleichungsvektor hinzu. Und zwar so viele,
      // wie man je nach IterSchritt braucht
      for(int i= iterPos(x); i< iterPos(x+1)-1-proper.size(); i++){		// checked

        // versucht so lange EINE Var zu eliminieren,
        // bis zwei unterschiedliche Zeilen entdeckt wurden,
        // bricht andernfalls ab.
        // also im Endeffekt liegt EINE Zeile eliminiert vor
        int j=i+1;
        boolean b;
        do{
          b = false;

          if( eraseVarAt( x, i, j) == null){
          System.err.print( "Falsche VariablenPos bei eraseVarAt()\n"
                          + "in fillEquations() uebergeben");
          return;
          }

          if( isZeroLine( last())){ // Falls Duplikate existen
            lgs.remove( last());
            b = true;

            if( ++j >= iterPos(x+1)){
              // VERKACKT
              System.out.print( "Offenbar existieren unendlich viele Loesungen\n"
                               +"Starte Suche Nach Parametern.");
            }
          }
        }while( b);
      }


    }
  }

  /**
  * Sucht nach bereits existierenden Zeilen, in denen die Variable
  * an der spezifizierten Stelle schon eliminiert ist.
  */
  private Vector<Integer> getProperLines( short x){
    Vector<Integer> ret = new Vector<Integer>();

    for( int i=0; i< linesOfLGS; i++)
      if( lgs.get(i)[x].getZ() == 0)
        ret.add(i);


    ret.retainAll( getProperLines((short)(x-1)));

    return ret;
  }

  /**
   * gibt die Position in der Gleichungsmenge bez des Iterationsschrittes an
   */
  private int iterPos( int iterStep){
    if( iterStep == 0)
      return 0;
    return iterPos( iterStep-1) + (numOfXs-iterStep+1) + (linesOfLGS-numOfXs);
  }

  /**
   * gibt an, ob die uebergebene Zeile nur mit Nullen besetzt ist
   */
  private boolean isZeroLine( int line){
    for( int i=0; i< lgs.get(0).length; i++)
      if( lgs.get(line)[i].getZ() != 0)
        return false;
    return true;
  }

  /**
   * stellvertretend fuer lgs.size()-1,
   * also die Stelle des letzten Elements
   */
  private int last(){ return lgs.size()-1;}

  /**
   * hat das Objekt wirklich genug Gleichungen, um das LGS_old eindeutig zu loesen?
   */
  private boolean isComplete(){
  if( numOfXs > linesOfLGS)
    return false;
  return true;
  }

  /**
   * gibt die String-Repraesentation einer Zeile
   * in LGS_old-Schreibweise zurueck
   */
  private String lts1( int line){
    String ret= "";
    
    int i=0;
    for(; i<numOfXs; i++){
      ret = ret + lgs.get(line)[i].toString() + " * x[" + i + "]";
      if( i<numOfXs-1)
        ret = ret + " + ";
    }
    ret = ret + " = " + lgs.get(line)[i].toString() + "\n";
    return ret;
  }

  /**
   * gibt die String-Repraesentation einer Zeile
   * in Matrix-Schreibweise zurueck
   */
  private String lts2( int line){
    String ret= "";


    int i=0;
    for(; i<numOfXs; i++)
      ret = ret + lgs.get(line)[i].toString() + "  ";
    ret = ret + "|";
    for(; i<lgs.get(0).length; i++)
      ret = ret + "  " + lgs.get(line)[i].toString();
    ret = ret + "\n";
    return ret;
  }

}

