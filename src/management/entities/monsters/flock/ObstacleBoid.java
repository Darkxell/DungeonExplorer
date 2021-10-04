package management.entities.monsters.flock;

import java.awt.Color;
import java.awt.Graphics2D;

import management.floors.Room;
import management.player.PlayerInfo;

public class ObstacleBoid extends Boid {

	public ObstacleBoid(Room roompointer, Flock parent, double x, double y) {
		super(roompointer, parent, x, y);
	}

	@Override
	public void updateM() {
	}

	@Override
	public void print(Graphics2D g2d) {
		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(Color.RED);
			g2d.fillRect((int) ((posX + roompointer.posX) * 16 - 3), (int) ((posY + roompointer.posY) * 16 - 3), 6, 6);
		}
	}

}
