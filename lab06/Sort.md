
**Sorting = putting items in order**, like arranging numbers from smallest to largest.

Example input:

`[5, 2, 9, 1, 6]`

Sorted output:

`[1, 2, 5, 6, 9]`


## Bubble sort

**Idea:** Repeatedly compare neighbors and swap them if they are in the wrong order.

Like bubbles rising to the top, the largest number slowly moves to the end.

Example:

`[5, 2, 9, 1]`

Compare `5` and `2`, swap:

`[2, 5, 9, 1]`

Compare `5` and `9`, no swap:

`[2, 5, 9, 1]`

Compare `9` and `1`, swap:

`[2, 5, 1, 9]`

Now `9` is in the correct final position.

**Good for:** Small lists
**Bad for:** Large lists
**Time:** `O(n²)`

## Insertion sort

**Idea:** Build a sorted part one item at a time.

Like sorting playing cards in your hand.

Example:

`[5, 2, 9, 1]`

Start with:

`[5]`

Insert `2` before `5`:

`[2, 5]`

Insert `9` after `5`:

`[2, 5, 9]`

Insert `1` at the front:

`[1, 2, 5, 9]`

**Good for:** Small lists or nearly sorted lists
**Bad for:** Large random lists
**Time:** `O(n²)`, but fast when nearly sorted

## Selection sort

**Idea:** Find the smallest item and put it first. Then find the next smallest and put it second.

Example:

`[5, 2, 9, 1]`

Smallest is `1`, swap with first:

`[1, 2, 9, 5]`

Next smallest is `2`, already correct:

`[1, 2, 9, 5]`

Next smallest is `5`, swap with `9`:

`[1, 2, 5, 9]`

**Good for:** Simple explanation
**Bad for:** Large lists
**Time:** `O(n²)`

## Merge sort

**Idea:** Split the list into halves, sort each half, then merge them.

Example:

`[5, 2, 9, 1]`

Split:

`[5, 2]` and `[9, 1]`

Split again:

`[5] [2] [9] [1]`

Merge sorted pairs:

`[2, 5]` and `[1, 9]`

Merge again:

`[1, 2, 5, 9]`

**Good for:** Reliable sorting
**Bad for:** Needs extra memory
**Time:** `O(n log n)`

## Quick sort

**Idea:** Pick a pivot, put smaller values on the left and larger values on the right, then repeat.

Example with pivot `5`:

`[5, 2, 9, 1, 6]`

Smaller than `5`:

`[2, 1]`

Pivot:

`[5]`

Larger than `5`:

`[9, 6]`

Now sort left and right:

`[1, 2] + [5] + [6, 9]`

Final:

`[1, 2, 5, 6, 9]`

**Good for:** Fast general-purpose sorting
**Bad for:** Can be slow if pivots are chosen badly
**Average time:** `O(n log n)`
**Worst time:** `O(n²)`

## Heap sort

**Idea:** Turn the list into a heap, which is a tree-like structure where the largest value is easy to remove.

Think of it like repeatedly pulling the biggest item from a priority pile.

Example:

`[5, 2, 9, 1, 6]`

Build a max heap, where biggest is on top:

`9`

Remove `9`, place it at the end.

Then remove `6`, then `5`, then `2`, then `1`.

Final sorted order:

`[1, 2, 5, 6, 9]`

**Good for:** Guaranteed good speed, no much extra memory
**Bad for:** Often less simple and less cache-friendly than quick sort
**Time:** `O(n log n)`

## Counting sort

**Idea:** Count how many times each value appears.

Works only when values are small integers or have a limited range.

Example:

`[4, 2, 2, 1, 4]`

Counts:

`1 appears 1 time`
`2 appears 2 times`
`3 appears 0 times`
`4 appears 2 times`

Rebuild:

`[1, 2, 2, 4, 4]`

**Good for:** Small integer ranges
**Bad for:** Large ranges like 1 to 1 billion
**Time:** `O(n + k)`, where `k` is the value range

## Bucket sort

