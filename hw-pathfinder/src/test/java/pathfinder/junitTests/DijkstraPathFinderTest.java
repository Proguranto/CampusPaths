package pathfinder.junitTests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import graph.Graph;
import pathfinder.DijkstraPathFinder;

/**
 * DijkstraPathFinderTest is a glassbox test of the DijkstraPathFinder class.
 */
public class DijkstraPathFinderTest {

    private static Graph<String, Double> g = new Graph<>();

    private static Graph.Node<String> n1 = new Graph.Node<>("n1");

    private static Graph.Node<String> n2 = new Graph.Node<>("n2");

    private static Graph.Node<String> n3 = new Graph.Node<>("n3");

    private static DijkstraPathFinder<String, Double> d;

    @BeforeClass
    public static void m() {
        g.insertNode(n1);
        g.insertNode(n2);

        d = new DijkstraPathFinder<>(g);
    }

    // Testing if shortestPathFinder will return null when path not found
    // or nodes do not exist in graph.
    @Test
    public void testShortestPathFinder() {
        assertNull(d.shortestPathFinder(n3.getLabel(), n3.getLabel()));
        assertNull(d.shortestPathFinder(n3.getLabel(), n2.getLabel()));
        assertNull(d.shortestPathFinder(n2.getLabel(), n3.getLabel()));
        assertNull(d.shortestPathFinder(n2.getLabel(), n3.getLabel()));
    }
}
