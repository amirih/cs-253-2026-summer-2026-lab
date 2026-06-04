package lab04;

/**
 * Step 09: Red-Black Tree Rule Checker
 * Concepts: root is black, red nodes have black children, equal black height on
 * all null paths.
 */
public class Step09RedBlackTreeValidation {
    enum Color {
        RED, BLACK
    }

    static class Node {
        int key;
        Color color;
        Node left, right;

        Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }
    }

    static boolean isRedBlackTree(Node root) {
        if (root == null)
            return true;
        if (root.color != Color.BLACK)
            return false;
        return blackHeight(root) != -1;
    }

    static int blackHeight(Node x) {
        if (x == null)
            return 1; // null external nodes are black
        if (x.color == Color.RED) {
            if (isRed(x.left) || isRed(x.right))
                return -1;
        }
        int left = blackHeight(x.left);
        int right = blackHeight(x.right);
        if (left == -1 || right == -1 || left != right)
            return -1;
        return left + (x.color == Color.BLACK ? 1 : 0);
    }

    static boolean isRed(Node x) {
        return x != null && x.color == Color.RED;
    }

    public static void main(String[] args) {
        Node root = new Node(10, Color.BLACK);
        root.left = new Node(5, Color.RED);
        root.right = new Node(15, Color.RED);
        root.left.left = new Node(3, Color.BLACK);
        root.left.right = new Node(7, Color.BLACK);
        root.right.left = new Node(12, Color.BLACK);
        root.right.right = new Node(18, Color.BLACK);
        System.out.println("Valid red-black tree: " + isRedBlackTree(root));
    }
}
