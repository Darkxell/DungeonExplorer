package res.images;

import java.awt.image.BufferedImage;

import res.Res;

/** Holds all of the sprites for the tiles in the game. */
public abstract class Res_Tiles {

    private static final int NUMBEROFROWS = 16;
    private static final int NUMBEROFCOLUMNS = 20;

    public static BufferedImage[] tilessprites = initialize();

    /** Returns an array containing all the tiles in the game. */
    private static BufferedImage[] initialize() {
	BufferedImage[] toreturn = new BufferedImage[NUMBEROFCOLUMNS * NUMBEROFROWS];
	for (int i = 0; i < NUMBEROFROWS; i++) {
	    
	    for (int j = 0; j < NUMBEROFCOLUMNS; j++) {
		toreturn[(NUMBEROFCOLUMNS * i) + j] = Res.createimage(Res.FOLDER_PATH
			+ "tiles\\tileset.png", 16 * j, 16 * i, 16, 16);
	    }
	    
	}
	return toreturn;
    }

}
