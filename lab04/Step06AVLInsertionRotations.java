package lab04;

import java.util.*;

/**
 * Step 06: AVL Insertion with Rotations
 * Concepts: LL, RR, LR, RL rotations after insert.
 */
public class Step06AVLInsertionRotations {
    static class AVLTree {
        private Node root;

        private static class Node {
            int key, height = 1;
            Node left, right;

            Node(int key) {
                this.key = key;
            }
        }

        private int height(Node x) {
            return x == null ? 0 : x.height;
        }

        private int balance(Node x) {
            return x == null ? 0 : height(x.left) - height(x.right);
        }

        private void update(Node x) {
            x.height = 1 + Math.max(height(x.left), height(x.right));
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node t2 = x.right;
            x.right = y;
            y.left = t2;
            update(y);
            update(x);
            return x;
        }

        private Node rotateLeft(Node x) {
            Node y = x.right;
            Node t2 = y.left;
            y.left = x;
            x.right = t2;
            update(x);
            update(y);
            return y;
        }

        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {
            if (node == null)
                return new Node(key);
            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);
            else
                return node;

            update(node);
            int bf = balance(node);

            if (bf > 1 && key < node.left.key)
                return rotateRight(node); // LL
            if (bf < -1 && key > node.right.key)
                return rotateLeft(node); // RR
            if (bf > 1 && key > node.left.key) { // LR
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (bf < -1 && key < node.right.key) { // RL
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
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

        public int height() {
            return height(root);
        }

        public int rootKey() {
            return root.key;
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        for (int x : new int[] { 40, 20, 10, 25, 30, 22, 50 })
            tree.insert(x);
        System.out.println("Inorder: " + tree.inorder());
        System.out.println("Root: " + tree.rootKey());
        System.out.println("Height: " + tree.height());
    }
}
