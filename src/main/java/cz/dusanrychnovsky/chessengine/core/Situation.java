package cz.dusanrychnovsky.chessengine.core;

import java.util.*;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Row.*;
import static cz.dusanrychnovsky.chessengine.util.MapExtensions.get;
import static java.util.stream.Collectors.toSet;

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
   * @return all moves which are valid in the represented situation,
   * by the current player
   */
  public Set<Move> getValidMoves() {
    return pieces.entrySet().stream()
      .flatMap(entry -> getValidMoves(entry.getValue(), entry.getKey()).stream())
      .collect(toSet());
  }

  /**
   * @return all moves which are valid for the given piece standing at
   * the given position in the represented situation by the current player
   */
  public Set<Move> getValidMoves(Piece piece, Position position) {
    if (getPieceAt(position).filter(p -> p.equals(piece)).isEmpty()) {
      throw new IllegalArgumentException(
        "Invalid starting position. Piece " + piece +
        " is not present at position " + position + "."
      );
    }

    if (piece.color() == currentPlayer) {
      return piece.type()
        .getMovePatterns(this, position)
        .stream()
        .filter(move -> isValid(piece, move))
        .collect(toSet());
    }
    else {
      return Set.of();
    }
  }

  private boolean isValid(Piece piece, Move move) {
    // intermediaries
    for (var pos : move.intermediaries()) {
      if (getPieceAt(pos).isPresent()) {
        return false;
      }
    }
    // to piece
    var toPiece = getPieceAt(move.to());
    if (toPiece.isPresent() && toPiece.get().color() == currentPlayer) {
      return false;
    }

    return true;
  }

  /**
   * @return true if the represented situation is a check. A situation is
   * a check whenever the current player's king is attacked by an enemy piece.
   */
  public boolean isCheck() {
    var kingPos = getKingsPosition(currentPlayer);
    if (kingPos.isEmpty()) {
      throw new IllegalStateException(
        "King pieces must always be present in any valid situation."
      );
    }

    var opponentsView = new Situation(currentPlayer.getOpposite(), pieces);
    var opponentsMoves = opponentsView.getValidMoves();
    return opponentsMoves.stream()
      .anyMatch(move -> move.to() == kingPos.get());
  }

  private Optional<Position> getKingsPosition(Color color) {
    return pieces.entrySet().stream()
      .filter(entry -> {
        var piece = entry.getValue();
        return piece.color() == color && piece.type() == KING;
      })
      .map(Map.Entry::getKey)
      .findFirst();
  }

  /**
   * @return situation which represents the result of applying the given move
   * to the represented situation.
   *
   * Note: Doesn't test validity of the give move.
   */
  public Situation apply(Move move) {
    var piece = getPieceAt(move.from()).orElseThrow(
      () -> new IllegalArgumentException("No piece exists at " + move.from())
    );
    var nextPieces = new HashMap<>(this.pieces);
    nextPieces.remove(move.from());
    nextPieces.put(move.to(), piece);
    return new Situation(
      this.currentPlayer.getOpposite(),
      nextPieces
    );
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Situation other)) {
      return false;
    }
    return
        this.currentPlayer == other.currentPlayer &&
        this.pieces.equals(other.pieces);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentPlayer, pieces);
  }
}
