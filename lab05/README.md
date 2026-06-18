# Lab 05: Tree Implementations

Lab05 contains larger tree implementations. It compares ordinary binary search trees with several self-balancing tree strategies.

## Concepts

| File | Concept | Simple description | Example |
|---|---|---|---|
| BST.java | Binary search tree | Stores values so smaller values go left and larger values go right. | Insert `50, 25, 75`; searching for `25` moves left from `50`. |
| AVL.java | AVL tree | A BST that keeps height balanced after inserts and deletes. | Insert `30, 20, 10`; the tree rotates so `20` becomes the balanced root. |
| Splay.java | Splay tree | Moves recently accessed nodes to the root using rotations. | Search for `40`; after the search, `40` is moved near or to the root. |
| RedBlack.java | Red-black tree | A BST with red and black node colors that maintain approximate balance. | Insert a red node. If rules break, recolor or rotate to fix the tree. |

## Big ideas

- A plain BST is simple, but performance depends on shape.
- AVL trees are more strictly balanced, so searching is very predictable.
- Splay trees are useful when recently accessed values are likely to be accessed again.
- Red-black trees are less strict than AVL trees, but still guarantee good performance.
- Rotations are the key operation used by self-balancing trees.

## Quick examples

### Plain BST problem

```text
Insert 1, 2, 3, 4

1
 \
  2
   \
    3
     \
      4
```

This shape is almost a linked list, so search can become slow.

### Balanced tree idea

```text
      2
     / \
    1   3
         \
          4
```

A balanced shape keeps search paths shorter.

### Rotation idea

```text
Right rotation around 30:

    30          20
   /           /  \
  20    ->    10  30
 /
10
```

## Study order

Review BST first. Then study AVL rotations. After that, compare splay behavior and red-black color rules.
