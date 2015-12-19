package res.images;

import java.awt.image.BufferedImage;

import res.Res;

/** Static class that holds the main menu images. */
public abstract class Res_MainMenu {

    public static BufferedImage background = Res.createimage(
	   ImagesHolder.MENU_BACKGROUND, 0, 0, 240, 160);

    public static BufferedImage light1 = Res.createimage(
	    ImagesHolder.MENU_LIGHT, 0, 0, 240, 160);
    public static BufferedImage light2 = Res.createimage(
	    ImagesHolder.MENU_LIGHT, 0, 160, 240, 160);
    public static BufferedImage light3 = Res.createimage(
	    ImagesHolder.MENU_LIGHT, 0, 320, 240, 160);
    public static BufferedImage light4 = Res.createimage(
	    ImagesHolder.MENU_LIGHT, 0, 480, 240, 160);

    public static BufferedImage start = Res.createimage(
	    ImagesHolder.MENU_START, 0, 0, 89, 18);

    public static BufferedImage sword1 = Res.createimage(
	    ImagesHolder.MENU_SWORD, 0, 0, 191, 98);
    public static BufferedImage sword2 = Res.createimage(
	    ImagesHolder.MENU_SWORD, 0, 98, 191, 98);
    
    public static BufferedImage title = Res.createimage(
	    ImagesHolder.MENU_TITLE, 0, 0, 167, 88);
    
    public static BufferedImage controls = Res.createimage(
	    ImagesHolder.CONTROLS, 0, 0, 240, 160);

}
