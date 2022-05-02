package sets;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * FiniteSetTest is a glassbox test of the FiniteSet class.
 */
public class FiniteSetTest {

  /** Test creating sets. */
  @Test
  public void testCreation() {
    assertEquals(Arrays.asList(),
        FiniteSet.of(new float[0]).getPoints());         // empty
    assertEquals(Arrays.asList(1f),
        FiniteSet.of(new float[] {1}).getPoints());      // one item
    assertEquals(Arrays.asList(1f, 2f),
        FiniteSet.of(new float[] {1, 2}).getPoints());   // two items
    assertEquals(Arrays.asList(1f, 2f),
        FiniteSet.of(new float[] {2, 1}).getPoints());   // two out-of-order
    assertEquals(Arrays.asList(-2f, 2f),
        FiniteSet.of(new float[] {2, -2}).getPoints());  // negative
  }

  // Some example sets used by the tests below.
  private static FiniteSet S0 = FiniteSet.of(new float[0]);   // Empty set.
  private static FiniteSet S1 = FiniteSet.of(new float[] {1}); // Set with one element.
  private static FiniteSet S12 = FiniteSet.of(new float[] {1, 2}); // Set with multiple elements.

  /** Test set equality. */
  @Test
  public void testEquals() {
    assertTrue(S0.equals(S0));
    assertFalse(S0.equals(S1));
    assertFalse(S0.equals(S12));

    assertFalse(S1.equals(S0));
    assertTrue(S1.equals(S1));
    assertFalse(S1.equals(S12));

    assertFalse(S12.equals(S0));
    assertFalse(S12.equals(S1));
    assertTrue(S12.equals(S12));
  }

  /** Test set size. */
  @Test
  public void testSize() {
    assertEquals(S0.size(), 0);
    assertEquals(S1.size(), 1);
    assertEquals(S12.size(), 2);
  }

  private static FiniteSet SNeg = FiniteSet.of(new float[] {-2, 3}); // Negative set.
  private static FiniteSet S13 = FiniteSet.of(new float[] {1, 3});
  private static FiniteSet S3 = FiniteSet.of(new float[] {3});
  private static FiniteSet S2 = FiniteSet.of(new float[] {2});
  private static FiniteSet S2min = FiniteSet.of(new float[] {-2});

  /** Tests forming the union of two finite sets. */
  @Test
  public void testUnion() {
    assertEquals(S0, S0.union(S0));   // Test Union with itself.
    assertEquals(S0.union(S1), S1.union(S0));   // Test if Union works both ways.
    assertEquals(S1, S0.union(S1));   // Test Union with a subset.

    assertEquals(S1, S1.union(S1));   // Test Union with itself.
    assertEquals(S1, S1.union(S0));   // Test Union with empty sets.
    assertEquals(S1.union(S12), S12.union(S1));   // Test if Union works both ways.
    assertEquals(S12, S1.union(S12));   // Test Union with a subset.

    assertEquals(S12, S12.union(S12));    // Test Union with itself.
    assertEquals(S12, S12.union(S0));   // Test Union with empty sets.
    assertEquals(S12.union(S0), S0.union(S12)); // Test if Union works both ways.
    assertEquals(S12, S12.union(S1));  // Test Union with a subset.

    assertEquals(SNeg, SNeg.union(SNeg));   // Test Union with itself.
    assertEquals(SNeg, SNeg.union(S0));   // Test Union with empty sets.
    assertEquals(SNeg.union(S0), S0.union(SNeg));   // Test if Union work both ways.

    assertFalse(S1.union(S0).equals(S12));  // Test if Union catch unintended behavior.
  }

  /** Tests forming the intersection of two finite sets. */
  @Test
  public void testIntersection() {
    assertEquals(S0, S0.intersection(S0));    // Test Intersect with itself.
    assertEquals(S0.intersection(S1), S1.intersection(S0));   // Test Intersect both ways.
    assertEquals(S0, S0.intersection(S1)); // Test Intersect with common elements.

    assertEquals(S1, S1.intersection(S1));    // Test Intersect with itself.
    assertEquals(S1.intersection(S0), S0.intersection(S1));   // Test Intersect both ways.
    assertEquals(S0, S1.intersection(S0));    // Test Intersect with empty set.
    assertEquals(S1, S1.intersection(S12));   // Test Intersect with common elements.
    assertEquals(S0, S1.intersection(SNeg));  // Test Intersect with no common elements.

    assertEquals(S12, S12.intersection(S12)); // Test Intersect with itself.
    assertEquals(S12.intersection(S1), S1.intersection(S12)); // Test Intersect both ways.
    assertEquals(S0, S12.intersection(S0)); // Test Intersect with empty set.
    assertEquals(S1, S12.intersection(S1)); // Test Intersect with common elements.
    assertEquals(S0, S12.intersection(SNeg)); // Test Intersect with no common elements.

    assertEquals(SNeg, SNeg.intersection(SNeg)); // Test Intersect with itself.
    assertEquals(SNeg.intersection(S1), S1.intersection(SNeg)); // Test intersect both ways.
    assertEquals(S0, SNeg.intersection(S0));  // Test Intersect with empty set.
    assertEquals(S0, SNeg.intersection(S1));  // Test Intersect with no common elements.

    assertFalse(S12.intersection(S13).equals(S0));  // Test to see if Intersect catches unintended behavior.
  }

  /** Tests forming the difference of two finite sets. */
  @Test
  public void testDifference() {
    assertEquals(S0, S0.difference(S0));  // Test diff with itself.
    assertEquals(S0, S0.difference(S1));  // Test with no common elements.
    assertFalse(S0.difference(S1).equals(S1.difference(S0))); // Test diff both ways.

    assertEquals(S0, S1.difference(S1));  // Test diff with itself.
    assertEquals(S1, S1.difference(SNeg)); // Test diff with no common elements.
    assertEquals(S0, S1.difference(S13)); // Test diff with common elements.
    assertEquals(S1, S1.difference(S0));  // Test diff with empty set.
    assertFalse(S1.difference(S0).equals(S0.difference(S1))); // Test diff both ways.

    assertEquals(S0, S12.difference(S12));  // Test diff with itself.
    assertEquals(S12, S12.difference(S3));   // Test diff with no common elements.
    assertEquals(S2,S12.difference(S13));   // Test diff with common elements.
    assertEquals(S12, S12.difference(S0));  // Test diff with empty set.
    assertFalse(S12.difference(S1).equals(S1.difference(S12))); // Test diff both ways.

    assertEquals(S0, SNeg.difference(SNeg));  // Test diff with itself.
    assertEquals(SNeg, SNeg.difference(S12)); // Test diff with no common elements.
    assertEquals(S2min, SNeg.difference(S3)); // Test diff with common elements.
    assertEquals(SNeg, SNeg.difference(S0));  // Test diff with empty set.
    assertFalse(S12.difference(S1).equals(S1.difference(S12))); // Test diff both ways.

    assertFalse(S1.equals(S1.difference(S12))); // Test to see if diff catches unintended behavior.
  }

}
