package lab02;

public class Step03Queue {
    // A queue is a data structure that follows the First In First Out (FIFO)
    // principle.
    // It has two main operations: enqueue (add to the back) and dequeue (remove
    // from the front).

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static class MyQueue {
        Node front;
        Node back;

        void enqueue(int value) {
            Node newNode = new Node(value);
            if (back != null) {
                back.next = newNode;
            }
            back = newNode;
            if (front == null) {
                front = back;
            }
        }

        Integer dequeue() {
            if (front == null) {
                return null; // Queue is empty
            }
            int value = front.value;
            front = front.next;
            if (front == null) {
                back = null; // Queue is now empty
            }
            return value;
        }

        void printQueue() {
            Node current = front;
            System.out.print("Front-> ");
            while (current != null) {
                System.out.print("|" + current.value + "|");
                current = current.next;
            }
            System.out.println(" <-Back");
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.printQueue();
        queue.enqueue(1);
        queue.printQueue();
        queue.enqueue(2);
        queue.printQueue();
        queue.enqueue(3);
        queue.printQueue();
        queue.enqueue(4);
        queue.printQueue();

        System.out.println("Removed: " + queue.dequeue());
        queue.printQueue();
        System.out.println("Removed: " + queue.dequeue());
        queue.printQueue();
        System.out.println("Removed: " + queue.dequeue());
        queue.printQueue();
        System.out.println("Removed: " + queue.dequeue());
        queue.printQueue();
    }

}
