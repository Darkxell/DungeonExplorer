package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.entities.Entity;
import management.entities.EntityState;
import management.player.PlayerInfo;
import util.MathUtils;

public class Darknut_slash extends EntityState {

	private int updater = 0;
	private int slashlength = 0;

	private static final int WINDUP = 16;
	private static final int SLASHDURATION = 29;

	public Darknut_slash(Entity parent) {
		super(parent);
	}

	@Override
	public void print(Graphics2D g2d) {
		if (this.parententity.entityDesign.getSpriteID() >= DarknutSpriteSheet.ID_SLASH_LEFT
				&& this.parententity.entityDesign.getSpriteID() <= DarknutSpriteSheet.ID_SLASH_DOWN) {
			// If slashing, draw with slash offsets (don't ask)
			switch (this.parententity.facing) {
			case Entity.DOWN:
				g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
						(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 40),
						(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 40), null);
				break;
			case Entity.LEFT:
			case Entity.RIGHT:
				g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
						(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 40),
						(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 40), null);
				break;
			case Entity.UP:
				g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
						(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 32),
						(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 40), null);
				break;
			}
		} else {
			// Draw with normal coordinates
			g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
					(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 28),
					(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 43), null);
		}

	}

	@Override
	public void update() {
		Darknut p = (Darknut) parententity;
		if (slashlength == 0) {
			super.parententity.lookAt(PlayerInfo.posX - super.parententity.roompointer.posX,
					PlayerInfo.posY - super.parententity.roompointer.posY);
			super.parententity.entityDesign.setSpriteID(DarknutSpriteSheet.ID_SLASH_LEFT + super.parententity.facing);
		}

		++slashlength;
		++updater;

		if (updater > 2) {
			updater = 0;
			p.entityDesign.next();
		}
		if (slashlength >= SLASHDURATION)
			p.nextState();
	}

}
