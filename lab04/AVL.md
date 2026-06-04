An **AVL tree** is a **self-balancing binary search tree**.

That means it follows two rules:

1. **Binary Search Tree rule**

   * Left child is smaller than the node.
   * Right child is larger than the node.

2. **Balance rule**

   * For every node, the heights of the left and right subtrees differ by at most **1**.

The balance value is:

```text
balance factor = height(left subtree) - height(right subtree)
```

Allowed values are:

```text
-1, 0, +1
```

If a node becomes `-2` or `+2`, the AVL tree fixes itself using **rotations**.

---

## Simple example

Insert these numbers:

```text
10, 20, 30
```

After inserting `10`:

```text
10
```

After inserting `20`:

```text
10
  \
   20
```

After inserting `30`:

```text
10
  \
   20
     \
      30
```

This is unbalanced because node `10` has a right subtree that is too tall.

```text
10
  \
   20
     \
      30
```

So AVL performs a **left rotation** around `10`.

Result:

```text
   20
  /  \
10    30
```

Now it is balanced.

---

## Why rotations help

Before rotation, the tree looked like a linked list:

```text
10 -> 20 -> 30
```

That is bad because searching can become slow.

After rotation:

```text
   20
  /  \
10    30
```

The tree is shorter, so search is faster.

---

## Another visual example

Insert:

```text
30, 20, 10
```

You get:

```text
    30
   /
 20
 /
10
```

This is too heavy on the left side, so AVL performs a **right rotation** around `30`.

After rotation:

```text
   20
  /  \
10    30
```

---

## The four AVL imbalance cases

### 1. Left Left case

```text
    30
   /
 20
 /
10
```

Fix: **right rotation**

```text
   20
  /  \
10    30
```

---

### 2. Right Right case

```text
10
  \
   20
     \
      30
```

Fix: **left rotation**

```text
   20
  /  \
10    30
```

---

### 3. Left Right case

```text
    30
   /
 10
   \
    20
```

Fix:
First left rotate `10`, then right rotate `30`.

Result:

```text
   20
  /  \
10    30
```

---

### 4. Right Left case

```text
10
  \
   30
  /
20
```

Fix:
First right rotate `30`, then left rotate `10`.

Result:

```text
   20
  /  \
10    30
```

---

## Main idea

An AVL tree is like a regular binary search tree, but it keeps checking:

```text
Is one side too much taller than the other?
```

If yes, it rotates nodes to stay balanced.

Because of this, search, insert, and delete stay fast:

```text
O(log n)
```

Think of an AVL tree as a **BST that automatically straightens itself whenever it starts leaning too much**.
