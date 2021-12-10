
/**
 * Title: hw6_1.java 
 * Abstract: This program takes user input and builds a 2D
 * matrix, representing a board with coins on cells marked
 * with "1" (and nothing on cells marked with 0). Starting
 * from the upper left hand corner and traversing to the
 * lower right, it calculates the optimal path in which a
 * maximum number of coins can be acquired. Traversal can
 * only occur from the current cell to the cell on the 
 * right or below.
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 12/14/2021
 */

import java.util.*;

public class hw6_1 {

    int[][] board; // Stores dynamic programming results
    int[][] reference; // Stores original user input
    int coins; // Used for max coins

    /**
     * Default constructor
     */
    hw6_1() {
        Scanner myScanner = new Scanner(System.in);

        int row = myScanner.nextInt();
        int col = myScanner.nextInt();

        coins = 0;

        // The board is intended to start at [1,1] for both arrays
        board = new int[row + 1][col + 1];
        reference = new int[row + 1][col + 1];

        // Initialize both 2d arrays
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i + 1][j + 1] = myScanner.nextInt();
                reference[i + 1][j + 1] = board[i + 1][j + 1];
            }
        }

        buildResultArray();
        myScanner.close();
    }

    /**
     * Helper for constructor. Value of current cell
     * is equal to the greater of the cell above or
     * cell to the left plus the contents of the
     * current cell.
     */
    private void buildResultArray() {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                int fromTop = board[i - 1][j] + board[i][j];
                int fromLeft = board[i][j - 1] + board[i][j];
                board[i][j] = fromTop > fromLeft ? fromTop : fromLeft;
            }
        }
    }

    /**
     * Returns optimal path and max coins by tracing
     * path from the lower right hand corner, back to
     * the upper left hand corner.
     * 
     * @return String Containing formatted output.
     */
    public String backTrace() {
        // Results are popped from a stack (reverse order)
        Deque<String> stack = new ArrayDeque<>();
        int row = board.length - 1;
        int col = board[row].length - 1;

        // Traverses from bottom right.
        // Tie breaker occurs by picking left, so sometimes
        // the current position will be below upper left corner.
        while (row != 0 && col != 0) {
            String currentPos = String.format("(%d,%d)", row, col);
            stack.push(currentPos);

            // Coins are picked up along the reverse path
            coins += reference[row][col];

            int leftCell = board[row][col - 1];
            int topCell = board[row - 1][col];

            // Will always traverse left in case of a tie
            if (topCell > leftCell) {
                row--;
            } else if (leftCell >= topCell) {
                col--;
            }
        }

        col++; // Set column back to 1 (0 due to while loop)

        // Returns to upper left if current position has not reached it
        while (row != 0) {
            row--;
            if (row != 0) {
                String currentPos = String.format("(%d,%d)", row, col);
                stack.push(currentPos);
                coins += reference[row][col];
            }
        }

        // Format results, retrieve forward path by popping stack
        String result = "Path:";
        result += stack.pop();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            result += "->";
            result += stack.pop();
        }

        // Format required output
        String result2 = String.format("Max coins:%d\n", coins);
        result2 += result;

        return result2;
    }

    public static void main(String args[]) {
        hw6_1 test = new hw6_1();
        System.out.println(test.backTrace());
    }
}
