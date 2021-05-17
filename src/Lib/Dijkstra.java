package Lib;

public class Dijkstra {
    static final int INFINITY = Integer.MAX_VALUE;

    static int[] getShortestDistArray(int[][] distAdjMatrix, int sourceVertex, int flag) {
        int vertices;
        if (flag == 1)
            //initializing all distances to infinity
            vertices = distAdjMatrix.length - 1;
        else
            vertices = distAdjMatrix.length;
        int[] dist = new int[vertices];
        boolean[] spt = new boolean[vertices];
        for (int i = 0; i < vertices; i++)
            dist[i] = INFINITY;

        //starting from source vertex
        dist[sourceVertex] = 0;
        //for each vertex
        for (int i = 0; i < vertices; i++) {
            //get minimum distance vertex
            int minVertex = getMinVertex(spt, dist);
            if (minVertex == -1)
                break;
            spt[minVertex] = true;
            //update its neighbours' distance
            for (int j = 0; j < vertices; j++) {
                if (!(spt[j] || distAdjMatrix[minVertex][j] == INFINITY)) {
                    int newDistFromSource = distAdjMatrix[minVertex][j] + dist[minVertex];
                    if (newDistFromSource < dist[j]) {
                        dist[j] = newDistFromSource;
                    }
                }
            }
        }
        return dist;
    }

    //returns minimum distance vertex which is not traversed already
    private static int getMinVertex(boolean[] spt, int[] dist) {
        int minDist = INFINITY;
        int minV = -1;

        for (int i = 0; i < spt.length; i++) {
            if (!spt[i] && dist[i] < minDist) {
                minDist = dist[i];
                minV = i;
            }
        }
        return minV;
    }
}
