package lab04;

/**
 * Step 01: Basic Binary Search Tree
 * Concepts: node, root, get, put, tree shape depends on insertion order.
 */
public class Step01BinarySearchTree {
    static class BST<K extends Comparable<K>, V> {
        private Node<K, V> root;

        private static class Node<K, V> {
            K key;
            V value;
            Node<K, V> left, right;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        public void put(K key, V value) {
            root = put(root, key, value);
        }

        private Node<K, V> put(Node<K, V> x, K key, V value) {
            if (x == null)
                return new Node<>(key, value);
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x.left = put(x.left, key, value);
            else if (cmp > 0)
                x.right = put(x.right, key, value);
            else
                x.value = value;
            return x;
        }

        public V get(K key) {
            Node<K, V> x = root;
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp < 0)
                    x = x.left;
                else if (cmp > 0)
                    x = x.right;
                else
                    return x.value;
            }
            return null;
        }

        public boolean contains(K key) {
            return get(key) != null;
        }

    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        String[] keys = { "S", "E", "A", "R", "C", "H", "X" };
        for (int i = 0; i < keys.length; i++)
            bst.put(keys[i], i);

        System.out.println("Value for R: " + bst.get("R"));
        System.out.println("Contains T: " + bst.contains("T"));
    }
}
