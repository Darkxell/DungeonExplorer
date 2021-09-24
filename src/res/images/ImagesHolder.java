package res.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import res.Res;

/**
 * Class that holds BufferedImages Objects to be used to create subImages. Those
 * images sould not be edited by getting their graphic objects.
 */
public abstract class ImagesHolder {

	public static final BufferedImage ENTITIES_PLAYER = readfile(Res.FOLDER_PATH + "entities/player.png");
	public static final BufferedImage ENTITIES_SLUGGULA = readfile(Res.FOLDER_PATH + "entities/sluggula.png");
	public static final BufferedImage ENTITIES_OKTOROK = readfile(Res.FOLDER_PATH + "entities/oktorok.png");
	public static final BufferedImage ENTITIES_FLOORMASTER = readfile(Res.FOLDER_PATH + "entities/floormaster.png");
	public static final BufferedImage ENTITIES_DARKNUT = readfile(Res.FOLDER_PATH + "entities/darknut.png");
	public static final BufferedImage ENTITIES_BOULDER = readfile(Res.FOLDER_PATH + "entities/boulder.png");
	public static final BufferedImage ENTITIES_TTEMS = readfile(Res.FOLDER_PATH + "entities/itemdrops.png");
	public static final BufferedImage ENTITIES_UNKNOWN = readfile(Res.FOLDER_PATH + "entities/unknown.png");
	public static final BufferedImage ENTITIES_MEAT = readfile(Res.FOLDER_PATH + "entities/meat.png");

	public static final BufferedImage PARTICLE_MOBDEATH = readfile(Res.FOLDER_PATH + "particles/mobdeath.png");
	public static final BufferedImage PARTICLE_BOULDERCOLLAPSE = readfile(
			Res.FOLDER_PATH + "particles/bouldercollapse.png");
	public static final BufferedImage PARTICLE_MOBHIT = readfile(Res.FOLDER_PATH + "particles/mobhit.png");

	public static final BufferedImage INVENTORY_INVENTORY = readfile(Res.FOLDER_PATH + "inventory/inventory.png");

	public static final BufferedImage MENU_BACKGROUND = readfile(Res.FOLDER_PATH + "mainmenu/background.png");
	public static final BufferedImage MENU_FILESELECT = readfile(Res.FOLDER_PATH + "mainmenu/fileselect.png");
	public static final BufferedImage MENU_LIGHT = readfile(Res.FOLDER_PATH + "mainmenu/light.png");
	public static final BufferedImage MENU_SELECTBG = readfile(Res.FOLDER_PATH + "mainmenu/selectbackground.png");
	public static final BufferedImage MENU_START = readfile(Res.FOLDER_PATH + "mainmenu/start.png");
	public static final BufferedImage MENU_SWORD = readfile(Res.FOLDER_PATH + "mainmenu/sword.png");
	public static final BufferedImage MENU_TITLE = readfile(Res.FOLDER_PATH + "mainmenu/title.png");

	public static final BufferedImage TILES = readfile(Res.FOLDER_PATH + "tiles/tileset.png");

	public static final BufferedImage FRAMESHADOW = readfile(Res.FOLDER_PATH + "tiles/frameshadow.png");
	public static final BufferedImage FRAMEICONS = readfile(Res.FOLDER_PATH + "tiles/frameicons.png");
	public static final BufferedImage CONTROLS = readfile(Res.FOLDER_PATH + "tiles/controls.png");
	public static final BufferedImage ICON = readfile(Res.FOLDER_PATH + "tiles/icon.png");

	public static BufferedImage readfile(String filepath) {
		try {
			BufferedImage start = ImageIO.read(new File(filepath));
			return start;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
