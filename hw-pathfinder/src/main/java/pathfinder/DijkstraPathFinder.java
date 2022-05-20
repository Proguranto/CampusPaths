package pathfinder;

import graph.*;
import pathfinder.datastructures.Path;

import java.util.*;

/**
 * Represents a path finding algorithm that uses Dijkstra's Algorithm to find
 * the minimum-cost path between two nodes in a graph. Note that all the
 * edge labels in the graph are non-negative.
 *
 * @param <N> node data type
 * @param <E> edge data type
 */
public class DijkstraPathFinder<N,E extends Number> {

    private static final boolean DEBUG = false;

    // The graph to find the shortest path in.
    //
    // RI: graph != null and edge labels in the graph are non-negative
    //     and graph does not contain null.
    // AF(this) = shortestPathFinder() returns the shortest path in the graph
    //            that is passed in the constructor from the given start and
    //            end nodes.
    private Graph<N,E> graph;

    /**
     * Creates a graph for the path finding algorithm.
     *
     * @param graph the graph used in the path finding algorithm.
     * @spec.requires  graph != null and graph contains no negative edge weights
     * @spec.effects constructs a graph to be used in the path finding
     *               algorithm.
     */
    public DijkstraPathFinder(Graph<N,E> graph) {
        checkRep();
        this.graph = new Graph<>();

        // Copy-in to avoid rep exposure.
        for (Graph.Node<N> n : graph.listNodes()) {
            this.graph.insertNode(n);
        }
        for (Graph.Edge<N,E> e : graph.listEdges()) {
            assert(e.getLabel().doubleValue() >= 0);
            this.graph.insertEdge(e);
        }
        checkRep();
    }

    /**
     * Finds the shortest path between two nodes in the graph.
     *
     * @param start starting node
     * @param dest destination node
     * @return the shortest path from the start node to dest node
     *         null if no path exist or the nodes are not in the graph
     * @spec.requires start != null and dest != null and
     *                start and dest are nodes in the graph
     */
    public Path<N> shortestPathFinder(N start, N dest) {
        checkRep();
        Graph.Node<N> startNode = new Graph.Node<>(start);
        Graph.Node<N> destNode = new Graph.Node<>(dest);

        if (!graph.containsNode(startNode) || !graph.containsNode(destNode)) return null;

        Comparator<Path<N>> compPaths = new Comparator<>() {
            @Override
            public int compare(Path<N> o1, Path<N> o2) {
                if (o1.getCost() > o2.getCost()) {
                    return 1;
                } else if (o1.getCost() < o2.getCost()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Queue<Path<N>> active = new PriorityQueue<>(compPaths);
        Set<Graph.Node<N>> finished = new HashSet<>();
        active.add(new Path<>(start));

        // Invariant: Any node in finished has already had the min-cost path found.
        //            Active contains new paths to be explored.
        while(active.size() != 0) {
            Path<N> minPath = active.remove();
            Graph.Node<N> minDest = new Graph.Node<>(minPath.getEnd());

            if (minDest.equals(destNode)) {
                checkRep();
                return minPath;
            }

            if (finished.contains(minDest)) {
                continue;
            }

            for (Graph.Edge<N,E> e : graph.childrenOf(minDest)) {
                if (!finished.contains(e.getChild())) {
                    Path<N> newPath = minPath.extend(e.getChild().getLabel(), e.getLabel().doubleValue());
                    active.add(newPath);
                }
            }

            finished.add(minDest);
        }
        // No more paths to be explored or found min-cost path to dest node.
        checkRep();
        return null;
    }

    private void checkRep() {
        assert graph != null : "graph is null!";
        if (DEBUG) {
            for(Graph.Edge<N,E> e : graph.listEdges()) {
                assert e.getLabel().doubleValue() >= 0 : "path cost is negative!";
            }
            assert !graph.containsNode(null) : "graph contains null!";
            assert !graph.containsEdge(null) : "graph contains null!";
        }
    }
}
