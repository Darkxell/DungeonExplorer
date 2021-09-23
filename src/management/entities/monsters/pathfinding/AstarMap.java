package management.entities.monsters.pathfinding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import management.floors.Room;
import management.floors.Tile;

public class AstarMap {

	public final int width;
	public final int height;
	public final int x;
	public final int y;
	public final int startX;
	public final int startY;
	public final int endX;
	public final int endY;

	public final AstarTile[] nodes;

	public AstarMap(Room r, int startX, int startY, int endX, int endY) {
		this.width = r.width;
		this.height = r.height;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.x = r.posX * 16;
		this.y = r.posY * 16;
		this.nodes = new AstarTile[width * height];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new AstarTile(i % width, i / width);
			nodes[i].walkable = r.getTile(i % width, i / width).type == Tile.TYPE_NORMAL;
		}
		nodes[d1(4, 4)].costG = 10;
		try {
			compute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/** Return the array1d point in a 2d map. */
	public int d1(int x, int y) {
		return x + this.width * y;
	}

	public void print(Graphics2D g2d) {
		for (int i = 0; i < this.width; i++)
			for (int j = 0; j < this.height; j++)
				try {
					if (i == startX && j == startY)
						g2d.setColor(new Color(0, 0, 255, 100));
					else if (i == endX && j == endY)
						g2d.setColor(new Color(255, 0, 0, 100));
					else 
						g2d.setColor(new Color(140, 140, 140, 100));
					if (nodes[d1(i, j)].walkable) {
						g2d.fillRect(16 * i + x + 2, 16 * j + y + 2, 12, 12);
						g2d.setColor(Color.GRAY);
						g2d.drawString("" + nodes[d1(i, j)].costF(), 16 * i + x + 3, 16 * j + y + 12);	
					}
				} catch (Exception e) {
					System.err.println("Astar not printed in the current \"room\" at coordinates : " + i + " / " + j);
				}
	}

	private void compute() {
		System.out.println(" > Starting the astar compute!");
		ArrayList<AstarTile> open = new ArrayList<>(20);
		open.add(nodes[d1(startX, startY)]);
		nodes[d1(startX, startY)].costG = 0;
		nodes[d1(startX, startY)].costH = computeHcost(startX, startY, endX, endY);
		
		for (int maxiter = 0; maxiter < 200; maxiter++) {
			AstarTile current = getCurrent(open);
			open.remove(current);
			current.closed = true;

			if (current == nodes[d1(endX, endY)]) {
				System.out.println(" > a* Path found!");
				return;
			}

			populateNeighbors(current);
			for (int i = 0; i < neighbors.length; i++) {
				
				
				
				
				
			}
			
		}
	}

	private AstarTile getCurrent(ArrayList<AstarTile> open) {
		AstarTile toreturn = null;
		for (int i = 0; i < open.size(); i++)
			if (toreturn == null || (toreturn.costF() >= 0 && toreturn.costF() > open.get(i).costF())) {
				toreturn = open.get(i);
			}
		return toreturn;
	}

	private int computeHcost(int x, int y, int destX, int destY) {
		return 0;
	}

	private AstarTile[] neighbors = new AstarTile[9];

	/** Populates the neighbours aray with neighbours to a tile. Contents may be null if there's nothing there (treat as impassible terain).*/
	private void populateNeighbors(AstarTile source) {
		neighbors[0] = source.x<=0?null:(source.y<=0?null:nodes[d1(source.x-1,source.y-1)]);
		neighbors[1] = source.y<=0?null:nodes[d1(source.x,source.y-1)];
		neighbors[2] = source.x>=width-1?null:(source.y<=0?null:nodes[d1(source.x+1,source.y-1)]);
		
		neighbors[3] = source.x<=0?null:nodes[d1(source.x-1,source.y)];
		neighbors[4] = source.x>=width-1?null:nodes[d1(source.x+1,source.y)];
		
		neighbors[5] = source.x<=0?null:(source.y>=height-1?null:nodes[d1(source.x-1,source.y+1)]);
		neighbors[6] = source.y>=height-1?null:nodes[d1(source.x,source.y+1)];
		neighbors[7] = source.x>=width-1?null:(source.y>=height-1?null:nodes[d1(source.x+1,source.y+1)]);
	}

}
