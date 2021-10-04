package management.entities.monsters.flock;

import java.awt.Color;
import java.awt.Graphics2D;

import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.entities.particle.MobDeath;
import management.floors.Room;
import management.player.PlayerInfo;
import util.MathVector;

/**
 * Content of a flock
 * 
 * @see Flock
 */
public class Boid extends Monster {

	public Flock parent;
	public MathVector direction = new MathVector(0d, 1d);

	public Boid(Room roompointer, Flock parent, double x, double y) {
		super(roompointer, x, y);
		this.parent = parent;
	}

	@Override
	public void updateM() {
		step();
		rebound();
	}

	private void step() {
		MathVector d = direction.normalize();
		this.posX += d.x * 0.04;
		this.posY += d.y * 0.04;
	}

	private void rebound() {
		if (this.posX < parent.posX)
			this.posX = parent.posX + parent.width;
		if (this.posY < parent.posY)
			this.posY = parent.posY + parent.height;
		
		if (this.posX > parent.posX + parent.width)
			this.posX = parent.posX;
		if (this.posY > parent.posY + parent.height)
			this.posY = parent.posY;
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.setColor(new Color(0, 0, 0, 120));
		g2d.fillRect((int) ((posX + roompointer.posX) * 16 - 3), (int) ((posY + roompointer.posY) * 16 - 3), 6, 6);
		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(Color.RED);
			int px = (int) ((posX + roompointer.posX) * 16), py = (int) ((posY + roompointer.posY) * 16);
			g2d.drawLine(px, py, (int) (px + 5 * direction.x), (int) (py + 5 * direction.y));
		}
	}

	@Override
	public void onhit() {
		parent.content.remove(this);
		kill();
		roompointer.addEntity(new MobDeath(roompointer, posX, posY));
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		Position[] points = new Position[9];
		double halfsize = 0.2d;
		points[0] = new Position(posX - halfsize, posY - halfsize);
		points[1] = new Position(posX, posY - halfsize);
		points[2] = new Position(posX + halfsize, posY - halfsize);
		points[3] = new Position(posX - halfsize, posY);
		points[4] = new Position(posX, posY);
		points[5] = new Position(posX + halfsize, posY);
		points[6] = new Position(posX - halfsize, posY + halfsize);
		points[7] = new Position(posX, posY + halfsize);
		points[8] = new Position(posX + halfsize, posY + halfsize);
		return new Hitbox(points);
	}

}
