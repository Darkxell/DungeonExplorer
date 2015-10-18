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
			new AnimatedSprite(new BufferedImage[] {
				Res_Player.walk_down1, Res_Player.walk_down2,
				Res_Player.walk_down3, Res_Player.walk_down4,
				Res_Player.walk_down5, Res_Player.walk_down6,
				Res_Player.walk_down7, Res_Player.walk_down8,
				Res_Player.walk_down9, Res_Player.walk_down10 }),
			new AnimatedSprite(new BufferedImage[] {
				Res_Player.walk_left1, Res_Player.walk_left2,
				Res_Player.walk_left3, Res_Player.walk_left4,
				Res_Player.walk_left5, Res_Player.walk_left6,
				Res_Player.walk_left7, Res_Player.walk_left8,
				Res_Player.walk_left9, Res_Player.walk_left10 }),
			new AnimatedSprite(new BufferedImage[] {
				Res_Player.walk_up1, Res_Player.walk_up2,
				Res_Player.walk_up3, Res_Player.walk_up4,
				Res_Player.walk_up5, Res_Player.walk_up6,
				Res_Player.walk_up7, Res_Player.walk_up8,
				Res_Player.walk_up9, Res_Player.walk_up10 })
		});

    }

}
