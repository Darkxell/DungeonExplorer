package management.entities.monsters.floormaster;

import java.awt.Graphics2D;

import management.entities.Entity;
import management.entities.EntityState;
import res.images.mobs.Res_Floormaster;

public class Floormaster_idle extends EntityState {

	public Floormaster_idle(Entity parent) {
		super(parent);
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(Res_Floormaster.grab[4],
				(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 16),
				(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 16), null);
	}

	@Override
	public void update() {
		if (parententity.distP2() < 36)
			super.parententity.state = new Floormaster_pathfind(super.parententity);

	}

}
