package lab04;

/**
 * Step 05: AVL Height and Balance Factor
 * Concepts: height, balance factor, height-balance property.
 */
public class Step05AVLBalanceCheck {
    static class Node {
        int key, height = 1;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    static int height(Node x) {
        return x == null ? 0 : x.height;
    }

    static int balanceFactor(Node x) {
        return x == null ? 0 : height(x.left) - height(x.right);
    }

    static void updateHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    static boolean isAVL(Node x) {
        if (x == null)
            return true;
        updateHeight(x);
        return Math.abs(balanceFactor(x)) <= 1 && isAVL(x.left) && isAVL(x.right);
    }

    public static void main(String[] args) {
        Node root = new Node(30);
        root.left = new Node(20);
        root.right = new Node(40);
        root.left.left = new Node(10);
        updateHeight(root.left);
        updateHeight(root.right);
        updateHeight(root);

        System.out.println("Root height: " + height(root));
        System.out.println("Root balance factor: " + balanceFactor(root));
        System.out.println("Is AVL: " + isAVL(root));
    }
}
