package lab06;

import java.util.Arrays;

/**
 * Step 02: Selection Sort
 * Idea: repeatedly find the smallest value in the unsorted part and move it
 * front.
 * Best/Average/Worst: O(n^2), Space: O(1)
 */
public class Step02SelectionSort {
    public static void selectionSort(int[] arr) {
        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            System.out.print(Arrays.toString(arr));
            for (int i = start + 1; i < arr.length; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }
            swap(arr, start, minIndex);
            System.out.println("  -> " + arr[start] + " and " + arr[minIndex] + " swapped");
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = { 22, 18, 12, -4, 27, 30, 36 };
        selectionSort(data);
        System.out.println(Arrays.toString(data));
    }
}
