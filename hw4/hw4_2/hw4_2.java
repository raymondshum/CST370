/**
 * Title: hw4_2.java 
 * Abstract: This program finds the maximum value in an array using a divide-
 * and-conquer technique.
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 11/30/2021
 */
import java.util.Arrays;
import java.util.Scanner;

public class hw4_2 {
    public static void main(String args[]) {
        int[] input = getInput();
        System.out.println(findMax(input, 0, input.length - 1));
    }

    /**
     * Gets user input from console and pushes to array.
     * @return int[] containing user input.
     */
    private static int[] getInput() {
        Scanner myScanner = new Scanner(System.in);

        int numVal = myScanner.nextInt();
        int[] input = new int[numVal];

        for (int i = 0; i < numVal; i++) {
            input[i] = myScanner.nextInt();
        }

        myScanner.close();

        return input;
    }

    /**
     * Based on Dr. Byun's sum_div_N_conq program. This function is similar to the
     * mergeSort algorithm. It recursively divides the array until it is examining
     * n single values. Each single value is compared to another value and the
     * maximum is returned through the call stack until all open function calls
     * are resolved.
     * @param values int[] User input.
     * @param start int Starting index of subarray.
     * @param end int Ending index of subarray.
     * @return int Maximum value of two subarrays.
     */
    public static int findMax(int[] values, int start, int end) {
        if (start == end) {
            return values[start];
        }

        int max1 = findMax(values, start, (start + end) / 2);
        int max2 = findMax(values, (start + end) / 2 + 1, end);

        return Math.max(max1, max2);
    }
}