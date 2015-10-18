package util;

import java.util.concurrent.ThreadLocalRandom;

/** Static class that holds static methods to manipulate numbers. */
public abstract class NumberUtil {

    /**
     * Gets an int representation aof the decimal part of a double value. Keep
     * in mind that the returned value is just a reprensentaion, not the actual
     * value. For exemple <code>15.245</code> will return <code>245</code>.
     */
    public static int getdecimalpart(double num) {
	long iPart = (long) num;
	double fPart = num - iPart;
	while (fPart != Math.floor(fPart)) {
	    fPart *= 10;
	}
	return (int) fPart;
    }

    /** Returns a random int between the min and the max values included. */
    public static int randomINT(int min, int max) {
	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
