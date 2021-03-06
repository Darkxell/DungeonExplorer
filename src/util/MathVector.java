package util;

import management.Position;

/** Reprensents a mathematical 2D vector. */
public class MathVector {

    public double x;
    public double y;

    /** Builds a mathematical vector. */
    public MathVector(double x, double y) {
	this.x = x;
	this.y = y;
    }

    /** returns the translation of the point by this vector. */
    public Position getTranslation(double x, double y) {
	return new Position(x + this.x, y + this.y);
    }

    /**
     * Gets the translation of the point by this vector, but with a set norma.
     * This method basically just uses the vector direction.
     */
    public Position getFixedTranslation(double x, double y, double length) {
	double z = Math.sqrt((this.x * this.x) + (this.y * this.y));
	double y2 = this.y * length / z;
	double x2 = this.x * length / z;
	return new Position(x + x2, y + y2);
    }

}
