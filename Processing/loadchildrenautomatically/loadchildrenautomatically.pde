void settings() {
  size(320,240, P3D);
}

// -------------------------------------------------------------------------------------------------
import java.lang.reflect.Constructor;

void setup() {
  background(255, 0, 0); // jawas

    ArrayList<XXX> childInstances = new ArrayList<XXX>();

      for( Class childClass : getClass().getDeclaredClasses() ) {
        if( isSubclass( XXX.class, childClass )) {
          try {
            Constructor childConstructor = childClass.getConstructor( getClass());
            XXX childInstance = (XXX)childConstructor.newInstance( this);
            childInstances.add( childInstance );
          }
          catch( Exception e) {
            println( e.toString() + "\nDid you implement a constructor on this class?");
          }
        }
      }

    for( XXX childInstance : childInstances)
    {
        println("child:     " + childInstance);
        childInstance.foo();
    }

}

/// Checks if the given child class is subclass of the given parent class.
boolean isSubclass( Class parent, Class child) {
    return parent.isAssignableFrom( child) && child != parent.class;
}

// -------------------------------------------------------------------------------------------------

void draw() {
  background(0, noise(second())*255, 0);
}

// -------------------------------------------------------------------------------------------------
// Standard Helpers

class Crappy {
  public int foo() { return 42; }
}

abstract class XXX {
  public abstract void foo();
}

class A extends XXX
{
  public A() {}
  public void foo() { println("SIMON SAYS"); }
}

class B extends XXX
{
  public B() {}
  public void foo() { println("GABBA GABBA ZEAH"); }
}
