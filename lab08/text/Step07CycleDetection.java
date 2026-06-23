package lab08.text;

import java.util.*;

/**
 * Step 07: Cycles, acyclic graphs, trees, and forests.
 * Concepts: cycle detection in undirected and directed graphs.
 */
public class Step07CycleDetection {
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

        public boolean hasCycle() {
            Set<String> visited = new HashSet<>();
            for (String vertex : adj.keySet()) {
                if (!visited.contains(vertex) && hasCycleFrom(vertex, null, visited)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasCycleFrom(String current, String parent, Set<String> visited) {
            visited.add(current);
            for (String neighbor : adj.get(current)) {
                if (!visited.contains(neighbor)) {
                    if (hasCycleFrom(neighbor, current, visited)) {
                        return true;
                    }
                } else if (!neighbor.equals(parent)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isTree() {
            return !adj.isEmpty() && !hasCycle() && connectedComponentCount() == 1;
        }

        public boolean isForest() {
            return !hasCycle();
        }

        private int connectedComponentCount() {
            Set<String> visited = new HashSet<>();
            int count = 0;
            for (String vertex : adj.keySet()) {
                if (visited.add(vertex)) {
                    count++;
                    Deque<String> stack = new ArrayDeque<>();
                    stack.push(vertex);
                    while (!stack.isEmpty()) {
                        String current = stack.pop();
                        for (String neighbor : adj.get(current)) {
                            if (visited.add(neighbor)) {
                                stack.push(neighbor);
                            }
                        }
                    }
                }
            }
            return count;
        }
    }

    static class DirectedGraph {
        enum Color {
            WHITE, GRAY, BLACK
        }

        private final Map<String, Set<String>> adj = new LinkedHashMap<>();

        public void addVertex(String v) {
            adj.putIfAbsent(v, new LinkedHashSet<>());
        }

        public void addEdge(String from, String to) {
            addVertex(from);
            addVertex(to);
            adj.get(from).add(to);
        }

        public boolean hasCycle() {
            Map<String, Color> color = new HashMap<>();
            for (String vertex : adj.keySet()) {
                color.put(vertex, Color.WHITE);
            }
            for (String vertex : adj.keySet()) {
                if (color.get(vertex) == Color.WHITE && dfs(vertex, color)) {
                    return true;
                }
            }
            return false;
        }

        private boolean dfs(String current, Map<String, Color> color) {
            color.put(current, Color.GRAY);
            for (String neighbor : adj.get(current)) {
                if (color.get(neighbor) == Color.GRAY) {
                    return true;
                }
                if (color.get(neighbor) == Color.WHITE && dfs(neighbor, color)) {
                    return true;
                }
            }
            color.put(current, Color.BLACK);
            return false;
        }
    }

    public static void main(String[] args) {
        UndirectedGraph tree = new UndirectedGraph();
        tree.addEdge("A", "B");
        tree.addEdge("A", "C");
        tree.addEdge("C", "D");
        System.out.println("Undirected graph is tree? " + tree.isTree());
        System.out.println("Undirected graph is forest? " + tree.isForest());

        UndirectedGraph cyclic = new UndirectedGraph();
        cyclic.addEdge("A", "B");
        cyclic.addEdge("B", "C");
        cyclic.addEdge("C", "A");
        System.out.println("Cyclic undirected graph has cycle? " + cyclic.hasCycle());

        DirectedGraph prerequisites = new DirectedGraph();
        prerequisites.addEdge("CS120", "CS140");
        prerequisites.addEdge("CS140", "CS253");
        prerequisites.addEdge("CS253", "CS341");
        System.out.println("Prerequisite graph has cycle? " + prerequisites.hasCycle());

        prerequisites.addEdge("CS341", "CS140");
        System.out.println("After adding bad edge, has cycle? " + prerequisites.hasCycle());
    }
}
