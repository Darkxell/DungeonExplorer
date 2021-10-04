package util;

import management.Position;

/** Represents a mathematical 2D vector. */
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
	 * Gets the translation of the point by this vector, but with a set norm. This
	 * method basically just uses the vector direction.
	 */
	public Position getFixedTranslation(double x, double y, double length) {
		double z = Math.sqrt((this.x * this.x) + (this.y * this.y));
		double y2 = this.y * length / z;
		double x2 = this.x * length / z;
		return new Position(x + x2, y + y2);
	}

	public MathVector clone() {
		return new MathVector(x, y);
	}

	public MathVector add(MathVector m) {
		this.x += m.x;
		this.y += m.y;
		return this;
	}

	public MathVector sub(MathVector m) {
		this.x -= m.x;
		this.y -= m.y;
		return this;
	}

	public MathVector mul(MathVector m) {
		this.x *= m.x;
		this.y *= m.y;
		return this;
	}

	public MathVector mul(double d) {
		this.x *= d;
		this.y *= d;
		return this;
	}

	public MathVector normalize() {
		double n = this.norm();
		mul(1 / n);
		return this;
	}

	/** Returns the norm of this vector */
	public double norm() {
		return Math.sqrt(this.normSquared());
	}

	/**
	 * Returns the square of this vector's norm. Faster then the actual norm
	 * calculation.
	 */
	public double normSquared() {
		return x * x + y * y;
	}

	public double dot(MathVector m) {
		return x * m.x + y * m.y;
	}

	public boolean isZero() {
		return x == 0d && y == 0d;
	}

}
