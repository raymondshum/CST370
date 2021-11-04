/**
 * Title: hw2_1.java 
 * Abstract: This program reads two timestamps and displays the difference
 *           between them. The second event always occurs after the first.
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 11/9/2021
 */

import java.util.*;

public class hw2_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Convert user input into arrays of {hh, mm, ss}
        int[] date1 = formatInput(input.nextLine().split(":", 0));
        int[] date2 = formatInput(input.nextLine().split(":", 0));

        // Calculate total number of seconds in each array
        int seconds1 = timeToSeconds(date1);
        int seconds2 = timeToSeconds(date2);
        int difference = 0;

        // Calculate difference between times - can convert to ternary
        if (seconds2 >= seconds1) { // second time occurs before the next day
            difference = seconds2 - seconds1;
        } else { //second time occurs on the next day (after midnight)
            difference = timeToSeconds(new int[] {24,00,00}) - seconds1 + seconds2;
        }

        System.out.println(secondsToTime(difference));
        input.close();
    }

    /**
     * Takes String[] and converts it to int[].
     * @param input String[] made from splitting user input on ":" character.
     * @return int[] of parsed String[].
     */
    static int[] formatInput(String[] input) {
        int[] dateArray = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            dateArray[i] = Integer.parseInt(input[i]);
        }

        return dateArray;
    }

    /**
     * Sums total seconds represented by all elements in date array.
     * @param date int[] in format {hh:mm:ss}
     * @return int sum of all values converted to seconds
     */
    static int timeToSeconds(int[] date) {
        int hours = date[0] * 60 * 60;
        int minutes = date[1] * 60;
        int seconds = date[2];
        return hours + minutes + seconds;
    }

    /**
     * Converts seconds to String formatted in hh:mm:ss
     * @param sec total number of seconds to be converted
     * @return String formatted as hh:mm:ss
     */
    static String secondsToTime(int sec) {
        int hours = sec / 3600;
        int minutes = (sec % 3600) / 60;
        int seconds = sec % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}