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

    private static Graph<String, String> g0 = new Graph<>();

    private static Graph<String, String> g10 = new Graph<>();

    private static Graph<String, String> g11 = new Graph<>();

    private static Graph<String, String> g12 = new Graph<>();

    private static Graph<String, String> g20 = new Graph<>();

    private static Graph<String, String> g21 = new Graph<>();

    private static Graph<String, String> g22 = new Graph<>();

    private static Graph.Node<String> n1 = new Graph.Node<>("n1");

    private static Graph.Node<String> n2 = new Graph.Node<>("n2");

    private static Graph.Node<String> n3 = new Graph.Node<>("n3");

    private static Graph.Node<String> dup = new Graph.Node<>("n3");

    private static Graph.Node<String> dup2 = new Graph.Node<>("n3");

    private static Graph.Edge<String, String> e11a = new Graph.Edge<>(n1, n1, "e11a");

    private static Graph.Edge<String, String> e11b = new Graph.Edge<>(n1, n1, "e11b");

    private static Graph.Edge<String, String> e12 = new Graph.Edge<>(n1, n2, "e12");

    private static Graph.Edge<String, String> e21 = new Graph.Edge<>(n2, n1, "e21");

    private static Graph.Edge<String, String> e32 = new Graph.Edge<>(n3, n2, "e12");

    private static Graph.Edge<String, String> dup3 = new Graph.Edge<>(n3, n2, "e12");

    private static Graph.Edge<String, String> dup4 = new Graph.Edge<>(n3, n2, "e12");

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
        assertEquals(n1,n1);

        // Symmetric
        assertEquals(n3,dup);
        assertEquals(dup,n3);

        // Transitive
        assertEquals(n3,dup);
        assertEquals(dup,dup2);
        assertEquals(n3,dup2);

        // Consistent
        assertEquals(n1,n1);

        // False with null
        assertNotEquals(n1,null);

        // Failure case
        assertNotEquals(n1,n2);
    }

    // Testing hashCode() for Nodes.
    @Test
    public void testNodeHashCode() {
        assertEquals(n1.hashCode(),n1.hashCode());
        assertEquals(n3,dup);
        assertEquals(n3.hashCode(),dup.hashCode());

        // Failure case
        assertNotEquals(n1.hashCode(),n2.hashCode());
    }

    // Testing equals() for Edge.
    @Test
    public void testEdgeEquals() {
        // Reflexive
        assertEquals(e12,e12);

        // Symmetric
        assertEquals(e32, dup3);
        assertEquals(dup3,e32);

        // Transitive
        assertEquals(e32,dup3);
        assertEquals(dup3,dup4);
        assertEquals(e32,dup4);

        // Consistent
        assertEquals(e12,e12);

        // False with null
        assertNotEquals(e12,null);

        // Failure case
        assertNotEquals(e12,e21);
    }

    // Testing hashCode() for Edge.
    @Test
    public void testEdgeHashCode() {
        assertEquals(e12.hashCode(), e12.hashCode());
        assertEquals(e32,dup3);
        assertEquals(e32.hashCode(),dup3.hashCode());

        // Failure case
        assertNotEquals(e12.hashCode(),e32.hashCode());
    }

    // Testing getLabel() for Node.
    @Test
    public void testNodeGetLabel() {
        assertEquals(n1.getLabel(),"n1");
        assertNotEquals(n1.getLabel(),"n2");
    }

    // Testing getLabel() for Edge.
    @Test
    public void testEdgeGetLabel() {
        assertEquals(e12.getLabel(),"e12");
        assertNotEquals(e12.getLabel(),"e32");
    }

    // Testing getChild() for Edge.
    @Test
    public void testEdgeGetChild() {
        assertEquals(e12.getChild(),n2);
        assertNotEquals(e12.getChild(),n1);
    }

    //Testing getParent() for Edge.
    @Test
    public void testEdgeGetParent() {
        assertEquals(e12.getParent(),n1);
        assertNotEquals(e12.getParent(),n2);
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
