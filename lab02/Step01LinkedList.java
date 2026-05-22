package lab02;

public class Step01LinkedList {

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.value);
            current = current.next;
            if (current != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        // Print the linked list
        printList(head);
    }

}
