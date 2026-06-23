package lab08.gui;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Step 04: Connected components in an undirected graph.
// This class extends the base graph data structure from Step01.
public class Step04ConnectedComponents extends Step01GraphBasics {
    public Step04ConnectedComponents() {
        super("Step04 Connected Components", false, false);
    }

    public List<List<String>> connectedComponents() {
        resetColors();
        Set<String> visited = new HashSet<>();
        List<List<String>> components = new ArrayList<>();
        Color[] colors = { Color.GREEN, Color.CYAN, Color.PINK, Color.YELLOW, Color.LIGHT_GRAY };
        int colorIndex = 0;

        for (String start : vertices.keySet()) {
            if (visited.contains(start)) {
                continue;
            }

            List<String> component = new ArrayList<>();
            Color componentColor = colors[colorIndex % colors.length];
            colorIndex++;

            ArrayDeque<String> queue = new ArrayDeque<>();
            queue.add(start);
            visited.add(start);
            colorVertex(start, componentColor);
            message = "Starting a new connected component at " + start;
            visualize();

            while (!queue.isEmpty()) {
                String current = queue.remove();
                component.add(current);

                for (String next : neighbors(current)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                        colorVertex(next, componentColor);
                        colorEdge(current, next, Color.RED);
                        message = "Adding " + next + " to component " + components.size();
                        visualize();
                        colorEdge(current, next, Color.GRAY);
                    }
                }
            }

            components.add(component);
            message = "Completed component: " + component;
            visualize();
        }

        return components;
    }

    public static void main(String[] args) {
        Step04ConnectedComponents graph = new Step04ConnectedComponents();
        graph.setDelay(700);

        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");

        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("b", "c");
        graph.addEdge("d", "e");
        graph.addEdge("d", "f");

        List<List<String>> components = graph.connectedComponents();
        System.out.println("Connected components: " + components);
    }
}
