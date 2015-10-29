package res.images;

import java.awt.image.BufferedImage;

import res.Res;

/** Static class that holds the player (link) images. */
public class Res_Player {

    public static BufferedImage still_down = Res.createimage(Res.FOLDER_PATH
	    + "entities\\player.png", 0, 0, 32, 32);
    public static BufferedImage still_left = Res.createimage(Res.FOLDER_PATH
	    + "entities\\player.png", 32, 0, 32, 32);
    public static BufferedImage still_up = Res.createimage(Res.FOLDER_PATH
	    + "entities\\player.png", 64, 0, 32, 32);

    public static BufferedImage[] walk_down = getPlayerSpriteRow(0, 32, 32, 10);

    public static BufferedImage[] walk_left = getPlayerSpriteRow(0, 64, 32, 10);

    public static BufferedImage[] walk_up = getPlayerSpriteRow(0, 96, 32, 10);

    /**
     * Gets multiple player sprites next to each other in the player.png file
     * and returns them inside an array.
     * 
     * @param x
     *            the X position of the sprite in the player.png file.
     * @param y
     *            the Y position of the sprite in the player.png file.
     * @param size
     *            the size of the sprite. The sprite is a square, and the x/y
     *            coordinates are it's top left corner.
     * @param spritesAmmount
     *            the ammount of sprites in the array. Also the length of the
     *            array, obviously.
     * */
    protected static BufferedImage[] getPlayerSpriteRow(int x, int y, int size,
	    int spritesAmmount) {
	BufferedImage[] toreturn = new BufferedImage[spritesAmmount];
	for (int i = 0; i < toreturn.length; i++)
	    toreturn[i] = Res.createimage(Res.FOLDER_PATH
		    + "entities\\player.png", x + (i * size), y, size, size);
	return toreturn;
    }

}
