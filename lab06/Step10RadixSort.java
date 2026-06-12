package lab06;

import java.util.Arrays;

/**
 * Step 10: Radix Sort, LSD version
 * Idea: sort numbers digit by digit from least significant to most significant.
 * This implementation handles non-negative integers.
 * Time: O(d * (n + 10)), Space: O(n + 10)
 */
public class Step10RadixSort {
    public static void radixSort(int[] arr) {
        if (arr.length < 2)
            return;

        int max = arr[0];
        for (int value : arr) {
            if (value < 0) {
                throw new IllegalArgumentException("This lab version supports non-negative integers only.");
            }
            max = Math.max(max, value);
        }

        for (int place = 1; max / place > 0; place *= 10) {
            countingSortByDigit(arr, place);
        }
    }

    private static void countingSortByDigit(int[] arr, int place) {
        int[] count = new int[10];
        int[] output = new int[arr.length];

        for (int value : arr) {
            int digit = (value / place) % 10;
            count[digit]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / place) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] data = { 217, 228, 163, 152, 65, 72, 67 };
        radixSort(data);
        System.out.println(Arrays.toString(data));
    }
}
