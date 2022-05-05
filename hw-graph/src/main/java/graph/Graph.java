package graph;

import java.util.List;

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

    /**
     * Creates an empty graph.
     */
    public Graph() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * The number of nodes in this.
     *
     * @return the number of nodes in this
     */
    public int sizeNode() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * The number of edges in this.
     *
     * @return the number of nodes in this
     */
    public int sizeEdge() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Checks if this contains a node with the name n.
     *
     * @param n target node
     * @return true if this contains node n, false otherwise
     * @throws IllegalArgumentException if n = null
     * @spec.requires n != null
     */
    public boolean containsNode(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Checks whether there exist an edge with label "label" from parent
     * node to child node.
     *
     * @param parent parent node
     * @param child child node
     * @param label edge label
     * @return true if there exist an edge with label "label" from parent node
     * to child node.
     * @throws IllegalArgumentException if parent = null or child = null or label = null
     * @spec.requires parent != null and child != null and label != null
     */
    public boolean containsEdge(Node parent, Node child, String label) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Gets all the children nodes of node n in lexicographical (alphabetical) order.
     *
     * @param n node to get children of
     * @return a list of all the children nodes of n in lexicographical (alphabetical) order
     * @throws IllegalArgumentException if n = null
     * @spec.requires n != null
     */
    public List<Node> getChildren(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Gets all the nodes in this.
     *
     * @return list of all the nodes in this
     */
    public List<Node> getNodes() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Gets all the edges in this.
     *
     * @return list of all the edges in this
     */
    public List<Node> getEdges() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }


    /**
     * Adds a node n to this.
     *
     * @param n node to be added to this
     * @throws IllegalArgumentException if n = null
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects adds n to this, if node with same value as n exist in this
     *               then does nothing
     */
    public void addNode(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Removes a node n from this.
     *
     * @param n node to be removed
     * @throws IllegalArgumentException if n = null
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects removes n from this if n exist in this, otherwise nothing
     */
    public void removeNode(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Adds an edge to this.
     *
     * @param e edge to be added to this
     * @throws IllegalArgumentException if e = null
     * @spec.requires e != null
     * @spec.modifies this
     * @spec.effects adds edge e to this, if edge with same value as e already
     *               exist in this, then does nothing
     */
    public void addEdge(Edge e) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Removes an edge from this.
     *
     * @param e edge to be removed
     * @throws IllegalArgumentException if e = null
     * @spec.requires e != null
     * @spec.modifies this
     * @spec.effects removes edge e from this if e exist in this, otherwise nothing
     */
    public void removeEdge(Edge e) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Changes the label of an edge in this.
     *
     * @param e edge that label is to be changed
     * @param newLabel new label for edge
     * @return true if label of e is successfully changed to newLabel, otherwise false
     * @throws IllegalArgumentException if e = null or newLabel = null
     * @spec.requires e != null and newLabel != null
     * @spec.modifies this
     * @spec.effects changes the label of e to newLabel if e exist in graph and does
     *               not create a duplicate edge in this
     */
    public boolean changeLabel(Edge e, String newLabel) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Changes the name of a node in this.
     *
     * @param n node that name is to be changed
     * @param newName new name for edge
     * @return true if name is successfully changed to newName, otherwise false
     * @throws IllegalArgumentException if n = null or newName = null
     * @spec.requires n != null and newName != null
     * @spec.modifies this
     * @spec.effects changes the name of n to newName if n exist in graph and
     *               changing it does not create a duplicate node in this
     */
    public boolean changeNodeName(Node n, String newName) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Represents a mutable node and contains information about the edges and
     * edge labels from the node.
     */
    public class Node {

        /**
         * Creates a node with a name.
         *
         * @param name node's name
         * @spec.requires name != null
         */
        public Node(String name) {
            //TODO: Implement this.
            throw new RuntimeException("This method is not yet implemented.");
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) {
                return false;
            }
            //TODO: Implement this.
            throw new RuntimeException("This method is not yet implemented.");
        }

        @Override
        public int hashCode() {
            //TODO: Implement this.
            throw new RuntimeException("This method is not yet implemented.");
        }
    }

    /**
     * Represents a mutable edge with a label, parent node, and child node.
     */
    public class Edge {

        /**
         * Creates an edge with a label, parent node, and child node.
         *
         * @param parent name of parent node
         * @param child name of child node
         * @param label edge label
         * @spec.requires parent != null and child != null and label != null
         */
        public Edge(String parent, String child, String label) {
            //TODO: Implement this.
            throw new RuntimeException("This method is not yet implemented.");
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) {
                return false;
            }
            //TODO: Implement this.
            throw new RuntimeException("This method is not yet implemented.");
        }

        @Override
        public int hashCode() {
            //TODO: Implement this.
            throw new RuntimeException("This method is not yet implemented.");
        }
    }
}
