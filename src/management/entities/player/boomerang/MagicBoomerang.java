package management.entities.player.boomerang;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.DungeonExplorer;
import management.entities.Entity;
import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;
import management.player.PlayerInfo;
import res.audio.SoundsHolder;
import res.images.Res_PlayerItems;
import util.MathVector;
import util.NumberUtil;

public class MagicBoomerang extends Entity {

	private ArrayList<BoomerangTarget> targets;

	private final double originalX;
	private final double originalY;

	public final byte SMOOTHING_NONE = 0;
	public final byte SMOOTHING_CHAIKIN = 1;
	public final byte SMOOTHING_HERMIT = 2;
	public final byte SMOOTHING_BEZIER = 3;

	private byte smoothing = SMOOTHING_BEZIER;

	public MagicBoomerang(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		this.originalX = x;
		this.originalY = y;
		targets = new ArrayList<>(roompointer.entities.length);
		for (int i = 0; i < roompointer.entities.length; i++)
			if (roompointer.entities[i] instanceof Monster)
				targets.add(new BoomerangTarget(roompointer.entities[i]));

		optimisePath();
		switch (smoothing) {
		case SMOOTHING_CHAIKIN:
			smoothChaikin(3);
			break;
		case SMOOTHING_HERMIT:
			smoothHermit(8);
			break;
		case SMOOTHING_BEZIER:
			smoothBezier(8);
			break;
		default:
			break;
		}
	}

	private int rotation = 0;
	private int next = 0;

	private static final float SPEED = 0.28f;

	@Override
	public void update() {
		if (rotation % 14 == 0)
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Boomerang.mp3"));

		if (rotation > 400 || targets == null || targets.size() == 0) {
			kill();
			return;
		}
		rotation++;

		double targetX = next >= targets.size() ? PlayerInfo.posX - roompointer.posX : targets.get(next).x,
				targetY = next >= targets.size() ? PlayerInfo.posY - roompointer.posY : targets.get(next).y;

		if (targetX > posX + SPEED * 2) {
			posX += SPEED;
		} else if (targetX + SPEED * 2 < posX) {
			posX -= SPEED;
		}
		if (targetY > posY + SPEED * 2) {
			posY += SPEED;
		} else if (targetY + SPEED * 2 < posY) {
			posY -= SPEED;
		}

		while (targetX <= posX + SPEED * 2 && targetX + SPEED * 2 >= posX & targetY <= posY + SPEED * 2
				&& targetY + SPEED * 2 >= posY) {
			if (next >= targets.size()) {
				kill();
				return;
			}
			if (targets.get(next).content != null)
				targets.get(next).content.onhit();
			next++;
			targetX = next >= targets.size() ? PlayerInfo.posX - roompointer.posX : targets.get(next).x;
			targetY = next >= targets.size() ? PlayerInfo.posY - roompointer.posY : targets.get(next).y;
		}
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(Res_PlayerItems.boomerang2[rotation / 2 % 3],
				(int) ((super.roompointer.posX + super.posX) * 16 - 8),
				(int) ((super.roompointer.posY + super.posY) * 16 - 8), null);

		if (PlayerInfo.DEBUGMODE) {
			for (int i = 0; i <= targets.size(); i++) {
				g2d.setColor(Color.YELLOW);
				double px = i == 0 ? originalX : targets.get(i - 1).x, py = i == 0 ? originalY : targets.get(i - 1).y,
						npx = i == targets.size() ? PlayerInfo.posX - roompointer.posX : targets.get(i).x,
						npy = i == targets.size() ? PlayerInfo.posY - roompointer.posY : targets.get(i).y;
				g2d.drawLine((int) ((super.roompointer.posX + px) * 16), (int) ((super.roompointer.posY + py) * 16),
						(int) ((super.roompointer.posX + npx) * 16), (int) ((super.roompointer.posY + npy) * 16));
				if (i != targets.size()) {
					if (targets.get(i).content != null)
						g2d.setColor(Color.ORANGE);
					g2d.fillRect((int) ((super.roompointer.posX + npx) * 16 - 2),
							(int) ((super.roompointer.posY + npy) * 16 - 2), 4, 4);
					if (targets.get(i).dir != null) {
						g2d.setColor(Color.GREEN);
						MathVector lefthandle = targets.get(i).getHandleLeft(),
								righthandle = targets.get(i).getHandleRight();
						g2d.drawLine((int) ((super.roompointer.posX + npx) * 16),
								(int) ((super.roompointer.posY + npy) * 16),
								(int) ((super.roompointer.posX + righthandle.x) * 16),
								(int) ((super.roompointer.posY + righthandle.y) * 16));
						if (smoothing == SMOOTHING_BEZIER) {
							g2d.drawLine((int) ((super.roompointer.posX + npx) * 16),
									(int) ((super.roompointer.posY + npy) * 16),
									(int) ((super.roompointer.posX + lefthandle.x) * 16),
									(int) ((super.roompointer.posY + lefthandle.y) * 16));
							g2d.fillRect((int) ((super.roompointer.posX + lefthandle.x) * 16 - 2),
									(int) ((super.roompointer.posY + lefthandle.y) * 16 - 2), 4, 4);
							g2d.fillRect((int) ((super.roompointer.posX + righthandle.x) * 16 - 2),
									(int) ((super.roompointer.posY + righthandle.y) * 16 - 2), 4, 4);
						}
					}
				}
				if (next < targets.size()) {
					g2d.setColor(Color.RED);
					double nextpx = targets.get(next).x, nextpy = targets.get(next).y;
					g2d.drawRect((int) ((super.roompointer.posX + nextpx) * 16 - 3),
							(int) ((super.roompointer.posY + nextpy) * 16 - 3), 5, 5);
				}
			}
		}
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		return null;
	}

