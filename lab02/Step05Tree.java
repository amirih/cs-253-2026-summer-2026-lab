package lab02;

import java.util.LinkedList;
import java.util.Queue;

public class Step05Tree {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class MyTree {
        Node root;

        void insert(int value) {
            if (root == null) {
                root = new Node(value);
            } else {
                insertRecursively(root, value);
            }
        }

        private void insertRecursively(Node current, int value) {
            // TODO: This is a simple insertion method that fills the tree level by level
            // You can implement a more complex insertion method

            if (current.left == null) {
                current.left = new Node(value);
            } else if (current.right == null) {
                current.right = new Node(value);
            } else if (current.left.left == null || current.left.right == null) {
                insertRecursively(current.left, value);
            } else {
                insertRecursively(current.right, value);
            }
        }

        void printTree(Node node) {
            if (node == null) {
                return;
            }

            Queue<Node> queue = new LinkedList<>();
            queue.add(node);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();

                for (int i = 0; i < levelSize; i++) {
                    Node current = queue.poll();

                    System.out.print("|" + current.value + "|");

                    if (current.left != null) {
                        queue.add(current.left);
                    }

                    if (current.right != null) {
                        queue.add(current.right);
                    }
                }

                System.out.println();
            }

        }
    }

    public static void main(String[] args) {
        MyTree tree = new MyTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println("Tree structure:");
        tree.printTree(tree.root);
    }
}
