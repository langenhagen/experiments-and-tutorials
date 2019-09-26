//////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Klasse Fraction zur Darstellung und zum Arbeiten mit gemeinen Brüchen 																		//
// - Funktionen unter Java                                                                                  //
//																																																					//
// -es ist darauf zu achten, dass Brueche in denen der Nenner 0 ist, nicht existieren koennen;              //
//  dieses Problem wurde mit verschiedenen Ansaetzen bei den Methoden umgangen:                             //
//  Konstruktor #2  -   Nenner wird 1 gesetzt                                                               //
//  setN()          -   ignorieren der Aktion                                                               //
//  reciprocal()    -   wirft null zurueck                                                                  //
//  die Divisionen  -   werfen null zurueck                                                                 //
//                                                                                                          //
//  Dies dient der einfachen Handlebarkeit ohne auferzwungene try/catch-Bloecke                             //
//                                                                                                          //
// Author: Barn																																															//
// Version: 181009                                                                                          //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
package lgs_testing;

public class Fraction {

  private long z;           // Zaehler
  private long n;           // Nenner

  /* Konvention: das Minuszeichen - sofern vorhanden - steht immer im Zaehler,
   * und nur dort
	 */

  // Konstruktoren //////////////////////////////////////////////////
  /**
   * Standardkonstruktor
   * - erzeugt eine Null<
   */
  public Fraction() { z=0; n=1;}

  /**
   * Konstruktor #2
   * -sollte fuer den Nenner '0' uebergeben werden,
   *  wird der Nenner gleich 1 gesetzt
   */
  public Fraction(long zaehler, long nenner){
    z = zaehler;
    n = Math.abs( nenner);

    if(n==0)
      n=1;
  }

  /** Konstruktor #3*/
  public Fraction(long zaehler){
    z = zaehler;
    n = 1;
  }

  /** Konstruktor #4*/
  public Fraction( Fraction f){
    z = f.getZ();
    n = f.getN();
  }

  // Getter, Setter /////////////////////////////////////////////////
  /** gettet den Zaehler der entsprechenden Bruches */
  public long getZ() { return z;}

  /** gettet den Nenner des entsprechenden Bruches */
  public long getN() { return n;}

  /** settet den Zaehler der Bruches */
  public void setZ( long zaehler){
    z = zaehler;
  }

  /** 
   * settet den Zahler des Bruches, unabhängig vom Vorzeichen
   * -sollte der Nenner 0 sein, wird die Aktion ignoriert
   */
  public void setN( long nenner){
    if(n>0)
      n = Math.abs(nenner);
  }
  
  // Arithmetische Methoden /////////////////////////////////////////
  /**
   * @author Barn
   * gibt das Negative des Bruches zurueck
   */
  public Fraction negative(){ return new Fraction( -z, n); }
  
  /** 
   * gibt das Reziproke des Bruches zurueck
   * gibt '0' zurueck sollte der Originalbruch 0 sein
   */
  public Fraction reciprocal(){
    if( z>0)
      return new Fraction( n, z );
    else if( z<0)
      return new Fraction( -n, -z);
    else return new Fraction(0);
  }
  
  /** kuerzt den Bruch und gibt ihn zurueck */
  public Fraction reduce() {
    long ggt = ggT(z,n);
    z /= ggt;
    n /= ggt;
    return this;
  }

  /** bringt den aktuellen und den uebergebenen Bruch auf den gleichen Nenner
   *  und gibt den Aktuellen zurueck
   */
  public Fraction oneNenner( Fraction f) {

    long f2N = f.getN();
    long kgv = kgV( n, f2N);

    long m1 = kgv/n;
    long m2 = kgv/ f2N;

    z *= m1;
    f.setZ( f.getZ()*m2);
    n *= m1;
    f.setN( f.getN()*m2);
    return this;
  }

  /**
   * Ueberprueft den aktuellen und den Uebergebenen Bruch auf Gleichheit,
   * unabhaengig von verschiedenen Darstellungsweisen des
   * gleichen Bruches
   */
  public boolean equals( Fraction f){
    Fraction t1 = new Fraction(this);
    Fraction t2 = new Fraction( f);
    t1.oneNenner( t2);

    if( t1.getZ() == t2.getZ() )
      return true;
    return false;
  }

  /**
   * checkt, ob der Aktuelle Bruch groesser als der Uebergebene ist
   */
  public boolean isBigger( Fraction f){
    Fraction t1 = new Fraction(this);
    Fraction t2 = new Fraction( f);
    t1.oneNenner( t2);

    if( t1.getZ() > t2.getZ())
      return true;
    return false;
  }

  /**
   * checkt, ob der Aktuelle Bruch kleiner als der Uebergebene ist
   */
  public boolean isSmaller( Fraction f){
    Fraction t1 = new Fraction(this);
    Fraction t2 = new Fraction( f);
    t1.oneNenner( t2);

    if( t1.getZ() < t2.getZ())
      return true;
    return false;
  }

