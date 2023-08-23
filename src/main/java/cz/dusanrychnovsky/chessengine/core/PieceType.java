package cz.dusanrychnovsky.chessengine.core;

import cz.dusanrychnovsky.chessengine.util.StreamExtensions;

import java.util.*;
import java.util.stream.Stream;

import static cz.dusanrychnovsky.chessengine.core.Direction.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.core.Row.R2;
import static cz.dusanrychnovsky.chessengine.core.Row.R7;
import static java.util.Arrays.asList;

/**
 * Represents type of chess piece.
 *
 * TODO: respect check/mate
 * TODO: support castling
 * TODO: respect pieces which can't move through other pieces
 * TODO: support pawn promotions
 * TODO: support en-passant pawn capture
 */
public enum PieceType {

  ROOK {
    /**
     * @return All moves a rook can make on an empty chessboard from the given
     * position. A rook can move to all positions in the same column and
     * in the same row, except the position on which it's already standing.
     */
    @Override
    public Stream<Move> getMovesFromPosition(Situation situation, Position position) {
      var allColumn = getAllColumn(position.getColumn());
      var allRow = getAllRow(position.getRow());
      return Stream.concat(allColumn, allRow)
        .filter(pos -> pos != position)
        .map(pos -> new Move(position, pos));
    }
  },

  KNIGHT {
    /**
     * @return All moves a knight can make on an empty chessboard from
     * the given position. A knight can move in L-shaped pattern.
     */
    @Override
    public Stream<Move> getMovesFromPosition(Situation situation, Position position) {
      var directions = new ArrayList<List<Direction>>();
      for (var horizontal : new Direction[] { left(), right() }) {
        for (var vertical : new Direction[] { top(), bottom() }) {
          directions.add(asList(horizontal, horizontal, vertical));
          directions.add(asList(vertical, vertical, horizontal));
        }
      }
      return directions.stream()
        .map(position::apply)
        .filter(Optional::isPresent)
        .map(pos -> new Move(position, pos.get()));
    }
  },

  BISHOP {
    /**
     * @return All moves a bishop can make on an empty chessboard from
     * the given position. A bishop can move to all positions diagonally,
     * except the position on which it's already standing.
     */
    @Override
    public Stream<Move> getMovesFromPosition(Situation situation, Position position) {
      var rightDiagonal = getAllRightDiagonal(position);
      var leftDiagonal = getAllLeftDiagonal(position);
      return Stream.concat(rightDiagonal, leftDiagonal)
        .filter(pos -> pos != position)
        .map(pos -> new Move(position, pos));
    }
  },

  QUEEN {
    /**
     * @return All moves a queen can make on an empty chessboard from
     * the given position. A queen can move to all positions in the same
     * column and the same row, as well as diagonally, except the position
     * on which it's already standing.
     */
    @Override
    public Stream<Move> getMovesFromPosition(Situation situation, Position position) {
      var allColumn = getAllColumn(position.getColumn());
      var allRow = getAllRow(position.getRow());
      var rightDiagonal = getAllRightDiagonal(position);
      var leftDiagonal = getAllLeftDiagonal(position);
      return StreamExtensions.concat(allColumn, allRow, rightDiagonal, leftDiagonal)
        .filter(pos -> pos != position)
        .map(pos -> new Move(position, pos));
    }
  },

  KING {
    /**
     * @return All moves a king can make on an empty chessboard from
     * the given position. A king can move to all adjacent fields.
     */
    @Override
    public Stream<Move> getMovesFromPosition(Situation situation, Position position) {
      return Position.getAllAdjacent(position)
        .map(pos -> new Move(position, pos));
    }
  },

  PAWN {
    /**
     * @return All moves a pawn can make from the given position, without
     * taking into account potential check implications. A pawn can move
     * vertically (white pawn up, black pawn down), exactly one position,
     * or two when starting from the pawn's initial position. A pawn can
     * additionally capture opponent's pieces which are located exactly
     * one position diagonally (white pawn top-right/-left, black pawn
     * bottom-right/-left).
     *
     * TODO: support en passant
     */
    @Override
    public Stream<Move> getMovesFromPosition(Situation situation, Position position) {

      var piece = situation.getPieceAt(position).orElseThrow();
      var color = piece.color();

      var moveDirection = getMoveDirection(color);
      var captureDirections = getCaptureDirections(color);

      var result = new HashSet<Optional<Position>>();
      result.add(position.apply(moveDirection));

      if (isInitialPosition(color, position)) {
        result.add(position.apply(moveDirection, moveDirection));
      }

      for (var direction : captureDirections) {
        var targetPosition = position.apply(direction);
        if (targetPosition.isPresent()) {
          var targetPiece = situation.getPieceAt(targetPosition.get());
          if (targetPiece.isPresent() && targetPiece.get().color() == color.getOpposite()) {
            result.add(targetPosition);
          }
        }
      }

      return result.stream()
        .filter(Optional::isPresent)
        .map(pos -> new Move(position, pos.get()));
    }

    private Set<Direction> getCaptureDirections(Color color) {
      return switch (color) {
        case WHITE -> Set.of(topLeft(), topRight());
        case BLACK -> Set.of(bottomLeft(), bottomRight());
        default -> throw new IllegalArgumentException("Unknown color: " + color);
      };
    }

    private Direction getMoveDirection(Color color) {
      return switch (color) {
        case WHITE -> top();
        case BLACK -> bottom();
        default -> throw new IllegalArgumentException("Unknown color: " + color);
      };
    }

    private boolean isInitialPosition(Color color, Position position) {
      return position.getRow() == switch (color) {
        case WHITE -> R2;
        case BLACK -> R7;
        default -> throw new IllegalArgumentException("Unknown color: " + color);
      };
    }
  };

  /**
   * @return All moves a piece of the represented type can make on an empty
   * chessboard from the given position.
   */
  public abstract Stream<Move> getMovesFromPosition(Situation situation, Position position);
}
