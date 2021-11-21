package res.images.mobs;

import java.awt.image.BufferedImage;

import res.Res;
import res.images.ImagesHolder;

public class Res_Chuchu {

	public static BufferedImage[] ZOL_BIG = Res.getSpriteRectRow(ImagesHolder.ENTITIES_ZOL, 0, 0, 16, 14, 2);
	public static BufferedImage[] ZOL_SMALL = Res.getSpriteRectRow(ImagesHolder.ENTITIES_ZOL, 0, 14, 16, 10, 2);
	
	public static BufferedImage CHUCHU_IDLE = Res.createimage(ImagesHolder.ENTITIES_BOSS1, 0, 0, 46, 45);
	public static BufferedImage[] CHUCHU_HURT = Res.getSpriteRectRow(ImagesHolder.ENTITIES_BOSS1, 0, 45, 46, 45,5);
	public static BufferedImage[] CHUCHU_JUMP = Res.getSpriteRectRow(ImagesHolder.ENTITIES_BOSS1, 0, 90, 46, 45,5);
	public static BufferedImage[] CHUCHU_FALL = Res.getSpriteRectRow(ImagesHolder.ENTITIES_BOSS1, 0, 135, 46, 45,5);

	public static BufferedImage CHUCHU_DROPLET = Res.createimage(ImagesHolder.ENTITIES_BOSS1, 46, 0, 32, 32);
	public static BufferedImage CHUCHU_FEET = Res.createimage(ImagesHolder.ENTITIES_BOSS1, 109, 0, 40, 22);
	
	public static BufferedImage STAR_LARGE = Res.createimage(ImagesHolder.ENTITIES_BOSS1, 78, 0, 15, 15);
	public static BufferedImage STAR_SMALL = Res.createimage(ImagesHolder.ENTITIES_BOSS1, 94, 0, 15, 15);
	
}
