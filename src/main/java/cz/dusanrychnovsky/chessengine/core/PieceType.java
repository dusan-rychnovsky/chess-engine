package cz.dusanrychnovsky.chessengine.core;

import java.util.stream.Stream;

import static cz.dusanrychnovsky.chessengine.core.Position.getAllColumn;
import static cz.dusanrychnovsky.chessengine.core.Position.getAllRow;

/**
 * Represents type of chess piece.
 */
public enum PieceType {

  Rook {
    /**
     * @return All moves a rook can make on an empty chessboard from the given
     * position. A rook can move to all positions in the same column and
     * in the same row, except the position on which it's already standing.
     */
    @Override
    public Stream<Move> getMovesFromPosition(Position position) {
      var allColumn = getAllColumn(position.getColumn());
      var allRow = getAllRow(position.getRow());
      return Stream.concat(allColumn, allRow)
        .filter(pos -> pos != position)
        .map(pos -> new Move(position, pos));
    }
  },

  Knight{
    @Override
    public Stream<Move> getMovesFromPosition(Position position) {
      throw new UnsupportedOperationException("Not yet implemented");
    }
  },

  Bishop{
    @Override
    public Stream<Move> getMovesFromPosition(Position position) {
      throw new UnsupportedOperationException("Not yet implemented");
    }
  },

  Queen{
    @Override
    public Stream<Move> getMovesFromPosition(Position position) {
      throw new UnsupportedOperationException("Not yet implemented");
    }
  },

  King{
    @Override
    public Stream<Move> getMovesFromPosition(Position position) {
      throw new UnsupportedOperationException("Not yet implemented");
    }
  },

  Pawn{
    @Override
    public Stream<Move> getMovesFromPosition(Position position) {
      throw new UnsupportedOperationException("Not yet implemented");
    }
  };

  /**
   * @return All moves a piece of the represented type can make on an empty
   * chessboard from the given position.
   */
  public abstract Stream<Move> getMovesFromPosition(Position position);
}
