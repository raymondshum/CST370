/**
 * Title: hw2_3.java 
 * Abstract: This program takes in user input, formats it and implements a recursive
 *           function that returns true if it is in the form of a palindrome.
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 11/9/2021
 */

import java.util.*;

public class hw2_3 {
    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.nextLine().toLowerCase().trim();

        // Remove all non alpha-numeric characters from user input string.
        input = input.replaceAll("[^a-z0-9]", "");

        // Print "TRUE" if input is a palindrome and "FALSE" if it is not
        System.out.println(isPalindrome(input) ? "TRUE" : "FALSE");

        myScanner.close();
    }

    /**
     * Performs recursive check on String parameter to see if it is
     * a palindrome.
     * @param input String that is being tested
     * @return TRUE unless any pair of characters is mismatched.
     */
    public static boolean isPalindrome(String input) {

        // Initial Condition (Base Case)
        if(input.length() <= 1) {
            return true;
        }

        // If first and last character match, call function again using
        // new paramater (substring of input without first and last character).
        // This will repeat until there is a character mismatch or base case is reached.
        if(input.charAt(0) == input.charAt(input.length() - 1)) {
            // Java: substring([inclusive]begin, [exclusive]end)
            return isPalindrome(input.substring(1, input.length()-1));
        } else {
            // If there is ever a character mismatch, false is returned through
            // all open function calls in the call stack.
            return false;
        }
    }
}
