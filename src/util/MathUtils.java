package util;

public class MathUtils {

	/**returns the square of the distance between x,y and a,b.*/
	public static double dist2(double x, double y, double a, double b) {
		return Math.pow(x-a, 2) + Math.pow(y-b, 2);
	}
	
}
