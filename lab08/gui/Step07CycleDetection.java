package lab08.gui;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

// Step 07: Cycle detection.
// This class extends BFS so it inherits the graph, DFS, BFS, and visualization tools.
public class Step07CycleDetection extends Step06BreadthFirstSearch {
    public Step07CycleDetection() {
        super("Step07 Cycle Detection");
    }

    public boolean hasCycleUndirected() {
        resetColors();
        Set<String> visited = new HashSet<>();

        for (String vertex : vertices.keySet()) {
            if (!visited.contains(vertex) && hasCycleFrom(vertex, null, visited)) {
                message = "Cycle found";
                visualize();
                return true;
            }
        }

        message = "No cycle found";
        visualize();
        return false;
    }

    private boolean hasCycleFrom(String current, String parent, Set<String> visited) {
        visited.add(current);
        colorVertex(current, Color.YELLOW);
        message = "Checking " + current + " for a cycle";
        visualize();

        for (String next : neighbors(current)) {
            if (!visited.contains(next)) {
                colorEdge(current, next, Color.RED);
                if (hasCycleFrom(next, current, visited)) {
                    return true;
                }
                colorEdge(current, next, Color.GRAY);
            } else if (!next.equals(parent)) {
                colorVertex(current, Color.RED);
                colorVertex(next, Color.RED);
                colorEdge(current, next, Color.RED);
                message = "Cycle edge found: " + current + " -- " + next;
                visualize();
                return true;
            }
        }

        colorVertex(current, Color.ORANGE);
        return false;
    }

    public boolean isTree() {
        return vertexCount() > 0 && !hasCycleUndirected() && connectedComponentsCount() == 1;
    }

    private int connectedComponentsCount() {
        Set<String> visited = new HashSet<>();
        int count = 0;
        for (String start : vertices.keySet()) {
            if (!visited.contains(start)) {
                count++;
                markComponent(start, visited);
            }
        }
        return count;
    }

    private void markComponent(String start, Set<String> visited) {
        visited.add(start);
        for (String next : neighbors(start)) {
            if (!visited.contains(next)) {
                markComponent(next, visited);
            }
        }
    }

    public static void main(String[] args) {
        Step07CycleDetection graph = new Step07CycleDetection();
        graph.setDelay(1700);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "A");

        boolean hasCycle = graph.hasCycleUndirected();
        System.out.println("Has cycle? " + hasCycle);
    }
}
