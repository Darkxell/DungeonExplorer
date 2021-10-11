package management.entities.monsters.darknut;

import java.awt.Color;
import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import main.DungeonExplorer;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.entities.items.BossKey;
import management.entities.monsters.pathfinding.DijkstraMap;
import management.entities.monsters.pathfinding.DijkstraNode;
import management.entities.particle.MobDeath;
import management.entities.particle.MobHit;
import management.floors.Room;
import management.player.PlayerInfo;
import res.audio.MusicHolder;
import res.audio.SoundsHolder;
import res.images.ImagesHolder;
import util.MathUtils;

public class Darknut extends Monster {

	/* package */ DijkstraMap dmap = new DijkstraMap();
	/* package */ int following = -1;

	/* package */ double circleX;
	/* package */ double circleY;

	private int index = 0;

	public Darknut(Room roompointer, double x, double y, int index) {
		this(roompointer, x, y);
		this.index = index;
	}

	public Darknut(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		circleX = x;
		circleY = y + 1;
		super.state = new Darknut_Spawn(this);
		super.entityDesign = new DarknutSpriteSheet();
		super.damage = 0.5d;
		super.hp = 100d;

		setDJgraph();

		try {
			dmap.compute(x, y, PlayerInfo.posX - roompointer.posX, PlayerInfo.posY - roompointer.posY);
		} catch (Exception e) {
			System.err.println("Couldn't compute Dijkstra map For a Darknut!");
			e.printStackTrace();
		}
		following = dmap.path.size() - 1;
	}

