package display.sprites.entities;

import display.sprites.AnimatedSprite;
import display.sprites.SpriteSheet;
import res.images.mobs.Res_Floormaster;

public class FloormasterSpriteSheet extends SpriteSheet {

	public static final int ID_BREATH = 0;
	public static final int ID_GRAB = 1;

	public FloormasterSpriteSheet() {
		super(null);
		super.sprites = new AnimatedSprite[] { new AnimatedSprite(Res_Floormaster.breath),
				new AnimatedSprite(Res_Floormaster.grab, ID_BREATH, this) };
	}

}
