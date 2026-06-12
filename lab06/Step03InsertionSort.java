package lab06;

import java.util.Arrays;

/**
 * Step 03: Insertion Sort
 * Idea: build a sorted left side, inserting one new value into its correct
 * spot.
 * Best: O(n), Average/Worst: O(n^2), Space: O(1)
 */
public class Step03InsertionSort {
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            System.out.print(Arrays.toString(arr));
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            System.out.println("  -> " + key + " inserted at position " + (j + 1));
        }
    }

    public static void main(String[] args) {
        int[] data = { 5, 2, 4, 6, 1, 3 };
        insertionSort(data);
        System.out.println(Arrays.toString(data));
    }
}
