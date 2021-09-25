package management.entities.monsters.pathfinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import util.MathUtils;

public class DijkstraMap {

	/** Immutable map for the dijkstra graph calculation. */
	public ArrayList<DijkstraNode> nodes = new ArrayList<>(16);

	public DijkstraNode start;
	public DijkstraNode end;

	public HashMap<DijkstraNode, Double> distances = new HashMap<>();
	public boolean[] closed = new boolean[] {};
	public DijkstraNode[] previous = new DijkstraNode[] {};

	private static final boolean DEBUGDIJKSTRA = true;

	/**
	 * The shortest path, from end to start. This object is null if nothing has been
	 * found.
	 */
	public ArrayList<DijkstraNode> path;

	public void compute(double startX, double startY, double endX, double endY) {
		if (DEBUGDIJKSTRA)
			System.out.println(" > Computing Dijkstra graph :\n" + Arrays.toString(nodes.toArray()));
		// Reset the clear
		closed = new boolean[nodes.size()];
		previous = new DijkstraNode[nodes.size()];
		start = closestN(startX, startY);
		end = closestN(endX, endY);
		distances.clear();
		path = null;
		// Innit
		for (int i = 0; i < nodes.size(); i++)
			distances.put(nodes.get(i), Double.MAX_VALUE);
		distances.put(start, 0d);
		// Loop on all nodes
		while (!allclosed()) {
			if (DEBUGDIJKSTRA)
				System.out.println("+--------------------------------------+");
			DijkstraNode s1 = distanceMinUnckecked();
			if (DEBUGDIJKSTRA) {
				System.out.println(Arrays.toString(closed));
				System.out.println("Computing node : " + s1);
				System.out.println(mapToString(distances));
			}
			closed[s1.index] = true;
			for (DijkstraNode s2 : s1.neighbors)
				updateDistances(s1, s2);
		}
		// After computing all nodes, get the shortest path between Start and End
		path = new ArrayList<>(20);
		DijkstraNode curr = end;
		while (curr != start) {
			path.add(curr);
			curr = previous[curr.index];
		}
		path.add(curr);
		if (DEBUGDIJKSTRA)
			System.out.println("Dijkstra shortest path found : " + Arrays.toString(path.toArray()));
	}

	/** Returns the closest node to a x,z position in the room. */
	private DijkstraNode closestN(double x, double y) {
		double distmax = Double.MAX_VALUE;
		DijkstraNode toreturn = null;
		for (int i = 0; i < nodes.size(); i++)
			if (MathUtils.dist2(x, y, nodes.get(i).x, nodes.get(i).y) < distmax) {
				distmax = MathUtils.dist2(x, y, nodes.get(i).x, nodes.get(i).y);
				toreturn = nodes.get(i);
			}
		return toreturn;
	}

	/** Returns the closest unchecked node in the distance map. */
	private DijkstraNode distanceMinUnckecked() {
		double min = Double.MAX_VALUE;
		DijkstraNode toreturn = null;
		int nodeamount = 0;
		for (int i = 0; i < nodes.size(); i++)
			if (!closed[i] && distances.get(nodes.get(i)) < min) {
				min = distances.get(nodes.get(i));
				toreturn = nodes.get(i);
				nodeamount++;
			}
		if (nodeamount == 0 && DEBUGDIJKSTRA)
			System.err.println("No node available for dijkstra distance check...!");
		return toreturn;
	}

	private void updateDistances(DijkstraNode s1, DijkstraNode s2) {
		if (DEBUGDIJKSTRA)
			System.out.print("Updating the distance of " + s2 + " from " + s1);
		if (distances.get(s2) > distances.get(s1) + s1.distWithNei(s2)) {
			distances.put(s2, distances.get(s1) + s1.distWithNei(s2));
			previous[s2.index] = s1;
			if (DEBUGDIJKSTRA)
				System.out.println(" (set to +" + distances.get(s1) + "+" + s1.distWithNei(s2) + "="
						+ (distances.get(s1) + s1.distWithNei(s2)) + ")");
		} else {
			if (DEBUGDIJKSTRA)
				System.out.println(" (not updated)");
		}
	}

	/** Predicate that returns true if the closed array only contain TRUE values. */
	private boolean allclosed() {
		for (int i = 0; i < closed.length; i++)
			if (!closed[i])
				return false;
		return true;
	}

	private static String mapToString(HashMap<DijkstraNode, Double> map) {
		StringBuilder mapAsString = new StringBuilder("[");
		for (Object key : map.keySet())
			mapAsString.append(key + "=" + map.get(key) + ", ");
		mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("]");
		return mapAsString.toString();
	}

}
