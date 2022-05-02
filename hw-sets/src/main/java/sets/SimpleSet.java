package sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an immutable set of points on the real line that is easy to
 * describe, either because it is a finite set, e.g., {p1, p2, ..., pN}, or
 * because it excludes only a finite set, e.g., R \ {p1, p2, ..., pN}. As with
 * FiniteSet, each point is represented by a Java float with a non-infinite,
 * non-NaN value.
 */
public class SimpleSet {

  // If this is a finite set, then the points are stored in a FiniteSet.
  // If this is an infinite set, then the points that are not in the
  // set are stored in a FiniteSet.
  // isInf checks if the set is infinite or finite.
  // RI: pointSet != null and pointSet does not contain any points that are NaNs,
  //     infinities, and duplicates.
  // AF(this) = {pointSet.getPoints().get(0), ...,
  //             pointSet.getPoints().get(pointSet.length-1)}        if isInf = false.
  //            R \ {pointSet.getPoints().get(0), pointSet[1], ...,
  //                 pointSet.getPoints().get(pointSet.length-1)}    otherwise.
  private final boolean isInf;
  private final FiniteSet pointSet;

  /**
   * Creates a simple set containing only the given points.
   * @param vals Array containing the points to make into a SimpleSet
   * @spec.requires points != null and has no NaNs, no infinities, and no dups
   * @spec.effects this = {vals[0], vals[1], ..., vals[vals.length-1]}
   */
  public SimpleSet(float[] vals) {
    this.isInf = false;
    vals = Arrays.copyOf(vals, vals.length);
    Arrays.sort(vals);
    this.pointSet = FiniteSet.of(vals);
  }

  /**
   * Private constructor that directly fills in the fields above.
   * @param complement Whether this = points or this = R \ points
   * @param points List of points that are in the set or not in the set
   * @spec.requires points != null
   * @spec.effects this = R \ points if complement else points
   */
  private SimpleSet(boolean complement, FiniteSet points) {
    this.isInf = complement;
    List<Float> copy = List.copyOf(points.getPoints());
    float[] vals = new float[copy.size()];
    for (int i = 0; i < vals.length; i++) {
      vals[i] = copy.get(i);
    }
    Arrays.sort(vals);
    this.pointSet = FiniteSet.of(vals);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SimpleSet))
      return false;

    SimpleSet other = (SimpleSet) o;
    if (this.isInf == other.isInf) {
      return this.pointSet.equals(other.pointSet);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns the number of points in this set.
   * @return N      if this = {p1, p2, ..., pN} and
   *         infty  if this = R \ {p1, p2, ..., pN}
   */
  public float size() {
    // If the set is infinity return infinity.
    if (isInf) return Float.POSITIVE_INFINITY;

    // If the set is not infinity return the size of infinity.
    return this.pointSet.size();
  }

  /**
   * Returns a string describing the points included in this set.
   * @return the string "R" if this contains every point,
   *     a string of the form "R \ {p1, p2, .., pN}" if this contains all
   *        but {@literal N > 0} points, or
   *     a string of the form "{p1, p2, .., pN}" if this contains
   *        {@literal N >= 0} points,
   *     where p1, p2, ... pN are replaced by the individual numbers.
   */
  public String toString() {
    // TODO: implement this with a loop. document its invariant
    //       a StringBuilder may be useful for creating the string

    return "";
  }

  /**
   * Returns a set representing the points R \ this.
   * @return R \ this
   */
  public SimpleSet complement() {
    // If this is an infinite set that has a finite number of points that aren't
    // in the set (could be 0 points), then this complement is a finite set that
    // are the points that aren't in the infinite set. Thus, with my representation
    // I just have to flip the value of isInf and now its a finite set with the
    // points in pointSet, which were the points not in the infinite set.
    // e.g. R \ {1, 3}, the complement = {1, 3}.

    // If this is a finite set, then this complement is an infinite set that does
    // not contain the points in the finite set. Therefore, with my representation,
    // I just have to flip the value of isInf, and now it represents an inf set
    // without points in pointSet, which were the points in the finite set.
    // e.g. {1, 2}, the complement = R \ {1, 2}.

    return new SimpleSet(!isInf, this.pointSet);
  }

  /**
   * Returns the union of this and other.
   * @param other Set to union with this one.
   * @spec.requires other != null
   * @return this union other
   */
  public SimpleSet union(SimpleSet other) {
    // If both sets are finite complements, then their union is an infinite set
    // with its complement being the intersection of the complements of both infinite sets
    // because the intersection represents elements that are not in both infinite sets.
    // e.g. R \ {1, 2} U R \ {1, 3} = R \ {1}.

    // If one set is a finite complement and the other is a finite set, then their union
    // is an infinite set with its complement being
    // the elements that live in the complement of the infinite set, but are not
    // in the finite set (i.e. the set difference).
    // e.g. R \ {1, 2} U {1, 3} = R \ {2} because {1, 2} \ {1,3} = {2}.

    // If both sets are finite sets, then their union is a finite set which is
    // the union of the finite sets.
    // e.g. {1, 3} U {2} = {1, 2, 3}

    if (this.isInf && other.isInf) {
      return new SimpleSet(true, this.pointSet.intersection(other.pointSet));
    } else if (this.isInf || other.isInf) {
      return new SimpleSet(true, this.pointSet.difference(other.pointSet));
    } else {
      return new SimpleSet(false, this.pointSet.union(other.pointSet));
    }
  }

  /**
   * Returns the intersection of this and other.
   * @param other Set to intersect with this one.
   * @spec.requires other != null
   * @return this intersect other
   */
  public SimpleSet intersection(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // NOTE: There is more than one correct way to implement this.

    if (this.isInf && other.isInf) {
      return new SimpleSet(true, this.pointSet.union(other.pointSet));
    } else if (this.isInf) {
      return new SimpleSet(false, other.pointSet.difference(this.pointSet));
    } else if (other.isInf) {
      return new SimpleSet(false, this.pointSet.difference(other.pointSet));
    } else {
      return new SimpleSet(false, this.pointSet.intersection(other.pointSet));
    }
  }

  /**
   * Returns the difference of this and other.
   * @param other Set to difference from this one.
   * @spec.requires other != null
   * @return this minus other
   */
  public SimpleSet difference(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // NOTE: There is more than one correct way to implement this.

    return new SimpleSet(new float[] {});
  }

}
