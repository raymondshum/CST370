/**
 * Title: hw1_2.java
 * Abstract: This program reads input numbers from a user and displays the numbers
 *           that appear with the highest frequency, in descending order.
 * Author: Raymond Shum
 * ID: 9030
 * Date: 11/2/2021
 */

import java.util.*;

public class hw1_2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StringBuilder result = new StringBuilder("Number:");
        int maxFreq = 0;
        int numElements = input.nextInt();

        // TreeMap is in reverse order for descending order result
        Map<Integer, Integer> frequencyList = new TreeMap<>(Collections.reverseOrder());

        // Count frequency of all incoming numbers
        for (int i = 0; i < numElements; i++) {
            int number = input.nextInt();
            int newFreq;

            // Set number as 1 in Treemap if does not exist
            if (!frequencyList.containsKey(number)) {
                newFreq = 1;
                frequencyList.put(number, newFreq);
            } else { // Add one to frequency count if number exists
                newFreq = frequencyList.get(number) + 1;
                frequencyList.put(number, newFreq);
            }
            maxFreq = Math.max(newFreq, maxFreq); //tracks highest frequency
        }

        System.out.printf("Frequency:%d\n", maxFreq);

        // Prints key if value matches highest frequency number (desc order)
        for (Integer key : frequencyList.keySet()) {
            if(frequencyList.get(key) == maxFreq) {
                result.append(key + " ");
            }
        }

        System.out.println(result);

        input.close();
    }
}
