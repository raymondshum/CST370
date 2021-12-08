
/**
 * Title: hw5_1.java 
 * Abstract: This assignment takes user input and conducts heap
 * operations. The program displays whether a user provided list
 * is in the form of a max heap. If not, it generates a max heap.
 * It then performs user requested insert, delete and display
 * commands.
 * Author: Raymond Shum 
 * ID: 9030 
 * Date: 12/08/2021
 */

import java.util.*;

public class hw5_1 {

    ArrayList<Integer> heap;
    String commands[];

    /**
     * Default constructor. Takes user input and initializes class members.
     */
    hw5_1() {
        Scanner myScanner = new Scanner(System.in);
        init_heap(myScanner);
        init_commands(myScanner);
        myScanner.close();
    }

    /**
     * Initializes heap container.
     * @param myScanner Scanner object initialized in default constructor.
     */
    private void init_heap(Scanner myScanner) {
        int numNodes = myScanner.nextInt();
        heap = new ArrayList<>();

        for (int i = 0; i < numNodes + 1; i++) {
            int nextVal = i == 0 ? 0 : myScanner.nextInt();
            heap.add(nextVal);
        }
    }

    /**
     * Initializes container that stores user commands.
     * @param myScanner Scanner object initialized in default constructor.
     */
    private void init_commands(Scanner myScanner) {
        int numCommands = myScanner.nextInt();
        myScanner.nextLine();
        commands = new String[numCommands];

        // Command parsing relies on detecting white space to identify "insert value"
        for (int i = 0; i < numCommands; i++) {
            commands[i] = myScanner.nextLine().trim();
        }
    }

    /**
     * Displays whether the current heap[] is a max heap.
     */
    public void displayHeapStatus() {
        String result = isHeap() ? "a" : "NOT a";
        System.out.printf("This is %s heap.\n", result);
    }

    /**
     * Traverses heap[] starting at the last node, until the first
     * non-root node. Returns false if a node is ever larger than
     * its parent.
     * @return True if heap[] is a max heap. False, otherwise.
     */
    private Boolean isHeap() {
        // Only one node in the heap.
        if (heap.size() == 2) {
            return true;
        }

        for (int i = heap.size() - 1; i > 1; i--) {
            int parent = i / 2;
            if (heap.get(i) > heap.get(parent)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Creates heap using the "button-up" approach. Calls
     * maxHeapify on each parent node, from last to root.
     */
    public void createMaxHeap() {
        // Exits if heap[] is already a max heap.
        if (isHeap()) {
            return;
        }

        // heap size is offset by 1 because size 1 = index 0, etc
        int lastParent = (heap.size() - 1) / 2;

        for (int i = lastParent; i > 0; i--) {
            maxHeapify(i);
        }
    }

    /**
     * Recursively calls heapify until condition for max heap (parent > child)
     * is met starting at parent node (index) and ending at leaf nodes.
     * @param index Int Index of parent (root) node of sub-tree to convert to max heap.
     */
    private void maxHeapify(int index) {
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;
        int lastIndex = heap.size() - 1;

        // Base case - Leaf node is reached
        if (leftChild > lastIndex) {
            return;
        }

        // Parent node only has left child
        if (rightChild > lastIndex) {
            if (heap.get(leftChild) > heap.get(index)) {
                Collections.swap(heap, leftChild, index);
                maxHeapify(leftChild);
            }
        } else { // Parent node has both children
            int leftValue = heap.get(leftChild);
            int rightValue = heap.get(rightChild);

            // Parent node is compared to the larger of its children nodes
            int largerChild = leftValue > rightValue ? leftChild : rightChild;

            if (heap.get(index) < heap.get(largerChild)) {
                Collections.swap(heap, index, largerChild);
                maxHeapify(largerChild);
            }
        }
    }

    /**
     * Parses user generated commands stored in commands[]
     * and calls corresponding method.
     */
    public void runCommand() {
        for (String command : commands) {
            // If a space is detected, call insert, passing
            // value of integer on right of space as param
            if (command.indexOf(" ") != -1) {
                String parse[] = command.split(" ", 2);
                int value = Integer.parseInt(parse[1]);
                insert(value);
            } else {
                switch (command) {
                    case "displayMax":
                        displayMax();
                        break;
                    case "deleteMax":
                        deleteMax();
                        break;
                    case "display":
                        display();
                        break;
                    default:
                        System.out.printf("Unrecognized command: %s\n", command);
                }
            }
        }
    }

    /**
     * Print the largest number in the heap.
     */
    private void displayMax() {
        System.out.println(heap.get(1));
    }

    /**
     * Print the heap, starting at root, traveling
     * left to right at each level, ending at leaves.
     */
    private void display() {
        for (int i = 1; i < heap.size(); i++) {
            System.out.printf("%d ", heap.get(i));
        }
        System.out.println();
    }

    /**
     * Insert value to the end of heap[] and
     * maxHeapify if no longer a max heap.
     * @param value Int To be inserted.
     */
    private void insert(int value) {
        heap.add(value);
        createMaxHeap();
    }

    /**
     * Delete max value in the heap. Swap, value of
     * root with last node. Remove last node. Turn
     * heap[] back into max heap.
     */
    private void deleteMax() {
        int lastIndex = heap.size() - 1;
        Collections.swap(heap, 1, lastIndex);
        int max = heap.remove(lastIndex);

        createMaxHeap();
    }

    public static void main(String args[]) {
        hw5_1 test = new hw5_1();
        test.displayHeapStatus();
        test.createMaxHeap();
        test.runCommand();
    }

}
