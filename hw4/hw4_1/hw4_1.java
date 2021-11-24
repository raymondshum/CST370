/**
 * Title: hw4_1.java 
 * Abstract: This program takes user input in the form of a list of positive
 * and negative integer values. It partitions the list by moving negative
 * values to the front and positive values to the rear (using two methods).
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 11/30/2021
 */
import java.util.Arrays;
import java.util.Scanner;

public class hw4_1 {
    public static void main(String args[]) {
        Scanner myScanner = new Scanner(System.in);
        int input[] = getInput(myScanner);

        int result1[] = partA(input);
        printArray(result1);

        int result2[] = partB(input);
        printArray(result2);

        myScanner.close();
    }

    /**
     * Generates int[] array from user input.
     * @param myScanner Scanner object.
     * @return int[] holding user input.
     */
    public static int[] getInput(Scanner myScanner) {
        int numVal = myScanner.nextInt();
        int input[] = new int[numVal];

        for (int i = 0; i < numVal; i++) {
            input[i] = myScanner.nextInt();
        }
        return input;
    }

    /**
     * Prints all values held in the array.
     * @param array int[]
     */
    public static void printArray(int[] array) {
        for (int val : array) {
            System.out.printf("%d ", val);
        }
        System.out.println();
    }

    /**
     * Performs partitioning method where points start at opposite
     * ends of the array and move towards each other.
     * @param array int[]
     * @return int[] containing partitioned values
     */
    public static int[] partA(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);

        int left = 0;
        int right = result.length - 1;

        while (left < right) {
            while (result[left] < 0 && left < result.length - 1) {
                left++;
            }
            while (result[right] > 0 && right > 0) {
                right--;
            }
            if (left < right) {
                swap(result, left, right);
            }
        }

        return result;
    }

    /**
     * Uses partitioning method where both pointers start on one side
     * (beginning) of the the array.
     * @param array int[]
     * @return int[] of partitioned values
     */
    public static int[] partB(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);

        int left = 0;
        int right = 0;

        while (right < result.length - 1 && left < result.length - 1) {
            while (result[left] < 0 && left < result.length - 1) {
                left++;
            }

            right = left < result.length ? left : result.length - 1;

            while (result[right] >= 0 && right < result.length - 1) {
                right++;
            }
            if (Integer.signum(result[left]) != Integer.signum(result[right])) {
                swap(result, left, right);
            }
        }

        return result;
    }

    /**
     * Swaps the value of two indices in an array.
     * @param array int[] 
     * @param ind1 index 1
     * @param ind2 index 2
     */
    public static void swap(int[] array, int ind1, int ind2) {
        int temp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = temp;
    }

}
