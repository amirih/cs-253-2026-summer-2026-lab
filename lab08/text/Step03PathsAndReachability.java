package lab08.text;

import java.util.*;

/**
 * Step 03: Paths and reachability.
 * Concepts: path, path length, neighbor, reachable vertex.
 */
public class Step03PathsAndReachability {
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

        public List<String> neighbors(String v) {
            return adj.getOrDefault(v, List.of());
        }

        public boolean isReachable(String start, String target) {
            return findAnyPath(start, target).isPresent();
        }

        public Optional<List<String>> findAnyPath(String start, String target) {
            if (!adj.containsKey(start) || !adj.containsKey(target)) {
                return Optional.empty();
            }
            Set<String> visited = new LinkedHashSet<>();
            List<String> path = new ArrayList<>();
            if (dfsPath(start, target, visited, path)) {
                return Optional.of(path);
            }
            return Optional.empty();
        }

        private boolean dfsPath(String current, String target, Set<String> visited, List<String> path) {
            visited.add(current);
            path.add(current);

            if (current.equals(target)) {
                return true;
            }

            for (String neighbor : neighbors(current)) {
                if (!visited.contains(neighbor) && dfsPath(neighbor, target, visited, path)) {
                    return true;
                }
            }

            path.remove(path.size() - 1);
            return false;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addUndirectedEdge("U", "V");
        graph.addUndirectedEdge("U", "W");
        graph.addUndirectedEdge("V", "X");
        graph.addUndirectedEdge("W", "Y");
        graph.addUndirectedEdge("X", "Y");
        graph.addUndirectedEdge("X", "Z");

        Optional<List<String>> path = graph.findAnyPath("U", "Y");
        System.out.println("Reachable U to Y? " + graph.isReachable("U", "Y"));
        System.out.println("One path U to Y: " + path.orElse(List.of()));
        path.ifPresent(p -> System.out.println("Path length by edges: " + (p.size() - 1)));
    }
}
