package lab08.text;

import java.util.*;

/**
 * Step 04: Connectedness and connected components.
 * Concepts: connected graph, maximal connected subgraphs, components.
 */
public class Step04ConnectedComponents {
    static class Graph {
        private final Map<String, Set<String>> adj = new LinkedHashMap<>();

        public void addVertex(String v) {
            adj.putIfAbsent(v, new LinkedHashSet<>());
        }

        public void addUndirectedEdge(String u, String v) {
            addVertex(u);
            addVertex(v);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        public List<Set<String>> connectedComponents() {
            Set<String> visited = new LinkedHashSet<>();
            List<Set<String>> components = new ArrayList<>();

            for (String vertex : adj.keySet()) {
                if (!visited.contains(vertex)) {
                    Set<String> component = new LinkedHashSet<>();
                    explore(vertex, visited, component);
                    components.add(component);
                }
            }
            return components;
        }

        public boolean isConnected() {
            if (adj.isEmpty()) {
                return true;
            }
            return connectedComponents().size() == 1;
        }

        private void explore(String start, Set<String> visited, Set<String> component) {
            Deque<String> stack = new ArrayDeque<>();
            stack.push(start);
            visited.add(start);

            while (!stack.isEmpty()) {
                String current = stack.pop();
                component.add(current);

                for (String neighbor : adj.get(current)) {
                    if (visited.add(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addUndirectedEdge("a", "b");
        graph.addUndirectedEdge("b", "c");
        graph.addUndirectedEdge("d", "e");
        graph.addVertex("f");

        System.out.println("Connected? " + graph.isConnected());
        System.out.println("Components:");
        int number = 1;
        for (Set<String> component : graph.connectedComponents()) {
            System.out.println("  component " + number++ + ": " + component);
        }
    }
}