**Idea:** Put values into groups called buckets, sort each bucket, then join the buckets.

Good when numbers are spread fairly evenly.

Example:

`[0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68]`

Make buckets by range:

| Bucket       | Values               |
| ------------ | -------------------- |
| `0.0 to 0.1` | `[]`                 |
| `0.1 to 0.2` | `[0.17, 0.12]`       |
| `0.2 to 0.3` | `[0.26, 0.21, 0.23]` |
| `0.3 to 0.4` | `[0.39]`             |
| `0.4 to 0.5` | `[]`                 |
| `0.5 to 0.6` | `[]`                 |
| `0.6 to 0.7` | `[0.68]`             |
| `0.7 to 0.8` | `[0.78, 0.72]`       |
| `0.8 to 0.9` | `[]`                 |
| `0.9 to 1.0` | `[0.94]`             |

Sort each bucket:

| Bucket       | Sorted values        |
| ------------ | -------------------- |
| `0.1 to 0.2` | `[0.12, 0.17]`       |
| `0.2 to 0.3` | `[0.21, 0.23, 0.26]` |
| `0.3 to 0.4` | `[0.39]`             |
| `0.6 to 0.7` | `[0.68]`             |
| `0.7 to 0.8` | `[0.72, 0.78]`       |
| `0.9 to 1.0` | `[0.94]`             |

Join buckets:

`[0.12, 0.17, 0.21, 0.23, 0.26, 0.39, 0.68, 0.72, 0.78, 0.94]`

**Good for:** Evenly distributed numbers
**Bad for:** Data that all falls into one bucket
**Average time:** `O(n + k)`
**Worst time:** `O(n²)` if buckets are badly balanced

## Radix sort with intermediate results

**Idea:** Sort by one digit at a time, usually from right to left.

Example:

`[170, 45, 75, 90, 802, 24, 2, 66]`

### Step 1: sort by ones digit

| Ones digit | Numbers     |
| ---------- | ----------- |
| `0`        | `[170, 90]` |
| `2`        | `[802, 2]`  |
| `4`        | `[24]`      |
| `5`        | `[45, 75]`  |
| `6`        | `[66]`      |

Intermediate result:

`[170, 90, 802, 2, 24, 45, 75, 66]`

### Step 2: sort by tens digit

Using the intermediate result above:

| Tens digit | Numbers     |
| ---------- | ----------- |
| `0`        | `[802, 2]`  |
| `2`        | `[24]`      |
| `4`        | `[45]`      |
| `6`        | `[66]`      |
| `7`        | `[170, 75]` |
| `9`        | `[90]`      |

Intermediate result:

`[802, 2, 24, 45, 66, 170, 75, 90]`

### Step 3: sort by hundreds digit

Using the intermediate result above:

| Hundreds digit | Numbers                   |
| -------------- | ------------------------- |
| `0`            | `[2, 24, 45, 66, 75, 90]` |
| `1`            | `[170]`                   |
| `8`            | `[802]`                   |

Final result:

`[2, 24, 45, 66, 75, 90, 170, 802]`

## Summary

| Algorithm      | Main idea                 | Best use                     | Average time     |
| -------------- | ------------------------- | ---------------------------- | ---------------- |
| Bubble sort    | Swap neighbors            | Small lists                  | `O(n²)`          |
| Insertion sort | Insert into sorted part   | Small or nearly sorted lists | `O(n²)`          |
| Selection sort | Select smallest each time | Learning                     | `O(n²)`          |
| Merge sort     | Split and merge           | Reliable general sorting     | `O(n log n)`     |
| Quick sort     | Pivot and partition       | Fast general sorting         | `O(n log n)`     |
| Heap sort      | Repeatedly remove max/min | Guaranteed speed             | `O(n log n)`     |
| Counting sort  | Count values              | Small integer ranges         | `O(n + k)`       |
| Radix sort     | Sort digit by digit       | Integers/strings             | `O(d × (n + k))` |
| Bucket sort    | Put values into buckets   | Evenly distributed values    | `O(n + k)`       |

