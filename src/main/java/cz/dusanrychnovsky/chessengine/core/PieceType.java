package cz.dusanrychnovsky.chessengine.core;

import cz.dusanrychnovsky.chessengine.util.StreamExtensions;

import java.util.*;
import java.util.stream.Stream;

import static cz.dusanrychnovsky.chessengine.core.Direction.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static java.util.Arrays.asList;

/**
 * Represents type of chess piece.
 *
 * TODO: respect check/mate
 * TODO: support castling
 * TODO: respect pieces which can't move through other pieces
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
    @Override
    public Stream<Move> getMovesFromPosition(Situation situation, Position position) {
      throw new UnsupportedOperationException("Not yet implemented");
    }
  };

  /**
   * @return All moves a piece of the represented type can make on an empty
   * chessboard from the given position.
   */
  public abstract Stream<Move> getMovesFromPosition(Situation situation, Position position);
}
