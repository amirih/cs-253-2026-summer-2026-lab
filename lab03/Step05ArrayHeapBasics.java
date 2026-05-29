package lab03;

import java.util.Arrays;

/*
 * Array Representation of a Complete Binary Tree
 * root index: 0
 * left child: 2*i + 1
 * right child: 2*i + 2
 * parent: (i - 1) / 2
 */
public class Step05ArrayHeapBasics {
    public static int parent(int i) {
        if (i <= 0)
            return -1;
        return (i - 1) / 2;
    }

    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }

    public static boolean hasLeft(int[] heap, int i) {
        return left(i) < heap.length;
    }

    public static boolean hasRight(int[] heap, int i) {
        return right(i) < heap.length;
    }

    public static boolean isMinHeap(int[] heap) {
        for (int i = 0; i < heap.length; i++) {
            if (hasLeft(heap, i) && heap[i] > heap[left(i)])
                return false;
            if (hasRight(heap, i) && heap[i] > heap[right(i)])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] heap = { 10, 15, 30, 40, 50, 100, 40 };
        System.out.println("heap array = " + Arrays.toString(heap));
        for (int i = 0; i < heap.length; i++) {
            System.out.printf("index %d: parent=%d left=%d right=%d%n", i, parent(i), left(i), right(i));
        }
        System.out.println("is min heap? " + isMinHeap(heap));
    }
}
