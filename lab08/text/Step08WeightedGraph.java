package lab08.text;

import java.util.*;

/**
 * Step 08: Weighted graphs.
 * Concepts: edge weights, path cost, why BFS ignores edge weights.
 */
public class Step08WeightedGraph {
    static class WeightedGraph {
        static class Edge {
            final String to;
            final int weight;

            Edge(String to, int weight) {
                this.to = to;
                this.weight = weight;
            }

            @Override
            public String toString() {
                return to + "(" + weight + ")";
            }
        }

        private final Map<String, List<Edge>> adj = new LinkedHashMap<>();

        public void addVertex(String v) {
            adj.putIfAbsent(v, new ArrayList<>());
        }

        public void addUndirectedEdge(String u, String v, int weight) {
            if (weight < 0) {
                throw new IllegalArgumentException("This lab uses nonnegative weights.");
            }
            addVertex(u);
            addVertex(v);
            adj.get(u).add(new Edge(v, weight));
            adj.get(v).add(new Edge(u, weight));
        }

        public int pathCost(List<String> path) {
            int total = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                String from = path.get(i);
                String to = path.get(i + 1);
                Optional<Edge> edge = adj.getOrDefault(from, List.of())
                        .stream()
                        .filter(e -> e.to.equals(to))
                        .findFirst();
                if (edge.isEmpty()) {
                    throw new IllegalArgumentException("No edge from " + from + " to " + to);
                }
                total += edge.get().weight;
            }
            return total;
        }

        public Optional<List<String>> bfsShortestPathByEdges(String start, String target) {
            Queue<String> queue = new ArrayDeque<>();
            Map<String, String> parent = new LinkedHashMap<>();
            Set<String> visited = new LinkedHashSet<>();

            queue.add(start);
            visited.add(start);
            parent.put(start, null);

            while (!queue.isEmpty()) {
                String current = queue.remove();
                if (current.equals(target)) {
                    return Optional.of(reconstruct(parent, target));
                }
                for (Edge edge : adj.getOrDefault(current, List.of())) {
                    if (visited.add(edge.to)) {
                        parent.put(edge.to, current);
                        queue.add(edge.to);
                    }
                }
            }
            return Optional.empty();
        }

        private List<String> reconstruct(Map<String, String> parent, String target) {
            LinkedList<String> path = new LinkedList<>();
            for (String at = target; at != null; at = parent.get(at)) {
                path.addFirst(at);
            }
            return path;
        }

        public void print() {
            for (String v : adj.keySet()) {
                System.out.println(v + " -> " + adj.get(v));
            }
        }
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();

        // Neighbor order makes BFS find A-E-F first, even though it is more expensive.
        graph.addUndirectedEdge("A", "E", 4);
        graph.addUndirectedEdge("E", "F", 5);
        graph.addUndirectedEdge("A", "D", 2);
        graph.addUndirectedEdge("D", "G", 1);
        graph.addUndirectedEdge("G", "H", 2);
        graph.addUndirectedEdge("H", "F", 1);
        graph.addUndirectedEdge("A", "B", 4);
        graph.addUndirectedEdge("B", "C", 7);
        graph.addUndirectedEdge("C", "F", 2);

        graph.print();

        List<String> bfsPath = graph.bfsShortestPathByEdges("A", "F").orElse(List.of());
        List<String> cheaperPath = List.of("A", "D", "G", "H", "F");

        System.out.println("BFS shortest by edges A to F: " + bfsPath);
        System.out.println("BFS path cost: " + graph.pathCost(bfsPath));
        System.out.println("Cheaper weighted path: " + cheaperPath);
        System.out.println("Cheaper path cost: " + graph.pathCost(cheaperPath));
    }
}
