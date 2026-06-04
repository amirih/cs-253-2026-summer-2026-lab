package lab04;

import java.util.*;

/**
 * Step 10: Red-Black Tree Insertion
 * Concepts: insert red node, recolor, rotate for inner/outer cases, keep root
 * black.
 */
public class Step10RedBlackTreeInsertion {
    enum Color {
        RED, BLACK
    }

    static class RedBlackTree {
        private Node root;

        private static class Node {
            int key;
            Color color = Color.RED;
            Node left, right, parent;

            Node(int key) {
                this.key = key;
            }
        }

        public void insert(int key) {
            Node inserted = bstInsert(key);
            fixAfterInsert(inserted);
        }

        private Node bstInsert(int key) {
            Node parent = null;
            Node x = root;
            while (x != null) {
                parent = x;
                if (key < x.key)
                    x = x.left;
                else if (key > x.key)
                    x = x.right;
                else
                    return x;
            }
            Node n = new Node(key);
            n.parent = parent;
            if (parent == null)
                root = n;
            else if (key < parent.key)
                parent.left = n;
            else
                parent.right = n;
            return n;
        }

        private void fixAfterInsert(Node n) {
            while (n != root && colorOf(parentOf(n)) == Color.RED) {
                Node p = parentOf(n);
                Node g = parentOf(p);
                if (p == leftOf(g)) {
                    Node u = rightOf(g);
                    if (colorOf(u) == Color.RED) { // Case 1
                        setColor(p, Color.BLACK);
                        setColor(u, Color.BLACK);
                        setColor(g, Color.RED);
                        n = g;
                    } else {
                        if (n == rightOf(p)) { // Case 2
                            n = p;
                            rotateLeft(n);
                            p = parentOf(n);
                            g = parentOf(p);
                        }
                        setColor(p, Color.BLACK); // Case 3
                        setColor(g, Color.RED);
                        rotateRight(g);
                    }
                } else {
                    Node u = leftOf(g);
                    if (colorOf(u) == Color.RED) { // Mirror Case 1
                        setColor(p, Color.BLACK);
                        setColor(u, Color.BLACK);
                        setColor(g, Color.RED);
                        n = g;
                    } else {
                        if (n == leftOf(p)) { // Mirror Case 2
                            n = p;
                            rotateRight(n);
                            p = parentOf(n);
                            g = parentOf(p);
                        }
                        setColor(p, Color.BLACK); // Mirror Case 3
                        setColor(g, Color.RED);
                        rotateLeft(g);
                    }
                }
            }
            root.color = Color.BLACK;
        }

        private void rotateLeft(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left != null)
                y.left.parent = x;
            y.parent = x.parent;
            if (x.parent == null)
                root = y;
            else if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
            y.left = x;
            x.parent = y;
        }

        private void rotateRight(Node x) {
            Node y = x.left;
            x.left = y.right;
            if (y.right != null)
                y.right.parent = x;
            y.parent = x.parent;
            if (x.parent == null)
                root = y;
            else if (x == x.parent.right)
                x.parent.right = y;
            else
                x.parent.left = y;
            y.right = x;
            x.parent = y;
        }

        private Color colorOf(Node x) {
            return x == null ? Color.BLACK : x.color;
        }

        private Node parentOf(Node x) {
            return x == null ? null : x.parent;
        }

        private Node leftOf(Node x) {
            return x == null ? null : x.left;
        }

        private Node rightOf(Node x) {
            return x == null ? null : x.right;
        }

        private void setColor(Node x, Color c) {
            if (x != null)
                x.color = c;
        }

        public List<String> inorderWithColors() {
            List<String> out = new ArrayList<>();
            inorder(root, out);
            return out;
        }

        private void inorder(Node x, List<String> out) {
            if (x == null)
                return;
            inorder(x.left, out);
            out.add(x.key + "(" + x.color + ")");
            inorder(x.right, out);
        }

        public int rootKey() {
            return root.key;
        }

        public Color rootColor() {
            return root.color;
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        for (int x : new int[] { 8, 20, 11, 14, 9, 4, 12 })
            tree.insert(x);
        System.out.println("Inorder with colors: " + tree.inorderWithColors());
        System.out.println("Root: " + tree.rootKey() + " " + tree.rootColor());
    }
}
