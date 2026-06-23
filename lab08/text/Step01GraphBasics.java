package lab08.text;

import java.util.*;

/**
 * Step 01: Graph basics with an undirected adjacency-list graph.
 * Concepts: vertices, edges, adjacency, degree, Graph G = (V, E).
 */
public class Step01GraphBasics {
    static class UndirectedGraph {
        private final Map<String, Set<String>> adj = new LinkedHashMap<>();

        public void addVertex(String v) {
            adj.putIfAbsent(v, new LinkedHashSet<>());
        }

        public void addEdge(String u, String v) {
            addVertex(u);
            addVertex(v);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        public Set<String> vertices() {
            return Collections.unmodifiableSet(adj.keySet());
        }

        public boolean hasEdge(String u, String v) {
            return adj.containsKey(u) && adj.get(u).contains(v);
        }

        public Set<String> neighbors(String v) {
            if (!adj.containsKey(v)) {
                throw new IllegalArgumentException("Unknown vertex: " + v);
            }
            return Collections.unmodifiableSet(adj.get(v));
        }

        public int degree(String v) {
            return neighbors(v).size();
        }

        public int edgeCount() {
            int totalDegrees = 0;
            for (String v : adj.keySet()) {
                totalDegrees += degree(v);
            }
            return totalDegrees / 2;
        }

        public void printGraph() {
            System.out.println("Vertices: " + vertices());
            System.out.println("Edges: " + edgeCount());
            for (String v : vertices()) {
                System.out.println(v + " -> " + neighbors(v) + " degree=" + degree(v));
            }
        }
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();

        // Same small idea as the slide example: V = {a, b, c, d}
        // E = {(a,c), (b,c), (b,d), (c,d)}
        graph.addEdge("a", "c");
        graph.addEdge("b", "c");
        graph.addEdge("b", "d");
        graph.addEdge("c", "d");

        graph.printGraph();
        System.out.println("Is b adjacent to d? " + graph.hasEdge("b", "d"));
    }
}
