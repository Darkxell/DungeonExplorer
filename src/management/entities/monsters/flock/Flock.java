package management.entities.monsters.flock;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;
import management.player.PlayerInfo;

/**
 * Entity that contains Boids. This flock entity spawns Boids and makes them
 * move. Do note that entities added by this flock are added to the parent room
 * too.
 */
public class Flock extends Monster {

	public ArrayList<Boid> content = new ArrayList<>(20);
	public double width, height;
	/** The maximum amount of mobs spawned at once. */
	public int size = 25;
	/** The maximum amount of boids spawned by this flock. */
	public int totalsize = 50;
	private int spawned = 0;
	private int spawner = 0;
	private static final int SPAWNCOOLDOWN = 15;

	public Flock(Room roompointer, double x, double y, double width, double height) {
		super(roompointer, x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void updateM() {
		if (spawned < totalsize) {
			spawner++;
			if (spawner >= SPAWNCOOLDOWN && content.size() < size) {
				spawner = 0;
				spawned++;
				Boid newboid = new Boid(roompointer, this, posX + width / 2, posY + height / 2);
				content.add(newboid);
				roompointer.addEntity(newboid);
			}
		}
	}

	@Override
	public void print(Graphics2D g2d) {
		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(Color.BLACK);
			g2d.drawRect((int) (16 * (posX + roompointer.posX)), (int) (16 * (posY + roompointer.posY)),
					(int) (width * 16), (int) (height * 16));
		}

		for (int i = 0; i < content.size(); i++)
			if (content.get(i) instanceof ObstacleBoid)
				content.get(i).print(g2d);
	}

	@Override
	public void onhit() {
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		return null;
	}

}
