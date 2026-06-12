package lab05;

public class AVL extends BST {

    static class Tree extends BST.Tree {

        public Tree() {
            super("AVL Tree Visualization");
        }

        @Override
        public void insert(int key) {
            root = insertAVL(root, key);

            if (root != null) {
                root.parent = null;
                root.color = Node.Color.ORANGE;
            }

            visualize();
        }

        private Node insertAVL(Node node, int key) {
            if (node == null) {
                Node newNode = new Node(key, Node.Color.GREEN);
                visualize();
                return newNode;
            }

            if (key < node.key) {
                node.left = insertAVL(node.left, key);
                node.left.parent = node;
                colorDefault(node.left);
            } else if (key > node.key) {
                node.right = insertAVL(node.right, key);
                node.right.parent = node;
                colorDefault(node.right);
            } else {
                colorDefault(node);
                return node;
            }

            colorDefault(node);
            return balance(node);
        }

        public void delete(int key) {
            root = deleteAVL(root, key);

            if (root != null) {
                root.parent = null;
                root.color = Node.Color.ORANGE;
            }

            visualize();
        }

        private Node deleteAVL(Node node, int key) {
            if (node == null) {
                return null;
            }

            if (key < node.key) {
                node.left = deleteAVL(node.left, key);

                if (node.left != null) {
                    node.left.parent = node;
                }

            } else if (key > node.key) {
                node.right = deleteAVL(node.right, key);

                if (node.right != null) {
                    node.right.parent = node;
                }

            } else {
                colorDeleting(node);

                if (node.left == null || node.right == null) {
                    Node child;

                    if (node.left != null) {
                        child = node.left;
                    } else {
                        child = node.right;
                    }

                    if (child != null) {
                        child.parent = node.parent;
                    }

                    return child;
                }

                Node successor = minValueNode(node.right);

                colorChosen(successor);
                colorReplacing(node);

                node.key = successor.key;

                colorAdded(node);

                node.right = deleteAVL(node.right, successor.key);

                if (node.right != null) {
                    node.right.parent = node;
                }
                colorDefault(node);
            }

            colorDefault(node);
            return balance(node);
        }

        private Node minValueNode(Node node) {
            Node current = node;

            while (current.left != null) {
                current = current.left;
            }
            colorChosen(current);

            return current;
        }

        private Node balance(Node node) {
            if (node == null) {
                return null;
            }

            int balance = getBalance(node);

            // Left Left case
            if (balance > 1 && getBalance(node.left) >= 0) {
                colorMoving(node);
                return rotateRight(node);
            }

            // Left Right case
            if (balance > 1 && getBalance(node.left) < 0) {
                colorMoving(node.left);
                node.left = rotateLeft(node.left);

                if (node.left != null) {
                    node.left.parent = node;
                }

                colorMoving(node);
                return rotateRight(node);
            }

            // Right Right case
            if (balance < -1 && getBalance(node.right) <= 0) {
                colorMoving(node);
                return rotateLeft(node);
            }

            // Right Left case
            if (balance < -1 && getBalance(node.right) > 0) {
                colorMoving(node.right);
                node.right = rotateRight(node.right);

                if (node.right != null) {
                    node.right.parent = node;
                }

                colorMoving(node);
                return rotateLeft(node);
            }
            colorDefault(node);
            return node;
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node temp = x.right;

            x.right = y;
            y.left = temp;

            x.parent = y.parent;
            y.parent = x;

            if (temp != null) {
                temp.parent = y;
            }

            colorAdded(x);
            colorDefault(y);
            colorDefault(x);
            visualize();

            return x;
        }

        private Node rotateLeft(Node x) {
            Node y = x.right;
            Node temp = y.left;

            y.left = x;
            x.right = temp;

            y.parent = x.parent;
            x.parent = y;

            if (temp != null) {
                temp.parent = x;
            }

            colorAdded(y);
            colorDefault(x);
            colorDefault(y);
            visualize();

            return y;
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + Math.max(height(node.left), height(node.right));
        }

        private int getBalance(Node node) {
            if (node == null) {
                return 0;
            }

            return height(node.left) - height(node.right);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.setDelay(100);

        tree.insert(5);
        tree.insert(6);
        tree.insert(10);
        tree.insert(2);
        tree.insert(9);
        tree.insert(1);
        tree.insert(3);
        tree.insert(8);
        tree.insert(4);
        tree.insert(7);

        tree.setDelay(1000);

        tree.delete(4);
        tree.delete(2);
        tree.delete(5);
        tree.delete(3);
        tree.delete(1);

        tree.closeVisualization();
    }
}