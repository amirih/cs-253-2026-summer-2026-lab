package lab08.text;

import java.util.*;

/**
 * Step 06: Breadth-first search.
 * Concepts: queue-based traversal, BFS tree, shortest path by number of edges.
 */
public class Step06BreadthFirstSearch {
    static class Graph {
        private final Map<String, List<String>> adj = new LinkedHashMap<>();

        public void addVertex(String v) {
            adj.putIfAbsent(v, new ArrayList<>());
        }

        public void addUndirectedEdge(String u, String v) {
            addVertex(u);
            addVertex(v);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        public List<String> bfsOrder(String start) {
            List<String> order = new ArrayList<>();
            Set<String> visited = new LinkedHashSet<>();
            Queue<String> queue = new ArrayDeque<>();
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.remove();
                order.add(current);

                for (String neighbor : adj.getOrDefault(current, List.of())) {
                    if (visited.add(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
            return order;
        }

        public Optional<List<String>> shortestPathByEdges(String start, String target) {
            if (!adj.containsKey(start) || !adj.containsKey(target)) {
                return Optional.empty();
            }

            Map<String, String> parent = new LinkedHashMap<>();
            Queue<String> queue = new ArrayDeque<>();
            Set<String> visited = new LinkedHashSet<>();

            queue.add(start);
            visited.add(start);
            parent.put(start, null);

            while (!queue.isEmpty()) {
                String current = queue.remove();
                if (current.equals(target)) {
                    return Optional.of(reconstructPath(parent, target));
                }

                for (String neighbor : adj.getOrDefault(current, List.of())) {
                    if (visited.add(neighbor)) {
                        parent.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
            return Optional.empty();
        }

        private List<String> reconstructPath(Map<String, String> parent, String target) {
            LinkedList<String> path = new LinkedList<>();
            for (String at = target; at != null; at = parent.get(at)) {
                path.addFirst(at);
            }
            return path;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addUndirectedEdge("a", "b");
        graph.addUndirectedEdge("a", "d");
        graph.addUndirectedEdge("a", "e");
        graph.addUndirectedEdge("b", "c");
        graph.addUndirectedEdge("e", "f");
        graph.addUndirectedEdge("d", "g");
        graph.addUndirectedEdge("g", "h");
        graph.addUndirectedEdge("d", "h");
        graph.addUndirectedEdge("h", "f");

        System.out.println("BFS order from a: " + graph.bfsOrder("a"));
        Optional<List<String>> path = graph.shortestPathByEdges("a", "h");
        System.out.println("Shortest path by edges a to h: " + path.orElse(List.of()));
    }
}
