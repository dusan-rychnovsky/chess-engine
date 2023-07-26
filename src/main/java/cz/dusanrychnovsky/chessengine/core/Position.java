package cz.dusanrychnovsky.chessengine.core;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

import static cz.dusanrychnovsky.chessengine.core.Column.*;
import static cz.dusanrychnovsky.chessengine.core.Direction.*;
import static cz.dusanrychnovsky.chessengine.core.Row.*;

/**
 * Represents a position on a chessboard, which is a combination
 * of a column (A to H) and a row (1 to 8).
 *
 * TODO: consider converting this to flyweight
 */
public enum Position {

  A1(CA, R1), A2(CA, R2), A3(CA, R3), A4(CA, R4), A5(CA, R5), A6(CA, R6), A7(CA, R7), A8(CA, R8),
  B1(CB, R1), B2(CB, R2), B3(CB, R3), B4(CB, R4), B5(CB, R5), B6(CB, R6), B7(CB, R7), B8(CB, R8),
  C1(CC, R1), C2(CC, R2), C3(CC, R3), C4(CC, R4), C5(CC, R5), C6(CC, R6), C7(CC, R7), C8(CC, R8),
  D1(CD, R1), D2(CD, R2), D3(CD, R3), D4(CD, R4), D5(CD, R5), D6(CD, R6), D7(CD, R7), D8(CD, R8),
  E1(CE, R1), E2(CE, R2), E3(CE, R3), E4(CE, R4), E5(CE, R5), E6(CE, R6), E7(CE, R7), E8(CE, R8),
  F1(CF, R1), F2(CF, R2), F3(CF, R3), F4(CF, R4), F5(CF, R5), F6(CF, R6), F7(CF, R7), F8(CF, R8),
  G1(CG, R1), G2(CG, R2), G3(CG, R3), G4(CG, R4), G5(CG, R5), G6(CG, R6), G7(CG, R7), G8(CG, R8),
  H1(CH, R1), H2(CH, R2), H3(CH, R3), H4(CH, R4), H5(CH, R5), H6(CH, R6), H7(CH, R7), H8(CH, R8);

  private final Column column;
  private final Row row;

  Position(Column column, Row row) {
    this.column = column;
    this.row = row;
  }

  public static Position get(Column column, Row row) {
    return valueOf(column.toString() + row.toString());
  }

  /**
   * @return All positions in the given column.
   */
  public static Stream<Position> getAllColumn(Column column) {
    return getAllInDirection(get(column, R1), top());
  }

  /**
   * @return All positions in the given row.
   */
  public static Stream<Position> getAllRow(Row row) {
    return getAllInDirection(get(CA, row), right());
  }

  /**
   * @return All positions in the right diagonal which goes through
   * the given position - that is, the diagonal that goes top right
   * and bottom left.
   */
  public static Stream<Position> getAllRightDiagonal(Position pos) {
    return Stream.concat(
      getAllInDirection(pos, topRight()),
      getAllInDirection(pos, bottomLeft())
        .filter(p -> p != pos)
    );
  }

  /**
   * @return All positions in the left diagonal which goes through
   * the given position - that is, the diagonal that goes top left
   * and bottom right.
   */
  public static Stream<Position> getAllLeftDiagonal(Position pos) {
    return Stream.concat(
      getAllInDirection(pos, topLeft()),
      getAllInDirection(pos, bottomRight())
        .filter(p -> p != pos)
    );
  }

  private static Stream<Position> getAllInDirection(Position from, Direction direction) {
    var result = new HashSet<Position>();
    var pos = Optional.of(from);
    while (pos.isPresent()) {
      result.add(pos.get());
      pos = direction.apply(pos.get());
    }
    return result.stream();
  }

  /**
   * @return All positions which are adjacent with the given position.
   */
  public static Stream<Position> getAllAdjacent(Position position) {
    return getAllAdjacent(position.getColumn(), position.getRow());
  }

  private static Stream<Position> getAllAdjacent(Column col, Row row) {
    var fromRow = row.getPrevious().orElse(row);
    var toRow = row.getNext().orElse(row);
    var fromCol = col.getPrevious().orElse(col);
    var toCol = col.getNext().orElse(col);

    var result = new HashSet<Position>();
    for (var c = fromCol; ; c = c.getNext().orElseThrow()) {
      for (var r = fromRow; ; r = r.getNext().orElseThrow()) {
        if (r != row || c != col) {
          result.add(Position.get(c, r));
        }
        if (r == toRow) {
          break;
        }
      }
      if (c == toCol) {
        break;
      }
    }
    return result.stream();
  }

  public Column getColumn() {
    return this.column;
  }

  public Row getRow() {
    return this.row;
  }
}
