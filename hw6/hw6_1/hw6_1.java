import java.util.*;

public class hw6_1 {

    int[][] board;
    int[][] reference;
    int coins;

    hw6_1() {
        Scanner myScanner = new Scanner(System.in);

        int row = myScanner.nextInt();
        int col = myScanner.nextInt();

        coins = 0;

        board = new int[row + 1][col + 1];
        reference = new int[row + 1][col + 1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i + 1][j + 1] = myScanner.nextInt();
                reference[i + 1][j + 1] = board[i + 1][j + 1];
            }
        }

        buildResultArray();

        myScanner.close();
    }

    private void buildResultArray() {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                int fromTop = board[i - 1][j] + board[i][j];
                int fromLeft = board[i][j - 1] + board[i][j];
                board[i][j] = fromTop > fromLeft ? fromTop : fromLeft;
            }
        }
    }

    public String backTrace() {
        Deque<String> stack = new ArrayDeque<>();
        int row = board.length - 1;
        int col = board[row].length - 1;

        while (row != 0 && col != 0) {
            String currentPos = String.format("(%d,%d)", row, col);
            stack.push(currentPos);

            coins += reference[row][col];

            int leftCell = board[row][col - 1];
            int topCell = board[row - 1][col];
            if (topCell > leftCell) {
                row--;
            } else if (leftCell >= topCell) {
                col--;
            }
        }

        col++;
        while (row != 0) {
            row--;
            if (row != 0) {
                String currentPos = String.format("(%d,%d)", row, col);
                stack.push(currentPos);
                coins += reference[row][col];
            }
        }

        String result = "Path:";
        result += stack.pop();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            result += "->";
            result += stack.pop();
        }

        String result2 = String.format("Max coins:%d\n", coins);
        result2 += result;

        return result2;
    }

    public static void main(String args[]) {
        hw6_1 test = new hw6_1();
        System.out.println(test.backTrace());;
    }
}
