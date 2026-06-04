package lab04;

import java.util.*;

/**
 * Step 08: Simple 4-Way Multiway Search Tree Node
 * Concepts: d-node, up to 3 key-value entries, up to 4 child references.
 * This lab focuses on search logic inside a multiway node/tree.
 */
public class Step08FourWayMultiWayTree {
    static class Entry {
        String key;
        int value;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    static class Node {
        Entry[] entries = new Entry[3];
        Node[] children = new Node[4];
        int count;
        boolean leaf = true;
    }

    static class FourWayTree {
        Node root = new Node();

        public void putInRoot(String key, int value) {
            if (root.count == 3)
                throw new IllegalStateException("Root node is full in this simple lab.");
            int i = root.count - 1;
            while (i >= 0 && key.compareTo(root.entries[i].key) < 0) {
                root.entries[i + 1] = root.entries[i];
                i--;
            }
            root.entries[i + 1] = new Entry(key, value);
            root.count++;
        }

        public Integer search(String key) {
            return search(root, key);
        }

        private Integer search(Node node, String key) {
            if (node == null)
                return null;
            int i = 0;
            while (i < node.count && key.compareTo(node.entries[i].key) > 0)
                i++;
            if (i < node.count && key.equals(node.entries[i].key))
                return node.entries[i].value;
            if (node.leaf)
                return null;
            return search(node.children[i], key);
        }

        public List<Entry> rootEntries() {
            return Arrays.asList(Arrays.copyOf(root.entries, root.count));
        }
    }

    public static void main(String[] args) {
        FourWayTree tree = new FourWayTree();
        tree.putInRoot("C", 3);
        tree.putInRoot("A", 1);
        tree.putInRoot("B", 2);
        System.out.println("Root entries: " + tree.rootEntries());
        System.out.println("Search B: " + tree.search("B"));
        System.out.println("Search X: " + tree.search("X"));
    }
}
