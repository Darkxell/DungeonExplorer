package management.entities.monsters.darknut;

import java.awt.Color;
import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.entities.monsters.pathfinding.DijkstraMap;
import management.entities.monsters.pathfinding.DijkstraNode;
import management.floors.Room;
import management.player.PlayerInfo;

public class Darknut extends Monster {

	private DijkstraMap dmap = new DijkstraMap();
	private int following = -1;

	public Darknut(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		super.state = new Darknut_Spawn(this);
		super.entityDesign = new DarknutSpriteSheet();
		super.damage = 0.5d;

		setDJgraph();

		try {
			dmap.compute(x, y, PlayerInfo.posX - roompointer.posX, PlayerInfo.posY - roompointer.posY);
		} catch (Exception e) {
			System.err.println("Couldn't compute Dijkstra map For a Darknut!");
			e.printStackTrace();
		}
	}

	@Override
	public void updateM() {
		super.state.update();
	}

	@Override
	public void print(Graphics2D g2d) {
		super.state.print(g2d);
		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(Color.RED);
			g2d.fillRect((int) (16 * (posX + roompointer.posX)) - 1, (int) (16 * (posY + roompointer.posY)) - 1, 2, 2);
			g2d.drawRect((int) (16 * (posX + roompointer.posX)) - 10, (int) (16 * (posY + roompointer.posY)) - 10, 21,
					21);
			// Print the Dijkstra graph
			for (int i = 0; i < dmap.nodes.size(); i++) {
				g2d.setColor(new Color(0, 255, 255, 150));
				g2d.drawString("" + dmap.nodes.get(i).index, (int) (16 * (dmap.nodes.get(i).x + roompointer.posX)) + 3,
						(int) (16 * (dmap.nodes.get(i).y + roompointer.posY)) + 12);
				for (int nei = 0; nei < dmap.nodes.get(i).neighbors.size(); nei++) {
					int offset = dmap.nodes.get(i).index > dmap.nodes.get(i).neighbors.get(nei).index ? 1 : -1;
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
					g2d.drawRect((int) (16 * (dmap.path.get(i).x + roompointer.posX)) - 3, (int) (16 * (dmap.path.get(i).y + roompointer.posY)) - 3, 6, 6);
				}
			if (dmap.path != null && following != -1) {
				g2d.setColor(Color.MAGENTA);
				g2d.fillRect((int) (16 * dmap.path.get(following).x + 6), (int) (16 * dmap.path.get(following).y + 6),
						4, 4);
			}
		}
	}

	@Override
	public void onhit() {
		System.out.println("Haha, you hit a darknut. Fool.");
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
				if (i > 0)
					n.addNeighbor(dmap.nodes.get(ij - 1), 2);
				if (i < graphwidth - 1)
					n.addNeighbor(dmap.nodes.get(ij + 1), 2);
				if (j > 0)
					n.addNeighbor(dmap.nodes.get(ij - graphwidth), 2);
				if (j < graphheight - 1)
					n.addNeighbor(dmap.nodes.get(ij + graphwidth), 2);
			}
	}

}