	@Override
	public void onhit() {
	}

	@Override
	public void kill() {
		super.kill();
		PlayerInfo.hasboomerang = true;
	}

	/**
	 * Sorts the targets arraylist using a montecarlo algorithm, derived from a
	 * genetic mutation algorithm.
	 */
	private void optimisePath() {
		if (targets.size() >= 2)
			for (int i = 0; i < 1000; i++) {
				optimiseStep();
			}
	}

	private void optimiseStep() {
		double previouslength = computePathLengthSquared();
		int swap1 = NumberUtil.randomINT(0, targets.size() - 1), swap2 = NumberUtil.randomINT(0, targets.size() - 1);
		if (swap1 == swap2)
			return;
		swapnumbers(swap1, swap2);
		double newlength = computePathLengthSquared();
		if (previouslength < newlength)
			swapnumbers(swap1, swap2);
	}

	private void swapnumbers(int index1, int index2) {
		BoomerangTarget temp = targets.get(index1);
		targets.set(index1, targets.get(index2));
		targets.set(index2, temp);
	}

	private double computePathLengthSquared() {
		if (targets == null || targets.size() == 0)
			return 0d;
		double toreturn = Math.pow(originalX - targets.get(0).x, 2) + Math.pow(originalY - targets.get(0).y, 2);
		for (int i = 1; i < targets.size(); i++) {
			BoomerangTarget p = targets.get(i);
			BoomerangTarget c = targets.get(i - 1);
			toreturn += Math.pow(p.x - c.x, 2) + Math.pow(p.y - c.y, 2);
		}
		return toreturn;
	}

	/** Smoothes the boomerang's path using Chaikin. */
	private void smoothChaikin(int iterations) {
		for (int iter = 0; iter < iterations; iter++) {
			ArrayList<BoomerangTarget> newTargets = new ArrayList<>(targets.size() * 2 + 2);
			for (int i = 0; i < targets.size(); i++) {
				double px1 = i == 0 ? originalX : targets.get(i - 1).x, py1 = i == 0 ? originalY : targets.get(i - 1).y,
						px2 = targets.get(i).x, py2 = targets.get(i).y;
				newTargets.add(between(px1, py1, px2, py2, 0.25));
				BoomerangTarget tar = between(px1, py1, px2, py2, 0.75);
				tar.content = targets.get(i).content;
				newTargets.add(tar);
			}
			targets = newTargets;
		}
	}

	/** Smoothes the boomerang's path using Hermit curves. */
	private void smoothHermit(int pad) {
		ArrayList<BoomerangTarget> newTargets = new ArrayList<>(targets.size() * pad + 5);
		// Computes the targets vectors to know where to throw the boomerang
		for (int i = 0; i < targets.size(); i++) {
			targets.get(i).dir = i == 0
					? new MathVector(-PlayerInfo.posX + roompointer.posX + targets.get(i).x,
							-PlayerInfo.posY + roompointer.posY + targets.get(i).y)
					: new MathVector((targets.get(i).x - targets.get(i - 1).x) * 1.5,
							(targets.get(i).y - targets.get(i - 1).y) * 1.5);

		}
		// Computes hermit curves for all mobs.
		for (int i = 0; i < targets.size(); i++) {
			double px1 = i == 0 ? originalX : targets.get(i - 1).x, py1 = i == 0 ? originalY : targets.get(i - 1).y,
					px2 = targets.get(i).x, py2 = targets.get(i).y;
			MathVector v1 = i == 0 ? new MathVector(0d, 0d) : targets.get(i - 1).dir;
			MathVector v2 = targets.get(i).dir;
			for (double j = 1 / (double) (pad); j <= 1d; j += 1 / (double) (pad))
				newTargets.add(hermitOf(px1, py1, px2, py2, v1, v2, j));
			newTargets.add(targets.get(i));
		}
		targets = newTargets;
	}

