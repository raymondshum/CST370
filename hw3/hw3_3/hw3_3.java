
/**
 * TITLE: hw3_3.java 
 * ABSTRACT: This program accepts user input and maps it to a graph, represented
 * as an adjacency list. It implements and performs DFS on the graph, starting at
 * vertex 0 and displays the results of the path walked as Mark[vertex]:order.
 * AUTHOR: Raymond Shum 
 * ID: 9030 
 * DATE: 11/16/2021
 */

import java.util.*;

public class hw3_3 {
    static private int count = 0; // order of nodes visited

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int numVert = input.nextInt();
        int numEdge = input.nextInt();

        // initialize mark array (holds vertices and order visited)
        int[] mark = new int[numVert];
        for (int i = 0; i < numVert; i++) {
            mark[i] = 0;
        }

        // initialize adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(numVert);
        for (int i = 0; i < numVert; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        // populate adjacency list with user input
        for (int i = 0; i < numEdge; i++) {
            int vertex = input.nextInt();
            int connectedVertex = input.nextInt();
            adjList.get(vertex).add(connectedVertex);
        }

        // sort each list in ascending order
        // represents the class' convention of alphabetical order
        for (ArrayList<Integer> list : adjList) {
            Collections.sort(list);
        }

        // use adjacency list to traverse each node in DFS order, updating mark[]
        dfs(adjList, mark);

        // print order of nodes visited
        for (int i = 0; i < mark.length; i++) {
            System.out.printf("Mark[%d]:%d\n", i, mark[i]);
        }

        input.close();
    }

    /**
     * "Main" dfs function.
     * @param adjList ArrayList<ArrayList<Integer>> Holds graph information
     * @param mark int[] Holds nodes visited and order
     */
    static void dfs(ArrayList<ArrayList<Integer>> adjList, int[] mark) {
        // Visits each vertex in the graph in ascending order
        for (int i = 0; i < adjList.size(); i++) {
            // Visit vertex if it hasn't been visited yet
            if (mark[i] == 0) {
                dfsHelper(adjList, adjList.get(i), mark, i);
            }
        }

    }

    /**
     * "Helper" dfs function. Is called recursively to explore unvisited connected nodes of
     * current vertex.
     * @param adjList ArrayList<ArrayList<Integer>> Holds graph information
     * @param list ArrayList<Integer> list of nodes connected to current vertex
     * @param mark int[] Holds nodes visited and order
     * @param vertex Vertex to be visited next
     */
    static void dfsHelper(ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> list, int[] mark, int vertex) {
        count += 1;
        mark[vertex] = count;

        // calls dfsHelper recursively on each node connected to vertex
        for (int vert : list) {
            // if it hasn't been visited, visit that node and then explore connected nodes
            if (mark[vert] == 0) {
                dfsHelper(adjList, adjList.get(vert), mark, vert);
            }
        }
    }
}
