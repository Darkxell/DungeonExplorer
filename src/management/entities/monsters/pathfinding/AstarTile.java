package management.entities.monsters.pathfinding;

public class AstarTile {

	public final int x, y;

	/** Cost from starting node */
	public int costG = -1;
	/** Heuristic cost from end node */
	public int costH = -1;
	/** Node this exploration came from in the astar algorithm, in the astar map. */
	public int from = -1;

	public boolean walkable;
	public boolean closed;

	public AstarTile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Returns G + H */
	public int costF() {
		return costG + costH;
	}

	@Override
	public String toString() {
		return "[AstarTile@" + x + "/" + y + " costing:" + costF() + "(G:" + costG + "/H:" + costH + ") "
				+ (walkable ? "floor" : "wall") + " and " + (closed ? "closed" : "open") + "]";
	}

}
