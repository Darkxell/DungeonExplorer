package util;

import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

import res.images.Res_Inventory;

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
	while (fPart != Math.floor(fPart))
	    fPart *= 10;
	return (int) fPart;
    }

    /** Returns a random int between the min and the max values included. */
    public static int randomINT(int min, int max) {
	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static final byte FONT_WHITESQUARES = 0;
    public static final byte FONT_YELLOWSQUARES = 1;

    /**
     * Prints the wanted number at the wanted coordinates with the wanted font.
     * 
     * @param font
     *            the font used. Search for </code>FONT_*</code> in the
     *            <code>NumberUtil</code>class.
     * @param number
     *            the number you want to print
     * */
    public static void printNumberWithFont(Graphics2D g2d, byte font, int x,
	    int y, int number) {
	int l = (int) (Math.log10(number) + 1);
	switch (font) {
	case FONT_WHITESQUARES:
	    g2d.drawImage(Res_Inventory.numbers_whitesquares[number % 10], x
		    + ((l - 1) * 6), y, null);
	    break;
	case FONT_YELLOWSQUARES:
	    g2d.drawImage(Res_Inventory.numbers_yellowsquares[number % 10], x
		    + ((l - 1) * 6), y, null);
	    break;
	}
	if (number >= 10)
	    printNumberWithFont(g2d, font, x, y, (int) (number / 10));

    }

}
