/**
 * Title: hw1_3.java
 * Abstract: This program reads directed graph data from a user and converts
 *           it to adjacency list format.
 * Author: Raymond Shum
 * ID: 9030
 * Date: 11/2/2021
 */

import java.lang.reflect.Array;
import java.util.*;

public class hw1_3 {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int numVertex = input.nextInt();
        int numEdges = input.nextInt(); 

        // Adjacency list is an ArrayList of ArrayLists
        ArrayList<Integer>[] adjList = new ArrayList[numVertex];

        // Index represents label of edge
        for(int i = 0; i < numVertex; i++){
            adjList[i] = new ArrayList<>();
        }

        // Add edges to each vertex as encountered
        for(int i = 0; i < numEdges; i++) {
            int index = input.nextInt();
            int edge = input.nextInt();

            adjList[index].add(edge);
        }
        
        // Sort ArrayList at each index in asc order before printing
        for(int i = 0; i < numVertex; i++){
            System.out.printf("%d", i);
            Collections.sort(adjList[i]);

            for(Integer edge : adjList[i]){
                System.out.printf("->%d", edge);
            }
            System.out.printf("\n");
        }

        input.close();
    }
}
