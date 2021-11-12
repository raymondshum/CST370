
/**
 * TITLE: hw3_2.java 
 * ABSTRACT: This program reads input graph data from a user. Then it
 * presents a solution to the travelling salesman problem (TSP) in the
 * form of a path and cost.
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

    /**
     * Prints 2D matrix (for debug purposes)
     * @param matrix int[][] to be printed
     */
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

    /**
     * Find possible permutations of paths (except for starting Vertex). Directly modifies the
     * permutations ArrayList. 
     * @param numVert int number of vertices in the graph
     * @param startingVertex int vertex to be excluded from permutations
     * @param permutations ArrayList<int[]> that will hold all possible permutations
     */
    static void findPerm(int numVert, int startingVertex, ArrayList<int[]> permutations) {
        ArrayList<Integer> vertList = new ArrayList<Integer>();

        // initialize array of possible vertices
        for (int i = 0; i < numVert; i++) {
            if (i != startingVertex) {
                vertList.add(i);
            }
        }

        // add all possible path permutations to permutations ArrayList 
        permute(vertList.stream().mapToInt(i -> i).toArray(), 0, permutations);
    }

    /**
     * This function modifies Dr. Byun's example permutation program from Replit. Instead of printing,
     * it adds each new int[] to ArrayList<int[]> permutations.
     * 
     * Source: https://replit.com/@YBYUN/JavaPermutations 
     * @param input int[] array containing vertices in graph, excluding starting vertex
     * @param startindex int position of starting index on input
     * @param permutations ArrayList<int[]> that permutations are pushed onto
     */
    static void permute(int[] input, int startindex, ArrayList<int[]> permutations) {
        int size = input.length;

        // push unique permutation onto permutations ArrayList
        if (size == startindex + 1) {
            int[] temp = new int[size];
            for (int i = 0; i < size; i++) {
                temp[i] = input[i];
            }
            permutations.add(temp);
        } else { //recursively generate permutations for each vertex
            for (int i = startindex; i < size; i++) {
                int temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;

                permute(input, startindex + 1, permutations);

                temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;
            }
        }
    }
    
    /**
     * Calculates lowest cost path & determines index within permutations ArrayList. Returns an int[] of
     * int[0] = index and int[1] = cost. If no path exists, it returns { -1, -1 }.
     * @param graph int[][] containing values of costs between vertices
     * @param permutations ArrayList<int[]> containing all possible path permutations excluding starting vertex
     * @param startingVertex int representing starting and ending vertex
     * @return int[] array with [0] = index and [1]= cost
     */
    static int[] calculateCosts(int[][] graph, ArrayList<int[]> permutations, int startingVertex) {

        int index = -1;
        int min = Integer.MAX_VALUE;

        // Calculate cost for each path in permutations
        for (int i = 0; i < permutations.size(); i++) {

            // create temp array with starting vertex added to front and end
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(0, startingVertex);
            for (int element : permutations.get(i)) {
                temp.add(element);
            }
            temp.add(startingVertex);

            boolean noPath = false;
            int cost = 0;

            // Calculate cost of path if it exists
            for (int j = 0; j < temp.size() - 1; j++) {
                noPath = false;
                int nextVert = j + 1;

                // Exit loop if next vertex cannot be reached
                if (graph[temp.get(j)][temp.get(nextVert)] == 0) {
                    noPath = true;
                    break;
                } else { // add cost of traveling to next vertex
                    cost += graph[temp.get(j)][temp.get(nextVert)];
                }
            }

            // update index & min cost values if path exists
            if (!noPath) {
                // update index if the current cost is the new minimum
                index = Math.min(min, cost) == cost ? i : index;
                min = Math.min(min, cost);
            }
        }
        // return {-1, -1} if no paths exist
        return new int[] { index, index == -1 ? -1 : min };
    }

    /**
     * Calculate the factorial of num
     * @param num
     * @return int representing num!
     */
    static int factorial(int num) {
        if (num == 1) {
            return num;
        }
        return num * factorial(num - 1);
    }
}
