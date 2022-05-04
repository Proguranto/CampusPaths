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
     *
     * @spec.modifies this
     * @spec.effects this = empty graph
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
     * Checks whether there exist an edge from node a to b, in this.
     *
     * @param a parent node
     * @param b child node
     * @return true if there exist an edge from node a to b, in this
     * @spec.requires a != null and b != null
     */
    public boolean containsEdge(Node a, Node b) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Gets all the parent nodes of node n in lexicographical (alphabetical) order.
     *
     * @param n
     * @return a list of all the parent nodes of n in lexicographical (alphabetical) order
     * @spec.requires n != null
     */
    public List<Node> getParents(Node n) {
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
     * @return a list of all the nodes in this in lexicographical (alphabetical) order
     */
    public List<Node> getNodes() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    // consider that nodes might be modified

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
     * Adds an edge from node a to node b.
     *
     * @param a parent node
     * @param b child node
     * @spec.requires a != null and b != null
     * @spec.modifies this
     * @spec.effects adds an edge from a to b, if a and b exist in this
     */
    public void addEdge(Node a, Node b) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    /**
     * Removes an edge from node a to b in this.
     *
     * @param a parent node
     * @param b child node
     * @spec.requires a != null and b != null
     * @spec.modifies this
     * @spec.effects removes an edge from a to b, if a and b exist in this and
     *               there exist an edge from a to b
     */
    public void removeEdge(Node a, Node b) {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    // size
    // isEmpty
    // containsNode
    // containsEdge
    // getParents
    // getChildren
    // getNodes
    // entrySet
    // path

    // equals
    // hashcode

    // addNode
    // removeNode
    // addEdge
    // removeEdge

    /**
     * Represents a node and contains information about the edges and
     * edge labels from the node.
     */
    class Node {

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

    // equals
    // hashCode
}
