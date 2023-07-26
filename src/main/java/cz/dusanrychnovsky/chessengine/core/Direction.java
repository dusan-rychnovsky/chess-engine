package cz.dusanrychnovsky.chessengine.core;

import java.util.Optional;
import java.util.function.Function;

/**
 * Represents a direction on a chessboard - horizontal, vertical, diagonal.
 */
public interface Direction {

  /**
   * @return position on a chessboard which is next to the given position
   * in the represented direction, if any.
   */
  Optional<Position> apply(Position from);

  private static Direction get(Function<Column, Optional<Column>> nextCol, Function<Row, Optional<Row>> nextRow) {
    return from -> {
      var col = nextCol.apply(from.getColumn());
      var row = nextRow.apply(from.getRow());
      if (col.isPresent() && row.isPresent()) {
        return Optional.of(
          Position.get(col.get(), row.get())
        );
      }
      return Optional.empty();
    };
  }

  /**
   * @return vertical direction (bottom to top)
   */
  static Direction top() {
    return Direction.get(Optional::of, Navigable::getNext);
  }

  /**
   * @return horizontal direction (left to right)
   */
  static Direction right() {
    return Direction.get(Navigable::getNext, Optional::of);
  }

  /**
   * @return diagonal direction (bottom-left to top-right)
   */
  static Direction topRight() {
    return Direction.get(Navigable::getNext, Navigable::getNext);
  }

  /**
   * @return diagonal direction (top-right to bottom-left)
   */
  static Direction bottomLeft() {
    return Direction.get(Navigable::getPrevious, Navigable::getPrevious);
  }

  /**
   * @return diagonal direction (bottom-right to top-left)
   */
  static Direction topLeft() {
    return Direction.get(Navigable::getPrevious, Navigable::getNext);
  }

  /**
   * @return diagonal direction (top-left to bottom-right)
   */
  static Direction bottomRight() {
    return Direction.get(Navigable::getNext, Navigable::getPrevious);
  }
}
