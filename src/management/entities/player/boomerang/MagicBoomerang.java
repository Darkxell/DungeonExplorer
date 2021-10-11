package management.entities.player.boomerang;

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

public class MagicBoomerang extends Entity {

	private ArrayList<Entity> targets;

	private final double originalX;
	private final double originalY;

	public MagicBoomerang(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		this.originalX = x;
		this.originalY = y;
		targets = new ArrayList<>(roompointer.entities.length);
		for (int i = 0; i < roompointer.entities.length; i++)
			if (roompointer.entities[i] instanceof Monster)
				targets.add(roompointer.entities[i]);
		optimisePath();
	}

	private int rotation = 0;
	private int next = 0;

	private static final float SPEED = 0.2f;

	@Override
	public void update() {
		if(rotation % 14 == 0)
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Boomerang.mp3"));
		
		if (rotation > 400 || targets == null || targets.size() == 0) {
			kill();
			return;
		}
		rotation++;
		
		double targetX = next >= targets.size() ? PlayerInfo.posX - roompointer.posX : targets.get(next).posX,
				targetY = next >= targets.size() ? PlayerInfo.posY - roompointer.posY : targets.get(next).posY;
		boolean moved = false;
		if (targetX > posX + SPEED * 2) {
			posX += SPEED;
			moved = true;
		} else if (targetX + SPEED * 2 < posX) {
			posX -= SPEED;
			moved = true;
		}

		if (targetY > posY + SPEED * 2) {
			posY += SPEED;
			moved = true;
		} else if (targetY + SPEED * 2 < posY) {
			posY -= SPEED;
			moved = true;
		}
		if (!moved) {
			if (next >= targets.size()) {
				kill();
				return;
			}
			targets.get(next).onhit();
			next++;
		}
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(Res_PlayerItems.boomerang2[rotation / 2 % 3],
				(int) ((super.roompointer.posX + super.posX) * 16 - 8),
				(int) ((super.roompointer.posY + super.posY) * 16 - 8), null);
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

	private void optimisePath() {

	}

	private double computePathLengthSquared() {
		if (targets == null || targets.size() == 0)
			return 0f;
		double toreturn = Math.pow(originalX - targets.get(0).posX, 2) + Math.pow(originalY - targets.get(0).posY, 2);
		for (int i = 1; i < targets.size(); i++) {
			Entity p = targets.get(i - 1);
			Entity c = targets.get(i - 1);
			toreturn += Math.pow(p.posX - c.posX, 2) + Math.pow(p.posY - c.posY, 2);
		}
		return toreturn;
	}

}
