package Lib;

public class FloydWarshall implements AllPairShortestPath {
    //static final int INFINITY = Integer.MAX_VALUE;
    @Override
    public int[][] getShortestDistAdjMatix(int[][] distAdjMatrix) {
        int vertices = distAdjMatrix.length;
        int[][] dist = new int[vertices][vertices];

        //Copying distAdjMatrix into dist
        for (int i = 0; i < vertices; i++)
            System.arraycopy(distAdjMatrix[i], 0, dist[i], 0, vertices);

        for (int k = 0; k < vertices; k++)
            for (int i = 0; i < vertices; i++)
                for (int j = 0; j < vertices; j++)
                    if (dist[i][k] != INFINITY && dist[k][j] != INFINITY)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        return dist;
    }
}
