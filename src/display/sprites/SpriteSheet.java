package display.sprites;

import java.awt.image.BufferedImage;

/**
 * Holds the different animated sprites for all the different action an entity
 * can do.
 */
public class SpriteSheet {

    /** The sprites. */
    protected AnimatedSprite[] sprites;

    protected int currentAnimatedspriteID;

    /** Constructs a SpriteSheet using an array of animated Sprites. */
    public SpriteSheet(AnimatedSprite[] sprites) {
	this.sprites = sprites;
    }

    /** moves to the next sprite. */
    public void next() {
	this.sprites[currentAnimatedspriteID].next();
    }

    /** gets the current sprite. */
    public BufferedImage getCurrentSprite() {
	return this.sprites[currentAnimatedspriteID].get();
    }

    /** sets the sprite to this ID. */
    public void setSpriteID(int id) {
	if(!sprites[id].isRepetable())
	    sprites[id].offset = 0;
	this.currentAnimatedspriteID = id;
    }

    /** gets this spritesheet animatedsprite current ID. */
    public int getSpriteID() {
	return currentAnimatedspriteID;
    }

}
