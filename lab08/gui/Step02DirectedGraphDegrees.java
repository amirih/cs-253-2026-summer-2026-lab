package lab08.gui;

import java.util.Map;

// Step 02: Directed graph degrees.
// This class extends Step01GraphBasics and reuses the graph data structure plus visualization.
public class Step02DirectedGraphDegrees extends Step01GraphBasics {
    public Step02DirectedGraphDegrees() {
        this("Step02 Directed Graph Degrees");
    }

    protected Step02DirectedGraphDegrees(String title) {
        super(title, true, false);
    }

    public void printDirectedDegrees() {
        for (Map.Entry<String, Vertex> entry : vertices.entrySet()) {
            String vertex = entry.getKey();
            System.out.println(vertex + ": in-degree=" + inDegree(vertex) + ", out-degree=" + outDegree(vertex));
        }
    }

    public static void main(String[] args) {
        Step02DirectedGraphDegrees graph = new Step02DirectedGraphDegrees();
        graph.setDelay(700);

        graph.addVertex("LAX");
        graph.addVertex("ORD");
        graph.addVertex("DFW");
        graph.addVertex("JFK");
        graph.addVertex("MIA");

        graph.addEdge("LAX", "ORD");
        graph.addEdge("ORD", "DFW");
        graph.addEdge("DFW", "ORD");
        graph.addEdge("JFK", "DFW");
        graph.addEdge("DFW", "MIA");
        graph.addEdge("JFK", "MIA");
        graph.addEdge("MIA", "JFK");

        graph.message = "Directed graph: each edge has an origin and a destination";
        graph.visualize();
        graph.printDirectedDegrees();
    }
}
