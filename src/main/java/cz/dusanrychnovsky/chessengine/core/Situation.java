package cz.dusanrychnovsky.chessengine.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.core.Row.R2;
import static cz.dusanrychnovsky.chessengine.core.Row.R7;
import static cz.dusanrychnovsky.chessengine.util.MapExtensions.get;

public class Situation {

  private final Map<Position, Piece> pieces;
  private final Color currentPlayer;

  public Situation(Color currentPlayer, Map<Position, Piece> pieces) {
    this.pieces = pieces; // TODO: use EnumMap
    this.currentPlayer = currentPlayer;
  }

  public static Situation getInitial() {
    var pieces = new HashMap<Position, Piece>();

    // white
    pieces.put(A1, new Piece(WHITE, ROOK));
    pieces.put(B1, new Piece(WHITE, KNIGHT));
    pieces.put(C1, new Piece(WHITE, BISHOP));
    pieces.put(D1, new Piece(WHITE, QUEEN));
    pieces.put(E1, new Piece(WHITE, KING));
    pieces.put(F1, new Piece(WHITE, BISHOP));
    pieces.put(G1, new Piece(WHITE, KNIGHT));
    pieces.put(H1, new Piece(WHITE, ROOK));

    // black
    pieces.put(A8, new Piece(BLACK, ROOK));
    pieces.put(B8, new Piece(BLACK, KNIGHT));
    pieces.put(C8, new Piece(BLACK, BISHOP));
    pieces.put(D8, new Piece(BLACK, QUEEN));
    pieces.put(E8, new Piece(BLACK, KING));
    pieces.put(F8, new Piece(BLACK, BISHOP));
    pieces.put(G8, new Piece(BLACK, KNIGHT));
    pieces.put(H8, new Piece(BLACK, ROOK));

    // pawns
    for (var col : Column.values()) {
      pieces.put(Position.get(col, R2), new Piece(WHITE, PAWN));
      pieces.put(Position.get(col, R7), new Piece(BLACK, PAWN));
    }

    return new Situation(WHITE, pieces);
  }

  /**
   * @return the piece which currently occupies the given position on the
   * board, if any
   */
  public Optional<Piece> getPieceAt(Position position) {
    return get(pieces, position);
  }

  public Color getCurrentPlayer() {
    return currentPlayer;
  }
}
