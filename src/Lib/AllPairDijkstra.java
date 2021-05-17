package Lib;

public class AllPairDijkstra implements AllPairShortestPath {
    @Override
    public int[][] getShortestDistAdjMatix(int[][] distAdjMatrix) {
        int vertices = distAdjMatrix.length;
        int[][] SDAM = new int[vertices][vertices];

        //for all vetrices
        for (int i = 0; i < vertices; i++) {
            //calling dijkstra
            SDAM[i] = Dijkstra.getShortestDistArray(distAdjMatrix, i, 0);
        }
        return SDAM;
    }
}
