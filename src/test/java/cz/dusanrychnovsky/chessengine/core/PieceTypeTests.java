package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.util.AssertExtensions.assertStreamSetEquals;

public class PieceTypeTests {

  @Test
  public void rookMovesShouldContainFullRowAndColumn() {
    assertStreamSetEquals(
      Set.of(
        // column
        new Move(D3, D1), new Move(D3, D2), new Move(D3, D4),
        new Move(D3, D5), new Move(D3, D6), new Move(D3, D7), new Move(D3, D8),
        // row
        new Move(D3, A3), new Move(D3, B3), new Move(D3, C3),
        new Move(D3, E3), new Move(D3, F3), new Move(D3, G3), new Move(D3, H3)
      ),
      ROOK.getMovesFromPosition(D3));
  }

  @Test
  public void bishopMovesShouldContainBothDiagonals() {
    assertStreamSetEquals(
      Set.of(
        // right diagonal
        new Move(D3, B1), new Move(D3, C2), new Move(D3, E4), new Move(D3, F5),
        new Move(D3, G6), new Move(D3, H7),
        // left diagonal
        new Move(D3, F1), new Move(D3, E2), new Move(D3, C4), new Move(D3, B5),
        new Move(D3, A6)
      ),
      BISHOP.getMovesFromPosition(D3));
  }

  @Test
  public void queenMovesShouldContainFullRowAndColumnAndBothDiagonals() {
    assertStreamSetEquals(
      Set.of(
        // column
        new Move(D3, D1), new Move(D3, D2), new Move(D3, D4),
        new Move(D3, D5), new Move(D3, D6), new Move(D3, D7), new Move(D3, D8),
        // row
        new Move(D3, A3), new Move(D3, B3), new Move(D3, C3),
        new Move(D3, E3), new Move(D3, F3), new Move(D3, G3), new Move(D3, H3),
        // right diagonal
        new Move(D3, B1), new Move(D3, C2), new Move(D3, E4), new Move(D3, F5),
        new Move(D3, G6), new Move(D3, H7),
        // left diagonal
        new Move(D3, F1), new Move(D3, E2), new Move(D3, C4), new Move(D3, B5),
        new Move(D3, A6)
      ),
      QUEEN.getMovesFromPosition(D3)
    );
  }
}
