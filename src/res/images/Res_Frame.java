package res.images;

import java.awt.image.BufferedImage;

import res.Res;

/**
 * Abstract class that holds the ressources for the main frame. Those ressources
 * are not related to the game but to the container.
 */
public abstract class Res_Frame {

    public static BufferedImage swadow_topleft = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 0, 0, 10, 10);
    public static BufferedImage swadow_topright = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 11, 0, 10, 10);
    public static BufferedImage swadow_botleft = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 0, 11, 10, 10);
    public static BufferedImage swadow_botright = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 11, 11, 10, 10);

    public static BufferedImage swadow_bot = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 11, 11, 1, 10);
    public static BufferedImage swadow_top = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 22, 0, 1, 10);
    public static BufferedImage swadow_left = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 0, 22, 10, 1);
    public static BufferedImage swadow_right = Res.createimage(
	    ImagesHolder.FRAMESHADOW, 11, 22, 10, 1);
    
    public static BufferedImage close = Res.createimage(
	    ImagesHolder.FRAMEICONS, 0, 0, 29, 20);
    public static BufferedImage close_active = Res.createimage(
	    ImagesHolder.FRAMEICONS, 30, 0, 29, 20);
    public static BufferedImage minimize = Res.createimage(
	    ImagesHolder.FRAMEICONS, 0, 21, 29, 20);
    public static BufferedImage minimize_active = Res.createimage(
	    ImagesHolder.FRAMEICONS, 30, 21, 29, 20);
    public static BufferedImage controls = Res.createimage(
	    ImagesHolder.FRAMEICONS, 0, 42, 29, 20);
    public static BufferedImage controls_active = Res.createimage(
	    ImagesHolder.FRAMEICONS, 30, 42, 29, 20);
}
