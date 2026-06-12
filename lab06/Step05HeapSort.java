package lab06;

import java.util.Arrays;

/**
 * Step 05: Heap Sort
 * Idea: build a max heap, repeatedly move the root to the sorted end, then
 * heapify.
 * Time: O(n log n), Space: O(1)
 */
public class Step05HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap from the last non-leaf node back to root.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Move max value to the end, shrink heap, and restore heap property.
        for (int end = n - 1; end > 0; end--) {
            swap(arr, 0, end);
            heapify(arr, end, 0);
        }
    }

    private static void heapify(int[] arr, int heapSize, int root) {
        System.out.println();

        System.out.print(Arrays.toString(arr) + "  -> heapifying index " + root);
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && arr[left] > arr[largest])
            largest = left;
        if (right < heapSize && arr[right] > arr[largest])
            largest = right;

        if (largest != root) {
            swap(arr, root, largest);
            heapify(arr, heapSize, largest);
        }

    }

    private static void swap(int[] arr, int i, int j) {
        System.out.print("  -> " + arr[i] + " and " + arr[j] + " swapped");
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = { 1, -15, 22, 40, 9, 91 };
        heapSort(data);
        System.out.println(Arrays.toString(data));
    }
}
