package lab08.gui;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Step 06: Breadth-first search.
// This class extends the DFS class and adds queue-based BFS.
public class Step06BreadthFirstSearch extends Step05DepthFirstSearch {
    public Step06BreadthFirstSearch() {
        super("Step06 Breadth-First Search");
    }

    protected Step06BreadthFirstSearch(String title) {
        super(title);
    }

    public List<String> breadthFirstTraversal(String start) {
        resetColors();
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> queue = new ArrayDeque<>();

        queue.add(start);
        visited.add(start);
        colorVertex(start, Color.GREEN);
        message = "BFS starts at " + start;
        visualize();

        while (!queue.isEmpty()) {
            String current = queue.remove();
            order.add(current);
            colorVertex(current, Color.YELLOW);
            message = "BFS removes " + current + " from the queue";
            visualize();

            for (String next : neighbors(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                    colorVertex(next, Color.GREEN);
                    colorEdge(current, next, Color.RED);
                    message = "BFS discovers " + next + " from " + current;
                    visualize();
                    colorEdge(current, next, Color.GRAY);
                }
            }

            colorVertex(current, Color.ORANGE);
        }

        message = "BFS order: " + order;
        visualize();
        return order;
    }

    public List<String> shortestPathByEdges(String start, String goal) {
        resetColors();
        Map<String, String> parent = new LinkedHashMap<>();
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> queue = new ArrayDeque<>();

        queue.add(start);
        visited.add(start);
        parent.put(start, null);
        colorVertex(start, Color.GREEN);
        message = "BFS shortest path search from " + start + " to " + goal;
        visualize();

        while (!queue.isEmpty()) {
            String current = queue.remove();
            colorVertex(current, Color.YELLOW);
            visualize();

            if (current.equals(goal)) {
                List<String> path = reconstructPath(parent, goal);
                highlightPath(path);
                return path;
            }

            for (String next : neighbors(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, current);
                    queue.add(next);
                    colorVertex(next, Color.GREEN);
                    colorEdge(current, next, Color.RED);
                    message = "BFS sets parent of " + next + " to " + current;
                    visualize();
                    colorEdge(current, next, Color.GRAY);
                }
            }
            colorVertex(current, Color.ORANGE);
        }

        message = "No path found from " + start + " to " + goal;
        visualize();
        return Collections.emptyList();
    }

    private List<String> reconstructPath(Map<String, String> parent, String goal) {
        List<String> path = new ArrayList<>();
        String current = goal;
        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Step06BreadthFirstSearch graph = new Step06BreadthFirstSearch();
        graph.setDelay(700);

        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");
        graph.addVertex("c");
        graph.addVertex("h");

        graph.addEdge("a", "b");
        graph.addEdge("a", "d");
        graph.addEdge("a", "e");
        graph.addEdge("b", "c");
        graph.addEdge("e", "f");
        graph.addEdge("f", "c");
        graph.addEdge("d", "h");
        graph.addEdge("h", "f");

        List<String> order = graph.breadthFirstTraversal("a");
        List<String> path = graph.shortestPathByEdges("a", "h");
        System.out.println("BFS order: " + order);
        System.out.println("Shortest path by edges from a to h: " + path);
    }
}
