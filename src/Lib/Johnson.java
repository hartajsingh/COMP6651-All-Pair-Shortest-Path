package Lib;

public class Johnson implements AllPairShortestPath {
	static final int INFINITY = Integer.MAX_VALUE;

	@Override
	public int[][] getShortestDistAdjMatix(int[][] distAdjMatrix) {
		int vertices = distAdjMatrix.length;
		int source;
		int[] distance = new int[vertices + 1];
		int[][] dist = new int[vertices + 1][vertices + 1];
		// Marking edges with distance 0 as infinity except if it is the distance to itself
		for (int i = 0; i < vertices; i++)
			for (int j = 0; j < vertices; j++) {
				if (i != j && distAdjMatrix[i][j] == 0)
					dist[i][j] = INFINITY;
				else
					dist[i][j] = distAdjMatrix[i][j];
			}
		//Adding an extra vertex with an edge to all the other vertices with cost 0.
		for (int k = 0; k <= vertices; k++) {
			dist[vertices][k] = 0;
			dist[vertices][k] = INFINITY;
		}
		source = vertices;//making the newly added vertex as source
		for (int i = 0; i <= source; i++)
			distance[i] = 0;
		boolean b = BellmanFord(dist, vertices, distance); //checking for negative edge cycle
		if (b) {
			for (int m = 0; m < vertices; m++)
				for (int n = 0; n < vertices; n++) {
					if (m != n && dist[m][n] != INFINITY)
						dist[m][n] = dist[m][n] + distance[m] - distance[n];    //making all the costs >=0 so that Lib.Dijkstra can be executed on it.
				}
			int[][] SDAM = new int[vertices][vertices];
			for (int i = 0; i < vertices; i++) {
				SDAM[i] = Dijkstra.getShortestDistArray(dist, i, 1);
				for (int j = 0; j < vertices; j++) {
					if (distAdjMatrix[i][j] != 0) {
						if (SDAM[i][j] != INFINITY) {
							int n = SDAM[i][j] - distance[i] + distance[j]; // rolling back to the original costs.
							SDAM[i][j] = n;
						}
					}
				}
			}
			return SDAM;
		} else
			System.out.println("Lib.Johnson's cannot handle negative weight cycle.\n");
		return null;
	}

	private boolean BellmanFord(int[][] dist, int source, int[] distance) {

		for (int i = 0; i <= source; i++)
			for (int m = 0; m <= source; m++)
				for (int n = 0; n <= source; n++) {
					if (m != n && dist[m][n] != INFINITY)
						if ((distance[m] + dist[m][n]) < distance[n])
							distance[n] = distance[m] + dist[m][n];
				}
		for (int m = 0; m < source; m++)
			for (int n = 0; n < source; n++) {
				if (m != n && dist[m][n] != INFINITY) {
					if (distance[n] > (distance[m] + dist[m][n]))// check for negative edge cycle.
						return false;
				}
			}
		return true;
	}
}
