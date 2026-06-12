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
        int[] count = new int[range];
        for (int value : arr) {
            count[value - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) { // stable placement
            int shifted = arr[i] - min;
            output[count[shifted] - 1] = arr[i];
            count[shifted]--;
        }
        return output;
    }

    public static void main(String[] args) {
        int[] data = { 9, 7, 2, 8, 9, 9, 2, 3, 2 };
        System.out.println(Arrays.toString(countingSort(data)));
    }
}
