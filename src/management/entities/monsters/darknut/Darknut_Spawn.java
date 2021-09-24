package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import management.entities.Entity;
import management.entities.EntityState;

public class Darknut_Spawn extends EntityState {

	private int updater = 0;
	
	public Darknut_Spawn(Entity parent) {
		super(parent);
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
				(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 28),
				(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 28), null);
	}

	@Override
	public void update() {
		++updater;
		if (updater > 2) {
			updater = 0;
			this.parententity.entityDesign.next();
		}
	}
}
