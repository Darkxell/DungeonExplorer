package display.sprites.misc;

import java.awt.image.BufferedImage;

import res.images.Res_FileSelect;
import display.sprites.AnimatedSprite;

/** Sprite Creator for the MenuPlayer. */
public class MenuPlayerSprite {

    /** Creates a MenuPlayer animatedSprite. */
    public static AnimatedSprite create() {
	return new AnimatedSprite(new BufferedImage[] { Res_FileSelect.player1,
		Res_FileSelect.player2, Res_FileSelect.player3,
		Res_FileSelect.player4, Res_FileSelect.player5,
		Res_FileSelect.player6, Res_FileSelect.player7,
		Res_FileSelect.player8, Res_FileSelect.player9,
		Res_FileSelect.player10 });
    }
}
