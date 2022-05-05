package graph.junitTests;

import org.junit.Test;
import static org.junit.Assert.*;
import graph.*;

/**
 * GraphTest is a glassbox test of the Graph class.
 */
public class GraphTest {

    /**
     * An empty graph.
     */
    private static Graph g1 = new Graph();

    /**
     * Test if the size of graph has the correct output.
     */
    @Test
    public void testSize() {
        assertEquals(0, g1.size());
    }

    /**
     * Test if graphs are empty with the correct output.
     */
    @Test
    public void isEmpty() {
    }

    /**
     * Tests if a graph contains a node with the correct output.
     */
    @Test
    public void containsNode() {
    }
}
