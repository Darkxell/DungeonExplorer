package display.sprites.entities;

import res.images.mobs.Res_Sluggula;
import display.sprites.AnimatedSprite;
import display.sprites.SpriteSheet;

public class SluggulaSpriteSheet extends SpriteSheet {

    public static final int ID_WALK_DOWN = 0;
    public static final int ID_WALK_LEFT = 1;
    public static final int ID_WALK_UP = 2;
    public static final int ID_WALK_RIGHT = 3;
    public static final int ID_FALL = 4;

    public SluggulaSpriteSheet() {
	super(null);
	super.sprites = new AnimatedSprite[] {
		new AnimatedSprite(Res_Sluggula.slime_down),
		new AnimatedSprite(Res_Sluggula.slime_left),
		new AnimatedSprite(Res_Sluggula.slime_up),
		new AnimatedSprite(Res_Sluggula.slime_right),
		new AnimatedSprite(Res_Sluggula.fall)
	};
    }
}
