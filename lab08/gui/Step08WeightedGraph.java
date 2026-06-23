package lab08.gui;

import java.util.Arrays;
import java.util.List;

// Step 08: Weighted graph.
// This class extends the base graph and turns on edge weights.
public class Step08WeightedGraph extends Step01GraphBasics {
    public Step08WeightedGraph() {
        this("Step08 Weighted Graph", false);
    }

    protected Step08WeightedGraph(String title, boolean directed) {
        super(title, directed, true);
    }

    public int pathCost(List<String> path) {
        int total = 0;
        for (int i = 0; i + 1 < path.size(); i++) {
            total += edgeWeight(path.get(i), path.get(i + 1));
        }
        highlightPath(path);
        message = "Path " + path + " has total cost " + total;
        visualize();
        return total;
    }

    public static void main(String[] args) {
        Step08WeightedGraph graph = new Step08WeightedGraph();
        graph.setDelay(700);

        graph.addVertex("A");
        graph.addVertex("E");
        graph.addVertex("D");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "E", 4);
        graph.addEdge("E", "F", 5);
        graph.addEdge("A", "D", 2);
        graph.addEdge("D", "G", 1);
        graph.addEdge("G", "H", 1);
        graph.addEdge("H", "F", 2);

        int firstCost = graph.pathCost(Arrays.asList("A", "E", "F"));
        int secondCost = graph.pathCost(Arrays.asList("A", "D", "G", "H", "F"));

        System.out.println("Cost of A-E-F: " + firstCost);
        System.out.println("Cost of A-D-G-H-F: " + secondCost);
    }
}
