package lab08.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Step 05: Depth-first search.
// This class extends the base graph and adds recursive DFS.
public class Step05DepthFirstSearch extends Step01GraphBasics {
    public Step05DepthFirstSearch() {
        super("Step05 Depth-First Search", false, false);
    }

    protected Step05DepthFirstSearch(String title) {
        super(title, false, false);
    }

    public List<String> depthFirstTraversal(String start) {
        resetColors();
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(start, null, visited, order);
        message = "DFS order: " + order;
        visualize();
        return order;
    }

    private void dfs(String current, String parent, Set<String> visited, List<String> order) {
        if (!vertices.containsKey(current) || visited.contains(current)) {
            return;
        }

        visited.add(current);
        order.add(current);
        colorVertex(current, Color.YELLOW);
        if (parent != null) {
            colorEdge(parent, current, Color.RED);
        }
        message = "DFS visits " + current;
        visualize();

        for (String next : neighbors(current)) {
            if (!visited.contains(next)) {
                colorVertex(next, Color.GREEN);
                message = "DFS goes deeper from " + current + " to " + next;
                visualize();
                dfs(next, current, visited, order);
            }
        }

        colorVertex(current, Color.ORANGE);
        if (parent != null) {
            colorEdge(parent, current, Color.GRAY);
        }
        message = "DFS backtracks from " + current;
        visualize();
    }

    public static void main(String[] args) {
        Step05DepthFirstSearch graph = new Step05DepthFirstSearch();
        graph.setDelay(700);

        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");

        graph.addEdge("a", "b");
        graph.addEdge("a", "d");
        graph.addEdge("b", "e");
        graph.addEdge("e", "f");
        graph.addEdge("f", "c");
        graph.addEdge("d", "e");

        List<String> order = graph.depthFirstTraversal("a");
        System.out.println("DFS order: " + order);
    }
}
