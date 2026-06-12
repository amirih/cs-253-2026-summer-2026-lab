package lab06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Step 09: Bucket Sort
 * Idea: scatter values into buckets, sort each bucket, then gather them back.
 * Average: O(n + k), Worst: O(n^2) if one bucket receives most values.
 */
public class Step09BucketSort {
    public static void bucketSort(int[] arr) {
        if (arr.length < 2)
            return;

        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        System.out.println("Min: " + min + ", Max: " + max);

        int bucketCount = (int) Math.sqrt(arr.length) + 1;
        int bucketSize = Math.max(1, (max - min) / bucketCount + 1);

        System.out.println("Bucket count: " + bucketCount + ", Bucket size: " + bucketSize);

        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        System.out.println("Buckets: " + buckets);

        for (int value : arr) {
            int bucketIndex = (value - min) / bucketSize;
            if (bucketIndex >= bucketCount)
                bucketIndex = bucketCount - 1;
            buckets.get(bucketIndex).add(value);
        }

        System.out.println("Buckets: " + buckets);

        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket); // You can replace this with insertion sort for practice.
            for (int value : bucket) {
                arr[index++] = value;
            }
            System.out.println("Buckets: " + buckets);
            System.out.println("Array after gathering: " + Arrays.toString(arr));
        }

    }

    public static void main(String[] args) {
        int[] data = { 15, 5, 2, 9, 8, 4, 22 };

        bucketSort(data);
        System.out.println(Arrays.toString(data));
    }
}
