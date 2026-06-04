package lab04;

import java.util.*;

/**
 * Step 07: Splay Tree
 * Concepts: zig, zig-zig, zig-zag. Accessed nodes move to the root.
 */
public class Step07SplayTree {
    static class SplayTree {
        private Node root;

        private static class Node {
            int key;
            Node left, right, parent;

            Node(int key) {
                this.key = key;
            }
        }

        private void rotateLeft(Node x) {
            Node y = x.right;
            if (y == null)
                return;
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
            if (y == null)
                return;
            x.left = y.right;
            if (y.right != null)
                y.right.parent = x;
            y.parent = x.parent;
            if (x.parent == null)
                root = y;
            else if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
            y.right = x;
            x.parent = y;
        }

        private void splay(Node x) {
            while (x.parent != null) {
                Node p = x.parent;
                Node g = p.parent;
                if (g == null) { // Zig
                    if (x == p.left)
                        rotateRight(p);
                    else
                        rotateLeft(p);
                } else if (x == p.left && p == g.left) { // Zig-Zig
                    rotateRight(g);
                    rotateRight(p);
                } else if (x == p.right && p == g.right) { // Zig-Zig
                    rotateLeft(g);
                    rotateLeft(p);
                } else if (x == p.right && p == g.left) { // Zig-Zag
                    rotateLeft(p);
                    rotateRight(g);
                } else { // Zig-Zag
                    rotateRight(p);
                    rotateLeft(g);
                }
            }
        }

        public void insert(int key) {
            if (root == null) {
                root = new Node(key);
                return;
            }
            Node x = root, parent = null;
            while (x != null) {
                parent = x;
                if (key < x.key)
                    x = x.left;
                else if (key > x.key)
                    x = x.right;
                else {
                    splay(x);
                    return;
                }
            }
            Node n = new Node(key);
            n.parent = parent;
            if (key < parent.key)
                parent.left = n;
            else
                parent.right = n;
            splay(n);
        }

        public boolean search(int key) {
            Node x = root, last = null;
            while (x != null) {
                last = x;
                if (key < x.key)
                    x = x.left;
                else if (key > x.key)
                    x = x.right;
                else {
                    splay(x);
                    return true;
                }
            }
            if (last != null)
                splay(last);
            return false;
        }

        public int rootKey() {
            return root == null ? -1 : root.key;
        }

        public List<Integer> inorder() {
            List<Integer> out = new ArrayList<>();
            inorder(root, out);
            return out;
        }

        private void inorder(Node x, List<Integer> out) {
            if (x == null)
                return;
            inorder(x.left, out);
            out.add(x.key);
            inorder(x.right, out);
        }
    }

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
        for (int x : new int[] { 10, 20, 30, 40, 50, 25 })
            tree.insert(x);
        System.out.println("Root after insertions: " + tree.rootKey());
        tree.search(20);
        System.out.println("Root after searching 20: " + tree.rootKey());
        System.out.println("Inorder: " + tree.inorder());
    }
}