	private BoomerangTarget hermitOf(double p0x, double p0y, double p1x, double p1y, MathVector v0, MathVector v1,
			double u) {
		double f1 = 2 * Math.pow(u, 3) - 3 * Math.pow(u, 2) + 1;
		double f2 = -2 * Math.pow(u, 3) + 3 * Math.pow(u, 2);
		double f3 = Math.pow(u, 3) - 2 * Math.pow(u, 2) + u;
		double f4 = Math.pow(u, 3) - Math.pow(u, 2);
		return new BoomerangTarget(f1 * p0x + f2 * p1x + f3 * v0.x + f4 * v1.x,
				f1 * p0y + f2 * p1y + f3 * v0.y + f4 * v1.y);
	}

	/** Smoothes the boomerang's path using Bezier cubic curves */
	private void smoothBezier(int pad) {
		ArrayList<BoomerangTarget> newTargets = new ArrayList<>(targets.size() * pad + 5);
		// Computes the targets vectors to know where to throw the boomerang
		for (int i = 0; i < targets.size(); i++) {
			final double scalar = 0.5;
			targets.get(i).dir = i == 0
					? new MathVector((-PlayerInfo.posX + roompointer.posX + targets.get(i).x) * scalar,
							(-PlayerInfo.posY + roompointer.posY + targets.get(i).y) * scalar)
					: new MathVector((targets.get(i).x - targets.get(i - 1).x) * scalar,
							(targets.get(i).y - targets.get(i - 1).y) * scalar);
		}
		// Computes the bezier curve
		for (int i = 0; i < targets.size(); i++) {
			double px1 = i == 0 ? originalX : targets.get(i - 1).x, py1 = i == 0 ? originalY : targets.get(i - 1).y,
					px2 = targets.get(i).x, py2 = targets.get(i).y;
			MathVector v1 = i == 0 ? new MathVector(originalX, originalY) : targets.get(i - 1).getHandleRight();
			MathVector v2 = targets.get(i).getHandleLeft();
			for (double j = 1 / (double) (pad); j <= 1d; j += 1 / (double) (pad))
				newTargets.add(bezierOf(new MathVector(px1, py1), v1, v2, new MathVector(px2, py2), j));
			newTargets.add(targets.get(i));
		}
		targets = newTargets;
	}

	private BoomerangTarget bezierOf(MathVector p0, MathVector p1, MathVector p2, MathVector p3, double t) {
		MathVector f0 = p0.clone().mul(Math.pow(1 - t, 3));
		MathVector f1 = p1.clone().mul(Math.pow(1 - t, 2)).mul(t);
		MathVector f2 = p2.clone().mul(1 - t).mul(Math.pow(t, 2));
		MathVector f3 = p3.clone().mul(Math.pow(t, 3));
		f0.add(f1.mul(3)).add(f2.mul(3)).add(f3);
		return new BoomerangTarget(f0.x, f0.y);
	}

	/**
	 * Returns a new target between the two given points. A ratio of 0 returns the
	 * first point, 1 returns the 2nd point. Anything in between is a linear
	 * projection.
	 */
	private BoomerangTarget between(double x1, double y1, double x2, double y2, double ratio) {
		return new BoomerangTarget(x1 * (1 - ratio) + x2 * ratio, y1 * (1 - ratio) + y2 * ratio);
	}

}

class BoomerangTarget {

	/**
	 * Vector containing a direction, may be used for bezier/hermit curving
	 * calculations. This pointer may be null.
	 */
	public MathVector dir = null;
	public double x;
	public double y;
	public Entity content;

	public BoomerangTarget(Entity e) {
		this.content = e;
		x = e.posX;
		y = e.posY;
	}

	public BoomerangTarget(Entity e, MathVector dir) {
		this.content = e;
		x = e.posX;
		y = e.posY;
		this.dir = dir;
	}

	public BoomerangTarget(double x, double y) {
		this.content = null;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[Boomerang target : " + (float) x + "/" + (float) y + " containing " + content
				+ (dir == null ? "]" : " having a direction " + dir + "]");
	}

	public MathVector getHandleRight() {
		if (dir == null)
			return new MathVector(x, y);
		return new MathVector(x + dir.x, y + dir.y);
	}

	public MathVector getHandleLeft() {
		if (dir == null)
			return new MathVector(x, y);
		return new MathVector(x - dir.x, y - dir.y);
	}

}
