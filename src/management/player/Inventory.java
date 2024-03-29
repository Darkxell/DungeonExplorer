package management.player;

/**
 * Object that represents the player inventory. This contains the defferent main
 * items owned by the player, the quest items, and the collectibles (ruppees,
 * shells,keys...). Basically holds everything but the player health.
 */
public class Inventory {

	public static final int ITEM_SWORD = 0;
	public static final int ITEM_GUSTJAR = 1;
	public static final int ITEM_STICK = 2;
	public static final int ITEM_BOOMERANG = 3;
	public static final int ITEM_SHIELD = 4;
	public static final int ITEM_MITS = 5;
	public static final int ITEM_LANTERN = 6;
	public static final int ITEM_BOMBS = 7;
	public static final int ITEM_BOOTS = 8;
	public static final int ITEM_CAPE = 9;
	public static final int ITEM_OCARINA = 10;
	public static final int ITEM_BOW = 11;
	public static final int ITEM_JAR_1 = 12;
	public static final int ITEM_JAR_2 = 13;
	public static final int ITEM_JAR_3 = 14;
	public static final int ITEM_JAR_4 = 15;
	public static final int ITEM_FIREROD = 16;

	public static final int JAR_EMPTY = 1;
	public static final int JAR_FAIRY = 2;
	public static final int JAR_MILK = 3;
	public static final int JAR_MILKHALF = 4;
	public static final int JAR_BLUEPOTION = 5;
	public static final int JAR_REDPOTION = 6;
	public static final int JAR_WATER = 7;
	public static final int JAR_GREENWATER = 8;
	public static final int JAR_REDAMULET = 9;
	public static final int JAR_GREENAMULET = 10;
	public static final int JAR_BLUEAMULET = 11;

	public double maxhealth = 4.0d;
	public int ruppees = 0;
	public int keys = 0;
	public int bombs = 0;
	public int arrows = 0;
	public int maxbombs = 10;
	public int maxarrows = 30;

	public int level_sword = 5; // max 5
	public int level_gustjar = 0; // max 1
	public int level_stick = 0; // max 1
	public int level_boomerang = 2; // max 2
	public int level_shield = 2; // max 2
	public int level_mits = 0; // max 1
	public int level_lantern = 0; // max 1
	public int level_bombs = 0; // max 2
	public int level_boots = 0;// max 1
	public int level_cape = 0; // max 1
	public int level_ocarina = 0; // max 1
	public int level_bow = 0; // max 2
	public int level_jar1 = JAR_FAIRY;
	public int level_jar2 = JAR_EMPTY;
	public int level_jar3 = JAR_WATER;
	public int level_jar4 = JAR_GREENAMULET;

	public boolean dungeon_hasmap = true;
	public boolean dungeon_hasbosskey = false;
	public boolean dungeon_hascompass = true;
}
