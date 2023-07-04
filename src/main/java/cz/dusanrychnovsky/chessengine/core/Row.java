package cz.dusanrychnovsky.chessengine.core;

/**
 * Represents a row on a chessboard - 1 to 8.
 */
public enum Row {
  R1, R2, R3, R4, R5, R6, R7, R8;

  /**
   * @return Name of the row.
   */
  @Override
  public String toString() {
    return super.toString().substring(1, 2);
  }
}
