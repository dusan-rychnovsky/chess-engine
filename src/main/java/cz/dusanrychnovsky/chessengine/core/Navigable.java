package cz.dusanrychnovsky.chessengine.core;

import java.util.Optional;

/**
 * A trait. For types with a total order, implements functionality of moving
 * to the next/previous value.
 */
public interface Navigable<T extends Navigable<T>> {

  /**
   * @return The position of the represented value within the represented total
   * ordering.
   */
  int getOrd();

  /**
   * @return A list of all values of the represented type.
   */
  T[] getAll();

  /**
   * @return Next value of the represented type within the represented total
   * ordering.
   */
  default Optional<T> getPrevious() {
    if (getOrd() > 0) {
      return Optional.of(getAll()[getOrd() - 1]);
    }
    else {
      return Optional.empty();
    }
  }

  /**
   * @return Previous value of the represented type within the represented total
   * ordering.
   */
  default Optional<T> getNext() {
    if (getOrd() < getAll().length - 1) {
      return Optional.of(getAll()[getOrd() + 1]);
    }
    else {
      return Optional.empty();
    }
  }

  /**
   * @return Distance (as in, number of values) between the represented and
   * the given value within the represented total ordering.
   */
  default int getDistanceTo(T other) {
    return Math.abs(getOrd() - other.getOrd());
  }
}
