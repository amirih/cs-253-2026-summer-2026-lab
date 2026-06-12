package lab05;

public class Splay extends BST {

    static class Tree extends BST.Tree {

        public Tree() {
            super("Splay Tree Visualization");
        }

        @Override
        public void insert(int key) {
            Node newNode = new Node(key, Node.Color.GREEN);

            if (root == null) {
                root = newNode;
                visualize();
                colorDefault(root);
                return;
            }

            Node current = root;
            Node parent = null;

            while (current != null) {
                parent = current;
                colorDefault(current);

                if (key < current.key) {
                    current = current.left;
                } else if (key > current.key) {
                    current = current.right;
                } else {
                    splay(current);
                    return;
                }
            }

            newNode.parent = parent;

            if (key < parent.key) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }

            visualize();
            splay(newNode);

            if (root != null) {
                root.color = Node.Color.ORANGE;
            }
        }

        public Node search(int key) {
            Node current = root;
            Node lastVisited = null;

            while (current != null) {
                lastVisited = current;
                colorMoving(current);

                if (key == current.key) {
                    colorChosen(current);
                    splay(current);
                    return current;
                } else if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            if (lastVisited != null) {
                splay(lastVisited);
            }

            return null;
        }

        public void delete(int key) {
            Node node = search(key);

            if (node == null || root == null || root.key != key) {
                return;
            }

            colorDeleting(root);

            Node leftSubtree = root.left;
            Node rightSubtree = root.right;

            if (leftSubtree != null) {
                leftSubtree.parent = null;
            }

            if (rightSubtree != null) {
                rightSubtree.parent = null;
            }

            if (leftSubtree == null) {
                root = rightSubtree;
            } else {
                root = leftSubtree;

                Node maxNode = maximum(root);
                splay(maxNode);

                root.right = rightSubtree;

                if (rightSubtree != null) {
                    rightSubtree.parent = root;
                }
            }

            if (root != null) {
                root.parent = null;
                colorDefault(root);
            }

            visualize();
        }

        private Node maximum(Node node) {
            Node current = node;

            while (current.right != null) {
                current = current.right;
                colorMoving(current);
            }

            colorChosen(current);
            return current;
        }

        private void splay(Node node) {
            if (node == null) {
                return;
            }

            while (node.parent != null) {
                Node parent = node.parent;
                Node grandparent = parent.parent;

                if (grandparent == null) {
                    if (node == parent.left) {
                        rotateRight(parent);
                    } else {
                        rotateLeft(parent);
                    }
                } else if (node == parent.left && parent == grandparent.left) {
                    rotateRight(grandparent);
                    rotateRight(parent);
                } else if (node == parent.right && parent == grandparent.right) {
                    rotateLeft(grandparent);
                    rotateLeft(parent);
                } else if (node == parent.right && parent == grandparent.left) {
                    rotateLeft(parent);
                    rotateRight(grandparent);
                } else {
                    rotateRight(parent);
                    rotateLeft(grandparent);
                }

                visualize();
            }

            root = node;
            root.parent = null;
            colorAdded(root);
            visualize();
            colorDefault(root);
        }

        private void rotateLeft(Node x) {
            Node y = x.right;

            if (y == null) {
                return;
            }

            colorMoving(x);
            colorMoving(y);

            x.right = y.left;

            if (y.left != null) {
                y.left.parent = x;
            }

            y.parent = x.parent;

            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }

            y.left = x;
            x.parent = y;

            colorDefault(x);
            colorDefault(y);
        }

        private void rotateRight(Node x) {
            Node y = x.left;

            if (y == null) {
                return;
            }

            colorMoving(x);
            colorMoving(y);

            x.left = y.right;

            if (y.right != null) {
                y.right.parent = x;
            }

            y.parent = x.parent;

            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.right) {
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }

            y.right = x;
            x.parent = y;

            colorDefault(x);
            colorDefault(y);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.setDelay(1000);

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

        tree.search(8);
        tree.search(1);
        tree.search(10);

        tree.delete(4);
        tree.delete(2);
        tree.delete(5);
        tree.delete(3);
        tree.delete(1);

        tree.closeVisualization();
    }
}