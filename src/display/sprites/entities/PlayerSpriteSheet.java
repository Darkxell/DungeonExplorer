package display.sprites.entities;

import java.awt.image.BufferedImage;

import res.images.Res_Player;
import display.sprites.AnimatedSprite;
import display.sprites.SpriteSheet;

/** SpriteSheet Creator for the player Object. */
public class PlayerSpriteSheet {

    public static final int ID_STILL_DOWN = 0;
    public static final int ID_STILL_LEFT = 1;
    public static final int ID_STILL_UP = 2;

    public static final int ID_WALK_DOWN = 3;
    public static final int ID_WALK_LEFT = 4;
    public static final int ID_WALK_UP = 5;

    /** Creates a new player SpriteSheet and returns it. */
    public static SpriteSheet create() {

	return new SpriteSheet(
		new AnimatedSprite[] {
			new AnimatedSprite(
				new BufferedImage[] { Res_Player.still_down }),
			new AnimatedSprite(
				new BufferedImage[] { Res_Player.still_left }),
			new AnimatedSprite(
				new BufferedImage[] { Res_Player.still_up }),
			new AnimatedSprite(Res_Player.walk_down),
			new AnimatedSprite(Res_Player.walk_left),
			new AnimatedSprite(Res_Player.walk_up) });

    }

}
