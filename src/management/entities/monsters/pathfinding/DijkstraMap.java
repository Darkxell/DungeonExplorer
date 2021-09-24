package management.entities.monsters.pathfinding;

import java.util.ArrayList;
import java.util.HashMap;

import util.MathUtils;

public class DijkstraMap {

	/**Immutable map for the dijkstra graph calculation.*/
	public ArrayList<DijkstraNode> nodes = new ArrayList<>(16);

	public DijkstraNode start;
	public DijkstraNode end;
	
	public HashMap<DijkstraNode, Double> distances = new HashMap<>();
	public boolean[] closed = new boolean[] {};
	
	public void compute(double startX, double startY,double endX, double endY) {
		distances.clear();
		closed = new boolean[nodes.size()];
		start = closestN(startX, startY);
		end = closestN(endX, endY);
		distances.put(start, 0d);
		closed[start.index] = true;
		
		
		//TODO: dijkstra here!
		
		
		
		
		
	}
	
	private DijkstraNode closestN(double x, double y) {
		double distmax = Double.MAX_VALUE;
		DijkstraNode toreturn = null;
		for (int i = 0; i < nodes.size(); i++)
			if(MathUtils.dist2(x, y, nodes.get(i).x, nodes.get(i).y) < distmax) {
				distmax = MathUtils.dist2(x, y, nodes.get(i).x, nodes.get(i).y);
				toreturn = nodes.get(i);
			}
		return toreturn;
	}
	
}
