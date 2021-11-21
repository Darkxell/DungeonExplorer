package res.images;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class that holds BufferedImages Objects to be used to create subImages. Those
 * images sould not be edited by getting their graphic objects.
 */
public abstract class ImagesHolder {

	public static final BufferedImage ENTITIES_PLAYER = readfile("/entities/player.png");
	public static final BufferedImage ENTITIES_SLUGGULA = readfile("/entities/sluggula.png");
	public static final BufferedImage ENTITIES_OKTOROK = readfile("/entities/oktorok.png");
	public static final BufferedImage ENTITIES_FLOORMASTER = readfile("/entities/floormaster.png");
	public static final BufferedImage ENTITIES_KEESE = readfile("/entities/keese.png");
	public static final BufferedImage ENTITIES_ZOL = readfile("/entities/zol.png");
	public static final BufferedImage ENTITIES_DARKNUT = readfile("/entities/darknut.png");
	public static final BufferedImage ENTITIES_DARKNUTSLASH = readfile("/entities/darknutslash.png");
	public static final BufferedImage ENTITIES_BOSS1 = readfile("/entities/bigchuchu.png");
	public static final BufferedImage ENTITIES_CIRCLE = readfile("/entities/circle.png");
	public static final BufferedImage ENTITIES_BOSSBAR = readfile("/entities/bossbar.png");
	public static final BufferedImage ENTITIES_BOULDER = readfile("/entities/boulder.png");
	public static final BufferedImage ENTITIES_TTEMS = readfile("/entities/itemdrops.png");
	public static final BufferedImage ENTITIES_UNKNOWN = readfile("/entities/unknown.png");
	public static final BufferedImage ENTITIES_MEAT = readfile("/entities/meat.png");

	public static final BufferedImage PARTICLE_MOBDEATH = readfile("/particles/mobdeath.png");
	public static final BufferedImage PARTICLE_BOULDERCOLLAPSE = readfile("/particles/bouldercollapse.png");
	public static final BufferedImage PARTICLE_MOBHIT = readfile("/particles/mobhit.png");
	public static final BufferedImage PARTICLE_BOOMERANG = readfile("/particles/boomerang.png");

	public static final BufferedImage INVENTORY_INVENTORY = readfile("/inventory/inventory.png");

	public static final BufferedImage MENU_BACKGROUND = readfile("/mainmenu/background.png");
	public static final BufferedImage MENU_FILESELECT = readfile("/mainmenu/fileselect.png");
	public static final BufferedImage MENU_LIGHT = readfile("/mainmenu/light.png");
	public static final BufferedImage MENU_SELECTBG = readfile("/mainmenu/selectbackground.png");
	public static final BufferedImage MENU_START = readfile("/mainmenu/start.png");
	public static final BufferedImage MENU_SWORD = readfile("/mainmenu/sword.png");
	public static final BufferedImage MENU_TITLE = readfile("/mainmenu/title.png");

	public static final BufferedImage TILES = readfile("/tiles/tileset.png");

	public static final BufferedImage FRAMESHADOW = readfile("/tiles/frameshadow.png");
	public static final BufferedImage FRAMEICONS = readfile("/tiles/frameicons.png");
	public static final BufferedImage CONTROLS = readfile("/tiles/controls.png");
	public static final BufferedImage ICON = readfile("/tiles/icon.png");

	public static BufferedImage readfile(String filepath) {
		try {
			BufferedImage start = ImageIO.read(ImagesHolder.class.getResource(filepath));
			return start;
		} catch (IOException e) {
			System.err.println("Couldn't read image file:");
			e.printStackTrace();
			return null;
		}
	}
}
