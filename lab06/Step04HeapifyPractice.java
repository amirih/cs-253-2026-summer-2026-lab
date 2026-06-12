package lab06;

import java.util.Arrays;

/**
 * Step 04: Heapify Practice
 * Idea: restore the max-heap property by moving one value down the tree.
 * This is the core operation used by heap sort.
 */
public class Step04HeapifyPractice {
    public static void heapify(int[] heap, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < heapSize && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != root) {
            swap(heap, root, largest);
            heapify(heap, heapSize, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] heapLikeArray = { 1, 9, 10, 22, 31, 40, 15 };
        heapify(heapLikeArray, heapLikeArray.length, 0);
        System.out.println(Arrays.toString(heapLikeArray));
    }
}
