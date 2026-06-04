A **splay tree** is a **self-adjusting binary search tree**.

It follows the normal **BST rule**:

```text
left child < node < right child
```

But it has one special behavior:

```text
Every time you access a node, move it to the root.
```

This movement is called **splaying**.

---

## Why do this?

A splay tree assumes:

```text
Recently used items are likely to be used again soon.
```

So it moves recently accessed nodes near the top, making future searches for them faster.

---

# Simple example

Start with this tree:

```text
        50
       /  \
     30    70
    /  \
  20    40
```

Now search for `20`.

In a normal BST, we find it here:

```text
        50
       /  \
     30    70
    /
  20
```

In a **splay tree**, after finding `20`, we move it to the root.

Final tree:

```text
        20
          \
           30
             \
              50
             /  \
           40    70
```

Now if we search for `20` again, it is right at the top.

---

# How does splaying work?

Splaying uses rotations.

There are three main cases.

---

## 1. Zig case

This happens when the node has a parent, but no grandparent.

Before:

```text
   30
  /
20
```

Splay `20` using one right rotation:

```text
20
  \
   30
```

For the opposite direction:

```text
30
  \
   40
```

Becomes:

```text
   40
  /
30
```

---

## 2. Zig-Zig case

This happens when the node and parent are on the same side.

Example: left-left case.

Before:

```text
      50
     /
   30
  /
20
```

Splay `20`.

First rotate `50` right:

```text
    30
   /  \
 20    50
```

Then rotate `30` right:

```text
20
  \
   30
     \
      50
```

---

## 3. Zig-Zag case

This happens when the node and parent are on opposite sides.

Example: left-right case.

Before:

```text
      50
     /
   20
     \
      30
```

Splay `30`.

First rotate `20` left:

```text
      50
     /
   30
  /
20
```

Then rotate `50` right:

```text
    30
   /  \
 20    50
```

---

# Insert in a splay tree

To insert a value:

1. Insert it like a normal BST.
2. Splay the inserted node to the root.

Example: insert `40`.

Before:

```text
    20
      \
       30
         \
          50
```

Insert `40`:

```text
    20
      \
       30
         \
          50
         /
       40
```

Then splay `40` to the root:

```text
       40
      /  \
    30    50
   /
 20
```

---

# Search in a splay tree

To search for a value:

1. Search like a normal BST.
2. If found, splay that node to the root.
3. If not found, usually splay the last accessed node.

---

# Delete in a splay tree

To delete value `x`:

1. Search for `x`.
2. Splay `x` to the root.
3. Remove the root.
4. Join the left and right subtrees.

Example, delete `30`:

```text
      30
     /  \
   20    50
        /
      40
```

Remove `30`:

```text
left subtree:   20

right subtree:  50
               /
             40
```

Then join them:

```text
      20
        \
         50
        /
      40
```

A cleaner join often splays the largest node in the left subtree, then attaches the right subtree.

---

# Key difference from AVL tree

An **AVL tree** keeps strict balance.

A **splay tree** does not keep strict balance. It only moves accessed nodes to the root.

```text
AVL tree:
Keeps tree balanced all the time.

Splay tree:
Adapts based on recent access.
```

---

# Time complexity

A single operation can sometimes be slow:

```text
O(n)
```

But over many operations, the average cost is:

```text
O(log n)
```

This is called **amortized O(log n)**.

---

## Main idea

A splay tree is a BST that says:

```text
The thing you just used should be easiest to reach next time.
```

So after every search, insert, or delete, it rearranges itself by moving the important node to the root.
