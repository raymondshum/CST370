/**
 * Title: hw6_2.java 
 * Abstract: This program takes user input and builds an
 * adjacency matrix of weighted paths between vertices. It
 * uses Floyd's algorithm to calculate and display all-pairs
 * shortest paths.
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 12/14/2021
 */

import java.util.Scanner;

public class hw6_2 {
    final int INF = -1;
    int numVert;
    int[][] adjMatrix;

    /**
     * Default Constructor
     */
    hw6_2() {
        Scanner myScanner = new Scanner(System.in);
        numVert = myScanner.nextInt();
        adjMatrix = new int[numVert][numVert];

        // Initialize adjacency matrix
        for (int row = 0; row < numVert; row++) {
            for (int col = 0; col < numVert; col++) {
                adjMatrix[row][col] = myScanner.nextInt();
            }
        }

        myScanner.close();
    }

    /**
     * Floyd's algorithm. Overwrites contents of adjacency matrix. My
     * implementation starts at Col/Row = 0 rather than 1.
     */
    public void floyd() {
        for (int k = 0; k < numVert; k++) {
            for (int i = 0; i < numVert; i++) {
                for (int j = 0; j < numVert; j++) {
                    // Skip cell if it is:
                    // (1) Within the column/row used for comparison in D(k) 
                    // (2) An edge from a vertex to to itself.
                    if ((i != k && j != k) && (i != j)) {
                        // Skip if either comparison cell contains infinity (no path)
                        if (adjMatrix[i][k] != INF && adjMatrix[k][j] != INF) {
                            int newWeight = adjMatrix[i][k] + adjMatrix[k][j];
                            int curWeight = adjMatrix[i][j];
                            // This is used because infinity is a negative number 
                            // (will usurp Min).
                            adjMatrix[i][j] = curWeight == INF
                                    ? newWeight
                                    : Math.min(newWeight, curWeight);
                        }
                    }
                }
            }
        }
    }

    /**
     * Display adjacency matrix in console.
     */
    public void printMatrix() {
        for (int row = 0; row < numVert; row++) {
            for (int col = 0; col < numVert; col++) {
                System.out.printf("%d ", adjMatrix[row][col]);
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        hw6_2 test = new hw6_2();
        test.floyd();
        test.printMatrix();
    }
}
