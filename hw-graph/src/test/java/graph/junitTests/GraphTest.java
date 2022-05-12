package graph.junitTests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import graph.*;
import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 * GraphTest is a glassbox test of the Graph class.
 */
public class GraphTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10);
    // 10 seconds max per method tested

    private static Graph g0 = new Graph();

    private static Graph g10 = new Graph();

    private static Graph g11 = new Graph();

    private static Graph g12 = new Graph();

    private static Graph g20 = new Graph();

    private static Graph g21 = new Graph();

    private static Graph g22 = new Graph();

    private static Graph.Node n1 = g0.new Node("n1");

    private static Graph.Node n2 = g0.new Node("n2");

    private static Graph.Node n3 = g0.new Node("n3");

    private static Graph.Node dup = g0.new Node("n3");

    private static Graph.Node dup2 = g0.new Node("n3");

    private static Graph.Edge e11a = g0.new Edge(n1, n1, "e11a");

    private static Graph.Edge e11b = g0.new Edge(n1, n1, "e11b");

    private static Graph.Edge e12 = g0.new Edge(n1, n2, "e12");

    private static Graph.Edge e21 = g0.new Edge(n2, n1, "e21");

    private static Graph.Edge e32 = g0.new Edge(n3, n2, "e12");

    private static Graph.Edge dup3 = g0.new Edge(n3, n2, "e12");

    private static Graph.Edge dup4 = g0.new Edge(n3, n2, "e12");

    @BeforeClass
    public static void m() {
        g10.insertNode(n1);

        g11.insertNode(n1);
        g11.insertEdge(e11a);

        g12.insertNode(n1);
        g12.insertEdge(e11a);
        g12.insertEdge(e11b);

        g20.insertNode(n1);
        g20.insertNode(n2);

        g21.insertNode(n1);
        g21.insertNode(n2);
        g21.insertEdge(e12);

        g22.insertNode(n1);
        g22.insertNode(n2);
        g22.insertEdge(e12);
        g22.insertEdge(e21);
    }

    // Testing equals() for Nodes.
    @Test
    public void testNodeEquals() {
        // Reflexive
        assertTrue(n1.equals(n1));

        // Symmetric
        assertTrue(n3.equals(dup));
        assertTrue(dup.equals(n3));

        // Transitive
        assertTrue(n3.equals(dup));
        assertTrue(dup.equals(dup2));
        assertTrue(n3.equals(dup2));

        // Consistent
        assertTrue(n1.equals(n1));

        // False with null
        assertFalse(n1.equals(null));

        // Failure case
        assertFalse(n1.equals(n2));
    }

    // Testing hashCode() for Nodes.
    @Test
    public void testNodeHashCode() {
        assertTrue(n1.hashCode() == n1.hashCode());
        assertTrue(n3.equals(dup));
        assertTrue(n3.hashCode() == dup.hashCode());

        // Failure case
        assertFalse(n1.hashCode() == n2.hashCode());
    }

    // Testing equals() for Edge.
    @Test
    public void testEdgeEquals() {
        // Reflexive
        assertTrue(e12.equals(e12));

        // Symmetric
        assertTrue(e32.equals(dup3));
        assertTrue(dup3.equals(e32));

        // Transitive
        assertTrue(e32.equals(dup3));
        assertTrue(dup3.equals(dup4));
        assertTrue(e32.equals(dup4));

        // Consistent
        assertTrue(e12.equals(e12));

        // False with null
        assertFalse(e12.equals(null));

        // Failure case
        assertFalse(e12.equals(e21));
    }

    // Testing hashCode() for Edge.
    @Test
    public void testEdgeHashCode() {
        assertTrue(e12.hashCode() == e12.hashCode());
        assertTrue(e32.equals(dup3));
        assertTrue(e32.hashCode() == dup3.hashCode());

        // Failure case
        assertFalse(e12.hashCode() == e32.hashCode());
    }

    // Testing getLabel() for Node.
    @Test
    public void testNodeGetLabel() {
        assertTrue(n1.getLabel().equals("n1"));
        assertFalse(n1.getLabel().equals("n2"));
    }

    // Testing getLabel() for Edge.
    @Test
    public void testEdgeGetLabel() {
        assertTrue(e12.getLabel().equals("e12"));
        assertFalse(e12.getLabel().equals("e32"));
    }

    // Testing getChild() for Edge.
    @Test
    public void testEdgeGetChild() {
        assertTrue(e12.getChild().equals(n2));
        assertFalse(e12.getChild().equals(n1));
    }

    //Testing getParent() for Edge.
    @Test
    public void testEdgeGetParent() {
        assertTrue(e12.getParent().equals(n1));
        assertFalse(e12.getParent().equals(n2));
    }

    // Testing sizeNode() for graphs with different number of nodes.
    @Test
    public void testSizeNode() {
        assertEquals(0, g0.sizeNode());
        assertEquals(1, g10.sizeNode());
        assertEquals(1, g11.sizeNode());
        assertEquals(1, g12.sizeNode());
        assertEquals(2, g20.sizeNode());
        assertEquals(2, g21.sizeNode());
        assertEquals(2, g22.sizeNode());
    }

    // Testing sizeEdge() for graphs with different number of nodes.
    @Test
    public void testSizeEdge() {
        assertEquals(0, g0.sizeEdge());
        assertEquals(0, g10.sizeEdge());
        assertEquals(1, g11.sizeEdge());
        assertEquals(2, g12.sizeEdge());
        assertEquals(0, g20.sizeEdge());
        assertEquals(1, g21.sizeEdge());
        assertEquals(2, g22.sizeEdge());
    }

    // Testing containsNode() for different kinds of graphs.
    @Test
    public void testContainsNode() {
        assertFalse(g0.containsNode(n1));
        assertTrue(g10.containsNode(n1));
        assertTrue(g11.containsNode(n1));
        assertTrue(g12.containsNode(n1));
        assertTrue(g20.containsNode(n1));
        assertTrue(g20.containsNode(n2));
        assertTrue(g21.containsNode(n1));
        assertTrue(g21.containsNode(n2));
        assertTrue(g22.containsNode(n1));
        assertTrue(g22.containsNode(n2));
    }

    // Testing containsEdge for different kinds of graphs.
    @Test
    public void testContainsEdge() {
        assertFalse(g0.containsEdge(e11a));
        assertFalse(g10.containsEdge(e11a));
        assertTrue(g11.containsEdge(e11a));
        assertTrue(g12.containsEdge(e11a));
        assertTrue(g12.containsEdge(e11b));
        assertFalse(g20.containsEdge(e11b));
        assertTrue(g21.containsEdge(e12));
        assertTrue(g22.containsEdge(e12));
        assertTrue(g22.containsEdge(e21));
    }

    // Testing insertEdge() what happens if graph doesn't
    // contain child or parent node.
    @Test
    public void testInsertEdge() {
        // parent and child not in graph
        assertFalse(g0.insertEdge(e12));

        // child in graph but not parent
        assertFalse(g10.insertEdge(e12));

        // parent in graph but not child
        assertFalse(g20.insertEdge(e32));
    }
}
