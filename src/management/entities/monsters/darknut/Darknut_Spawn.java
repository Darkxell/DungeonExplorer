package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.entities.Entity;
import management.entities.EntityState;
import management.player.PlayerInfo;

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
			Darknut p = (Darknut) parententity;
			p.circleX = 3.5 + Math.random() * 10;
			p.circleY = 3.5 + Math.random() * 8;
			try {
				p.dmap.compute(p.posX, p.posY, PlayerInfo.posX - p.roompointer.posX,
						PlayerInfo.posY - p.roompointer.posY);
			} catch (Exception e) {
				System.err.println("Couldn't compute Dijkstra map For a Darknut!");
				e.printStackTrace();
			}
			p.following = p.dmap.path.size() - 1;
			this.parententity.state = new Darknut_step(parententity);
		}
	}
}
