package graph;

import java.util.List;

/**
 * Represents a mutable directed labeled graph where no 2 edges
 * with the same parent and child nodes will have the same edge label.
 * The children of node B are the nodes to which there is an edge from.
 * The parents of node B are the nodes from which there is an edge to B.
 * Also, the graph contains no duplicate nodes.
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
    public int size() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Checks whether this has no nodes or not.
     *
     * @return true if this has no nodes, false otherwise
     */
    public boolean isEmpty() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Checks if this contains a node n.
     *
     * @param n the target node
     * @return true if this contains node n
     * @spec.requires n != null
     */
    public boolean containsNode(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Checks whether there exist an edge with label from node a to b, in this.
     *
     * @param a parent node
     * @param b child node
     * @param label edge label
     * @return true if there exist an edge with label from node a to b, in this
     * @spec.requires a != null and b != null and label != null
     */
    public boolean containsEdge(Node a, Node b, String label) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Gets all the children nodes of node n in lexicographical (alphabetical) order.
     *
     * @param n
     * @return a list of all the children nodes of n in lexicographical (alphabetical) order
     * @spec.requires n != null
     */
    public List<Node> getChildren(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Gets all the nodes in this.
     *
     * @return a list of all the nodes in this in lexicographical (alphabetical) order, if
     *         this is empty, then return empty list.
     */
    public List<Node> getNodes() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Adds a node n to this.
     *
     * @param n node to be added to this
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects adds n to this, if no node equal to n exist in this
     */
    public void addNode(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Removes a node n from this.
     *
     * @param n node to be removed
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects removes n from this, if n exist in this
     */
    public void removeNode(Node n) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Adds an edge with label from node a to node b.
     *
     * @param a parent node
     * @param b child node
     * @param label edge label
     * @spec.requires a != null and b != null and label != null
     * @spec.modifies this
     * @spec.effects adds an edge with label from a to b, if a and b exist in this
     */
    public void addEdge(Node a, Node b, String label) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Removes an edge with label from node a to b in this.
     *
     * @param a parent node
     * @param b child node
     * @param label edge label
     * @spec.requires a != null and b != null and label != null
     * @spec.modifies this
     * @spec.effects removes an edge with label from a to b, if a and b exist in this and
     *               there exist an edge with label from a to b
     */
    public void removeEdge(Node a, Node b, String label) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Represents a node and contains information about the edges and
     * edge labels from the node.
     */
    class Node {

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
}
