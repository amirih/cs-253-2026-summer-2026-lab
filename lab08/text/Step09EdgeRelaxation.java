package lab08.text;

import java.util.*;

/**
 * Step 09: Edge relaxation.
 * Concepts: distance table, previous vertex table, improving a path through an
 * edge.
 */
public class Step09EdgeRelaxation {
    static class Edge {
        final String from;
        final String to;
        final int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " -> " + to + " (" + weight + ")";
        }
    }

    static class RelaxationTable {
        static final int INF = 1_000_000;
        final Map<String, Integer> distance = new LinkedHashMap<>();
        final Map<String, String> previous = new LinkedHashMap<>();

        public RelaxationTable(Collection<String> vertices, String source) {
            for (String v : vertices) {
                distance.put(v, INF);
                previous.put(v, null);
            }
            distance.put(source, 0);
        }

        public boolean relax(Edge edge) {
            int fromDistance = distance.get(edge.from);
            int toDistance = distance.get(edge.to);

            if (fromDistance != INF && fromDistance + edge.weight < toDistance) {
                distance.put(edge.to, fromDistance + edge.weight);
                previous.put(edge.to, edge.from);
                return true;
            }
            return false;
        }

        public void print() {
            System.out.println("Distance table:");
            for (String v : distance.keySet()) {
                String d = distance.get(v) == INF ? "INF" : String.valueOf(distance.get(v));
                System.out.printf("  %s: distance=%s previous=%s%n", v, d, previous.get(v));
            }
        }
    }

    public static void main(String[] args) {
        List<String> vertices = List.of("A", "B", "C", "D", "E", "F", "G");
        List<Edge> edges = List.of(
                new Edge("A", "B", 2),
                new Edge("A", "D", 1),
                new Edge("D", "C", 2),
                new Edge("D", "E", 2),
                new Edge("D", "G", 4),
                new Edge("B", "C", 1),
                new Edge("E", "F", 5),
                new Edge("G", "F", 1));

        RelaxationTable table = new RelaxationTable(vertices, "A");
        table.print();

        System.out.println("\nRelaxing each edge once in listed order:");
        for (Edge edge : edges) {
            boolean changed = table.relax(edge);
            System.out.println(edge + " changed? " + changed);
        }

        System.out.println();
        table.print();
    }
}
