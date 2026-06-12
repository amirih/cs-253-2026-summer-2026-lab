package lab06;

import java.util.Arrays;

/**
 * Step 01: Bubble Sort
 * Idea: repeatedly compare adjacent values and swap if they are out of order.
 * Best: O(n) with early stop, Average/Worst: O(n^2), Space: O(1)
 */
public class Step01BubbleSort {
    public static void bubbleSort(int[] arr) {
        boolean swapped;
        for (int pass = 0; pass < arr.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < arr.length - 1 - pass; i++) {
                System.out.print(Arrays.toString(arr));
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapped = true;
                    System.out.print("  -> " + arr[i + 1] + " and " + arr[i] + " swapped");
                }
                System.out.println();
            }
            if (!swapped)
                break; // already sorted
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = { 8, 2, 5, 4, 1 };
        bubbleSort(data);
        System.out.println(Arrays.toString(data));
    }
}
