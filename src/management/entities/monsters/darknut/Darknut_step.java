package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import management.entities.Entity;
import management.entities.EntityState;
import util.MathUtils;

public class Darknut_step extends EntityState {

	private int updater = 0;
	private int steplength = 0;

	private static final int DASHFRAMES = 12;
	
	public Darknut_step(Entity parent) {
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
		++steplength;
		if (updater > (7)) {
			updater = 0;
			this.parententity.entityDesign.next();
		}

		Darknut p = (Darknut) parententity;
		if (p.dmap.path != null) {
			if (p.following == -1)
				p.following = p.dmap.path.size() - 1;
			double distppp = MathUtils.dist2(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5,
					this.parententity.posX, this.parententity.posY);
			if (p.following > 0 && distppp < 0.35) {
				p.following -= 1;
			}
			p.moveto(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5,
					steplength > DASHFRAMES ? 0.032 : 0.051);
		}

	}

}
