package lab08.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Step 01: Base graph data structure and automatic visualization.
// Other lab files extend this class instead of rewriting graph methods.
public class Step01GraphBasics {
    static class Vertex {
        String name;
        int x;
        int y;
        Color color;

        Vertex(String name) {
            this.name = name;
            this.color = Color.ORANGE;
        }
    }

    static class Edge {
        String from;
        String to;
        int weight;
        boolean directed;
        Color color;

        Edge(String from, String to, int weight, boolean directed) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.directed = directed;
            this.color = Color.GRAY;
        }
    }

    protected final Map<String, Vertex> vertices;
    protected final List<Edge> edges;
    protected boolean directed;
    protected boolean weighted;
    protected int delay;
    protected JFrame frame;
    protected GraphPanel panel;
    protected String message;

    public Step01GraphBasics() {
        this("Step01 Graph Basics", false, false);
    }

    protected Step01GraphBasics(String title, boolean directed, boolean weighted) {
        this.vertices = new LinkedHashMap<>();
        this.edges = new ArrayList<>();
        this.directed = directed;
        this.weighted = weighted;
        this.delay = 700;
        this.message = "A graph G = (V, E) with " + this.vertices.size() + " vertices and " + this.edges.size()
                + " edges.";

        if (!GraphicsEnvironment.isHeadless()) {
            this.frame = getVisualizationFrame(title);
            this.panel = new GraphPanel(this);
            this.frame.add(this.panel);
        }
    }

    public void addVertex(String name) {
        if (!vertices.containsKey(name)) {
            vertices.put(name, new Vertex(name));
            visualize();
        }
    }

    public void addVertices(String... names) {
        for (String name : names) {
            addVertex(name);
        }
    }

    public void addEdge(String from, String to) {
        addEdge(from, to, 1);
    }

    public void addEdge(String from, String to, int weight) {
        if (!vertices.containsKey(from)) {
            addVertex(from);
        }
        if (!vertices.containsKey(to)) {
            addVertex(to);
        }

        edges.add(new Edge(from, to, weight, directed));
        visualize();
    }

    public int vertexCount() {
        return vertices.size();
    }

    public int edgeCount() {
        return edges.size();
    }

    public boolean hasEdge(String from, String to) {
        return findEdge(from, to) != null;
    }

    public int degree(String vertex) {
        if (directed) {
            return inDegree(vertex) + outDegree(vertex);
        }
        return neighbors(vertex).size();
    }

    public int inDegree(String vertex) {
        int count = 0;
        for (Edge edge : edges) {
            if (edge.to.equals(vertex)) {
                count++;
            }
            if (!edge.directed && edge.from.equals(vertex)) {
                count++;
            }
        }
        return count;
    }

    public int outDegree(String vertex) {
        int count = 0;
        for (Edge edge : edges) {
            if (edge.from.equals(vertex)) {
                count++;
            }
            if (!edge.directed && edge.to.equals(vertex)) {
                count++;
            }
        }
        return count;
    }

    public List<String> neighbors(String vertex) {
        List<String> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.from.equals(vertex)) {
                result.add(edge.to);
            } else if (!edge.directed && edge.to.equals(vertex)) {
                result.add(edge.from);
            }
        }
        return result;
    }

    protected Edge findEdge(String from, String to) {
        for (Edge edge : edges) {
            if (edge.from.equals(from) && edge.to.equals(to)) {
                return edge;
            }
            if (!edge.directed && edge.from.equals(to) && edge.to.equals(from)) {
                return edge;
            }
        }
        return null;
    }

    protected int edgeWeight(String from, String to) {
        Edge edge = findEdge(from, to);
        if (edge == null) {
            throw new IllegalArgumentException("No edge from " + from + " to " + to);
        }
        return edge.weight;
    }

    protected void colorVertex(String name, Color color) {
        Vertex vertex = vertices.get(name);
        if (vertex != null) {
            vertex.color = color;
        }
    }

    protected void colorEdge(String from, String to, Color color) {
        Edge edge = findEdge(from, to);
        if (edge != null) {
            edge.color = color;
        }
    }

    protected void resetColors() {
        for (Vertex vertex : vertices.values()) {
            vertex.color = Color.ORANGE;
        }
        for (Edge edge : edges) {
            edge.color = Color.GRAY;
        }
    }

    protected void highlightPath(List<String> path) {
        resetColors();
        for (String vertex : path) {
            colorVertex(vertex, Color.GREEN);
        }
        for (int i = 0; i + 1 < path.size(); i++) {
            colorEdge(path.get(i), path.get(i + 1), Color.RED);
        }
        this.message = "Highlighted path: " + path;
        visualize();
    }

    public void printGraph() {
        System.out.println("Vertices: " + vertices.keySet());
        System.out.println("Edges:");
        for (Edge edge : edges) {
            String arrow = edge.directed ? " -> " : " -- ";
            String weightText = weighted ? "  weight=" + edge.weight : "";
            System.out.println("  " + edge.from + arrow + edge.to + weightText);
        }
    }

    public void visualize() {
        if (panel == null) {
            return;
        }

        Runnable draw = () -> {
            frame.setVisible(true);
            panel.repaint();
        };

        try {
            if (SwingUtilities.isEventDispatchThread()) {
                draw.run();
            } else {
                SwingUtilities.invokeAndWait(draw);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

        pause(delay);
    }

    protected JFrame getVisualizationFrame(String title) {
        JFrame newFrame = new JFrame(title);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(1600, 900);
        newFrame.setLocationRelativeTo(null);
        return newFrame;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    protected void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void closeVisualization() {
        if (frame != null) {
            frame.dispose();
        }
    }

    static class GraphPanel extends JPanel {
        private static final int NODE_RADIUS = 34;
        private final Step01GraphBasics graph;

        GraphPanel(Step01GraphBasics graph) {
            this.graph = graph;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            layoutVerticesAutomatically();
            drawMessage(g2);
            for (Edge edge : graph.edges) {
                drawEdge(g2, edge);
            }
            for (Vertex vertex : graph.vertices.values()) {
                drawVertex(g2, vertex);
            }
        }

        private void layoutVerticesAutomatically() {
            int count = graph.vertices.size();
            if (count == 0) {
                return;
            }

            int width = Math.max(getWidth(), 1200);
            int height = Math.max(getHeight(), 800);
            int centerX = width / 2;
            int centerY = height / 2 + 30;

            if (count == 1) {
                Vertex only = graph.vertices.values().iterator().next();
                only.x = centerX;
                only.y = centerY;
                return;
            }

            List<String> order = new ArrayList<>(graph.vertices.keySet());
            order = improveCircularOrder(order);

            int horizontalRoom = width - 180;
            int verticalRoom = height - 220;
            int radius = Math.max(160, Math.min(horizontalRoom, verticalRoom) / 2);

            for (int index = 0; index < order.size(); index++) {
                Vertex vertex = graph.vertices.get(order.get(index));
                double angle = -Math.PI / 2 + (2.0 * Math.PI * index / count);
                vertex.x = centerX + (int) (radius * Math.cos(angle));
                vertex.y = centerY + (int) (radius * Math.sin(angle));
            }
        }

        private List<String> improveCircularOrder(List<String> order) {
            if (order.size() <= 3 || graph.edges.size() <= 1) {
                return order;
            }

            long bestScore = layoutScore(order);
            boolean improved = true;
            int passes = 0;
            int maxPasses = 8;

            while (improved && passes < maxPasses) {
                improved = false;
                passes++;

                for (int i = 0; i < order.size(); i++) {
                    for (int j = i + 1; j < order.size(); j++) {
                        Collections.swap(order, i, j);
                        long score = layoutScore(order);

                        if (score < bestScore) {
                            bestScore = score;
                            improved = true;
                        } else {
                            Collections.swap(order, i, j);
                        }
                    }
                }
            }

            return order;
        }

        private long layoutScore(List<String> order) {
            long crossings = countEdgeCrossings(order);
            long edgeLengthPenalty = edgeLengthPenalty(order);
            return crossings * 1_000_000L + edgeLengthPenalty;
        }

        private long edgeLengthPenalty(List<String> order) {
            Map<String, Integer> index = buildIndexMap(order);
            int n = order.size();
            long penalty = 0;

            for (Edge edge : graph.edges) {
                Integer a = index.get(edge.from);
                Integer b = index.get(edge.to);
                if (a == null || b == null || a.equals(b)) {
                    continue;
                }

                int difference = Math.abs(a - b);
                int circularDistance = Math.min(difference, n - difference);
                penalty += circularDistance * circularDistance;
            }

            return penalty;
        }

        private long countEdgeCrossings(List<String> order) {
            Map<String, Integer> index = buildIndexMap(order);
            int n = order.size();
            long crossings = 0;

            for (int i = 0; i < graph.edges.size(); i++) {
                Edge first = graph.edges.get(i);
                Integer a = index.get(first.from);
                Integer b = index.get(first.to);

                if (a == null || b == null || a.equals(b)) {
                    continue;
                }

                for (int j = i + 1; j < graph.edges.size(); j++) {
                    Edge second = graph.edges.get(j);

                    if (shareEndpoint(first, second)) {
                        continue;
                    }

                    Integer c = index.get(second.from);
                    Integer d = index.get(second.to);

                    if (c == null || d == null || c.equals(d)) {
                        continue;
                    }

                    if (circularEdgesCross(a, b, c, d, n)) {
                        crossings++;
                    }
                }
            }

            return crossings;
        }

        private Map<String, Integer> buildIndexMap(List<String> order) {
            Map<String, Integer> index = new LinkedHashMap<>();
            for (int i = 0; i < order.size(); i++) {
                index.put(order.get(i), i);
            }
            return index;
        }

        private boolean shareEndpoint(Edge first, Edge second) {
            return first.from.equals(second.from)
                    || first.from.equals(second.to)
                    || first.to.equals(second.from)
                    || first.to.equals(second.to);
        }

        private boolean circularEdgesCross(int a, int b, int c, int d, int n) {
            boolean cBetweenAB = isBetweenClockwise(a, b, c, n);
            boolean dBetweenAB = isBetweenClockwise(a, b, d, n);
            boolean aBetweenCD = isBetweenClockwise(c, d, a, n);
            boolean bBetweenCD = isBetweenClockwise(c, d, b, n);

            return cBetweenAB != dBetweenAB && aBetweenCD != bBetweenCD;
        }

        private boolean isBetweenClockwise(int start, int end, int value, int n) {
            if (start < end) {
                return start < value && value < end;
            }
            return value > start || value < end;
        }

        private void drawMessage(Graphics2D g2) {
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.PLAIN, 16));
            g2.drawString(graph.message, 30, 35);
            g2.drawString("Vertices: " + graph.vertexCount() + ", Edges: " + graph.edgeCount(), 30, 60);
        }

        private void drawEdge(Graphics2D g2, Edge edge) {
            Vertex from = graph.vertices.get(edge.from);
            Vertex to = graph.vertices.get(edge.to);
            if (from == null || to == null) {
                return;
            }

            double dx = to.x - from.x;
            double dy = to.y - from.y;
            double length = Math.sqrt(dx * dx + dy * dy);
            if (length == 0) {
                return;
            }

            double ux = dx / length;
            double uy = dy / length;

            int startX = from.x + (int) (ux * NODE_RADIUS);
            int startY = from.y + (int) (uy * NODE_RADIUS);
            int endX = to.x - (int) (ux * NODE_RADIUS);
            int endY = to.y - (int) (uy * NODE_RADIUS);

            g2.setColor(edge.color);
            g2.setStroke(new BasicStroke(edge.color == Color.RED ? 4 : 2));
            g2.drawLine(startX, startY, endX, endY);

            if (edge.directed) {
                drawArrowHead(g2, startX, startY, endX, endY);
            }

            if (graph.weighted) {
                int labelX = (startX + endX) / 2;
                int labelY = (startY + endY) / 2;
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                g2.drawString(String.valueOf(edge.weight), labelX + 8, labelY - 8);
            }
        }

        private void drawArrowHead(Graphics2D g2, int startX, int startY, int endX, int endY) {
            double angle = Math.atan2(endY - startY, endX - startX);
            int size = 16;

            int x1 = endX - (int) (size * Math.cos(angle - Math.PI / 6));
            int y1 = endY - (int) (size * Math.sin(angle - Math.PI / 6));
            int x2 = endX - (int) (size * Math.cos(angle + Math.PI / 6));
            int y2 = endY - (int) (size * Math.sin(angle + Math.PI / 6));

            g2.drawLine(endX, endY, x1, y1);
            g2.drawLine(endX, endY, x2, y2);
        }

        private void drawVertex(Graphics2D g2, Vertex vertex) {
            g2.setColor(vertex.color);
            g2.fillOval(vertex.x - NODE_RADIUS, vertex.y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(vertex.x - NODE_RADIUS, vertex.y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

            g2.setFont(new Font("Arial", Font.BOLD, 18));
            FontMetrics metrics = g2.getFontMetrics();
            int textX = vertex.x - metrics.stringWidth(vertex.name) / 2;
            int textY = vertex.y + metrics.getAscent() / 2 - 4;
            g2.drawString(vertex.name, textX, textY);
        }
    }

    public static void main(String[] args) {
        Step01GraphBasics graph = new Step01GraphBasics();
        graph.setDelay(700);

        graph.addVertices("a", "b", "c", "d");

        graph.addEdge("a", "c");
        graph.addEdge("b", "c");
        graph.addEdge("b", "d");
        graph.addEdge("c", "d");

        graph.message = "Graph G = (V, E). Degree of c = " + graph.degree("c");
        graph.visualize();
        graph.printGraph();
        System.out.println("degree(c) = " + graph.degree("c"));
    }
}
