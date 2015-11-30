package res;

import java.awt.image.BufferedImage;

public abstract class Res {

    public static final String FOLDER_PATH = "C:\\Users\\Darkxell_mc\\Desktop\\ressources\\";

    /** Gets a part of a buffered Image. */
    public static BufferedImage createimage(BufferedImage image, int x, int y,
	    int width, int height) {
	return image.getSubimage(x, y, width, height);
    }

    /**
     * Gets multiple player sprites next to each other in the specified
     * BufferImage sheet file and returns them inside an array.
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
    public static BufferedImage[] getSpriteRow(BufferedImage sheet, int x,
	    int y, int size, int spritesAmmount) {
	BufferedImage[] toreturn = new BufferedImage[spritesAmmount];
	for (int i = 0; i < toreturn.length; i++)
	    toreturn[i] = Res.createimage(sheet, x + (i * size), y, size, size);
	return toreturn;
    }

    /**
     * Basically the same a s a getspriterow command, but the sprite doesn't
     * have to be a square.
     * 
     * @see <code>getSpriteRow()</code>
     */
    public static BufferedImage[] getSpriteRectRow(BufferedImage sheet, int x,
	    int y, int width, int height, int spritesAmmount) {
	BufferedImage[] toreturn = new BufferedImage[spritesAmmount];
	for (int i = 0; i < toreturn.length; i++)
	    toreturn[i] = Res.createimage(sheet, x + (i * width), y, width, height);
	return toreturn;
    }
}