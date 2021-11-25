import java.util.*;

public class hw4_3 {
    public static void main(String args[]) {
        Graph myGraph = new Graph();
        Kahn myTopo = new Kahn(myGraph);
        myTopo.calcInDegree();
        myTopo.printInDegree();
        myTopo.calcOrder();
    }
}

class Graph {
    int numEdge;
    int numVert;
    ArrayList<ArrayList<Integer>> adjList;

    Graph() {
        Scanner myScanner = new Scanner(System.in);
        numVert = myScanner.nextInt();
        numEdge = myScanner.nextInt();
        adjList = new ArrayList<>(numVert);

        for (int i = 0; i < numVert; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < numEdge; i++) {
            int thisVert = myScanner.nextInt();
            int edgeTo = myScanner.nextInt();
            adjList.get(thisVert).add(edgeTo);
        }

        for (int i = 0; i < numVert; i++) {
            Collections.sort(adjList.get(i));
        }

        myScanner.close();
    }

    public int getNumEdge() {
        return numEdge;
    }

    public int getNumVert() {
        return numVert;
    }

    // Shallow copy
    public ArrayList<ArrayList<Integer>> getAdjList() {
        return adjList;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (ArrayList<Integer> list : adjList) {
            result.append(String.format("%d", count));
            for (int edge : list) {
                result.append(String.format("->%d", edge));
            }
            count++;
            result.append(String.format("\n"));
        }
        return result.toString();
    }
}

class Kahn {
    int[] inDegree;
    Graph myGraph;

    Kahn(Graph myGraph) {
        this.myGraph = myGraph;
        int numVert = myGraph.getNumVert();

        inDegree = new int[numVert];
        Arrays.fill(inDegree, 0);
    }

    public void calcInDegree() {
        ArrayList<ArrayList<Integer>> adjList = myGraph.getAdjList();
        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {
                inDegree[adjList.get(i).get(j)] += 1;
            }
        }
    }

    public void printInDegree() {
        for (int i = 0; i < inDegree.length; i++) {
            System.out.printf("In-degree[%d]:%d\n", i, inDegree[i]);
        }
    }

    public void calcOrder() {
        ArrayList<ArrayList<Integer>> adjList = myGraph.getAdjList();
        ArrayList<Integer> lastAdded = new ArrayList<>();

        boolean hasCycle = false;
        boolean visited[] = new boolean[myGraph.getNumVert()];
        Arrays.fill(visited, false);

        int numVisited = 0;
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0 && visited[i] == false) {
                queue.offer(i);
                visited[i] = true;
                numVisited++;
                lastAdded.add(i);
                result.append(String.format("%d->", i));
            }
        }

        while (numVisited < myGraph.getNumVert() && !hasCycle) {

            while (queue.size() > 0) {
                int vertex = queue.poll();
                for (int edge : adjList.get(vertex)) {
                    inDegree[edge] -= 1;
                    if (inDegree[edge] == 0) {
                        queue.offer(edge);
                        visited[edge] = true;
                        numVisited++;
                        lastAdded.add(edge);
                        result.append(String.format("%d->", edge));
                    }
                }
            }

            int numSources = 0;
            for (int i = 0; i < inDegree.length; i++) {
                if ((inDegree[i] == 0 && visited[i] == false) || (inDegree[i] == 0 && lastAdded.contains(i))) {
                    numSources++;
                }
            }
            if (numSources == 0) {
                hasCycle = true;
            }
            lastAdded.clear();
        }

        if (hasCycle) {
            System.out.println("No Order:");
        } else {
            result = result.delete(result.length() - 2, result.length());
            System.out.println("Order:" + result.toString());
        }
    }
}