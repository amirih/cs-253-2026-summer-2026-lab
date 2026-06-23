package lab08.text;

import java.util.*;

/**
 * Step 02: Directed graph basics.
 * Concepts: directed edge, origin, destination, in-degree, out-degree.
 */
public class Step02DirectedGraphDegrees {
    static class DirectedGraph {
        private final Map<String, Set<String>> outgoing = new LinkedHashMap<>();
        private final Map<String, Set<String>> incoming = new LinkedHashMap<>();

        public void addVertex(String v) {
            outgoing.putIfAbsent(v, new LinkedHashSet<>());
            incoming.putIfAbsent(v, new LinkedHashSet<>());
        }

        public void addEdge(String origin, String destination) {
            addVertex(origin);
            addVertex(destination);
            if (outgoing.get(origin).add(destination)) {
                incoming.get(destination).add(origin);
            }
        }

        public Set<String> vertices() {
            return Collections.unmodifiableSet(outgoing.keySet());
        }

        public Set<String> outgoingNeighbors(String v) {
            checkVertex(v);
            return Collections.unmodifiableSet(outgoing.get(v));
        }

        public Set<String> incomingNeighbors(String v) {
            checkVertex(v);
            return Collections.unmodifiableSet(incoming.get(v));
        }

        public int outDegree(String v) {
            return outgoingNeighbors(v).size();
        }

        public int inDegree(String v) {
            return incomingNeighbors(v).size();
        }

        public boolean hasEdge(String origin, String destination) {
            return outgoing.containsKey(origin) && outgoing.get(origin).contains(destination);
        }

        private void checkVertex(String v) {
            if (!outgoing.containsKey(v)) {
                throw new IllegalArgumentException("Unknown vertex: " + v);
            }
        }
    }

    public static void main(String[] args) {
        DirectedGraph flights = new DirectedGraph();
        flights.addEdge("LAX", "ORD");
        flights.addEdge("DFW", "ORD");
        flights.addEdge("ORD", "DFW");
        flights.addEdge("JFK", "DFW");
        flights.addEdge("MIA", "DFW");
        flights.addEdge("DFW", "LAX");
        flights.addEdge("JFK", "MIA");
        flights.addEdge("BOS", "JFK");

        for (String airport : flights.vertices()) {
            System.out.printf(
                    "%s: outgoing=%s outDegree=%d, incoming=%s inDegree=%d%n",
                    airport,
                    flights.outgoingNeighbors(airport),
                    flights.outDegree(airport),
                    flights.incomingNeighbors(airport),
                    flights.inDegree(airport));
        }

        System.out.println("Edge LAX -> ORD exists? " + flights.hasEdge("LAX", "ORD"));
        System.out.println("Edge ORD -> LAX exists? " + flights.hasEdge("ORD", "LAX"));
    }
}
