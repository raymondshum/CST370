/**
 * Title: hw2_2.java 
 * Abstract: This program reads input and displays all possible values in 
 *           the form of [Decimal Number]:[Binary Number]:[Subset]
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 11/9/2021
 */

import java.util.*;
import java.lang.Math;

public class hw2_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] elements;
        int numElements = input.nextInt(); 
        int totalCombinations = (int) Math.pow(2, numElements); // total binary numbers
        input.nextLine(); // get rid of newline character

        // initialize elements[] to dummy value if no elements
        if (numElements == 0) {
            elements = new String[1];
            elements[0] = "EMPTY";
        } else { // split user input into array of strings
            elements = input.nextLine().split(" ", 0);
        }

        // Print each possible [Decimal]:[Binary]:[Subset] as new line
        for (int i = 0; i < totalCombinations; i++) {
            int element = i; // was used to tune starting index value

            // Sets binaryString to "0" if there are no elements
            // Else, left pads binaryString with "0" until it reaches numElements
            String binaryString = (numElements == 0) ? "0"
                    : String.format("%" + numElements + "s", Integer.toBinaryString(element)).replace(' ', '0');
            
            StringBuffer subset = new StringBuffer();

            // Subset is always "EMPTY" at element 0
            if (element == 0) {
                subset.append("EMPTY");
            } else {
                // Index of padded binaryString corresponds with index on elements[]
                for (int j = 0; j < binaryString.length(); j++) {
                    // Append elements[index] if corresponding index on binaryString is "1"
                    if (binaryString.charAt(j) == '1') {
                        subset.append(elements[j] + " ");
                    }
                }
            }

            System.out.printf("%d:%s:%s\n", element, binaryString, subset);
        }

        input.close();
    }
}
