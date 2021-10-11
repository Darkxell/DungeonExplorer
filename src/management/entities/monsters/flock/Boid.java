package management.entities.monsters.flock;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.entities.items.Heart;
import management.entities.particle.MobDeath;
import management.floors.Room;
import management.player.PlayerInfo;
import res.images.mobs.Res_Keese;
import util.MathUtils;
import util.MathVector;
import util.NumberUtil;

/**
 * Content of a flock
 * 
 * @see Flock
 */
public class Boid extends Monster {

	public static Random rand = new Random();

	public Flock parent;
	public MathVector direction;

	public static final double RANGE_REPULSE = 0.65d;
	public static final double RANGE_ALIGN = 2d;
	public static final double RANGE_ATTACK = 2d;
	public static final double RANGE_LURK = 4.8d;

	public Boid(Room roompointer, Flock parent, double x, double y) {
		super(roompointer, x, y);
		this.parent = parent;
		this.direction = new MathVector(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1);
	}

	@Override
	public void updateM() {
		align();
		repulse();
		lurk();
		attack();
		step();
		rebound();
		spriteupdater++;
		if (spriteupdater > 29)
			spriteupdater = 0;
	}

	private void step() {
		MathVector d = direction.normalize();
		this.posX += d.x * 0.06;
		this.posY += d.y * 0.06;
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

	/**
	 * Changes this boid's direction slightly to be more in line with its neighbors.
	 */
	private void align() {
		ArrayList<Boid> p = parent.content;
		MathVector adjustment = new MathVector(0d, 0d);
		for (int i = 0; i < p.size(); i++)
			// Compiled properly, don't bother
			if (p.get(i) != this && distanceSquared(p.get(i)) < RANGE_ALIGN * RANGE_ALIGN) {
				adjustment.x += p.get(i).direction.x;
				adjustment.y += p.get(i).direction.y;
			}
		if (!adjustment.isZero()) {
			adjustment.normalize().mul(0.08d);
			this.direction.add(adjustment).normalize();
		}
	}

	/**
	 * Changes this boid's direction to go slightly towards its neighbors.
	 */
	private void lurk() {
		ArrayList<Boid> p = parent.content;
		MathVector adjustment = new MathVector(0d, 0d);
		for (int i = 0; i < p.size(); i++)
			// Compiled properly, don't bother
			if (p.get(i) != this && distanceSquared(p.get(i)) < RANGE_LURK * RANGE_LURK) {
				adjustment.x += p.get(i).posX - this.posX;
				adjustment.y += p.get(i).posY - this.posY;
			}
		if (!adjustment.isZero()) {
			adjustment.normalize().mul(0.04d);
			this.direction.add(adjustment).normalize();
		}
	}

	/**
	 * Changes this boid's direction to go slightly towards the player
	 */
	private void attack() {
		MathVector adjustment = new MathVector(0d, 0d);
		double distp = MathUtils.dist2(super.posX + roompointer.posX, super.posY + roompointer.posY, PlayerInfo.posX,
				PlayerInfo.posY);
		if (distp < RANGE_ATTACK * RANGE_ATTACK) {
			adjustment.x += PlayerInfo.posX - roompointer.posX - this.posX;
			adjustment.y += PlayerInfo.posY - roompointer.posY - this.posY;
		}
		if (!adjustment.isZero()) {
			adjustment.normalize().mul(0.2d);
			this.direction.add(adjustment).normalize();
		}
	}

	/**
	 * Changes this boid's direction to go away from its very close neighbors.
	 */
	private void repulse() {
		ArrayList<Boid> p = parent.content;
		MathVector adjustment = new MathVector(0d, 0d);
		for (int i = 0; i < p.size(); i++)
			// Compiled properly, don't bother
			if (p.get(i) != this && distanceSquared(p.get(i)) < RANGE_REPULSE * RANGE_REPULSE) {
				adjustment.x -= p.get(i).posX - this.posX;
				adjustment.y -= p.get(i).posY - this.posY;
			}
		if (!adjustment.isZero()) {
			adjustment.normalize().mul(0.8d);
			this.direction.add(adjustment).normalize();
		}
	}

	private int spriteupdater = 0;

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(Res_Keese.keese[spriteupdater / 5], (int) ((super.roompointer.posX + super.posX) * 16 - 16),
				(int) ((super.roompointer.posY + super.posY) * 16 - 18), null);
		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(new Color(0, 0, 0, 120));
			g2d.fillRect((int) ((posX + roompointer.posX) * 16 - 3), (int) ((posY + roompointer.posY) * 16 - 3), 6, 6);
			g2d.setColor(Color.RED);
			int px = (int) ((posX + roompointer.posX) * 16), py = (int) ((posY + roompointer.posY) * 16);
			g2d.drawLine(px, py, (int) (px + 5 * direction.x), (int) (py + 5 * direction.y));
			g2d.setColor(new Color(255, 255, 255, 35));
			int r2align = (int) (16 * RANGE_ALIGN * 2), r2repulse = (int) (16 * RANGE_REPULSE * 2),
					r2lurk = (int) (16 * RANGE_LURK * 2);
			g2d.drawOval(px - r2align / 2, py - r2align / 2, r2align, r2align);
			g2d.drawOval(px - r2repulse / 2, py - r2repulse / 2, r2repulse, r2repulse);
			g2d.setColor(new Color(255, 255, 255, 20));
			g2d.drawOval(px - r2lurk / 2, py - r2lurk / 2, r2lurk, r2lurk);
		}
	}

	@Override
	public void onhit() {
		parent.content.remove(this);
		kill();
		roompointer.addEntity(new MobDeath(roompointer, posX, posY));
		int r = NumberUtil.randomINT(1, 10);
		if (r == 1)
		    roompointer.addEntity(new Heart(roompointer, posX, posY,20));
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

	public double distanceSquared(Boid with) {
		return Math.pow(this.posX - with.posX, 2) + Math.pow(this.posY - with.posY, 2);
	}

}
