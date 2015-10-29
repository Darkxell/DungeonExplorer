package display.sprites.misc;

import java.awt.Graphics2D;

import res.images.Res_Inventory;

/** Class that holds the inventoryCursor utilities. */
public abstract class InventoryCursor {

    private static int updater;
    private static boolean isSmaller;

    public static final int RED = 0;
    public static final int GREEN = 1;

    /** updates the sprite. */
    public static void update() {
	++updater;
	if (updater > 15) {
	    updater = 0;
	    isSmaller = !isSmaller;
	}
    }

    /**
     * Draws the cursor on the graphics objects specified, at the wanted
     * coordinates.
     */
    public static void drawCursor(Graphics2D g2d, int posX, int posY,
	    int width, int height, int color) {
	switch (color) {
	case RED:
	    if (isSmaller) {
		g2d.drawImage(Res_Inventory.Cursor1red, posX + 1, posY + 1,
			null);
		g2d.drawImage(Res_Inventory.Cursor2red, posX + width - 8,
			posY + 1, null);
		g2d.drawImage(Res_Inventory.Cursor3red, posX + 1, posY + height
			- 8, null);
		g2d.drawImage(Res_Inventory.Cursor4red, posX + width - 8, posY
			+ height - 8, null);
	    } else {
		g2d.drawImage(Res_Inventory.Cursor1red, posX, posY, null);
		g2d.drawImage(Res_Inventory.Cursor2red, posX + width - 7, posY,
			null);
		g2d.drawImage(Res_Inventory.Cursor3red, posX,
			posY + height - 7, null);
		g2d.drawImage(Res_Inventory.Cursor4red, posX + width - 7, posY
			+ height - 7, null);
	    }
	    break;
	case GREEN:
	    if (isSmaller) {
		g2d.drawImage(Res_Inventory.Cursor1green, posX + 1, posY + 1,
			null);
		g2d.drawImage(Res_Inventory.Cursor2green, posX + width - 8,
			posY + 1, null);
		g2d.drawImage(Res_Inventory.Cursor3green, posX + 1, posY
			+ height - 8, null);
		g2d.drawImage(Res_Inventory.Cursor4green, posX + width - 8,
			posY + height - 8, null);
	    } else {
		g2d.drawImage(Res_Inventory.Cursor1green, posX, posY, null);
		g2d.drawImage(Res_Inventory.Cursor2green, posX + width - 7,
			posY, null);
		g2d.drawImage(Res_Inventory.Cursor3green, posX, posY + height
			- 7, null);
		g2d.drawImage(Res_Inventory.Cursor4green, posX + width - 7,
			posY + height - 7, null);
	    }
	    break;
	}

    }
}
