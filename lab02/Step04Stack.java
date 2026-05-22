package lab02;

public class Step04Stack {
    // A stack is a data structure that follows the Last In First Out (LIFO)
    // principle.
    // It has two main operations: push (add to the top) and pop (remove from
    // the top).

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static class MyStack {
        Node top;

        void push(int value) {
            Node newNode = new Node(value);
            newNode.next = top;
            top = newNode;
        }

        Integer pop() {
            System.out.println("Popping from stack...");
            if (top == null) {
                return null; // Stack is empty
            }
            int value = top.value;
            top = top.next;
            return value;
        }

        void printStack() {
            Node current = top;
            if (current == null)
                System.out.println("| |");
            while (current != null) {
                System.out.println("|" + current.value + "|");
                current = current.next;
            }
            System.out.println("--------");
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.printStack();
        stack.push(1);
        stack.printStack();
        stack.push(2);
        stack.printStack();
        stack.push(3);
        stack.printStack();
        stack.push(4);
        stack.printStack();

        System.out.println("Popped: " + stack.pop());
        stack.printStack();
        System.out.println("Popped: " + stack.pop());
        stack.printStack();
        System.out.println("Popped: " + stack.pop());
        stack.printStack();
        System.out.println("Popped: " + stack.pop());
        stack.printStack();
    }
}
