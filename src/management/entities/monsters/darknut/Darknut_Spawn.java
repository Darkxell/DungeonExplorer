package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.entities.Entity;
import management.entities.EntityState;

public class Darknut_Spawn extends EntityState {

	private int updater = 0;
	private int exitspawn = 60;

	public Darknut_Spawn(Entity parent) {
		super(parent);
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
				(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 28),
				(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 43), null);
	}

	@Override
	public void update() {
		++updater;
		exitspawn--;
		if (updater > (this.parententity.entityDesign.getSpriteID() == DarknutSpriteSheet.ID_SPAWN ? 4 : 7)) {
			updater = 0;
			this.parententity.entityDesign.next();
		}
		if (exitspawn < 0) {
			this.parententity.state = new Darknut_step(parententity);
		}
	}
}
