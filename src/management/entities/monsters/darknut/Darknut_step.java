package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.entities.Entity;
import management.entities.EntityState;
import management.player.PlayerInfo;
import util.MathUtils;

public class Darknut_step extends EntityState {

	private int updater = 0;
	private int steplength = 0;

	private static final int DASHFRAMES = 14;

	public Darknut_step(Entity parent) {
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
		switch (steplength) {
		case 0:
			super.parententity.lookAt(PlayerInfo.posX - super.parententity.roompointer.posX,
					PlayerInfo.posY - super.parententity.roompointer.posY);
			super.parententity.entityDesign.setSpriteID(DarknutSpriteSheet.ID_MOVE_LEFT + super.parententity.facing);
			break;
		case DASHFRAMES:
			super.parententity.entityDesign.setSpriteID(DarknutSpriteSheet.ID_IDLE_LEFT + super.parententity.facing);
			break;
		}
		++updater;
		++steplength;
		if (updater > (7)) {
			updater = 0;
			this.parententity.entityDesign.next();
		}

		Darknut p = (Darknut) parententity;
		if (p.dmap.path != null) {
			double distppp = MathUtils.dist2(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5,
					this.parententity.posX, this.parententity.posY);
			p.moveto(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5,
					steplength > DASHFRAMES ? 0.024 : 0.075);
			if (distppp < 0.35)
				p.nextState();
		}

	}

}
