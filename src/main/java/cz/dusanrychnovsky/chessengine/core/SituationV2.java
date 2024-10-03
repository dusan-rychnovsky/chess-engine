package cz.dusanrychnovsky.chessengine.core;

import java.util.*;
import java.util.stream.Stream;

import static cz.dusanrychnovsky.chessengine.core.Color.BLACK;
import static cz.dusanrychnovsky.chessengine.core.Color.WHITE;
import static cz.dusanrychnovsky.chessengine.core.PieceTypeV2.*;
import static cz.dusanrychnovsky.chessengine.core.Row.*;
import static cz.dusanrychnovsky.chessengine.util.MapExtensions.get;
import static java.util.stream.Collectors.toSet;

public class SituationV2 {

  private final Map<Position, PieceV2> pieces;
  private final Color currentPlayer;

  public SituationV2(Color currentPlayer, Map<Position, PieceV2> pieces) {
    this.pieces = pieces; // TODO: use EnumMap
    this.currentPlayer = currentPlayer;
  }

  /**
   * @return initial chess situation
   */
  public static SituationV2 getInitial() {
    var pattern = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);

    var pieces = new EnumMap<Position, PieceV2>(Position.class);
    for (int i = 0; i < pattern.size(); i++) {
      var col = Column.values()[i];
      var pieceType = pattern.get(i);

      pieces.put(Position.get(col, R1), new PieceV2(WHITE, pieceType));
      pieces.put(Position.get(col, R2), new PieceV2(WHITE, PAWN));

      pieces.put(Position.get(col, R8), new PieceV2(BLACK, pieceType));
      pieces.put(Position.get(col, R7), new PieceV2(BLACK, PAWN));
    }

    return new SituationV2(WHITE, pieces);
  }

  /**
   * @return all moves which are valid in the represented situation,
   * by the current player
   */
  public Stream<MoveV2> getValidMoves() {
    var result = new HashSet<MoveV2>();
    for (var entry : pieces.entrySet()) {
      var position = entry.getKey();
      var piece = entry.getValue();
      if (piece.color() == currentPlayer) {
        result.addAll(
            piece.type().getMovesTemplate(this, position)
        );
      }
    }
    return result.stream();
  }

  /**
   * @return the piece which currently occupies the given position on the
   * board, if any
   */
  public Optional<PieceV2> getPieceAt(Position position) {
    return get(pieces, position);
  }

  public Color getCurrentPlayer() {
    return currentPlayer;
  }
}
