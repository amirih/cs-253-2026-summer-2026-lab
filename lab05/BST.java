package lab05;

import java.awt.*;
import javax.swing.*;

// visualize the tree using GUI
public class BST {

    // Node class for the tree
    static class Node {
        int key;

        enum Color {
            ORANGE, BLACK, RED, GRAY, GREEN, YELLOW, BROWN, WHITE
        }

        Color color;
        Node left, right, parent;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = Color.ORANGE;
        }

        public Node(int key, Color color) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = color;
        }
    }

    static class Tree {
        Node root;
        JFrame frame;
        private int delay;

        public Tree() {
            this.root = null;
            this.delay = 1000;
            this.frame = this.getVisualizationFrame("Binary Search Tree Visualization");

        }

        public Tree(String title) {
            this.root = null;
            this.delay = 1000;
            this.frame = this.getVisualizationFrame(title);
        }

        public void insert(int key) {
            root = insert(root, key);
            this.visualize();

            root.color = Node.Color.ORANGE;

            if (root != null) {
                root.parent = null;
            }
        }

        private Node insert(Node root, int key) {
            if (root == null) {
                return new Node(key, Node.Color.GREEN);
            }
            if (key < root.key) {
                root.left = insert(root.left, key);
                this.visualize();

                colorDefault(root.left);
                root.left.parent = root;

            } else if (key > root.key) {
                root.right = insert(root.right, key);
                this.visualize();

                colorDefault(root.right);
                root.right.parent = root;

            }

            return root;
        }

        void delete(int key) {
            root = delete(root, key);
            this.visualize();
        }

        private Node delete(Node root, int key) {
            if (root == null) {
                return root;
            }

            if (key < root.key) {
                root.left = delete(root.left, key);

            } else if (key > root.key) {
                root.right = delete(root.right, key);

            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                Node temp = minValueNode(root.right);
                colorMoving(temp);
                colorReplacing(root);
                root.key = temp.key;
                colorAdded(root);
                colorDeleting(temp);

                root.right = delete(root.right, temp.key);
            }
            colorDefault(root);
            return root;
        }

        private Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            colorChosen(current);
            return current;
        }

        void colorDeleting(Node node) {
            if (node != null) {
                node.color = Node.Color.WHITE;
                this.visualize();
            }
        }

        void colorChosen(Node node) {
            if (node != null) {
                node.color = Node.Color.YELLOW;
                this.visualize();
            }
        }

        void colorAdded(Node node) {
            if (node != null) {
                node.color = Node.Color.GREEN;
                this.visualize();
            }
        }

        void colorReplacing(Node node) {
            if (node != null) {
                node.color = Node.Color.BROWN;
                this.visualize();
            }
        }

        void colorDefault(Node node) {
            if (node != null) {
                node.color = Node.Color.ORANGE;
                this.visualize();
            }
        }

        void colorMoving(Node node) {
            if (node != null) {
                node.color = Node.Color.GRAY;
                this.visualize();
            }
        }

        void visualize() {
            visualize(this.delay);
        }

        void visualize(int delay) {
            this.frame.add(new TreePanel(root));
            this.frame.setVisible(true);
            pause(delay);
        }

        private JFrame getVisualizationFrame(String title) {
            JFrame newFrame = new JFrame(title);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setSize(1600, 900);
            newFrame.setLocationRelativeTo(null);
            return newFrame;
        }

        void pause(int milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void closeVisualization() {
            this.frame.dispose();
        }

        public void setDelay(int delay) {
            this.delay = delay;

        }
    }

    static class TreePanel extends JPanel {
        private final Node root;
        private static final int NODE_RADIUS = 40;
        private static final int VERTICAL_GAP = 70;

        public TreePanel(Node root) {
            this.root = root;
            setBackground(java.awt.Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (root != null) {
                drawTree(g, root, getWidth() / 2, 50, getWidth() / 4);
            }
        }

        private void drawTree(Graphics g, Node node, int x, int y, int horizontalGap) {
            if (node == null) {
                return;
            }

            if (horizontalGap < 30) {
                horizontalGap = 30;
            }

            g.setColor(java.awt.Color.BLACK);

            if (node.left != null) {
                int childX = x - horizontalGap;
                int childY = y + VERTICAL_GAP;

                g.drawLine(x, y, childX, childY);
                drawTree(g, node.left, childX, childY, horizontalGap / 2);
            }

            if (node.right != null) {
                int childX = x + horizontalGap;
                int childY = y + VERTICAL_GAP;

                g.drawLine(x, y, childX, childY);
                drawTree(g, node.right, childX, childY, horizontalGap / 2);
            }

            g.setColor(getAwtColor(node.color));
            g.fillOval(
                    x - NODE_RADIUS,
                    y - NODE_RADIUS,
                    NODE_RADIUS * 2,
                    NODE_RADIUS * 2);

            g.setColor(java.awt.Color.BLACK);
            g.drawOval(
                    x - NODE_RADIUS,
                    y - NODE_RADIUS,
                    NODE_RADIUS * 2,
                    NODE_RADIUS * 2);

            String text = String.valueOf(node.key);
            FontMetrics fm = g.getFontMetrics();
            // increas the font size
            g.setFont(new Font("Arial", Font.BOLD, 16));

            int textX = x - fm.stringWidth(text) / 2;
            int textY = y + fm.getAscent() / 2 - 3;

            g.drawString(text, textX, textY);
        }

        private java.awt.Color getAwtColor(Node.Color color) {
            switch (color) {
                case RED:
                    return java.awt.Color.RED;
                case BLACK:
                    return java.awt.Color.BLACK;
                case GRAY:
                    return java.awt.Color.GRAY;
                case GREEN:
                    return java.awt.Color.GREEN;
                case YELLOW:
                    return java.awt.Color.YELLOW;
                case BROWN:
                    return new java.awt.Color(165, 42, 42);
                case WHITE:
                    return java.awt.Color.WHITE;
                case ORANGE:
                default:
                    return java.awt.Color.ORANGE;
            }
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.setDelay(100);
        tree.insert(5);
        tree.insert(6);
        tree.insert(10);
        tree.insert(2);
        tree.insert(9);
        tree.insert(1);
        tree.insert(3);
        tree.insert(8);
        tree.insert(4);
        tree.insert(7);
        tree.setDelay(1000);

        tree.delete(4);
        tree.delete(2);
        tree.delete(5);
        tree.delete(3);
        tree.delete(1);

        tree.closeVisualization();

    }
}