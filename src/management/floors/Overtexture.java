package management.floors;

import java.awt.image.BufferedImage;

/**
 * Represents a texture of something to print above the player, tiles and
 * entities, like a door top or a ceiling.
 */
public class Overtexture {

    /** The sprite of the Overtexture. */
    public BufferedImage sprite;

    public int x;
    public int y;

    public Overtexture(BufferedImage sprite, int x, int y) {
	this.sprite = sprite;
	this.x = x;
	this.y = y;
    }

}
