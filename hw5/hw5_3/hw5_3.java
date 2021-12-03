import java.util.*;
import java.math.*;

public class hw5_3 {
    final int EMPTY = -1;
    final float MAX_LOAD_FACTOR = 0.5f;

    int hashTable[];
    String commands[];
    int numKeys;
    int tSize;

    hw5_3() {
        Scanner myScanner = new Scanner(System.in);
        tSize = myScanner.nextInt();
        numKeys = 0;

        initHashTable(myScanner);
        initCommands(myScanner);

        myScanner.close();
    }

    private void initHashTable(Scanner myScanner) {
        hashTable = new int[tSize];
        Arrays.fill(hashTable, EMPTY);
    }

    private void initCommands(Scanner myScanner) {
        int numCommands = myScanner.nextInt();
        myScanner.nextLine();

        commands = new String[numCommands];
        for (int i = 0; i < numCommands; i++) {
            commands[i] = myScanner.nextLine().trim();
        }
    }

    public void runCommand() {
        for(String command : commands){
            if(command.indexOf(" ") == -1){
                tableSize();
            } else {
                String parse[] = command.split(" ", 2);
                execute(parse[0], Integer.parseInt(parse[1]));
            }
        }
    }

    private void execute(String funcName, int param) {
        switch(funcName) {
            case "displayStatus":
                displayStatus(param);
                break;
            case "insert":
                insert(param);
                break;
            case "search":
                search(param);
                break;
            default:
                System.out.printf("Invalid Command: %s", funcName);
        }
    }

    public void insert(int value) {
        insertTo(value, hashTable);

        float loadFactor = (float) numKeys / (float) tSize;
        if (loadFactor >= MAX_LOAD_FACTOR) {
            rehash();
        }
    }

    private void insertTo(int value, int[] to) {
        int key = hash(value);

        if (to[key] == EMPTY) {
            to[key] = value;
        } else {
            int newKey = linearProbe(key, value, to);
            to[newKey] = value;
        }

        numKeys++;
    }

    private int hash(int value) {
        return value % tSize;
    }

    private int linearProbe(int key, int value, int[] table) {
        int position = (key + 1) % tSize;
        while (position != key) {
            if (table[position] == EMPTY) {
                return position;
            }
            position = (position + 1) % tSize;
        }
        return -1;
    }

    private void rehash() {
        String curSize = Integer.toString(2 * tSize);
        BigInteger newSize = new BigInteger(curSize).nextProbablePrime();

        tSize = newSize.intValue();
        numKeys = 0;

        int newTable[] = new int[tSize];
        Arrays.fill(newTable, -1);

        for (int entry : hashTable) {
            if (entry != EMPTY) {
                insertTo(entry, newTable);
            }
        }
        hashTable = newTable;
    }

    public void search(int value) {
        int key = hash(value);
        if (hashTable[key] == EMPTY) {
            System.out.printf("%d Not found\n", value);
        } else if (hashTable[key] == value) {
            System.out.printf("%d Found\n", value);
        } else {
            int position = linearProbe(key, value, hashTable);
            String result = position == -1 ? "Not found" : "Found";
            System.out.printf("%d %s\n", value, result);
        }
    }

    public void tableSize() {
        System.out.println(tSize);
    }

    public void displayStatus(int index) {
        int entry = hashTable[index];
        System.out.println(entry == EMPTY ? "Empty" : entry);
    }

    public void printHashTable() {
        for (int num : hashTable) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }

    public static void main(String args[]) {
        hw5_3 test = new hw5_3();
        test.runCommand();
    }
}
