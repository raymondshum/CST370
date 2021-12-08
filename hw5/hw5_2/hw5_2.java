
/**
 * Title: hw5_2.java 
 * Abstract: This assignment performs takes user input, builds an
 * array of random integers and compares performance between two
 * sort methods: quick sort and merge sort. Both methods sort a 
 * separate clone of the same random integer array.
 * 
 * The sorting programs were referenced from the following sources:
 * 
 * Merge Sort - Baeldung:
 * https://www.baeldung.com/java-merge-sort
 * 
 * Quick Sort - Geeks For Geeks
 * https://www.geeksforgeeks.org/quick-sort/
 * 
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 12/08/2021
 */
import java.util.*;

public class hw5_2 {

    int inputSize;
    int randArray[];

    /**
     * Default constructor.
     * 
     * @param value Int Intended size of array.
     */
    hw5_2(int value) {
        // Array defaults to length = 1 if invalid user input.
        this.inputSize = value < 1 ? 1 : value;
        initArray();
    }

    /**
     * Helper for constructor. Initialize array with
     * random integers.
     */
    private void initArray() {
        Random rand = new Random();
        randArray = new int[inputSize];
        for (int i = 0; i < randArray.length; i++) {
            randArray[i] = rand.nextInt();
        }
    }

    /**
     * Asks user for size of array to be sorted. Value must be
     * integer > 0. User has 3 chances to input a valid number
     * before sentinel value (-1) is returned.
     * 
     * @return userInput Int Size of array to be sorted.
     */
    public static int getUserInput() {
        Scanner myScanner = new Scanner(System.in);

        boolean success = false;
        int count = 0;
        int userInput = -1;

        while (!success && count < 3) {
            System.out.printf("Enter input size: ");
            try {
                userInput = myScanner.nextInt();
                if (userInput <= 0) {
                    System.out.println("Must be > 0. Please try again.");
                    userInput = -1;
                } else {
                    success = true;
                }
            } catch (Exception e) {
                myScanner.next();
                System.out.println("Not an integer. Please try again.");
            }
            count++;
        }
        myScanner.close();
        return userInput;
    }

    /**
     * Runs both sorting methods and prints the results to
     * console. A new array is cloned from randArray and passed
     * to either quickSort or mergeSort. This is so that
     * each method sorts the same array.
     */
    public void runSort() {
        double[] runTime = new double[2];
        for (int i = 0; i < 2; i++) {
            int[] array = cloneRandArray();
            runTime[i] = timer(i, array);
        }
        printResult(runTime);
    }

    /**
     * Helper for runSort. Formats and displays results string.
     * @param runTime double[] Array holding sorting result times.
     */
    private void printResult(double[] runTime) {
        StringBuilder result = new StringBuilder();
        String title = " Execution Time ";
        buildHeader(result, 15);
        result.append(title);
        buildHeader(result, 15);
        result.append("\n");

        for (int i = 0; i < runTime.length; i++) {
            String sortType = i == 0 ? "Merge Sort" : "Quick Sort";
            result.append(String.format("%s: \t%f milliseconds\n", sortType, runTime[i]));
        }

        buildHeader(result, 30 + title.length());
        System.out.println(result.toString());
    }

    /**
     * Helper for printResult. Builds the border
     * of the header.
     * 
     * @param result StringBuilder Containing result string.
     * @param length Int Length of border.
     */
    private void buildHeader(StringBuilder result, int length) {
        for (int i = 0; i < length; i++) {
            result.append("=");
        }
    }

    /**
     * Helper for runSort. Runs either quickSort
     * (if code = 0) or mergeSort otherwise. Returns
     * execution time for either function in
     * milliseconds, converted from nanoseconds.
     * @param code Int 1 = mergeSort, else quickSort
     * @param array int[] Holds result times.
     * @return double Result time for single method call.
     */
    private double timer(int code, int[] array) {
        long startTime = System.nanoTime();
        if (code == 0) {
            mergeSort(array, array.length);
        } else {
            quickSort(array, 0, array.length - 1);
        }
        long endTime = System.nanoTime();
        double result = (endTime - startTime) / 1E6;
        return result;
    }

    /**
     * Helper for runSort. Returns reference to
     * deep copy of randArray.
     * @return int[] Clone of randArray
     */
    private int[] cloneRandArray() {
        int[] temp = randArray.clone();
        return temp;
    }

    /**
     * Merge Sort program from Baeldung.
     * https://www.baeldung.com/java-merge-sort
     */
    public void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    /**
     * Helper for mergeSort. Fill in remaining (and ragged)
     * sub arrays.
     */
    private void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    /**
     * Quick Sort program from Geeks for Geeks.
     * https://www.geeksforgeeks.org/quick-sort/
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Helper for quickSort. Partitions by selecting
     * last element as pivot.
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /**
     * Geeks for Geeks "swap" method.
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String args[]) {
        int value = getUserInput();

        if (value == -1) {
            System.out.println("Incorrect input has been entered 3 times. " +
                    "Constructor will default to input size = 1.");
        }

        hw5_2 test = new hw5_2(value);
        test.runSort();
    }
}
