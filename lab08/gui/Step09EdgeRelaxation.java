package lab08.gui;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

// Step 09: Edge relaxation.
// This class extends the weighted graph class and adds distance and previous tables.
public class Step09EdgeRelaxation extends Step08WeightedGraph {
    protected static final int INF = 1_000_000_000;
    protected final Map<String, Integer> distance;
    protected final Map<String, String> previous;

    public Step09EdgeRelaxation() {
        this("Step09 Edge Relaxation");
    }

    protected Step09EdgeRelaxation(String title) {
        super(title, true);
        this.distance = new LinkedHashMap<>();
        this.previous = new LinkedHashMap<>();
    }

    protected void initializeDistances(String source) {
        distance.clear();
        previous.clear();
        for (String vertex : vertices.keySet()) {
            distance.put(vertex, INF);
            previous.put(vertex, null);
        }
        distance.put(source, 0);
    }

    public boolean relax(String from, String to) {
        int fromDistance = distance.getOrDefault(from, INF);
        int toDistance = distance.getOrDefault(to, INF);
        int weight = edgeWeight(from, to);

        colorVertex(from, Color.YELLOW);
        colorVertex(to, Color.GREEN);
        colorEdge(from, to, Color.RED);
        message = "Relax edge " + from + " -> " + to + " with weight " + weight;
        visualize();

        if (fromDistance != INF && fromDistance + weight < toDistance) {
            distance.put(to, fromDistance + weight);
            previous.put(to, from);
            message = "Updated D[" + to + "] to " + distance.get(to) + " using " + from;
            visualize();
            return true;
        }

        message = "No update for " + to + "; current distance is better";
        visualize();
        return false;
    }

    public void printDistanceTable() {
        System.out.println("Distance table:");
        for (String vertex : vertices.keySet()) {
            String value = distance.get(vertex) == INF ? "infinity" : String.valueOf(distance.get(vertex));
            System.out.println("  " + vertex + ": distance=" + value + ", previous=" + previous.get(vertex));
        }
    }

    public static void main(String[] args) {
        Step09EdgeRelaxation graph = new Step09EdgeRelaxation();
        graph.setDelay(700);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("D");
        graph.addVertex("C");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "C", 4);
        graph.addEdge("D", "C", 2);

        graph.initializeDistances("A");
        graph.message = "Initial distances: D[A] = 0, all others = infinity";
        graph.visualize();

        graph.relax("A", "B");
        graph.relax("A", "D");
        graph.relax("B", "C");
        graph.relax("D", "C");
        graph.printDistanceTable();
    }
}
