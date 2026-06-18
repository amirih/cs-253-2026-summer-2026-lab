# Lab 04: Search Trees and Self-Balancing Trees

Lab04 studies ordered tree structures. The main goal is to understand how trees support searching, insertion, deletion, traversal, and balancing.

## Concepts

| Step | Concept                   | Simple description                                                                     | Example                                                                     |
| ---- | ------------------------- | -------------------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| 1    | Binary search tree        | A binary tree where smaller values go left and larger values go right.                 | Insert `5, 3, 7`: `3` goes left of `5`, `7` goes right.                     |
| 2    | BST traversals            | Different ways to visit tree nodes.                                                    | In-order traversal of a BST prints values in sorted order.                  |
| 3    | Ordered operations        | Operations that use the sorted structure of a BST.                                     | Find min, max, predecessor, successor, floor, or ceiling.                   |
| 4    | BST deletion              | Remove a node while keeping the BST property true.                                     | Deleting a node with two children often replaces it with its successor.     |
| 5    | AVL balance check         | Checks whether every node has left and right subtree heights that differ by at most 1. | A node with left height `2` and right height `1` is balanced.               |
| 6    | AVL insertion rotations   | After insertion, rotations restore AVL balance.                                        | Inserting `30, 20, 10` causes a right rotation.                             |
| 7    | Splay tree                | After access, move the accessed node to the root using rotations.                      | Searching for `7` brings `7` closer to the top for faster repeated access.  |
| 8    | Four-way multiway tree    | A tree node can store multiple keys and have multiple children.                        | A node with keys `[10, 20, 30]` can separate four child ranges.             |
| 9    | Red-black tree validation | Check color rules that keep the tree approximately balanced.                           | The root must be black, and red nodes cannot have red children.             |
| 10   | Red-black tree insertion  | Insert like a BST, then recolor or rotate to restore red-black rules.                  | Inserting a red node under a red parent may require recoloring or rotation. |

## Big ideas

- BSTs are fast when balanced, but can become slow if they grow like a linked list.
- Tree traversal order changes the meaning of the output.
- AVL trees keep very strict balance using rotations.
- Splay trees adapt to recent access patterns.
- Red-black trees use color rules to stay balanced enough for efficient operations.
- Multiway trees store more than one key per node and are useful for disk-friendly tree designs.

## Quick examples

### BST property

```text
      8
     / \
    4   12
```

Every value in the left subtree is less than `8`. Every value in the right subtree is greater than `8`.

### AVL rotation idea

```text
Before: 30 -> 20 -> 10 is too left-heavy
After right rotation: 20 becomes the root, with 10 left and 30 right
```

### Red-black rule example

```text
A red node cannot have a red child.
If this happens after insertion, fix it with recoloring or rotations.
```

## Study order

Start with plain BST insertion, traversal, ordered operations, and deletion. Then study AVL trees, splay trees, multiway trees, and red-black trees.
