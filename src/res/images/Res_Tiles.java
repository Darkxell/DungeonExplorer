package res.images;

import java.awt.image.BufferedImage;

import res.Res;

/** Holds all of the sprites for the tiles in the game. */
public abstract class Res_Tiles {

    private static final int NUMBEROFROWS = 16;

    public static BufferedImage[] tilessprites = initialize();

    /** Returns an array containing all the tiles in the game. */
    private static BufferedImage[] initialize() {
	BufferedImage[] toreturn = new BufferedImage[16 * NUMBEROFROWS];
	for (int i = 0; i < NUMBEROFROWS; i++) {
	    for (int j = 0; j < 16; j++) {
		toreturn[(16 * i) + j] = Res.createimage(Res.FOLDER_PATH
			+ "tiles\\tileset.png", 16 * i, 16 * j, 16, 16);
	    }
	}
	return toreturn;
    }

}
