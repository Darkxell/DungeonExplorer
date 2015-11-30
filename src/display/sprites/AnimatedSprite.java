package display.sprites;

import java.awt.image.BufferedImage;

/**
 * An image basically representing an animated image. The image doesn't updates
 * itself but contains an array of normal images, and can be scrolled using the
 * <code>next()</code> method.<br/>
 * An animatedSprite can be either repetable or not. If it is it will loop when
 * reaching the end, if it's not then a replacing sprite must be specified for
 * when it reaches the end, unlike a particle.
 */
public class AnimatedSprite {

    /** the images of the animated sprite. */
    public BufferedImage[] images;
    /**
     * is true if the sprite is repetable. A repetable sprite will play in a
     * loop once it reaches its last frame, where a non repetable one will set
     * his spritesheet to the <code>endingSpriteID</code> sprite.
     */
    private boolean repeatable;

    /**
     * if the sprite is not repetable, this ID is set as the parent spritesheet
     * offset.
     */
    public int endingSpriteID;

    /** The the Sprite is not repetable, the <code>CurrentAnimatedSpriteID</code> */
    private SpriteSheet parentsheet;

    /** the current sprite offset. */
    public int offset;

    /** Creates a repetable AnimatedSprite uning the bufferedImages array. */
    public AnimatedSprite(BufferedImage[] images) {
	this.images = images;
	this.repeatable = true;
    }

    /**
     * Creates a non repetable AnimatedSprite using an array of images and a
     * goto sprite to set at the end.
     */
    public AnimatedSprite(BufferedImage[] images, int endingSpriteID,
	    SpriteSheet parentsheet) {
	this(images);
	this.repeatable = false;
	this.endingSpriteID = endingSpriteID;
	this.parentsheet = parentsheet;
    }

    /** Gets the current sprite */
    public BufferedImage get() {
	return images[offset];
    }

    /** Updates the sprite (and maybe the spritesheet) to the next one. */
    public void next() {
	++offset;
	if (repeatable && offset >= images.length) {
	    offset = 0;
	} else if (offset >= images.length && !repeatable) {
	    this.parentsheet.setSpriteID(this.endingSpriteID);
	    this.offset = 0;
	}
    }

    /** Returns treue if this sprite is repetable. */
    public boolean isRepetable() {
	return this.repeatable;
    }
}