	@Override
	public void updateM() {
		super.state.update();
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(ImagesHolder.ENTITIES_CIRCLE, (int) (16 * (circleX + roompointer.posX)) - 16,
				(int) (16 * (circleY + roompointer.posY)) - 16, null);
		super.state.print(g2d);
		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(Color.RED);
			g2d.fillRect((int) (16 * (posX + roompointer.posX)) - 1, (int) (16 * (posY + roompointer.posY)) - 1, 2, 2);
			g2d.drawRect((int) (16 * (posX + roompointer.posX)) - 10, (int) (16 * (posY + roompointer.posY)) - 10, 21,
					21);
			// Print the Dijkstra graph
			for (int i = 0; i < dmap.nodes.size(); i++) {
				g2d.setColor(new Color(0, 255, 255, 110));
				g2d.drawString("" + dmap.nodes.get(i).index, (int) (16 * (dmap.nodes.get(i).x + roompointer.posX)) + 3,
						(int) (16 * (dmap.nodes.get(i).y + roompointer.posY)) + 12);
				for (int nei = 0; nei < dmap.nodes.get(i).neighbors.size(); nei++) {
					double length = dmap.nodes.get(i).distances.get(nei);
					int offset = dmap.nodes.get(i).index > dmap.nodes.get(i).neighbors.get(nei).index ? 1 : -1;
					if (length < 3) {
						g2d.setColor(new Color(0, 255, 255, 110));
					} else {
						g2d.setColor(new Color(0, 100, 235, 180));
						offset *= (int) (length / 1.25);
					}
					g2d.drawLine((int) (16 * (dmap.nodes.get(i).x + roompointer.posX)) + offset,
							(int) (16 * (dmap.nodes.get(i).y + roompointer.posY)) + offset,
							(int) (16 * (dmap.nodes.get(i).neighbors.get(nei).x + roompointer.posX)) + offset,
							(int) (16 * (dmap.nodes.get(i).neighbors.get(nei).y + roompointer.posY)) + offset);
				}
				if (dmap.start != null && dmap.nodes.get(i) == dmap.start)
					g2d.setColor(Color.RED);
				else if (dmap.end != null && dmap.nodes.get(i) == dmap.end)
					g2d.setColor(Color.BLUE);
				else
					g2d.setColor(Color.CYAN);
				g2d.fillRect((int) (16 * (dmap.nodes.get(i).x + roompointer.posX)) - 2,
						(int) (16 * (dmap.nodes.get(i).y + roompointer.posY)) - 2, 4, 4);
			}
			// Print the path
			if (dmap.path != null)
				for (int i = 0; i < dmap.path.size(); i++) {
					g2d.setColor(Color.BLACK);
					g2d.drawRect((int) (16 * (dmap.path.get(i).x + roompointer.posX)) - 3,
							(int) (16 * (dmap.path.get(i).y + roompointer.posY)) - 3, 6, 6);
				}
			if (dmap.path != null && following != -1) {
				g2d.setColor(Color.MAGENTA);
				g2d.drawRect((int) (16 * (dmap.path.get(following).x + roompointer.posX)) - 3,
						(int) (16 * (dmap.path.get(following).y + roompointer.posY)) - 3, 6, 6);
			}
		}
	}

	@Override
	public void onhit() {
		roompointer.addEntity(new MobHit(roompointer, posX, posY - 0.2));
		super.hp -= 1.5;
		if (super.hp <= 0) {
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Boss_Kill.mp3"));

			roompointer.addEntity(new MobDeath(roompointer, posX, posY));
			if (!PlayerInfo.playerInventory.dungeon_hasbosskey && isLastinroom())
				roompointer.addEntity(new BossKey(roompointer, posX, posY, 16));
			if (isLastinroom()) {
				DungeonExplorer.sm.setBackgroundMusic(MusicHolder.getSong("MC_DeepwoodShrine.mp3"));
				DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Element_Thune.mp3"));
			}
			kill();
		}
	}

	private int slashCooldown = 0;

	/* package */ void nextState() {
		// If the Darknut does a still attack
		double distp = MathUtils.dist2(super.posX + roompointer.posX, super.posY + roompointer.posY, PlayerInfo.posX,
				PlayerInfo.posY);
		slashCooldown--;
		if (distp < 12.5 && slashCooldown <= 0 && Math.random() > 0.4d) {
			slashCooldown = 3;
			super.state = new Darknut_slash(this);
			return;
		}
		// If the Darknut follows the next Dijkstra Node
		if (!(state instanceof Darknut_teleport))
			following -= 1;
		if (following == -1) {

			super.state = new Darknut_teleport(this);
			return;
		}
		if (dmap.nodes != null && following != -1 && following != dmap.path.size() - 1) {
			double length = dmap.path.get(following + 1).distWithNei(dmap.path.get(following));
			if (length > 3d) {
				super.state = new Darknut_charge(this);
				return;
			}
		}
		super.state = new Darknut_step(this);
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		Position[] points = new Position[9];
		double halfsize = 0.65d;
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

	/* package */ void moveto(double x, double y, double speed) {
		if (posX + 0.5 - x > 0.1)
			posX -= speed;
		if (posX + 0.5 - x < 0.1)
			posX += speed;
		if (posY + 0.5 - y > 0.1)
			posY -= speed;
		if (posY + 0.5 - y < 0.1)
			posY += speed;
	}

	private void setDJgraph() {
		int graphwidth = 6, graphheight = 5;
		for (int j = 0; j < graphheight; j++)
			for (int i = 0; i < graphwidth; i++) {
				DijkstraNode n = new DijkstraNode(3.5 + i * 2, 3.5 + j * 2);
				n.index = j * graphwidth + i;
				dmap.nodes.add(n);
			}
		for (int i = 0; i < graphwidth; i++)
			for (int j = 0; j < graphheight; j++) {
				int ij = j * graphwidth + i;
				DijkstraNode n = dmap.nodes.get(ij);
				boolean leftn = i > 0, rightn = i < graphwidth - 1, upn = j > 0, downn = j < graphheight - 1;
				if (leftn)
					n.addNeighbor(dmap.nodes.get(ij - 1), 2);
				if (rightn)
					n.addNeighbor(dmap.nodes.get(ij + 1), 2);
				if (upn)
					n.addNeighbor(dmap.nodes.get(ij - graphwidth), 2);
				if (downn)
					n.addNeighbor(dmap.nodes.get(ij + graphwidth), 2);
				if (leftn && upn)
					n.addNeighbor(dmap.nodes.get(ij - 1 - graphwidth), 2.828d);
				if (leftn && downn)
					n.addNeighbor(dmap.nodes.get(ij - 1 + graphwidth), 2.828d);
				if (rightn && upn)
					n.addNeighbor(dmap.nodes.get(ij + 1 - graphwidth), 2.828d);
				if (rightn && downn)
					n.addNeighbor(dmap.nodes.get(ij + 1 + graphwidth), 2.828d);

				if (i == 0) {
					n.addNeighbor(dmap.nodes.get(ij + graphwidth - 1), 3.9);
				} else if (i == 1) {
					n.addNeighbor(dmap.nodes.get(ij + graphwidth - 2), 3.5);
				} else if (i == graphwidth - 1) {
					n.addNeighbor(dmap.nodes.get(ij - graphwidth + 1), 3.9);
				} else if (i == graphwidth - 2) {
					n.addNeighbor(dmap.nodes.get(ij - graphwidth + 2), 3.5);
				}
			}
	}

	private static final Color BB1 = new Color(122, 59, 199);
	private static final Color BB2 = new Color(111, 51, 190);
	private static final Color BB3 = new Color(58, 13, 156);

	@Override
	public void printOnUI(Graphics2D g2d) {
		int px = 20, py = 140 - (10 * index);
		g2d.drawImage(ImagesHolder.ENTITIES_BOSSBAR, px, py, null);
		g2d.setColor(BB1);
		g2d.fillRect(px + 13, py + 5, (int) super.hp, 3);
		g2d.setColor(BB2);
		g2d.fillRect(px + 13, py + 6, (int) super.hp + 1, 1);
		g2d.setColor(BB3);
		g2d.fillRect(px + 13, py + 7, (int) super.hp + 2, 1);
	}

	/**
	 * predicate that returns true if this darknut is the last/only one in the room.
	 */
	private boolean isLastinroom() {
		for (int i = 0; i < roompointer.entities.length; i++)
			if (roompointer.entities[i] instanceof Darknut)
				return false;
		return true;
	}

}
