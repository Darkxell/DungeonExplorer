package management.entities.monsters.pathfinding;

import java.util.ArrayList;

public class DijkstraNode {

	public ArrayList<DijkstraNode> neighbors = new ArrayList<>(12);
	public ArrayList<Double> distances = new ArrayList<>(12);

	public double x;
	public double y;

	public int index;
	
	public DijkstraNode(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[DijkstraNode@" + x + "/" + y + " with " + neighbors.size() + " neighbors.";
	}
	
	public void addNeighbor(DijkstraNode n, double dist) {
		this.neighbors.add(n);
		this.distances.add(dist);
	}
}
