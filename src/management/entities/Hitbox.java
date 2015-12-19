package management.entities;

import util.DoubleRectangle;
import management.Position;

/** Class that represents an entity's hitbox. */
public class Hitbox {

    /**
     * An array of 9 positions representing the 9 cardinal points of the entity.
     * Use this for quick calculations
     */
    public Position[] cardinals;

    /** Creates a hitbox object using a positions array. */
    public Hitbox(Position[] cardinals) {
	if (cardinals.length != 9)
	    System.err
		    .println("Warning : created a hitbox object with a corrupted cardinals array.");
	this.cardinals = cardinals;
    }

    /**
     * Returns a <code>Rectangle</code> representing the entity's hitbox. Use
     * this for precise calculations
     */
    public DoubleRectangle getRectHitbox() {
	return new DoubleRectangle(cardinals[0].x, cardinals[0].y,
		cardinals[8].x - cardinals[0].x, cardinals[8].y
			- cardinals[0].y);
    }

}
