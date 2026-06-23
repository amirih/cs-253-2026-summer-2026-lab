package lab08.text;

import java.util.*;

/**
 * Step 05: Depth-first search.
 * Concepts: recursive DFS, stack-based DFS, DFS tree parent map.
 */
public class Step05DepthFirstSearch {
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

        public List<String> recursiveDfsOrder(String start) {
            List<String> order = new ArrayList<>();
            Set<String> visited = new LinkedHashSet<>();
            recursiveDfs(start, visited, order);
            return order;
        }

        private void recursiveDfs(String current, Set<String> visited, List<String> order) {
            if (!visited.add(current)) {
                return;
            }
            order.add(current);
            for (String neighbor : adj.getOrDefault(current, List.of())) {
                recursiveDfs(neighbor, visited, order);
            }
        }

        public List<String> stackDfsOrder(String start) {
            List<String> order = new ArrayList<>();
            Set<String> visited = new LinkedHashSet<>();
            Deque<String> stack = new ArrayDeque<>();
            stack.push(start);

            while (!stack.isEmpty()) {
                String current = stack.pop();
                if (!visited.add(current)) {
                    continue;
                }
                order.add(current);

                List<String> neighbors = new ArrayList<>(adj.getOrDefault(current, List.of()));
                Collections.reverse(neighbors);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
            return order;
        }

        public Map<String, String> dfsParentTree(String start) {
            Map<String, String> parent = new LinkedHashMap<>();
            Set<String> visited = new LinkedHashSet<>();
            dfsParent(start, null, visited, parent);
            return parent;
        }

        private void dfsParent(String current, String from, Set<String> visited, Map<String, String> parent) {
            visited.add(current);
            parent.put(current, from);

            for (String neighbor : adj.getOrDefault(current, List.of())) {
                if (!visited.contains(neighbor)) {
                    dfsParent(neighbor, current, visited, parent);
                }
            }
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
        graph.addUndirectedEdge("h", "f");

        System.out.println("Recursive DFS from a: " + graph.recursiveDfsOrder("a"));
        System.out.println("Stack DFS from a:     " + graph.stackDfsOrder("a"));
        System.out.println("DFS parent tree:      " + graph.dfsParentTree("a"));
    }
}
