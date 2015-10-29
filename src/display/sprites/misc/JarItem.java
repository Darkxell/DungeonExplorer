package display.sprites.misc;

import java.awt.image.BufferedImage;

import management.player.Inventory;
import res.images.Res_Items;

public abstract class JarItem {

    private static int updater;

    /** updates the fairy jar sprite */
    public static void update() {
	++updater;
	if (updater > 30)
	    updater = 0;
    }

    /**
     * returns the jar sprite accorded to the jar level. if the jar level is 0
     * or too hight, null will be returned.
     */
    public static BufferedImage getJarSprite(int jarlevel) {
	switch (jarlevel) {
	case Inventory.JAR_EMPTY:
	    return Res_Items.jar_empty;
	case Inventory.JAR_FAIRY:
	    return (updater > 20) ? Res_Items.jar_fairy1
		    : (updater > 10) ? Res_Items.jar_fairy2
			    : Res_Items.jar_fairy1;
	case Inventory.JAR_MILK:
	    return Res_Items.jar_milk;
	case Inventory.JAR_MILKHALF:
	    return Res_Items.jar_halfmilk;
	case Inventory.JAR_BLUEPOTION:
	    return Res_Items.jar_bluepotion;
	case Inventory.JAR_REDPOTION:
	    return Res_Items.jar_redpotion;
	case Inventory.JAR_WATER:
	    return Res_Items.jar_water;
	case Inventory.JAR_GREENWATER:
	    return Res_Items.jar_greenwater;
	case Inventory.JAR_REDAMULET:
	    return Res_Items.jar_redamulet;
	case Inventory.JAR_GREENAMULET:
	    return Res_Items.jar_greenamulet;
	case Inventory.JAR_BLUEAMULET:
	    return Res_Items.jar_blueamulet;
	}
	return null;
    }
}
