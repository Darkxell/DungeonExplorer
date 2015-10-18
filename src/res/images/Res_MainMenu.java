package res.images;

import java.awt.image.BufferedImage;

import res.Res;

/** Static class that holds the main menu images. */
public abstract class Res_MainMenu {

    public static BufferedImage background = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\background.png", 0, 0, 240, 160);

    public static BufferedImage light1 = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\light.png", 0, 0, 240, 160);
    public static BufferedImage light2 = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\light.png", 0, 160, 240, 160);
    public static BufferedImage light3 = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\light.png", 0, 320, 240, 160);
    public static BufferedImage light4 = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\light.png", 0, 480, 240, 160);

    public static BufferedImage start = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\start.png", 0, 0, 89, 18);

    public static BufferedImage sword1 = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\sword.png", 0, 0, 191, 98);
    public static BufferedImage sword2 = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\sword.png", 0, 98, 191, 98);
    
    public static BufferedImage title = Res.createimage(Res.FOLDER_PATH
	    + "mainmenu\\title.png", 0, 0, 167, 88);

}
