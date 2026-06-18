# Lab 03: Priority Queues, Heaps, and Maps

Lab03 focuses on data structures that store items by priority or by key. Priority queues are often implemented with heaps. Maps store key-value pairs.

## Concepts

| Step | Concept                      | Simple description                                                                  | Example                                                                             |
| ---- | ---------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| 1    | Priority queue               | Removes the most important item first, not necessarily the oldest item.             | Tasks with priorities: urgent task comes out before normal task.                    |
| 2    | Comparable and Comparator    | Ways to define how objects should be ordered.                                       | Sort students by ID using `Comparable`, or by name using a `Comparator`.            |
| 3    | Unsorted list priority queue | Insert is easy, but removal must scan the whole list to find the best priority.     | Store `[5, 1, 3]`. To remove min, scan all values and remove `1`.                   |
| 4    | Sorted list priority queue   | Keep the list sorted so removal is easy, but insertion may take longer.             | Store `[1, 3, 5]`. Removing min is fast because `1` is already first.               |
| 5    | Array heap basics            | A heap can be stored in an array using index formulas for parent and children.      | At index `i`, left child is `2i + 1`, right child is `2i + 2`.                      |
| 6    | Min heap                     | A tree where every parent is less than or equal to its children.                    | In `[1, 3, 2, 7]`, `1` is the root and is the smallest value.                       |
| 7    | Heap priority queue          | A priority queue backed by a heap for efficient insert and remove-min.              | Insert `4, 2, 8`; removing returns `2` first.                                       |
| 8    | Simple map                   | Stores pairs of keys and values. Find a value using its key.                        | Key `Ava` and value `95` means the key points to grade `95`.                        |
| 9    | Separate chaining hash map   | Uses buckets. If multiple keys land in the same bucket, store them in a small list. | Keys `Ava` and `Noah` may hash to the same bucket, so both are kept in that bucket. |
| 10   | Sorted array ordered map     | Keeps keys sorted in an array so ordered operations are possible.                   | Keys `[10, 20, 30]` make it easy to find the next key after `20`.                   |

## Big ideas

- A normal queue cares about arrival order. A priority queue cares about priority.
- A heap is a complete binary tree with a heap-order rule.
- Heaps are commonly used for efficient priority queues.
- A map connects one key to one value.
- Hash maps aim for fast lookup. Ordered maps preserve key order.

## Quick examples

### Priority queue

```text
insert("email", 3)
insert("exam", 1)
insert("homework", 2)
removeMin() returns "exam"
```

### Min heap

```text
      1
     / \
    3   2
   /
  7
```

Every parent is smaller than its children.

### Map

```text
put("Ava", 95)
get("Ava") returns 95
```

## Study order

Start with priority queues, then compare unsorted and sorted list implementations. After that, study heaps. Finish with maps and hash maps.
