package lab06;

import java.util.Arrays;

/**
 * Step 08: Counting Sort
 * Idea: count occurrences, use prefix sums, then place values directly.
 * Handles negative values by shifting with min.
 * Time: O(n + k), Space: O(n + k), where k = max - min + 1
 */
public class Step08CountingSort {
    public static int[] countingSort(int[] arr) {
        if (arr.length == 0)
            return new int[0];

        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        int range = max - min + 1;
        System.out.println("Min: " + min + ", Max: " + max + ", Range: " + range);
        int[] count = new int[range];
        for (int value : arr) {
            count[value - min]++;
        }
        System.out.println("Count array: " + Arrays.toString(count));
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        System.out.println("Prefix sums: " + Arrays.toString(count));

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) { // stable placement
            int shifted = arr[i] - min;
            System.out.println("Placing value: " + arr[i] + " at index: " + (count[shifted] - 1));
            output[count[shifted] - 1] = arr[i];
            System.out.println("Output array: " + Arrays.toString(output));
            count[shifted]--;
            System.out.println("Updated count array: " + Arrays.toString(count));
        }
        System.out.println("Sorted array: " + Arrays.toString(output));
        return output;

    }

    public static void main(String[] args) {
        int[] data = { 9, 7, 2, 8, 9, 9, 2, 3, 2 };
        System.out.println("Original array: " + Arrays.toString(data));
        System.out.println(Arrays.toString(countingSort(data)));
    }
}
