/** code taken from http://www.paulinternet.nl/?page=bicubic
date: 140906
*/

public class CubicInterpolator {
  
  public static double getValue (double[] p, double x) {
    return p[1] + 0.5 * x*(p[2] - p[0] + x*(2.0*p[0] - 5.0*p[1] + 4.0*p[2] - p[3] + x*(3.0*(p[1] - p[2]) + p[3] - p[0])));
  }
}
