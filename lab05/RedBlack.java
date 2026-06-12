package lab05;

public class RedBlack extends BST {

    static class Tree extends BST.Tree {

        @Override
        public void insert(int key) {
            Node newNode = new Node(key, Node.Color.RED);

            Node parent = null;
            Node current = root;

            while (current != null) {
                parent = current;

                if (key < current.key) {
                    current = current.left;
                } else if (key > current.key) {
                    current = current.right;
                } else {
                    return;
                }
            }

            newNode.parent = parent;

            if (parent == null) {
                root = newNode;
            } else if (key < parent.key) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }

            visualize();
            fixInsert(newNode);

            root.color = Node.Color.GRAY;
            root.parent = null;
            visualize();
        }

        private void fixInsert(Node node) {
            while (node != root && colorOf(parentOf(node)) == Node.Color.RED) {
                Node parent = parentOf(node);
                Node grandparent = parentOf(parent);

                if (parent == leftOf(grandparent)) {
                    Node uncle = rightOf(grandparent);

                    if (colorOf(uncle) == Node.Color.RED) {
                        parent.color = Node.Color.GRAY;
                        uncle.color = Node.Color.GRAY;
                        grandparent.color = Node.Color.RED;
                        node = grandparent;
                    } else {
                        if (node == rightOf(parent)) {
                            node = parent;
                            rotateLeft(node);
                        }

                        parentOf(node).color = Node.Color.GRAY;
                        parentOf(parentOf(node)).color = Node.Color.RED;
                        rotateRight(parentOf(parentOf(node)));
                    }
                } else {
                    Node uncle = leftOf(grandparent);

                    if (colorOf(uncle) == Node.Color.RED) {
                        parent.color = Node.Color.GRAY;
                        uncle.color = Node.Color.GRAY;
                        grandparent.color = Node.Color.RED;
                        node = grandparent;
                    } else {
                        if (node == leftOf(parent)) {
                            node = parent;
                            rotateRight(node);
                        }

                        parentOf(node).color = Node.Color.GRAY;
                        parentOf(parentOf(node)).color = Node.Color.RED;
                        rotateLeft(parentOf(parentOf(node)));
                    }
                }

                visualize();
            }

            root.color = Node.Color.GRAY;
        }

        @Override
        public void delete(int key) {
            Node node = searchNode(key);

            if (node == null) {
                return;
            }

            deleteNode(node);

            if (root != null) {
                root.color = Node.Color.GRAY;
                root.parent = null;
            }

            visualize();
        }

        private void deleteNode(Node node) {
            Node y = node;
            Node x;
            Node xParent;

            Node.Color originalColor = colorOf(y);

            if (node.left == null) {
                x = node.right;
                xParent = node.parent;
                transplant(node, node.right);
            } else if (node.right == null) {
                x = node.left;
                xParent = node.parent;
                transplant(node, node.left);
            } else {
                y = minimum(node.right);
                originalColor = colorOf(y);
                x = y.right;

                if (y.parent == node) {
                    xParent = y;
                    if (x != null) {
                        x.parent = y;
                    }
                } else {
                    xParent = y.parent;
                    transplant(y, y.right);

                    y.right = node.right;
                    y.right.parent = y;
                }

                transplant(node, y);

                y.left = node.left;
                y.left.parent = y;
                y.color = node.color;
            }

            visualize();

            if (originalColor == Node.Color.GRAY) {
                fixDelete(x, xParent);
            }
        }

