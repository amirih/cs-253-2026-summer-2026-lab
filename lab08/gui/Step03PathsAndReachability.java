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

// Step 03: Paths and reachability.
// This class extends the directed graph class from Step02.
public class Step03PathsAndReachability extends Step02DirectedGraphDegrees {
    public Step03PathsAndReachability() {
        super("Step03 Paths and Reachability");
    }

    public boolean reachable(String start, String goal) {
        return !findPath(start, goal).isEmpty();
    }

    public List<String> findPath(String start, String goal) {
        if (!vertices.containsKey(start) || !vertices.containsKey(goal)) {
            return Collections.emptyList();
        }

        resetColors();
        Map<String, String> parent = new LinkedHashMap<>();
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> stack = new ArrayDeque<>();

        stack.push(start);
        visited.add(start);
        parent.put(start, null);
        colorVertex(start, Color.GREEN);
        message = "Searching for a path from " + start + " to " + goal;
        visualize();

        while (!stack.isEmpty()) {
            String current = stack.pop();
            colorVertex(current, Color.YELLOW);
            message = "Current vertex: " + current;
            visualize();

            if (current.equals(goal)) {
                List<String> path = reconstructPath(parent, goal);
                highlightPath(path);
                return path;
            }

            List<String> nextVertices = neighbors(current);
            Collections.reverse(nextVertices);
            for (String next : nextVertices) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, current);
                    colorVertex(next, Color.GREEN);
                    colorEdge(current, next, Color.RED);
                    message = "Discovered " + next + " from " + current;
                    visualize();
                    stack.push(next);
                    colorEdge(current, next, Color.GRAY);
                }
            }
            colorVertex(current, Color.ORANGE);
        }

        message = "No path from " + start + " to " + goal;
        visualize();
        return Collections.emptyList();
    }

    protected List<String> reconstructPath(Map<String, String> parent, String goal) {
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
        Step03PathsAndReachability graph = new Step03PathsAndReachability();
        graph.setDelay(700);

        graph.addVertex("U");
        graph.addVertex("V");
        graph.addVertex("W");
        graph.addVertex("X");
        graph.addVertex("Y");
        graph.addVertex("Z");

        graph.addEdge("U", "V");
        graph.addEdge("U", "W");
        graph.addEdge("V", "X");
        graph.addEdge("W", "Y");
        graph.addEdge("X", "Z");
        graph.addEdge("Z", "Y");

        List<String> path = graph.findPath("U", "Y");
        System.out.println("Path from U to Y: " + path);
    }
}
