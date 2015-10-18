package display.sprites.misc;

import java.awt.image.BufferedImage;

import res.images.Res_FileSelect;
import display.sprites.AnimatedSprite;

/** Sprite Creator for the MenuCristal. */
public class MenuCristalSprite {

    /** Creates a MenuCristal ANimatedSprite. */
    public static AnimatedSprite create() {
	return new AnimatedSprite(new BufferedImage[] {
		Res_FileSelect.cristal1, Res_FileSelect.cristal2,
		Res_FileSelect.cristal3, Res_FileSelect.cristal4,
		Res_FileSelect.cristal5, Res_FileSelect.cristal6,
		Res_FileSelect.cristal7 });
    }
}