        private void fixDelete(Node node, Node parent) {
            while (node != root && colorOf(node) == Node.Color.GRAY) {
                if (parent == null) {
                    break;
                }

                if (node == leftOf(parent)) {
                    Node sibling = rightOf(parent);

                    if (colorOf(sibling) == Node.Color.RED) {
                        sibling.color = Node.Color.GRAY;
                        parent.color = Node.Color.RED;
                        rotateLeft(parent);
                        sibling = rightOf(parent);
                    }

                    if (colorOf(leftOf(sibling)) == Node.Color.GRAY &&
                            colorOf(rightOf(sibling)) == Node.Color.GRAY) {
                        if (sibling != null) {
                            sibling.color = Node.Color.RED;
                        }

                        node = parent;
                        parent = parentOf(node);
                    } else {
                        if (colorOf(rightOf(sibling)) == Node.Color.GRAY) {
                            if (leftOf(sibling) != null) {
                                leftOf(sibling).color = Node.Color.GRAY;
                            }

                            if (sibling != null) {
                                sibling.color = Node.Color.RED;
                                rotateRight(sibling);
                            }

                            sibling = rightOf(parent);
                        }

                        if (sibling != null) {
                            sibling.color = parent.color;
                        }

                        parent.color = Node.Color.GRAY;

                        if (rightOf(sibling) != null) {
                            rightOf(sibling).color = Node.Color.GRAY;
                        }

                        rotateLeft(parent);
                        node = root;
                    }
                } else {
                    Node sibling = leftOf(parent);

                    if (colorOf(sibling) == Node.Color.RED) {
                        sibling.color = Node.Color.GRAY;
                        parent.color = Node.Color.RED;
                        rotateRight(parent);
                        sibling = leftOf(parent);
                    }

                    if (colorOf(rightOf(sibling)) == Node.Color.GRAY &&
                            colorOf(leftOf(sibling)) == Node.Color.GRAY) {
                        if (sibling != null) {
                            sibling.color = Node.Color.RED;
                        }

                        node = parent;
                        parent = parentOf(node);
                    } else {
                        if (colorOf(leftOf(sibling)) == Node.Color.GRAY) {
                            if (rightOf(sibling) != null) {
                                rightOf(sibling).color = Node.Color.GRAY;
                            }

                            if (sibling != null) {
                                sibling.color = Node.Color.RED;
                                rotateLeft(sibling);
                            }

                            sibling = leftOf(parent);
                        }

                        if (sibling != null) {
                            sibling.color = parent.color;
                        }

                        parent.color = Node.Color.GRAY;

                        if (leftOf(sibling) != null) {
                            leftOf(sibling).color = Node.Color.GRAY;
                        }

                        rotateRight(parent);
                        node = root;
                    }
                }

                visualize();
            }

            if (node != null) {
                node.color = Node.Color.GRAY;
            }
        }

        private Node searchNode(int key) {
            Node current = root;

            while (current != null) {
                if (key == current.key) {
                    return current;
                } else if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        private Node minimum(Node node) {
            Node current = node;

            while (current.left != null) {
                current = current.left;
            }

            return current;
        }

        private void transplant(Node oldNode, Node newNode) {
            if (oldNode.parent == null) {
                root = newNode;
            } else if (oldNode == oldNode.parent.left) {
                oldNode.parent.left = newNode;
            } else {
                oldNode.parent.right = newNode;
            }

            if (newNode != null) {
                newNode.parent = oldNode.parent;
            }
        }

        private void rotateLeft(Node node) {
            Node rightChild = node.right;

            if (rightChild == null) {
                return;
            }

            node.right = rightChild.left;

            if (rightChild.left != null) {
                rightChild.left.parent = node;
            }

            rightChild.parent = node.parent;

            if (node.parent == null) {
                root = rightChild;
            } else if (node == node.parent.left) {
                node.parent.left = rightChild;
            } else {
                node.parent.right = rightChild;
            }

            rightChild.left = node;
            node.parent = rightChild;

            visualize();
        }

        private void rotateRight(Node node) {
            Node leftChild = node.left;

            if (leftChild == null) {
                return;
            }

            node.left = leftChild.right;

            if (leftChild.right != null) {
                leftChild.right.parent = node;
            }

            leftChild.parent = node.parent;

            if (node.parent == null) {
                root = leftChild;
            } else if (node == node.parent.right) {
                node.parent.right = leftChild;
            } else {
                node.parent.left = leftChild;
            }

            leftChild.right = node;
            node.parent = leftChild;

            visualize();
        }

        private Node parentOf(Node node) {
            if (node == null) {
                return null;
            }

            return node.parent;
        }

        private Node leftOf(Node node) {
            if (node == null) {
                return null;
            }

            return node.left;
        }

        private Node rightOf(Node node) {
            if (node == null) {
                return null;
            }

            return node.right;
        }

        private Node.Color colorOf(Node node) {
            if (node == null) {
                return Node.Color.GRAY;
            }

            return node.color;
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

        tree.delete(4);
        tree.delete(2);
        tree.delete(5);
        tree.delete(3);
        tree.delete(1);

        tree.closeVisualization();
    }
}