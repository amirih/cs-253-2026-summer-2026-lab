package lab08.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Step 10: Dijkstra shortest paths.
// This class extends edge relaxation and adds a priority queue.
public class Step10DijkstraShortestPaths extends Step09EdgeRelaxation {
    static class QueueEntry implements Comparable<QueueEntry> {
        String vertex;
        int cost;

        QueueEntry(String vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(QueueEntry other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public Step10DijkstraShortestPaths() {
        super("Step10 Dijkstra Shortest Paths");
    }

    public Map<String, Integer> dijkstra(String source) {
        initializeDistances(source);
        resetColors();

        PriorityQueue<QueueEntry> queue = new PriorityQueue<>();
        Set<String> finalized = new HashSet<>();
        queue.add(new QueueEntry(source, 0));

        colorVertex(source, Color.GREEN);
        message = "Dijkstra starts at source " + source;
        visualize();

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.remove();
            String current = entry.vertex;

            if (finalized.contains(current)) {
                continue;
            }

            finalized.add(current);
            colorVertex(current, Color.YELLOW);
            message = "Remove minimum vertex " + current + " with distance " + distance.get(current);
            visualize();

            for (String next : neighbors(current)) {
                if (finalized.contains(next)) {
                    continue;
                }

                int oldDistance = distance.get(next);
                boolean changed = relax(current, next);
                if (changed) {
                    queue.add(new QueueEntry(next, distance.get(next)));
                    message = "Decrease key for " + next + " from " + formatDistance(oldDistance) + " to "
                            + distance.get(next);
                    visualize();
                }
                colorEdge(current, next, Color.GRAY);
            }

            colorVertex(current, Color.LIGHT_GRAY);
        }

        message = "Dijkstra finished. Shortest distances are in the table.";
        visualize();
        return distance;
    }

    public List<String> shortestPathTo(String target) {
        if (!previous.containsKey(target) || distance.get(target) == INF) {
            return Collections.emptyList();
        }

        List<String> path = new ArrayList<>();
        String current = target;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);
        highlightPath(path);
        return path;
    }

    private String formatDistance(int value) {
        return value == INF ? "infinity" : String.valueOf(value);
    }

    public static void main(String[] args) {
        Step10DijkstraShortestPaths graph = new Step10DijkstraShortestPaths();
        graph.setDelay(700);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("D");
        graph.addVertex("C");
        graph.addVertex("E");
        graph.addVertex("G");
        graph.addVertex("F");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "E", 1);
        graph.addEdge("D", "B", 4);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "G", 4);
        graph.addEdge("C", "F", 5);
        graph.addEdge("E", "F", 5);
        graph.addEdge("G", "F", 1);

        graph.dijkstra("A");
        graph.printDistanceTable();
        List<String> path = graph.shortestPathTo("F");
        System.out.println("Shortest path from A to F: " + path);
        System.out.println("Cost: " + graph.distance.get("F"));
    }
}
