package res.images.mobs;

import java.awt.image.BufferedImage;

import res.Res;
import res.images.ImagesHolder;

public class Res_Oktorok {
    public static BufferedImage still_up = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 48, 48, 16, 16);
    public static BufferedImage still_down = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 48, 0, 16, 16);
    public static BufferedImage still_left = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 48, 16, 16, 16);
    public static BufferedImage still_right = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 48, 32, 16, 16);
    
    public static BufferedImage shoot_up = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 32, 48, 16, 16);
    public static BufferedImage shoot_down = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 32, 0, 16, 16);
    public static BufferedImage shoot_left = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 32, 16, 16, 16);
    public static BufferedImage shoot_right = Res.createimage(
	    ImagesHolder.ENTITIES_OKTOROK, 32, 32, 16, 16);

    public static BufferedImage[] walk_up = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_OKTOROK, 0, 48, 16, 2);
    public static BufferedImage[] walk_down = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_OKTOROK, 0, 0, 16, 2);
    public static BufferedImage[] walk_left = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_OKTOROK, 0, 16, 16, 2);
    public static BufferedImage[] walk_right = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_OKTOROK, 0, 32, 16, 2);
    
    public static BufferedImage[] boulder = Res.getSpriteRow(
	    ImagesHolder.ENTITIES_BOULDER, 0, 0, 10, 3);
    
}
