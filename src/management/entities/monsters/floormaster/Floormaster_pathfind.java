package management.entities.monsters.floormaster;

import java.awt.Graphics2D;

import management.entities.Entity;
import management.entities.EntityState;
import management.entities.monsters.pathfinding.AstarMap;
import management.player.PlayerInfo;

public class Floormaster_pathfind extends EntityState {

	private int updater;
	private AstarMap pmap;

	public Floormaster_pathfind(Entity parent) {
		super(parent);
		pmap = new AstarMap(parent.roompointer, (int) parent.posX, (int) parent.posY,
				(int) PlayerInfo.posX - parent.roompointer.posX, (int) PlayerInfo.posY - parent.roompointer.posY);
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
				(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 16),
				(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 16), null);
		if (PlayerInfo.DEBUGMODE) {
			pmap.print(g2d);
		}
	}

	@Override
	public void update() {
		++updater;
		if (updater > 16) {
			updater = 0;
			this.parententity.entityDesign.next();
		}
		if (parententity.distP2() >= 50)
			parententity.state = new Floormaster_idle(super.parententity);
		if (parententity.posX - PlayerInfo.posX + parententity.roompointer.posX > 1)
			parententity.posX -= 0.015;
		if (parententity.posX - PlayerInfo.posX + parententity.roompointer.posX < 1)
			parententity.posX += 0.015;
		if (parententity.posY - PlayerInfo.posY + parententity.roompointer.posY > 1)
			parententity.posY -= 0.015;
		if (parententity.posY - PlayerInfo.posY + parententity.roompointer.posY < 1)
			parententity.posY += 0.015;
	}

}
