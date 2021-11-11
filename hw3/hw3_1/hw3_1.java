/**
 * Title: hw3_1.java 
 * Abstract: This program takes input values, sorts in ascending order and displays
 *           them. Consecutive integers are displayed as ranges in the form of 
 *           int[0]-int[n-1].
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 11/16/2021
 */

import java.util.*;

public class hw3_1 {
    public static void main(String[] args) {

        // Initialize variables
        Scanner input = new Scanner(System.in);
        int arraySize = input.nextInt();
        int numArray[] = new int[arraySize];
        StringBuilder result = new StringBuilder();

        // Build array
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = input.nextInt();
        }

        // Sort array
        Arrays.sort(numArray);

        // Traverses sorted array and builds output string
        for (int i = 0; i < numArray.length; i++) {

            int j = i;
            result.append(" " + numArray[i]);

            // Tests if next index is also the the value of current index + 1
            if (i + 1 != numArray.length && numArray[i + 1] == numArray[i] + 1) {

                j = i + 1; // use j as a second pointer

                // Continues testing to see if successive indexes are also successive integer values
                while (j != numArray.length && numArray[j] == numArray[j - 1] + 1) {
                    j++;
                }

                // Corrects for offsets (while loop performs check after previous loop's iteration)
                if (j >= numArray.length) {
                    j = numArray.length - 1;
                } else {
                    j--; 
                }
                
                result.append("-" + numArray[j]);
                i = j; // Skip values of i already accounted for in result string
            }

        }

        System.out.println(result.toString().trim());
        input.close();
    }
}
