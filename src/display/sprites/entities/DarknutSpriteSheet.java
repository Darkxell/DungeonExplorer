package display.sprites.entities;

import java.awt.image.BufferedImage;

import display.sprites.AnimatedSprite;
import display.sprites.SpriteSheet;
import res.images.mobs.Res_Darknut;

public class DarknutSpriteSheet extends SpriteSheet {

	public static final int ID_SPAWN = 0;

	public static final int ID_IDLE_LEFT = 1;
	public static final int ID_IDLE_RIGHT = 2;
	public static final int ID_IDLE_UP = 3;
	public static final int ID_IDLE_DOWN = 4;

	public static final int ID_MOVE_LEFT = 5;
	public static final int ID_MOVE_RIGHT = 6;
	public static final int ID_MOVE_UP = 7;
	public static final int ID_MOVE_DOWN = 8;

	public DarknutSpriteSheet() {
		super(null);
		super.sprites = new AnimatedSprite[] {
				new AnimatedSprite(new BufferedImage[] { Res_Darknut.spawn[0], Res_Darknut.spawn[1],
						Res_Darknut.spawn[2], Res_Darknut.spawn[1], Res_Darknut.spawn[0], Res_Darknut.spawn[3],
						Res_Darknut.spawn[4], Res_Darknut.spawn[3], Res_Darknut.spawn[0], Res_Darknut.spawn[5],
						Res_Darknut.spawn[6], Res_Darknut.spawn[7] }, ID_IDLE_DOWN, this),
				new AnimatedSprite(Res_Darknut.idle_down), new AnimatedSprite(Res_Darknut.idle_down),
				new AnimatedSprite(Res_Darknut.idle_down), new AnimatedSprite(Res_Darknut.idle_down),
				new AnimatedSprite(Res_Darknut.move_down), new AnimatedSprite(Res_Darknut.move_down),
				new AnimatedSprite(Res_Darknut.move_down), new AnimatedSprite(Res_Darknut.move_down) };
	}

}
