import java.util.*;

public class hw2_3 {
    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);

        String input = myScanner.nextLine().toLowerCase().trim();
        input = input.replaceAll("[^a-z0-9]", "");
        System.out.println(isPalindrome(input) ? "TRUE" : "FALSE");
        myScanner.close();
    }

    public static boolean isPalindrome(String input) {
        if(input.length() <= 1) {
            return true;
        }

        if(input.charAt(0) == input.charAt(input.length() - 1)) {
            return isPalindrome(input.substring(1, input.length()-1));
        } else {
            return false;
        }
    }
}
