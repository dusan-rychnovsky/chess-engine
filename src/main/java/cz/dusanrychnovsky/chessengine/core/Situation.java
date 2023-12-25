package cz.dusanrychnovsky.chessengine.core;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Row.*;
import static cz.dusanrychnovsky.chessengine.util.MapExtensions.get;

public class Situation {

  private final Map<Position, Piece> pieces;
  private final Color currentPlayer;

  public Situation(Color currentPlayer, Map<Position, Piece> pieces) {
    this.pieces = pieces; // TODO: use EnumMap
    this.currentPlayer = currentPlayer;
  }

  /**
   * @return initial chess situation
   */
  public static Situation getInitial() {
    var pattern = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);

    var pieces = new EnumMap<Position, Piece>(Position.class);
    for (int i = 0; i < pattern.size(); i++) {
      var col = Column.values()[i];
      var pieceType = pattern.get(i);

      pieces.put(Position.get(col, R1), new Piece(WHITE, pieceType));
      pieces.put(Position.get(col, R2), new Piece(WHITE, PAWN));

      pieces.put(Position.get(col, R8), new Piece(BLACK, pieceType));
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
