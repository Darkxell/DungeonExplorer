package res.images.mobs;

import java.awt.image.BufferedImage;

import res.Res;
import res.images.ImagesHolder;

public class Res_Sluggula {

    public static BufferedImage[] slime_up = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_SLUGGULA, 0, 0, 32, 4);
    public static BufferedImage[] slime_right = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_SLUGGULA, 0, 32, 32, 4);
    public static BufferedImage[] slime_left = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_SLUGGULA, 0, 64, 32, 4);
    public static BufferedImage[] slime_down = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_SLUGGULA, 0, 96, 32, 4);

    public static BufferedImage[] fall = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_SLUGGULA, 0, 128, 32, 4);
}
