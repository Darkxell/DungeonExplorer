package res.images;

import java.awt.image.BufferedImage;

import res.Res;

public abstract class Res_Inventory {

    public static BufferedImage background = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 316, 319, 240, 160);
    
    public static BufferedImage ItemsOverlay = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 5, 56, 209, 135);
    public static BufferedImage SubItemsOverlay = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 319, 56, 209, 135);
    public static BufferedImage MapOverlay = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 6, 374, 225, 156);
    
    public static BufferedImage ItemsTitle = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 7, 2, 112, 22);
    public static BufferedImage SubItemsTitle = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 321, 2, 212, 22);
    public static BufferedImage MapTitle = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 7, 319, 32, 22);
    
    public static BufferedImage ButtonLeft = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 7, 29, 21, 21);
    public static BufferedImage ButtonRight = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 30, 29, 21, 21);
    public static BufferedImage ButtonS = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 108, 34, 12, 14);
    public static BufferedImage ButtonD = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 122, 34, 12, 14);
    
    public static BufferedImage Cursor1red = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 219, 57, 7, 7);
    public static BufferedImage Cursor2red = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 227, 57, 7, 7);
    public static BufferedImage Cursor3red = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 219, 65, 7, 7);
    public static BufferedImage Cursor4red = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 227, 65, 7, 7);
    public static BufferedImage Cursor1green = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 235, 57, 7, 7);
    public static BufferedImage Cursor2green = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 243, 57, 7, 7);
    public static BufferedImage Cursor3green = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 235, 65, 7, 7);
    public static BufferedImage Cursor4green = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 243, 65, 7, 7);
    
    public static BufferedImage activeItemBG = Res.createimage(Res.FOLDER_PATH
	    + "inventory\\inventory.png", 219, 87, 26, 19);
}
