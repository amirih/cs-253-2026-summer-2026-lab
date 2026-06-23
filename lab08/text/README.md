# Lab 08: Graph Algorithms and Shortest Paths (simple)

Lab08 focuses on graph representation, graph traversal, reachability, connectedness, cycles, weighted edges, edge relaxation, and Dijkstra's shortest path algorithm.

## Concepts

| Step | Concept                | Simple description                                                                                            | Example                                                                                              |
| ---- | ---------------------- | ------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| 1    | Graph basics           | Store vertices and undirected edges, then check neighbors, adjacency, degree, and edge count.                 | Vertices `{a, b, c, d}` with edges `(a,c)`, `(b,c)`, `(b,d)`, `(c,d)` gives degree of `c = 3`.       |
| 2    | Directed graph degrees | Store one-way edges and compute in-degree and out-degree separately.                                          | Flight edge `LAX -> ORD` means `LAX` has one outgoing edge and `ORD` has one incoming edge.          |
| 3    | Paths and reachability | Determine whether one vertex can be reached from another and return one valid path.                           | If `U -> V -> X -> Y`, then `Y` is reachable from `U` and one path is `[U, V, X, Y]`.                |
| 4    | Connected components   | Group vertices into maximal connected subgraphs.                                                              | If `{a,b,c}` are connected and `{d,e}` are connected separately, the graph has two components.       |
| 5    | Depth-first search     | Explore one path as far as possible before backtracking.                                                      | Starting at `a`, DFS may visit `[a, b, e, f, c, d, g, h]` depending on edge order.                   |
| 6    | Breadth-first search   | Visit vertices level by level using a queue, giving shortest paths by number of edges in an unweighted graph. | In an unweighted graph, BFS from `a` to `h` may return `[a, d, h]` because it uses the fewest edges. |
| 7    | Cycle detection        | Check whether a graph contains a cycle and distinguish trees, forests, and DAGs.                              | Edges `A-B`, `B-C`, `C-A` form a cycle, so the graph is not a tree.                                  |
| 8    | Weighted graphs        | Attach a cost to each edge and compare path cost instead of only edge count.                                  | Path `[A, E, F]` may use fewer edges, but `[A, D, G, H, F]` may have lower total cost.               |
| 9    | Edge relaxation        | Try to improve the best known distance to a vertex using a new edge.                                          | If `D[A] = 0`, edge `A -> D` with weight `1` updates `D[D]` from infinity to `1`.                    |
| 10   | Dijkstra's algorithm   | Use a priority queue and repeated relaxation to find minimum-cost paths from one source.                      | From `A`, the shortest path to `F` can be reconstructed using the previous-vertex table.             |

## Big ideas

- A graph is made of vertices and edges.
- Undirected edges work both ways, while directed edges have an origin and destination.
- Degree counts incident edges; directed graphs use in-degree and out-degree.
- Reachability asks whether a path exists between two vertices.
- Connected components split a graph into connected groups.
- DFS is useful for deep exploration, cycle detection, and connectedness.
- BFS is useful for shortest paths by number of edges in unweighted graphs.
- Weighted graphs require cost-aware algorithms because fewer edges may not mean lower cost.
- Relaxation is the main update step used in shortest path algorithms.
- Dijkstra's algorithm finds shortest paths in graphs with nonnegative edge weights.

## Study order

Study graph basics first, then directed graphs and reachability. After that, study connected components, DFS, BFS, and cycle detection. Finish with weighted graphs, edge relaxation, and Dijkstra's algorithm.
