package cz.dusanrychnovsky.chessengine.core;

import java.util.Map;
import java.util.Optional;

import static cz.dusanrychnovsky.chessengine.util.MapExtensions.get;

public class Situation {

  private final Map<Position, Piece> pieces;
  private final Color currentPlayer;

  public Situation(Color currentPlayer, Map<Position, Piece> pieces) {
    this.pieces = pieces; // TODO: use EnumMap
    this.currentPlayer = currentPlayer;
  }

  /**
   * @return the piece which currently occupies the given position on the
   * board, if any
   */
  public Optional<Piece> getPieceAt(Position position) {
    return get(pieces, position);
  }
}
