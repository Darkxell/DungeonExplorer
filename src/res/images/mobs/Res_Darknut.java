package res.images.mobs;

import java.awt.image.BufferedImage;

import res.Res;
import res.images.ImagesHolder;

public class Res_Darknut {

	public static BufferedImage[] spawn = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 0, 56, 8);

	public static BufferedImage[] idle_down = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 56, 56, 7);
	public static BufferedImage[] idle_up = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 392, 56, 7);
	public static BufferedImage[] idle_left = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 168, 56, 7);
	public static BufferedImage[] idle_right = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 280, 56, 7);

	public static BufferedImage[] move_down = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 112, 56, 6);
	public static BufferedImage[] move_up = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 448, 56, 6);
	public static BufferedImage[] move_left = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 224, 56, 6);
	public static BufferedImage[] move_right = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 336, 56, 6);

	public static BufferedImage[] windup_left = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 0, 504, 56, 4);
	public static BufferedImage[] windup_right = Res.getSpriteRow(ImagesHolder.ENTITIES_DARKNUT, 224, 504, 56, 4);

	public static BufferedImage[] charge_left = Res.getSpriteRectRow(ImagesHolder.ENTITIES_DARKNUT, 0, 560, 64, 56, 6);
	public static BufferedImage[] charge_right = Res.getSpriteRectRow(ImagesHolder.ENTITIES_DARKNUT, 0, 616, 64, 56, 6);

	public static BufferedImage[] slash_down = Res.getSpriteRectRow(ImagesHolder.ENTITIES_DARKNUTSLASH, 0, 0, 80, 64,
			11);
	public static BufferedImage[] slash_left = Res.getSpriteRectRow(ImagesHolder.ENTITIES_DARKNUTSLASH, 0, 64, 80, 64,
			11);
	public static BufferedImage[] slash_right = Res.getSpriteRectRow(ImagesHolder.ENTITIES_DARKNUTSLASH, 0, 128, 80, 64,
			11);
	public static BufferedImage[] slash_up = Res.getSpriteRectRow(ImagesHolder.ENTITIES_DARKNUTSLASH, 0, 192, 80, 64,
			11);
	
	public static BufferedImage ghost = Res.createimage(ImagesHolder.ENTITIES_DARKNUT, 392, 56, 56, 56);
	public static BufferedImage ghosttp = Res.createimage(ImagesHolder.ENTITIES_DARKNUT, 392, 112, 56, 56);

}
