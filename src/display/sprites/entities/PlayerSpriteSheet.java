package display.sprites.entities;

import java.awt.image.BufferedImage;

import res.images.Res_Player;
import display.sprites.AnimatedSprite;
import display.sprites.SpriteSheet;

/** SpriteSheet Creator for the player Object. */
public class PlayerSpriteSheet extends SpriteSheet {

    public static final int ID_STILL_DOWN = 0;
    public static final int ID_STILL_LEFT = 1;
    public static final int ID_STILL_UP = 2;
    public static final int ID_WALK_DOWN = 3;
    public static final int ID_WALK_LEFT = 4;
    public static final int ID_WALK_UP = 5;
    public static final int ID_SLASH_DOWN_1 = 6;
    public static final int ID_SLASH_LEFT_1 = 7;
    public static final int ID_SLASH_UP_1 = 8;
    public static final int ID_SLASH_DOWN_2 = 9;
    public static final int ID_SLASH_LEFT_2 = 10;
    public static final int ID_SLASH_UP_2 = 11;
    public static final int ID_SLASH_DOWN_3 = 12;
    public static final int ID_SLASH_LEFT_3 = 13;
    public static final int ID_SLASH_UP_3 = 14;
    public static final int ID_SLASH_DOWN_4 = 15;
    public static final int ID_SLASH_LEFT_4 = 16;
    public static final int ID_SLASH_UP_4 = 17;
    public static final int ID_SLASH_DOWN_5 = 18;
    public static final int ID_SLASH_LEFT_5 = 19;
    public static final int ID_SLASH_UP_5 = 20;

    /** Creates a new player PlayerSpriteSheet and returns it. */
    public PlayerSpriteSheet() {
	super(null);
	super.sprites = new AnimatedSprite[] {
		new AnimatedSprite(
			new BufferedImage[] { Res_Player.still_down }),
		new AnimatedSprite(
			new BufferedImage[] { Res_Player.still_left }),
		new AnimatedSprite(new BufferedImage[] { Res_Player.still_up }),
		new AnimatedSprite(Res_Player.walk_down),
		new AnimatedSprite(Res_Player.walk_left),
		new AnimatedSprite(Res_Player.walk_up),
		new AnimatedSprite(Res_Player.slash_down_1, ID_STILL_DOWN, this),
		new AnimatedSprite(Res_Player.slash_left_1, ID_STILL_LEFT, this),
		new AnimatedSprite(Res_Player.slash_up_1, ID_STILL_UP, this),
		new AnimatedSprite(Res_Player.slash_down_2, ID_STILL_DOWN, this),
		new AnimatedSprite(Res_Player.slash_left_2, ID_STILL_LEFT, this),
		new AnimatedSprite(Res_Player.slash_up_2, ID_STILL_UP, this),
		new AnimatedSprite(Res_Player.slash_down_3, ID_STILL_DOWN, this),
		new AnimatedSprite(Res_Player.slash_left_3, ID_STILL_LEFT, this),
		new AnimatedSprite(Res_Player.slash_up_3, ID_STILL_UP, this),
		new AnimatedSprite(Res_Player.slash_down_4, ID_STILL_DOWN, this),
		new AnimatedSprite(Res_Player.slash_left_4, ID_STILL_LEFT, this),
		new AnimatedSprite(Res_Player.slash_up_4, ID_STILL_UP, this),
		new AnimatedSprite(Res_Player.slash_down_5, ID_STILL_DOWN, this),
		new AnimatedSprite(Res_Player.slash_left_5, ID_STILL_LEFT, this),
		new AnimatedSprite(Res_Player.slash_up_5, ID_STILL_UP, this), };

    }

}
