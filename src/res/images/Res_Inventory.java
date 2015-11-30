package res.images;

import java.awt.image.BufferedImage;

import res.Res;

public abstract class Res_Inventory {

    public static BufferedImage background = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 316, 319, 240, 160);

    public static BufferedImage ItemsOverlay = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 5, 56, 209, 135);
    public static BufferedImage SubItemsOverlay = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 319, 56, 209, 135);
    public static BufferedImage MapOverlay = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 6, 374, 225, 156);

    public static BufferedImage ItemsTitle = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 7, 2, 112, 22);
    public static BufferedImage SubItemsTitle = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 321, 2, 212, 22);
    public static BufferedImage MapTitle = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 7, 319, 32, 22);

    public static BufferedImage ButtonLeft = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 7, 29, 21, 21);
    public static BufferedImage ButtonRight = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 30, 29, 21, 21);
    public static BufferedImage ButtonS = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 108, 34, 12, 14);
    public static BufferedImage ButtonD = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 122, 34, 12, 14);

    public static BufferedImage Cursor1red = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 219, 57, 7, 7);
    public static BufferedImage Cursor2red = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 227, 57, 7, 7);
    public static BufferedImage Cursor3red = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 219, 65, 7, 7);
    public static BufferedImage Cursor4red = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 227, 65, 7, 7);
    public static BufferedImage Cursor1green = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 235, 57, 7, 7);
    public static BufferedImage Cursor2green = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 243, 57, 7, 7);
    public static BufferedImage Cursor3green = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 235, 65, 7, 7);
    public static BufferedImage Cursor4green = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 243, 65, 7, 7);

    public static BufferedImage activeItemBG = Res.createimage(
	    ImagesHolder.INVENTORY_INVENTORY, 219, 87, 26, 19);

    public static BufferedImage[] numbers_whitesquares = Res.getSpriteRectRow(
	    ImagesHolder.INVENTORY_INVENTORY, 117, 219, 6, 7, 10);
    public static BufferedImage[] numbers_yellowsquares = Res.getSpriteRectRow(
	    ImagesHolder.INVENTORY_INVENTORY, 117, 229, 6, 7, 10);
}
