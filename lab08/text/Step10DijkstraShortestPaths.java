package lab08.text;

import java.util.*;

/**
 * Step 10: Dijkstra shortest paths.
 * Concepts: nonnegative weights, priority queue, edge relaxation, path
 * reconstruction.
 */
public class Step10DijkstraShortestPaths {
    static class WeightedDigraph {
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

        public void addEdge(String from, String to, int weight) {
            if (weight < 0) {
                throw new IllegalArgumentException("Dijkstra requires nonnegative edge weights.");
            }
            addVertex(from);
            addVertex(to);
            adj.get(from).add(new Edge(to, weight));
        }

        public Set<String> vertices() {
            return adj.keySet();
        }

        public List<Edge> edgesFrom(String v) {
            return adj.getOrDefault(v, List.of());
        }
    }

    static class DijkstraResult {
        private final Map<String, Integer> distance;
        private final Map<String, String> previous;
        private final String source;

        DijkstraResult(String source, Map<String, Integer> distance, Map<String, String> previous) {
            this.source = source;
            this.distance = distance;
            this.previous = previous;
        }

        public int distanceTo(String vertex) {
            return distance.get(vertex);
        }

        public List<String> pathTo(String target) {
            if (!distance.containsKey(target) || distance.get(target) == Integer.MAX_VALUE) {
                return List.of();
            }

            LinkedList<String> path = new LinkedList<>();
            for (String at = target; at != null; at = previous.get(at)) {
                path.addFirst(at);
            }

            if (!path.isEmpty() && path.getFirst().equals(source)) {
                return path;
            }
            return List.of();
        }

        public void printAll() {
            for (String vertex : distance.keySet()) {
                String d = distance.get(vertex) == Integer.MAX_VALUE ? "INF" : String.valueOf(distance.get(vertex));
                System.out.printf("%s: distance=%s path=%s%n", vertex, d, pathTo(vertex));
            }
        }
    }

    static class QueueEntry implements Comparable<QueueEntry> {
        final String vertex;
        final int distance;

        QueueEntry(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(QueueEntry other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static DijkstraResult dijkstra(WeightedDigraph graph, String source) {
        Map<String, Integer> distance = new LinkedHashMap<>();
        Map<String, String> previous = new LinkedHashMap<>();
        PriorityQueue<QueueEntry> pq = new PriorityQueue<>();

        for (String vertex : graph.vertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
        }
        if (!distance.containsKey(source)) {
            throw new IllegalArgumentException("Unknown source: " + source);
        }

        distance.put(source, 0);
        pq.add(new QueueEntry(source, 0));

        while (!pq.isEmpty()) {
            QueueEntry entry = pq.remove();

            // Skip stale queue entries created before a better distance was found.
            if (entry.distance != distance.get(entry.vertex)) {
                continue;
            }

            for (WeightedDigraph.Edge edge : graph.edgesFrom(entry.vertex)) {
                int candidate = entry.distance + edge.weight;
                if (candidate < distance.get(edge.to)) {
                    distance.put(edge.to, candidate);
                    previous.put(edge.to, entry.vertex);
                    pq.add(new QueueEntry(edge.to, candidate));
                }
            }
        }

        return new DijkstraResult(source, distance, previous);
    }

    public static void main(String[] args) {
        WeightedDigraph graph = new WeightedDigraph();
        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "E", 3);
        graph.addEdge("D", "C", 2);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "G", 4);
        graph.addEdge("D", "F", 8);
        graph.addEdge("C", "F", 10);
        graph.addEdge("E", "F", 5);
        graph.addEdge("G", "F", 1);

        DijkstraResult result = dijkstra(graph, "A");
        result.printAll();

        System.out.println("Shortest A to F: " + result.pathTo("F"));
        System.out.println("Cost A to F: " + result.distanceTo("F"));
    }
}
