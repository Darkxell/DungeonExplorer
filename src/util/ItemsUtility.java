package util;

import java.awt.image.BufferedImage;

import display.sprites.misc.JarItem;
import res.images.Res_Items;
import management.player.Inventory;
import management.player.PlayerInfo;

/** Class that holds static utility methods to manipulate items. */
public abstract class ItemsUtility {

    /**
     * Gets the sprite of the wanted item. This method will check in the player
     * inventory the level of the item to determinate the appropried sprite.
     * This will return null if the player don't have the wanted item (level 0)
     */
    public static BufferedImage getSpriteFromID(int itemID) {
	switch (itemID) {
	case Inventory.ITEM_SWORD:
	    switch (PlayerInfo.playerInventory.level_sword) {
	    case 1:
		return Res_Items.sword1;
	    case 2:
		return Res_Items.sword2;
	    case 3:
		return Res_Items.sword3;
	    case 4:
		return Res_Items.sword4;
	    case 5:
		return Res_Items.sword5;
	    }
	    break;
	case Inventory.ITEM_GUSTJAR:
	    if (PlayerInfo.playerInventory.level_gustjar == 1)
		return Res_Items.gustjar;
	    break;
	case Inventory.ITEM_STICK:
	    if (PlayerInfo.playerInventory.level_stick == 1)
		return Res_Items.stick;
	    break;
	case Inventory.ITEM_BOOMERANG:
	    switch (PlayerInfo.playerInventory.level_boomerang) {
	    case 1:
		return Res_Items.boomerang1;
	    case 2:
		return Res_Items.boomerang2;
	    }
	    break;
	case Inventory.ITEM_SHIELD:
	    switch (PlayerInfo.playerInventory.level_shield) {
	    case 1:
		return Res_Items.shield1;
	    case 2:
		return Res_Items.shield2;
	    }
	    break;
	case Inventory.ITEM_MITS:
	    if (PlayerInfo.playerInventory.level_mits == 1)
		return Res_Items.mits;
	    break;
	case Inventory.ITEM_LANTERN:
	    if (PlayerInfo.playerInventory.level_lantern == 1)
		return Res_Items.lantern;
	    break;
	case Inventory.ITEM_BOMBS:
	    switch (PlayerInfo.playerInventory.level_bombs) {
	    case 1:
		return Res_Items.bomb1;
	    case 2:
		return Res_Items.bomb2;
	    }
	    break;
	case Inventory.ITEM_BOOTS:
	    if (PlayerInfo.playerInventory.level_boots == 1)
		return Res_Items.boots;
	    break;
	case Inventory.ITEM_CAPE:
	    if (PlayerInfo.playerInventory.level_cape == 1)
		return Res_Items.cape;
	    break;
	case Inventory.ITEM_OCARINA:
	    if (PlayerInfo.playerInventory.level_ocarina == 1)
		return Res_Items.ocarina;
	    break;
	case Inventory.ITEM_BOW:
	    switch (PlayerInfo.playerInventory.level_bow) {
	    case 1:
		return Res_Items.bow1;
	    case 2:
		return Res_Items.bow2;
	    }
	    break;
	case Inventory.ITEM_JAR_1:
	    return JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar1);
	case Inventory.ITEM_JAR_2:
	    return JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar2);
	case Inventory.ITEM_JAR_3:
	    return JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar3);
	case Inventory.ITEM_JAR_4:
	    return JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar4);
	case Inventory.ITEM_FIREROD:
	    return null;

	}
	return null;
    }
}
