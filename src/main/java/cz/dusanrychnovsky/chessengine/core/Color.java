package cz.dusanrychnovsky.chessengine.core;

/**
 * Represents color of a chess player and of chess pieces - white or black.
 */
public enum Color {
  WHITE, BLACK;

  /**
   * @return the opposite color.
   */
  public Color getOpposite() {
    if (this == WHITE) {
      return BLACK;
    }
    else {
      return WHITE;
    }
  }
}
