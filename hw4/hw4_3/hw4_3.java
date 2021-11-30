/**
 * Title: hw4_3.java 
 * Abstract: This program conducts topological sorting based on
 * Kahn's algorithm (covered in the lecture).
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 11/30/2021
 */

import java.util.*;

public class hw4_3 {
    public static void main(String args[]) {
        Graph myGraph = new Graph();
        Kahn myTopo = new Kahn(myGraph);
        myTopo.calcInDegree();
        myTopo.printInDegree();
        myTopo.calcOrder();
    }
}

/**
 * This class takes user input and arranges it into
 * an adjacency list.
 */
class Graph {
    int numEdge;
    int numVert;
    ArrayList<ArrayList<Integer>> adjList;
    /**
     * Default constructor. Takes preformatted user input.
     */
    Graph() {
        Scanner myScanner = new Scanner(System.in);
        numVert = myScanner.nextInt();
        numEdge = myScanner.nextInt();
        adjList = new ArrayList<>(numVert);

        // Initialize adjacency list
        for (int i = 0; i < numVert; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        // Populate adjacency list with user input
        for (int i = 0; i < numEdge; i++) {
            int thisVert = myScanner.nextInt();
            int edgeTo = myScanner.nextInt();
            adjList.get(thisVert).add(edgeTo);
        }

        // Sort edges in ascending order
        for (int i = 0; i < numVert; i++) {
            Collections.sort(adjList.get(i));
        }
        myScanner.close();
    }

    /**
     * Getter. Number of edges in graph.
     */
    public int getNumEdge() {
        return numEdge;
    }

    /**
     * Getter. Number of vertices in graph.
     */
    public int getNumVert() {
        return numVert;
    }

    /**
     * Getter. Returns shallow copy of adjacency list.
     */
    public ArrayList<ArrayList<Integer>> getAdjList() {
        return adjList;
    }

    /**
     * Displays adjacency list for debug purposes.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (ArrayList<Integer> list : adjList) {
            result.append(String.format("%d", count));
            for (int edge : list) {
                result.append(String.format("->%d", edge));
            }
            count++;
            result.append(String.format("\n"));
        }
        return result.toString();
    }
}

/**
 * Holds methods for topological sorting based on Kahn's algorithm.
 */
class Kahn {
    int[] inDegree;
    Graph myGraph;

    /**
     * Default constructor.
     * @param myGraph Graph of user input
     */
    Kahn(Graph myGraph) {
        this.myGraph = myGraph;
        int numVert = myGraph.getNumVert();

        inDegree = new int[numVert];
        Arrays.fill(inDegree, 0);
    }

    /**
     * Updates initialized inDegree[] to represent Step 1 values.
     */
    public void calcInDegree() {
        ArrayList<ArrayList<Integer>> adjList = myGraph.getAdjList();

        // inDegree[vertex] starts at 0 for all values. Adds 1 to each vertex
        // (inDegree[vertex]) for each incoming edge.
        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {
                inDegree[adjList.get(i).get(j)] += 1;
            }
        }
    }

    /**
     * Prints formatted values of inDegree[] to console.
     */
    public void printInDegree() {
        for (int i = 0; i < inDegree.length; i++) {
            System.out.printf("In-degree[%d]:%d\n", i, inDegree[i]);
        }
    }

    /**
     * Calculates and prints order of visited nodes when performing
     * topological sorting using Kahn's algorithm.
     */
    public void calcOrder() {
        ArrayList<ArrayList<Integer>> adjList = myGraph.getAdjList();
        LinkedList<Integer> queue = new LinkedList<>(); 
        LinkedList<String> result = new LinkedList<>(); // Holds sorted order.

        // Add initial source nodes (vertices) to the queue.
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // This will traverse all vertices that are not part of a cycle. Once the queue is empty,
        // there are no longer any source nodes found in the graph.
        while (queue.size() > 0) {
            int vertex = queue.poll();

            // Add dequeued node to result, formatting is different if it is the first node.
            result.add(result.size() == 0 ? String.format("Order:%d", vertex) : String.format("->%d", vertex));

            // After dequeueing node, decrement inDegree values for all vertices with incoming edges
            // from that node.
            for (int edge : adjList.get(vertex)) {
                inDegree[edge] -= 1;

                // If inDegree becomes 0, add the vertex to the queue as a new source node
                if (inDegree[edge] == 0) {
                    queue.offer(edge);
                }
            }
        }

        // Nodes in result & numVert will differ if there is a cycle in the graph 
        // (no new source nodes)
        if (result.size() != myGraph.getNumVert()) {
            System.out.println("No Order:");
        } else {
            for (String str : result) {
                System.out.printf(str);
            }
            System.out.println();
        }
    }

}