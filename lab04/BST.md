A **BST** means **Binary Search Tree**.

It is a tree where each node follows this rule:

```text
left child < node < right child
```

So smaller values go to the **left**, and bigger values go to the **right**.

---

## Simple example

Insert:

```text
50, 30, 70, 20, 40, 60, 80
```

The BST becomes:

```text
        50
       /  \
     30    70
    / \    / \
  20  40  60  80
```

---

## Searching in a BST

Suppose we search for `40`.

Start at root:

```text
40 < 50
```

Go left to `30`.

```text
40 > 30
```

Go right to `40`.

Found it.

Visual path:

```text
        50
       /
     30
       \
        40
```

---

## Inserting in a BST

Insert `35` into this tree:

```text
        50
       /  \
     30    70
    / \    / \
  20  40  60  80
```

Steps:

```text
35 < 50  → go left
35 > 30  → go right
35 < 40  → go left
```

Result:

```text
        50
       /  \
     30    70
    / \    / \
  20  40  60  80
      /
    35
```

---

## Deleting in a BST

There are 3 cases.

### 1. Delete a leaf node

Delete `20`:

```text
        50
       /  \
     30    70
      \    / \
      40  60  80
```

Just remove it.

---

### 2. Delete a node with one child

Before:

```text
     30
       \
        40
       /
      35
```

Delete `40`, connect `35` to `30`:

```text
     30
       \
        35
```

---

### 3. Delete a node with two children

Delete `50`:

```text
        50
       /  \
     30    70
    / \    / \
  20  40  60  80
```

Use the **smallest value in the right subtree**, which is `60`.

Replace `50` with `60`:

```text
        60
       /  \
     30    70
    / \      \
  20  40      80
```

---

## Traversal

### In-order traversal

Left, Root, Right

For this BST:

```text
        50
       /  \
     30    70
    / \    / \
  20  40  60  80
```

In-order gives:

```text
20, 30, 40, 50, 60, 70, 80
```

Important point:

```text
In-order traversal of a BST gives sorted order.
```

---

## Time complexity

If the tree is balanced:

```text
search: O(log n)
insert: O(log n)
delete: O(log n)
```

If the tree becomes like a chain:

```text
10
  \
   20
     \
      30
        \
         40
```

Then operations become:

```text
O(n)
```

---

## Main idea

A **BST** is a tree that keeps values ordered:

```text
smaller values on the left
bigger values on the right
```

It makes searching faster than checking every value one by one, especially when the tree is balanced.
