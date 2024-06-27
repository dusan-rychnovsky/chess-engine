package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.util.AssertExtensions.assertStreamSetEquals;

public class PieceTypeTests {

  // TODO: temporary placeholder until more advanced features are implemented (see PieceType)
  private final Situation situation = new Situation(WHITE, new HashMap<>());

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
      ROOK.getMovesTemplate(situation, D3));
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
      BISHOP.getMovesTemplate(situation, D3));
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
      QUEEN.getMovesTemplate(situation, D3)
    );
  }

  @Test
  public void kingMovesShouldContainAllAdjacentFields() {
    assertStreamSetEquals(
      Set.of(
        new Move(D3, C4), new Move(D3, D4), new Move(D3, E4),
        new Move(D3, C3), new Move(D3, E3),
        new Move(D3, C2), new Move(D3, D2), new Move(D3, E2)
      ),
      KING.getMovesTemplate(situation, D3)
    );
  }

  @Test
  public void knightMovesShouldContainEightPositionsFromChessboardCenter() {
    assertStreamSetEquals(
      Set.of(
        new Move(C5, D7), new Move(C5, E6), new Move(C5, E4), new Move(C5, D3),
        new Move(C5, B3), new Move(C5, A4), new Move(C5, A6), new Move(C5, B7)
      ),
      KNIGHT.getMovesTemplate(situation, C5)
    );
  }

  @Test
  public void knightMovesShouldContainTwoPositionsFromChessboardCorner() {
    assertStreamSetEquals(
      Set.of(new Move(A1, B3), new Move(A1, C2)),
      KNIGHT.getMovesTemplate(situation, A1)
    );
  }

  @Test
  public void knightMovesShouldContainFourPositionsFromChessboardEdge() {
    assertStreamSetEquals(
      Set.of(
        new Move(H5, G7), new Move(H5, F6), new Move(H5, F4), new Move(H5, G3)
      ),
      KNIGHT.getMovesTemplate(situation, H5)
    );
  }

  @Test
  public void knightMovesShouldContainSixPositionsNearChessboardEdge() {
    assertStreamSetEquals(
      Set.of(
        new Move(E7, G8), new Move(E7, G6), new Move(E7, F5),
        new Move(E7, D5), new Move(E7, C6), new Move(E7, C8)
      ),
      KNIGHT.getMovesTemplate(situation, E7)
    );
  }

  @Test
  public void knightMovesShouldContainFourPositionsNearChessboardCorner() {
    assertStreamSetEquals(
      Set.of(
        new Move(B7, D8), new Move(B7, D6), new Move(B7, C5), new Move(B7, A5)
      ),
      KNIGHT.getMovesTemplate(situation, B7)
    );
  }

  @Test
  public void whitePawnShouldMoveUp() {
    assertStreamSetEquals(
      Set.of(new Move(E4, E5)),
      PAWN.getMovesTemplate(
        new Situation(
          WHITE,
          Map.of(
            E4, new Piece(WHITE, PAWN)
          )
        ),
        E4
      )
    );
  }

  @Test
  public void blackPawnShouldMoveDown() {
    assertStreamSetEquals(
      Set.of(new Move(D5, D4)),
      PAWN.getMovesTemplate(
        new Situation(
          BLACK,
          Map.of(
            D5, new Piece(BLACK, PAWN)
          )
        ),
        D5
      )
    );
  }

  @Test
  public void whitePawnCanMoveTwoSquaresUpWhenFromInitialPosition() {
    assertStreamSetEquals(
      Set.of(new Move(E2, E3), new Move(E2, E4)),
      PAWN.getMovesTemplate(
        new Situation(
          WHITE,
          Map.of(
            E2, new Piece(WHITE, PAWN)
          )
        ),
        E2
      )
    );
  }

  @Test
  public void blackPawnCanMoveTwoSquaresDownWhenFromInitialPosition() {
    assertStreamSetEquals(
      Set.of(new Move(D7, D6), new Move(D7, D5)),
      PAWN.getMovesTemplate(
        new Situation(
          BLACK,
          Map.of(
            D7, new Piece(BLACK, PAWN)
          )
        ),
        D7
      )
    );
  }

  @Test
  public void pawnCanCapturePiecesWhichAreDirectlyAndDiagonallyInFrontOfThem() {
    assertStreamSetEquals(
      Set.of(new Move(E4, E5), new Move(E4, D5)),
      PAWN.getMovesTemplate(
        new Situation(
          WHITE,
          Map.of(
            E4, new Piece(WHITE, PAWN),
            D5, new Piece(BLACK, KNIGHT),
            F5, new Piece(WHITE, PAWN)
          )
        ),
        E4
      )
    );
  }
}
