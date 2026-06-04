package lab04;

/**
 * Step 03: BST Ordered Operations
 * Concepts: min, max, floor, ceiling, rank, select.
 */
public class Step03BSTOrderedOperations {
    static class BST {
        private Node root;

        private static class Node {
            int key, size = 1;
            Node left, right;

            Node(int key) {
                this.key = key;
            }
        }

        private int size(Node x) {
            return x == null ? 0 : x.size;
        }

        public int size() {
            return size(root);
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
            x.size = 1 + size(x.left) + size(x.right);
            return x;
        }

        public Integer min() {
            if (root == null)
                return null;
            Node x = root;
            while (x.left != null)
                x = x.left;
            return x.key;
        }

        public Integer max() {
            if (root == null)
                return null;
            Node x = root;
            while (x.right != null)
                x = x.right;
            return x.key;
        }

        public Integer floor(int key) {
            Node x = floor(root, key);
            return x == null ? null : x.key;
        }

        private Node floor(Node x, int key) {
            if (x == null)
                return null;
            if (key == x.key)
                return x;
            if (key < x.key)
                return floor(x.left, key);
            Node t = floor(x.right, key);
            return t != null ? t : x;
        }

        public Integer ceiling(int key) {
            Node x = ceiling(root, key);
            return x == null ? null : x.key;
        }

        private Node ceiling(Node x, int key) {
            if (x == null)
                return null;
            if (key == x.key)
                return x;
            if (key > x.key)
                return ceiling(x.right, key);
            Node t = ceiling(x.left, key);
            return t != null ? t : x;
        }

        public int rank(int key) {
            return rank(root, key);
        }

        private int rank(Node x, int key) {
            if (x == null)
                return 0;
            if (key < x.key)
                return rank(x.left, key);
            if (key > x.key)
                return 1 + size(x.left) + rank(x.right, key);
            return size(x.left);
        }

        public Integer select(int index) {
            Node x = select(root, index);
            return x == null ? null : x.key;
        }

        private Node select(Node x, int index) {
            if (x == null)
                return null;
            int leftSize = size(x.left);
            if (index < leftSize)
                return select(x.left, index);
            if (index > leftSize)
                return select(x.right, index - leftSize - 1);
            return x;
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        for (int x : new int[] { 5, 2, 8, 1, 3, 7, 9 })
            tree.insert(x);
        System.out.println("min=" + tree.min() + ", max=" + tree.max());
        System.out.println("floor(6)=" + tree.floor(6) + ", ceiling(6)=" + tree.ceiling(6));
        System.out.println("rank(7)=" + tree.rank(7));
        System.out.println("select(3)=" + tree.select(3));
    }
}
