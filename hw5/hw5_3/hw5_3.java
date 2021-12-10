
/**
 * Title: hw5_3.java 
 * Abstract: This assignment takes user input and simulates the operations
 * of linear probing as covered in the lecture. We build as hash table based
 * on user input, rehash the table once load factor exceeds 0.5 and perform
 * insert/search operations using linear probing. 
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 12/08/2021
 */

import java.util.*;
import java.math.*;

public class hw5_3 {
    final int EMPTY = -1;
    final float MAX_LOAD_FACTOR = 0.5f;

    int hashTable[];
    String commands[];
    int numKeys;
    int tSize;

    /**
     * Default constructor
     */
    hw5_3() {
        Scanner myScanner = new Scanner(System.in);
        tSize = myScanner.nextInt();
        numKeys = 0;

        initHashTable(myScanner);
        initCommands(myScanner);

        myScanner.close();
    }

    /**
     * Helper for constructor. Initialize hash table
     * with user input.
     * 
     * @param myScanner Scanner Initialized in default constructor.
     */
    private void initHashTable(Scanner myScanner) {
        hashTable = new int[tSize];
        Arrays.fill(hashTable, EMPTY);
    }

    /**
     * Helper for constructor. Initialize and store
     * user commands.
     * 
     * @param myScanner Initialized in default constructor.
     */
    private void initCommands(Scanner myScanner) {
        int numCommands = myScanner.nextInt();
        myScanner.nextLine();

        commands = new String[numCommands];
        for (int i = 0; i < numCommands; i++) {
            commands[i] = myScanner.nextLine().trim();
        }
    }

    /**
     * Parse stored commands and call corresponding methods.
     */
    public void runCommand() {
        for (String command : commands) {
            if (command.indexOf(" ") == -1) {
                tableSize();
            } else {
                String parse[] = command.split(" ", 2);
                execute(parse[0], Integer.parseInt(parse[1]));
            }
        }
    }

    /**
     * Helper for runCommand. Identifies requested method
     * and passes user defined parameter.
     * 
     * @param funcName String Name of method to be called.
     * @param param    Int Value of parameter passed to method.
     */
    private void execute(String funcName, int param) {
        switch (funcName) {
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

    /**
     * Inserts new value into hash table. Calculates load factor
     * after every insert and rehashes if exceeded.
     * 
     * @param value
     */
    public void insert(int value) {
        insertTo(value, hashTable);

        float loadFactor = (float) numKeys / (float) tSize;
        if (loadFactor >= MAX_LOAD_FACTOR) {
            rehash();
        }
    }

    /**
     * Helper for insert and rehash. Hashes value into a
     * key and attempts to insert to passed table. Performs
     * linear probing if collision occurs.
     * 
     * @param value Int Value to be inserted.
     * @param to    Int[] Hash Table to insert to.
     */
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

    /**
     * Helper for insertTo and search. Method for hashing
     * keys covered in lecture.
     * 
     * @param value Int Value to be hashed into a key.
     * @return Int Key
     */
    private int hash(int value) {
        return value % tSize;
    }

    /**
     * Helper for insertTo and search. Attempts to find
     * value at index pointed to by hashed key. Performs
     * linear probing. Starts at position immediately
     * following key parameter and loops around array
     * (as if it is circular).
     * 
     * @param key   Int Previously hashed key.
     * @param value Int Value searched for.
     * @param table Int[] Hash table to search in.
     * @return Int -1 if not found, index if found.
     */
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

    /**
     * Helper for insert. This rehashes the current table. It
     * calculates a new table size, and initializes a new
     * hash table. It rehashes the entries of the old table
     * and inserts them to the new table.
     */
    private void rehash() {
        // Calculate next prime after doubling table size
        String curSize = Integer.toString(2 * tSize);
        BigInteger newSize = new BigInteger(curSize).nextProbablePrime();

        // Set new table values
        tSize = newSize.intValue();
        numKeys = 0;

        // Initialize new hash table
        int newTable[] = new int[tSize];
        Arrays.fill(newTable, EMPTY);

        // Rehash and reinsert all values in old table
        for (int entry : hashTable) {
            if (entry != EMPTY) {
                insertTo(entry, newTable);
            }
        }
        hashTable = newTable;
    }

    /**
     * Searches for an integer value in the hash table using
     * hashing and linear probing. Then prints the result.
     * 
     * @param value Int Value to be found.
     */
    public void search(int value) {
        int key = hash(value);
        if (hashTable[key] == EMPTY) {
            System.out.printf("%d Not found\n", value);
        } else if (hashTable[key] == value) {
            System.out.printf("%d Found\n", value);
        } else {
            int position = linearProbe(key, value, hashTable);
            String result = "";
            if(position == -1 || hashTable[position] != value) {
                result = "Not found";
            } else {
                result = "Found";
            }
            System.out.printf("%d %s\n", value, result);
        }
    }

    /**
     * Displays absolute max entries allowed in hash table.
     */
    public void tableSize() {
        System.out.println(tSize);
    }

    /**
     * Displays whether a hash table entry is "Empty" or
     * the value if it is occupied.
     * 
     * @param index
     */
    public void displayStatus(int index) {
        int entry = hashTable[index];
        System.out.println(entry == EMPTY ? "Empty" : entry);
    }

    public static void main(String args[]) {
        hw5_3 test = new hw5_3();
        test.runCommand();
    }
}
