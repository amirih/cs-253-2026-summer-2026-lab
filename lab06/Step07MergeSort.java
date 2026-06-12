package lab06;

import java.util.Arrays;

/**
 * Step 07: Merge Sort
 * Idea: split the array into halves, sort each half, then merge sorted halves.
 * Time: O(n log n), Space: O(n)
 */
public class Step07MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr.length < 2)
            return;
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right)
            return;

        System.out.println(Arrays.toString(getArraySlice(arr, left, right)));

        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static int[] getArraySlice(int[] arr, int low, int high) {
        int[] slice = new int[high - low + 1];
        System.arraycopy(arr, low, slice, 0, slice.length);
        return slice;
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        System.out.println("Merging: " + Arrays.toString(getArraySlice(arr, left, mid)) + " and "
                + Arrays.toString(getArraySlice(arr, mid + 1, right)));

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= right)
            temp[k++] = arr[j++];

        for (int index = left; index <= right; index++) {
            arr[index] = temp[index];
        }

        System.out.println("Merged: " + Arrays.toString(getArraySlice(arr, left, right)));
    }

    public static void main(String[] args) {
        int[] data = { 9, 5, 2, 4, 11, 6, 22 };
        mergeSort(data);
        System.out.println(Arrays.toString(data));
    }
}
