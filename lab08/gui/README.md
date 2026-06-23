# Lab 08: Graph Algorithms and Shortest Paths

Lab08 focuses on graph representation, automatic graph visualization, graph traversal, reachability, connectedness, cycles, weighted edges, edge relaxation, and Dijkstra's shortest path algorithm.

The lab uses inheritance. `Step01GraphBasics.java` is the base graph data structure and Swing visualizer. The later files extend it so they reuse vertices, edges, degrees, neighbors, coloring, path highlighting, and visualization.

The graph visualizer is automatic and crossing-aware. You do not give screen coordinates for vertices. Add vertices by name, or just add edges and the missing vertices are created automatically. The visualizer tries many circular vertex orders and keeps the one with fewer edge crossings and shorter edge spans.

## Concepts

| Step | Java file                          | Concept                                               | Simple description                                                                                           | Example                                                                                        |
| ---- | ---------------------------------- | ----------------------------------------------------- | ------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------- |
| 1    | `Step01GraphBasics.java`           | Graph data structure and crossing-aware visualization | Store vertices and edges, draw them with Swing, and automatically arrange vertices to reduce edge crossings. | Vertices `{a, b, c, d}` with edges `(a,c)`, `(b,c)`, `(b,d)`, `(c,d)` gives degree of `c = 3`. |
| 2    | `Step02DirectedGraphDegrees.java`  | Directed graph degrees                                | Extend the base graph and compute in-degree and out-degree for one-way edges.                                | Edge `LAX -> ORD` gives `LAX` one outgoing edge and `ORD` one incoming edge.                   |
| 3    | `Step03PathsAndReachability.java`  | Paths and reachability                                | Extend the directed graph and find whether one vertex can reach another.                                     | If `U -> V -> X -> Z -> Y`, then `Y` is reachable from `U`.                                    |
| 4    | `Step04ConnectedComponents.java`   | Connected components                                  | Extend the base graph and group vertices into separate connected pieces.                                     | `{a,b,c}` and `{d,e,f}` are two connected components.                                          |
| 5    | `Step05DepthFirstSearch.java`      | Depth-first search                                    | Extend the base graph and explore one path as far as possible before backtracking.                           | DFS from `a` may visit `a, b, e, f, c` before returning.                                       |
| 6    | `Step06BreadthFirstSearch.java`    | Breadth-first search                                  | Extend DFS and use a queue to visit vertices level by level.                                                 | BFS gives a shortest path by number of edges in an unweighted graph.                           |
| 7    | `Step07CycleDetection.java`        | Cycle detection                                       | Extend BFS and detect whether an undirected graph contains a cycle.                                          | `A-B`, `B-C`, `C-A` forms a cycle.                                                             |
| 8    | `Step08WeightedGraph.java`         | Weighted graphs                                       | Extend the base graph and attach costs to edges.                                                             | A path with fewer edges may cost more than a longer path.                                      |
| 9    | `Step09EdgeRelaxation.java`        | Edge relaxation                                       | Extend weighted graphs and update distance and previous tables.                                              | If `D[A] = 0` and edge `A -> D` has weight `1`, then `D[D]` becomes `1`.                       |
| 10   | `Step10DijkstraShortestPaths.java` | Dijkstra's algorithm                                  | Extend edge relaxation and use a priority queue to find shortest paths.                                      | From `A`, Dijkstra finds the minimum-cost path to every reachable vertex.                      |

## Inheritance structure

```text
Step01GraphBasics
├── Step02DirectedGraphDegrees
│   └── Step03PathsAndReachability
├── Step04ConnectedComponents
├── Step05DepthFirstSearch
│   └── Step06BreadthFirstSearch
│       └── Step07CycleDetection
└── Step08WeightedGraph
    └── Step09EdgeRelaxation
        └── Step10DijkstraShortestPaths
```

## Big ideas

- `Step01GraphBasics` is the reusable graph data structure.
- Later steps extend the previous code instead of copying graph methods.
- Vertices store a name and a color.
- Edges store endpoints, weight, direction, and color.
- Vertex screen positions are computed automatically by the visualizer.
- The visualizer uses a crossing-aware circular layout heuristic, so students do not need to choose coordinates.
- You can call `addVertex("A")`, `addVertices("A", "B", "C")`, or simply `addEdge("A", "B")`.
- DFS explores deeply before backtracking.
- BFS explores level by level and gives shortest paths by edge count.
- Weighted graphs need cost-aware algorithms.
- Edge relaxation is the update rule behind Dijkstra's algorithm.
- Dijkstra's algorithm finds shortest paths with nonnegative edge weights.

## Quick examples

### Compile all files

```bash
javac *.java
```

### Run one lab step

```bash
java Step01GraphBasics
java Step05DepthFirstSearch
java Step10DijkstraShortestPaths
```

### Base graph usage

```java
Step01GraphBasics graph = new Step01GraphBasics();
graph.addVertices("a", "b", "c");
graph.addEdge("a", "c");
graph.addEdge("b", "c");
graph.visualize();
```

### Even shorter usage

```java
Step01GraphBasics graph = new Step01GraphBasics();
graph.addEdge("a", "c");
graph.addEdge("b", "c");
graph.visualize();
```

### Inheritance usage

```java
Step10DijkstraShortestPaths graph = new Step10DijkstraShortestPaths();
graph.addEdge("A", "B", 2);
graph.addEdge("A", "D", 1);
graph.addEdge("D", "B", 4);
graph.dijkstra("A");
```

## Study order

Study `Step01GraphBasics.java` first because it contains the shared graph data structure and automatic visualization. Then study directed graphs and reachability. After that, study connected components, DFS, BFS, and cycle detection. Finish with weighted graphs, edge relaxation, and Dijkstra's algorithm.

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
