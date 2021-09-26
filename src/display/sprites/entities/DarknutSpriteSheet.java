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

	public static final int ID_WINDUP_LEFT = 9;
	public static final int ID_WINDUP_RIGHT = 10;
	public static final int ID_CHARGE_LEFT = 11;
	public static final int ID_CHARGE_RIGHT = 12;

	public static final int ID_SLASH_LEFT = 13;
	public static final int ID_SLASH_RIGHT = 14;
	public static final int ID_SLASH_UP = 15;
	public static final int ID_SLASH_DOWN = 16;

	public DarknutSpriteSheet() {
		super(null);
		super.sprites = new AnimatedSprite[] {
				new AnimatedSprite(new BufferedImage[] { Res_Darknut.spawn[0], Res_Darknut.spawn[1],
						Res_Darknut.spawn[2], Res_Darknut.spawn[1], Res_Darknut.spawn[0], Res_Darknut.spawn[3],
						Res_Darknut.spawn[4], Res_Darknut.spawn[3], Res_Darknut.spawn[0], Res_Darknut.spawn[5],
						Res_Darknut.spawn[6], Res_Darknut.spawn[7] }, ID_IDLE_DOWN, this),
				new AnimatedSprite(Res_Darknut.idle_left), new AnimatedSprite(Res_Darknut.idle_right),
				new AnimatedSprite(Res_Darknut.idle_up), new AnimatedSprite(Res_Darknut.idle_down),
				new AnimatedSprite(Res_Darknut.move_left), new AnimatedSprite(Res_Darknut.move_right),
				new AnimatedSprite(Res_Darknut.move_up), new AnimatedSprite(Res_Darknut.move_down),
				new AnimatedSprite(Res_Darknut.windup_left, ID_CHARGE_LEFT, this),
				new AnimatedSprite(Res_Darknut.windup_right, ID_CHARGE_RIGHT, this),
				new AnimatedSprite(Res_Darknut.charge_left), new AnimatedSprite(Res_Darknut.charge_right),
				new AnimatedSprite(Res_Darknut.slash_left, ID_IDLE_LEFT, this),
				new AnimatedSprite(Res_Darknut.slash_right, ID_IDLE_RIGHT, this),
				new AnimatedSprite(Res_Darknut.slash_up, ID_IDLE_UP, this),
				new AnimatedSprite(Res_Darknut.slash_down, ID_IDLE_DOWN, this) };
	}

}
