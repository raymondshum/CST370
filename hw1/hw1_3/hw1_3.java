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
        int numEdges = input.nextInt();
        int numVertex = input.nextInt();

        ArrayList<Integer>[] adjList = new ArrayList[numEdges];

        for(int i = 0; i < numEdges; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < numVertex; i++) {
            int index = input.nextInt();
            int edge = input.nextInt();

            adjList[index].add(edge);
        }
        
        for(int i = 0; i < numEdges; i++){
            System.out.printf("%d", i);
            Collections.sort(adjList[i]);

            for(Integer number : adjList[i]){
                System.out.printf("->%d", number);
            }
            System.out.printf("\n");
        }

        input.close();
    }
}
