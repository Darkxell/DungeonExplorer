package management.floors;

import java.awt.image.BufferedImage;

import res.images.Res_Tiles;
import util.StringMatcher;
import display.sprites.AnimatedSprite;

/**
 * Object that represents the phisical 16x16 area on the ground. Thoose tiles
 * are only the most common ones, see TileEvent for more interesting things.
 */
public class Tile {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_SOLID = 1;
    public static final int TYPE_WATER = 2;
    public static final int TYPE_LAVA = 3;
    public static final int TYPE_JUMPABLE_LEFT = 4;
    public static final int TYPE_JUMPABLE_RIGHT = 5;
    public static final int TYPE_JUMPABLE_UP = 6;
    public static final int TYPE_JUMPABLE_DOWN = 7;
    public static final int TYPE_VOID = 8;

    /**
     * The sprite of the tile. Please note that this sprite MUST be repetable,
     * since there is no spritesheet for normal tiles.
     */
    public AnimatedSprite sprite;

    /** The type of the tile. */
    public int type;

    private int updater;

    /**
     * Creates a tile using an AnimatedSprite Object and the type of the desired
     * tile.
     */
    public Tile(AnimatedSprite texture, int type) {
	this.sprite = texture;
	this.type = type;
    }

    /**
     * Creates a tile using a tile sourcecode.<br/>
     * <strong>Wanted Syntax</strong>:<br/>
     * <code>(imgID,imgID,...,imgID):type</code>
     */
    public Tile(String code) {
	String[] parts = code.split(":");
	this.type = Integer.parseInt(parts[1]);
	String[] ImageIDs = StringMatcher.getBracketsContent(parts[0], 1)
		.split(",");
	BufferedImage[] images = new BufferedImage[ImageIDs.length];
	for (int i = 0; i < images.length; i++) {
	    images[i] = Res_Tiles.tilessprites[Integer.parseInt(ImageIDs[i])];
	}
	this.sprite = new AnimatedSprite(images);
    }

    /**
     * Updates the tile. Actually only updates the tile sprite, since a normal
     * tile can't change it's state, uneless you switch the whole tile.
     */
    public void update() {
	++updater;
	if (updater > 4) {
	    sprite.next();
	    updater = 0;
	}
    }

    /**
     * returns true if the player can go on the specified area of the tile. This
     * returns true if the player can go inside it, doesn't mean he will live
     * long if he goes, depends on the tile...
     * 
     * @param pos
     *            the position on the tile. between 0 and 1.
     */
    public static boolean canWalkOn(int tileType, double x, double y) {
	if (tileType == TYPE_NORMAL || tileType == TYPE_WATER
		|| tileType == TYPE_LAVA || tileType == TYPE_VOID) {
	    return true;
	}
	if (tileType == TYPE_SOLID) {
	    return false;
	}
	System.out.println("test");
	return false; // TODO : add all the weird tiles.
    }

}
