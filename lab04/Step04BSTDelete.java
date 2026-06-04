package lab04;

import java.util.*;

/**
 * Step 04: BST Deletion
 * Concepts: delete leaf, delete one-child node, delete two-child node using
 * successor.
 */
public class Step04BSTDelete {
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

        public void delete(int key) {
            root = delete(root, key);
        }

        private Node delete(Node x, int key) {
            if (x == null)
                return null;
            if (key < x.key)
                x.left = delete(x.left, key);
            else if (key > x.key)
                x.right = delete(x.right, key);
            else {
                if (x.right == null)
                    return x.left;
                if (x.left == null)
                    return x.right;
                Node old = x;
                x = min(old.right);
                x.right = deleteMin(old.right);
                x.left = old.left;
            }
            return x;
        }

        private Node min(Node x) {
            while (x.left != null)
                x = x.left;
            return x;
        }

        private Node deleteMin(Node x) {
            if (x.left == null)
                return x.right;
            x.left = deleteMin(x.left);
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
    }

    public static void main(String[] args) {
        BST tree = new BST();
        for (int x : new int[] { 50, 30, 70, 20, 40, 60, 80 })
            tree.insert(x);
        System.out.println("Before: " + tree.inorder());
        tree.delete(20); // leaf
        tree.delete(30); // one child after deleting 20
        tree.delete(70); // two children
        System.out.println("After:  " + tree.inorder());
    }
}
