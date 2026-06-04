package lab04;

import java.util.*;

/**
 * Step 02: BST Traversals
 * Concepts: inorder traversal returns sorted keys, preorder and postorder show
 * structure.
 */
public class Step02BSTTraversals {
    static class BST {
        private Node root;

        private static class Node {
            int key;
            Node left, right;

            Node(int key) {
                this.key = key;
            }
        }

        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node x, int key) {
            if (x == null)
                return new Node(key);
            if (key < x.key)
                x.left = insert(x.left, key);
            else if (key > x.key)
                x.right = insert(x.right, key);
            return x;
        }

        public List<Integer> inorder() {
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(Node x, List<Integer> out) {
            if (x == null)
                return;
            inorder(x.left, out);
            out.add(x.key);
            inorder(x.right, out);
        }

        public List<Integer> preorder() {
            List<Integer> result = new ArrayList<>();
            preorder(root, result);
            return result;
        }

        private void preorder(Node x, List<Integer> out) {
            if (x == null)
                return;
            out.add(x.key);
            preorder(x.left, out);
            preorder(x.right, out);
        }

        public List<Integer> postorder() {
            List<Integer> result = new ArrayList<>();
            postorder(root, result);
            return result;
        }

        private void postorder(Node x, List<Integer> out) {
            if (x == null)
                return;
            postorder(x.left, out);
            postorder(x.right, out);
            out.add(x.key);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        for (int x : new int[] { 8, 3, 10, 1, 6, 14, 4, 7, 13 })
            tree.insert(x);
        System.out.println("Inorder sorted: " + tree.inorder());
        System.out.println("Preorder:       " + tree.preorder());
        System.out.println("Postorder:      " + tree.postorder());
    }
}
