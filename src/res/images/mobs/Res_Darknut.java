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
	
}
