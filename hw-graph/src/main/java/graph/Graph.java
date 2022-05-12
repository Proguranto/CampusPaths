package graph;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a mutable directed labeled graph.
 * The children of node B are the nodes to which there is an edge from.
 * The parents of node B are the nodes from which there is an edge to B.
 * Also, the graph contains no duplicate nodes.
 *
 * Abstract Invariant: No two nodes have the same name, no 2 edges
 *                     with the same parent and child nodes will have
 *                     the same edge label.
 */
public class Graph {

    public final static boolean DEBUG = false;

    // Nodes and Edges are stored in a set.
    //
    // RI: edges != null and nodes != null and
    //     !edges.contains(null) and !nodes.contains(null)
    // AF(this) = A directed a labeled graph with
    //            nodes: all elements in nodes
    //            edges: all elements in edges
    private Set<Edge> edges;

    private Set<Node> nodes;

    /**
     * Creates an empty graph.
     *
     * @spec.effects creates an empty graph
     */
    public Graph() {
        edges = new HashSet<>();
        nodes = new HashSet<>();

        checkRep();
    }

    /**
     * Gets the number of nodes in the graph.
     *
     * @return the number of nodes in the graph
     */
    public int sizeNode() {
        checkRep();
        return nodes.size();
    }

    /**
     * Gets the number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    public int sizeEdge() {
        checkRep();
        return edges.size();
    }

    /**
     * Checks if the graph contains a node n.
     *
     * @param n target node
     * @return true if the graph contains node n, false otherwise
     * @spec.requires n != null
     */
    public boolean containsNode(Node n) {
        checkRep();
        return nodes.contains(n);
    }

    /**
     * Checks whether there exist an edge e in the graph.
     *
     * @param e target edge
     * @return true if there exist an edge e in the graph
     * @spec.requires e != null
     */
    public boolean containsEdge(Edge e) {
        checkRep();
        return edges.contains(e);
    }

    /**
     * Gets all the outgoing edges of node n.
     *
     * @param n node to get outgoing edges of
     * @return a list of all the outgoing edges of n
     * @spec.requires n != null
     */
    public List<Edge> childrenOf(Node n) {
        checkRep();

        // Finding outgoing edges.
        List<Edge> children = new ArrayList<>();
        for (Edge e : edges) {
            if (e.parent.equals(n)) {
                children.add(e);
            }
        }

        checkRep();
        return children;
    }

    /**
     * Gets all the nodes in the graph.
     *
     * @return list of all the nodes in the graph
     */
    public List<Node> listNodes() {
        checkRep();

        // Copy-out to avoid rep exposure.
        List<Node> result = new ArrayList<>();
        for (Node n : nodes) {
            result.add(n);
        }

        checkRep();
        return result;
    }

    /**
     * Inserts a node n to the graph.
     *
     * @param n node to be added to the graph
     * @return true if adds node n to the graph, false otherwise
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects inserts n to the graph and return true, if node with same value
     *               as n exist in the graph then does nothing and return false
     */
    public boolean insertNode(Node n) {
        checkRep();

        Boolean b = nodes.add(n);

        checkRep();
        return b;
    }

    /**
     * Inserts an edge e to the graph.
     *
     * @param e edge to be added to the graph
     * @return true if adds edge e to graph, false otherwise
     * @spec.requires e != null
     * @spec.modifies this
     * @spec.effects inserts edge e to the graph and returns true, if edge with same value as e already
     *               exist in the graph or if graph does not have the child or parent node
     *               in edge, then does nothing and return false
     */
    public boolean insertEdge(Edge e) {
        checkRep();

        if (nodes.contains(e.child) && nodes.contains(e.parent)) {
            Boolean b = edges.add(e);
            checkRep();
            return b;
        }

        checkRep();
        return false;
    }

    /**
     * Represents an immutable node with a label.
     */
    public class Node {

        // Node's label is stored as a string.
        //
        // RI: label != null
        // AF(this) = Node with label: label
        private final String label;

        /**
         * Creates a node with a label.
         *
         * @param label node's label
         * @spec.requires label != null
         * @spec.effects creates a node with a label
         */
        public Node(String label) {
            this.label = label;

            checkRep();
        }

        /**
         * Gets the label of the node.
         *
         * @return node's label
         */
        public String getLabel() {
            return this.label;
        }

        @Override
        public boolean equals(Object o) {
            checkRep();
            if (!(o instanceof Node)) {
                return false;
            }

            Node other = (Node) o;
            checkRep();
            return this.label.equals(other.label);
        }

        @Override
        public int hashCode() {
            checkRep();
            return label.hashCode();
        }

        private void checkRep() {
            assert label != null : "this.label is null!";
        }
    }

    /**
     * Represents an immutable edge with a label, parent node, and child node.
     */
    public class Edge {

        // Stores the parent node and child node as a Node, and label as a String.
        //
        // RI: parent != null and child != null and label != null
        // AF(this) = Edge with parent node: parent
        //                      child node: child
        //                      label: label
        private final Node parent;

        private final Node child;

        private final String label;

        /**
         * Creates an edge with a label, parent node, and child node.
         *
         * @param parent parent node
         * @param child child node
         * @param label edge label
         * @spec.requires parent != null and child != null and label != null
         */
        public Edge(Node parent, Node child, String label) {
            this.parent = parent;
            this.child = child;
            this.label = label;

            checkRep();
        }

        /**
         * Gets the edge's label.
         *
         * @return edge's label
         */
        public String getLabel() {
            return this.label;
        }

        /**
         * Gets the edge's child node.
         *
         * @return edge's child node
         */
        public Node getChild() {
            return this.child;
        }

        /**
         * Gets the edge's parent node.
         *
         * @return edge's parent node
         */
        public Node getParent() {
            return this.parent;
        }

        @Override
        public boolean equals(Object o) {
            checkRep();
            if (!(o instanceof Edge)) {
                return false;
            }

            Edge other = (Edge) o;
            checkRep();
            return this.parent.equals(other.parent) && this.child.equals(other.child)
                    && this.label.equals(other.label);
        }

        @Override
        public int hashCode() {
            return (this.parent.hashCode() + this.label.hashCode()) * this.child.hashCode();
        }

        private void checkRep() {
            assert parent != null : "this.parent is null!";
            assert child != null : "this.child is null!";
            assert label != null : "this.label is null!";
        }
    }

    private void checkRep() {
        assert this.edges != null : "this.edges is null!";
        assert this.nodes != null : "this.nodes is null!";
        if (DEBUG) {
            assert !this.edges.contains(null) : "this.edges has a null element!";
            assert !this.nodes.contains(null) : "this.nodes has a null element!";
        }
    }
}
