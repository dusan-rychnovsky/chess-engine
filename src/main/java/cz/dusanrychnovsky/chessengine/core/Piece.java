package cz.dusanrychnovsky.chessengine.core;

import static cz.dusanrychnovsky.chessengine.core.Color.WHITE;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;

/**
 * Represents a chess piece of a certain color and certain type.
 */
public record Piece(Color color, PieceType type) {
  public static Piece WHITE_BISHOP = new Piece(WHITE, BISHOP);
  public static Piece WHITE_KNIGHT = new Piece(WHITE, KNIGHT);
}