  /** addiert den aktuellen und den uebergebenen Bruch
   *  und gibt das Ergebnis gekuerzt zurueck
   *  - der aktuelle Bruch bleibt gleich
   */
  public Fraction add( Fraction f){
    Fraction t1 = new Fraction(this);
    Fraction t2 = new Fraction( f);
    
    t1.oneNenner(t2);
    t1 = new Fraction( t1.getZ() + t2.getZ(), t1.getN() );
    return t1.reduce();
  }

  /** addiert einen Bruch mit einem Integer und gibt das Ergebnis
   *  gekuerzt zurueck
   *  - der aktuelle Bruch bleibt gleich
   */
  public Fraction add(int z){
    Fraction t1 = new Fraction(this);
    Fraction t2 = new Fraction( z);

    t1.oneNenner(t2);
    t1 = new Fraction( t1.getZ() + t2.getZ(), t1.getN() );
    return t1.reduce();
  }

  /** subtrahiert 2 Brüche und gibt das Ergebnis gekuerzt zurueck
   *  - der aktuelle Bruch bleibt gleich
   */
  public Fraction substract( Fraction f){
    Fraction t1 = new Fraction(this);
    Fraction t2 = (new Fraction( f)).negative();
    
    t1.oneNenner(t2);
    t1 = new Fraction( t1.getZ() + t2.getZ(), t1.getN() );
    return t1.reduce();
  }

  /** subtrahiert einen Bruch mit einem Integer und gibt das Ergebnis
   *  gekuerzt zurueck
   *  - der aktuelle Bruch bleibt gleich
   */
  public Fraction substract( int z){

    Fraction t1 = new Fraction(this);
    Fraction t2 = (new Fraction( z)).negative();

    t1.oneNenner(t2);
    t1 = new Fraction( t1.getZ() + t2.getZ(), t1.getN() );
    return t1.reduce();
  }

  /** multipliziert 2 Brueche und gibt das Ergebnis
   *  gekuerzt zurueck
   */
  public Fraction multiply( Fraction f){
    return (new Fraction ( z*f.getZ(), n*f.getN())).reduce();
  }

  /** multipliziert den Bruch mit einer Ganzzahl und gibt das Ergebnis
   *  gekuerzt zurueck
   */
  public Fraction multiply( int i){
    return (new Fraction( z*i, n)).reduce();
  }

  /** dividiert den aktuellen Bruch durch den Uebergebenen
   *  und gibt das Ergebnis gekuerzt das zurueck
   */
  public Fraction divide( Fraction f){
    return multiply( f.reciprocal());
  }

  /** dividiert den Bruch mit einer Ganzzahl und gibt das Ergebnis
   *  gekuerzt zurueck
   *  - ignoriert die Aktion, wenn die Zahl 0 ist und gibt dann den
   *    Originalbruch zurueck
   */
  public Fraction divide( int i){
    if( i==0)
      return null;
    return (new Fraction( z, n*i)).reduce();
  }

  // Repraesentationsmethoden ///////////////////////////////////////
  /**
   * @author Barn
   * gibt den Bruch als Dezimalbruch wieder
   * ACHTUNG: - bei besonders großen Zahlen können Ungenauigkeiten
   *            und fehlerhafte Ergebnisse auftreten!
   */
  public double toDouble(){
    return (double)z / n;
  }

  /**
   * @author Barn
   * gibt den Bruch als String-Repraesentation wieder
   */
    @Override
  public String toString(){
    return "" + z + "/" + n;
  }

  // Hilfsmethoden //////////////////////////////////////////////////
  /**
   * @author Barn
   * Berechnet das den groessten gemeinsamen Teiler zweier long-Werte
   * @param z1 erste Zahl
   * @param z2 zweite Zahl
   * @return groesster gemeinsamer Teiler der beiden Params  
   */
  private long ggT( long z1, long z2){
    long a, b, r = -1;
    if( z1 == 0 || z2 == 0 )
      return 1;
    
    if( z1 >= z2){
      a = Math.abs(z1);
      b = Math.abs(z2);
    }
    else{
      a = Math.abs(z2);
      b = Math.abs(z1);
    }

    while( r != 0){
      r = a % b;
      a = b;
      b = r;
    }
    return a;
  }
  
  /**
   * @author Barn
   * Berechnet das kleinste gemeinsame Vielfache zweier long-Werte
   * @param z1 erste Zahl
   * @param z2 zweite Zahl
   * @return kleinstes gemeinsame Vielfache der beiden Params
   */
  private long kgV( long z1, long z2)
  { return (long) ( (z1* z2) / ggT(z1,z2)); }

} // END Fraction-Klasse