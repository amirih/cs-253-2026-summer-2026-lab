# Lab 06: Sorting Algorithms

Lab06 focuses on sorting algorithms. Sorting means rearranging values into order, usually smallest to largest.

## Concepts

| Step | Algorithm | Simple description | Example |
|---|---|---|---|
| 1 | Bubble sort | Repeatedly compare neighbors and swap them if they are out of order. | `[3, 1, 2]` becomes `[1, 2, 3]` after repeated neighbor swaps. |
| 2 | Selection sort | Repeatedly find the smallest remaining value and put it in the next position. | From `[4, 2, 5]`, select `2` and move it to the front. |
| 3 | Insertion sort | Build a sorted section one item at a time by inserting each item into the right place. | Sorting cards in your hand one card at a time. |
| 4 | Heapify practice | Rearrange part of an array so it satisfies the heap property. | In a max heap, parent values are greater than or equal to child values. |
| 5 | Heap sort | Turn the array into a heap, then repeatedly remove the largest or smallest value. | `[4, 1, 3]` is heapified, then values are extracted in sorted order. |
| 6 | Quick sort | Pick a pivot, partition values around it, then sort each side recursively. | Pivot `5`: values less than `5` go left, greater values go right. |
| 7 | Merge sort | Split the array, sort each half, then merge the sorted halves. | Merge `[1, 4]` and `[2, 3]` into `[1, 2, 3, 4]`. |
| 8 | Counting sort | Count how many times each value appears, then rebuild the sorted array. | `[2, 1, 2, 0]` has counts for `0`, `1`, and `2`. |
| 9 | Bucket sort | Place values into buckets, sort each bucket, then combine them. | Scores from `0` to `99` can be grouped into ten buckets. |
| 10 | Radix sort | Sort numbers digit by digit, usually from least significant digit to most significant digit. | Sort by ones digit, then tens digit, then hundreds digit. |

## Big ideas

- Bubble, selection, and insertion sort are simple but usually slower on large arrays.
- Heap sort, quick sort, and merge sort are faster general-purpose algorithms.
- Counting, bucket, and radix sort can be very fast when input values have useful limits or structure.
- Some sorting algorithms compare values directly. Others use counting, buckets, or digits.

## Quick comparison

| Algorithm | Main idea | Typical time |
|---|---|---|
| Bubble sort | Swap neighbors | `O(n^2)` |
| Selection sort | Select smallest | `O(n^2)` |
| Insertion sort | Insert into sorted part | `O(n^2)` |
| Heap sort | Use heap structure | `O(n log n)` |
| Quick sort | Partition around pivot | Average `O(n log n)` |
| Merge sort | Split and merge | `O(n log n)` |
| Counting sort | Count values | `O(n + k)` |
| Bucket sort | Sort groups | Depends on buckets |
| Radix sort | Sort by digits | `O(d * (n + k))` |

## Study order

Start with bubble, selection, and insertion sort. Then learn heap sort, quick sort, and merge sort. Finish with non-comparison sorts: counting, bucket, and radix sort.
