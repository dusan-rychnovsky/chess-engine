package cz.dusanrychnovsky.chessengine.core;

/**
 * Represents a column on a chessboard - A to H.
 */
public enum Column {
  CA, CB, CC, CD, CE, CF, CG, CH;

  /**
   * @return Name of the column.
   */
  @Override
  public String toString() {
    return super.toString().substring(1, 2);
  }
}
