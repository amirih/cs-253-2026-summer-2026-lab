# Lab 02: Linked Lists, Stacks, Queues, and Trees

Lab02 introduces basic data structures built from nodes. A node stores data and references to other nodes.

## Concepts

| Step | Concept            | Simple description                                                              | Example                                                            |
| ---- | ------------------ | ------------------------------------------------------------------------------- | ------------------------------------------------------------------ |
| 1    | Singly linked list | A chain of nodes where each node points to the next node.                       | `1 -> 2 -> 3` means node `1` points to `2`, and `2` points to `3`. |
| 2    | Doubly linked list | A linked list where each node points forward and backward.                      | `1 <-> 2 <-> 3` lets you move from `2` to `1` or from `2` to `3`.  |
| 3    | Queue              | First in, first out. Add to the back and remove from the front.                 | People in a line: first person in line is served first.            |
| 4    | Stack              | Last in, first out. Add and remove from the top.                                | A stack of plates: the last plate placed on top is removed first.  |
| 5    | Binary tree        | A tree where each node has at most two children, usually called left and right. | Root `5` can have left child `3` and right child `7`.              |

## Big ideas

- Linked lists use references instead of array indexes.
- Queues are useful when order of arrival matters.
- Stacks are useful when the most recent item should be handled first.
- Trees represent hierarchical data.
- Many later structures, such as search trees and heaps, are specialized trees.

## Quick examples

### Singly linked list

```text
head -> 10 -> 20 -> 30 -> null
```

To insert `40` at the end, follow `next` links until the last node, then set its `next` to the new node.

### Queue

```text
enqueue(1), enqueue(2), enqueue(3)
dequeue() returns 1
```

### Stack

```text
push(1), push(2), push(3)
pop() returns 3
```

### Binary tree

```text
      5
     / \
    3   7
```

## Study order

Learn linked lists first, because queues and stacks can be implemented using linked nodes. Then study trees, because tree ideas are expanded in later labs.
