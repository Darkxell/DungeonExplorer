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

    public static BufferedImage[] slash_down_1 = getPlayerSpriteRow(0, 128, 64, 8);
    public static BufferedImage[] slash_left_1 = getPlayerSpriteRow(0, 192, 64, 8);
    public static BufferedImage[] slash_up_1 = getPlayerSpriteRow(0, 256, 64, 8);
    
    public static BufferedImage[] slash_down_2 = getPlayerSpriteRow(0, 320, 64, 8);
    public static BufferedImage[] slash_left_2 = getPlayerSpriteRow(0, 384, 64, 8);
    public static BufferedImage[] slash_up_2 = getPlayerSpriteRow(0, 448, 64, 8);
    
    public static BufferedImage[] slash_down_3 = getPlayerSpriteRow(0, 512, 64, 8);
    public static BufferedImage[] slash_left_3 = getPlayerSpriteRow(0, 576, 64, 8);
    public static BufferedImage[] slash_up_3 = getPlayerSpriteRow(0, 640, 64, 8);
    
    public static BufferedImage[] slash_down_4 = getPlayerSpriteRow(0, 704, 64, 8);
    public static BufferedImage[] slash_left_4 = getPlayerSpriteRow(0, 768, 64, 8);
    public static BufferedImage[] slash_up_4 = getPlayerSpriteRow(0, 832, 64, 8);
    
    public static BufferedImage[] slash_down_5 = getPlayerSpriteRow(0, 896, 64, 8);
    public static BufferedImage[] slash_left_5 = getPlayerSpriteRow(0, 960, 64, 8);
    public static BufferedImage[] slash_up_5 = getPlayerSpriteRow(0, 1024, 64, 8);

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
