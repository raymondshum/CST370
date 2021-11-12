
/**
 * TITLE: hw3_2.java 
 * ABSTRACT: 
 * AUTHOR: Raymond Shum 
 * ID: 9030 
 * DATE: 11/16/2021
 */

import java.lang.reflect.Array;
import java.util.*;

public class hw3_2 {
    public static void main(String args[]) {

        // Initialize variables
        Scanner input = new Scanner(System.in);
        int numVert = input.nextInt();
        int numEdge = input.nextInt();
        int graph[][] = new int[numVert][numVert];
        ArrayList<int[]> permutations = new ArrayList<int[]>();

        // Initialize adjacency matrix
        for (int i = 0; i < numVert; i++) {
            for (int j = 0; j < numVert; j++) {
                graph[i][j] = 0;
            }
        }

        // Load user input into adjacency matrix
        for (int i = 0; i < numEdge; i++) {
            int vertexA = input.nextInt();
            int vertexB = input.nextInt();
            int cost = input.nextInt();
            graph[vertexA][vertexB] = cost;
        }

        int startingVertex = input.nextInt();

        // generate all possible permutations
        findPerm(numVert, startingVertex, permutations);

        // calculate costs for all permutations
        int lowestCostIndex[] = calculateCosts(graph, permutations, startingVertex);

        // display special case if no paths found
        if (lowestCostIndex[0] == -1) {
            System.out.println("Path:");
        } else { // display lowest cost path
            int[] temp = permutations.get(lowestCostIndex[0]);
            String pathString = "Path:" + startingVertex + "->";
            for (int i = 0; i < temp.length; i++) {
                pathString += temp[i] + "->";
            }
            pathString += startingVertex;
            System.out.println(pathString);
        }
        System.out.println("Cost:" + lowestCostIndex[1]);

        input.close();
    }

    static void printMatrix(int[][] matrix) {
        String test = "";
        for (int[] array : matrix) {
            for (int element : array) {
                test += element + " ";
            }
            System.out.println(test);
            test = "";
        }
    }

    static void findPerm(int numVert, int startingVertex, ArrayList<int[]> permutations) {
        ArrayList<Integer> vertList = new ArrayList<Integer>(numVert - 1);

        // initialize array of possible vertices
        for (int i = 0; i < numVert; i++) {
            if (i != startingVertex) {
                vertList.add(i);
            }
        }

        permute(vertList.stream().mapToInt(i -> i).toArray(), 0, permutations, startingVertex);
    }

    static void permute(int[] input, int startindex, ArrayList<int[]> permutations, int startingVertex) {
        int size = input.length;

        if (size == startindex + 1) {
            int[] temp = new int[size];
            for (int i = 0; i < size; i++) {
                temp[i] = input[i];
            }
            permutations.add(temp);
        } else {
            for (int i = startindex; i < size; i++) {
                int temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;

                permute(input, startindex + 1, permutations, startingVertex);

                temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;
            }
        }
    }

    static int[] calculateCosts(int[][] graph, ArrayList<int[]> permutations, int startingVertex) {

        int index = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < permutations.size(); i++) {

            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(0, startingVertex);
            for (int element : permutations.get(i)) {
                temp.add(element);
            }
            temp.add(startingVertex);

            boolean noPath = false;
            int cost = 0;

            for (int j = 0; j < temp.size() - 1; j++) {
                noPath = false;
                int nextVert = j + 1;

                if (graph[temp.get(j)][temp.get(nextVert)] == 0) {
                    noPath = true;
                    break;
                } else {
                    cost += graph[temp.get(j)][temp.get(nextVert)];
                }
            }

            if (!noPath) {
                index = Math.min(min, cost) == cost ? i : index;
                min = Math.min(min, cost);
            }
        }
        return new int[] { index, index == -1 ? -1 : min };
    }

    static int factorial(int num) {
        if (num == 1) {
            return num;
        }
        return num * factorial(num - 1);
    }
}
