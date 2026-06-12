package lab06;

import java.util.Arrays;

/**
 * Step 06: Quick Sort
 * Idea: choose a pivot, partition values around it, then recursively sort
 * sides.
 * Average: O(n log n), Worst: O(n^2), Space: O(log n) recursion average
 */
public class Step06QuickSort {
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high)
            return;

        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int smaller = low - 1;

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                smaller++;
                swap(arr, smaller, i);
            }
        }
        swap(arr, smaller + 1, high);
        return smaller + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = { 8, 5, 2, 4, 11, 9, 22 };
        quickSort(data);
        System.out.println(Arrays.toString(data));
    }
}
