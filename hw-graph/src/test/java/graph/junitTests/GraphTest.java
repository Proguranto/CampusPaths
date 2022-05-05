package graph.junitTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import graph.*;

/**
 * GraphTest is a glassbox test of the Graph class.
 */
public class GraphTest {

    private static Graph g0;
    private static Graph g1;
    private static Graph g21;
    private static Graph g22;

    @Before
    public void m() {
        g0 = new Graph();

        g1 = new Graph();
        g1.addNode(g1.new Node("n1"));

        g21 = new Graph();
        g21.addNode(g21.new Node("n1"));
        g21.addNode(g21.new Node("n2"));
        g21.addEdge(g21.new Edge("n1", "n2", "5"));

        g22 = new Graph();
        g22.addNode(g22.new Node("n1"));
        g22.addNode(g22.new Node("n2"));
        g22.addEdge(g22.new Edge("n2", "n1", "4"));
    }

    @Test
    public void testSizeNode() {
        // tested in test script
    }

    @Test
    public void testSizeEdge() {
        assertEquals(g0.sizeEdge(), 0);
        assertEquals(g1.sizeEdge(), 0);
        assertEquals(g21.sizeEdge(), 1);
        assertEquals(g22.sizeEdge(), 2);
    }

    @Test
    public void testContainsNode() {
        assertFalse(g0.containsNode(g0.new Node("n1")));
        assertTrue(g1.containsNode(g1.new Node("n1")));
    }

    @Test
    public void testContainsEdge() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testGetChildren() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testGetNodes() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testGetEdges() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
        // need to test
    }

    @Test
    public void testAddNode() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testRemoveNode() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
        // need to test
    }

    @Test
    public void testAddEdge() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testRemoveEdge() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
        // need to test
    }

    @Test
    public void testChangeLabel() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
        // need to test
    }

    @Test
    public void testChangeNodeName() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
        // need to test
    }

    @Test
    public void testNodeEquals() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testNodeHashCode() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testEdgeEquals() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }

    @Test
    public void testEdgeHashCode() {
        // TODO: Implement this.
        throw new RuntimeException("This method is not yet implemented.");
    }
}
