package transition;

import java.util.ArrayList;

/**
 * <h2>ComparableList</h2>
 *
 * Helper list that implements
 * comparable method
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class ComparableList<T>
        extends ArrayList<T>
        implements Comparable<ComparableList<T>> {
  /**
   * Compare two lists based
   * on the size, and on the
   * elements
   *
   * @param o other list to compare.
   * @return compareTo with sizes and
   *          elements.
   */
  @Override
  public int compareTo(ComparableList<T> o) {
    int sizeComp = Integer.compare(size(), o.size());
    if (sizeComp == 0) {
      for (int i = 0; i < size(); i++) {
        Comparable<T> elem1 = (Comparable<T>) get(i);
        int comp = elem1.compareTo(o.get(i));
        if (comp != 0)
          return comp;
      }
      return 0;
    }
    return sizeComp;
  }
}
