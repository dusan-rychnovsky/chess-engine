package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.Piece.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.core.Row.*;
import static cz.dusanrychnovsky.chessengine.core.Column.*;
import static org.junit.Assert.*;

public class SituationTests {

  @Test
  public void getPieceAt_returnsPieceStandingOnGivenPosition() {
    assertEquals(
      Optional.of(new Piece(BLACK, KING)),
      new Situation(
        WHITE,
        Map.of(
          C2, new Piece(WHITE, ROOK),
          D4, new Piece(BLACK, KING)
        )
      ).getPieceAt(D4));
  }

  @Test
  public void getPieceAt_noPieceExistsOnGivenPosition_returnsEmptyResult() {
    assertEquals(
      Optional.empty(),
      new Situation(WHITE, new HashMap<>())
        .getPieceAt(D4));
  }

  @Test
  public void getInitial_returnsInitialChessSituation() {
    var situation = Situation.getInitial();
    // color
    assertEquals(WHITE, situation.getCurrentPlayer());
    // pawns
    for (var col : Column.values()) {
      assertPieceAt(situation, Position.get(col, R2), WHITE, PAWN);
      assertPieceAt(situation, Position.get(col, R7), BLACK, PAWN);
    }
    // other pieces
    for (var entry : Map.of(WHITE, R1, BLACK, R8).entrySet()) {
      var color = entry.getKey();
      var row = entry.getValue();
      assertPieceAt(situation, Position.get(CA, row), color, ROOK);
      assertPieceAt(situation, Position.get(CB, row), color, KNIGHT);
      assertPieceAt(situation, Position.get(CC, row), color, BISHOP);
      assertPieceAt(situation, Position.get(CD, row), color, QUEEN);
      assertPieceAt(situation, Position.get(CE, row), color, KING);
      assertPieceAt(situation, Position.get(CF, row), color, BISHOP);
      assertPieceAt(situation, Position.get(CG, row), color, KNIGHT);
      assertPieceAt(situation, Position.get(CH, row), color, ROOK);
    }
  }

  private void assertPieceAt(Situation situation, Position position, Color color, PieceType pieceType) {
    var piece = situation.getPieceAt(position);
    assertTrue(piece.isPresent());
    assertEquals(
      new Piece(color, pieceType),
      piece.get());
  }

  @Test
  public void getValidMoves_bishopAndRook_combinesValidMoves() {
    assertEquals(
      Set.of(
        // bishop - two diagonals from B7
        new Move(B7, A6, Set.of()),
        new Move(B7, C8, Set.of()),
        new Move(B7, A8, Set.of()),
        new Move(B7, C6, Set.of()),
        new Move(B7, D5, Set.of(C6)),
        new Move(B7, E4, Set.of(C6, D5)),
        new Move(B7, F3, Set.of(C6, D5, E4)),
        new Move(B7, G2, Set.of(C6, D5, E4, F3)),
        new Move(B7, H1, Set.of(C6, D5, E4, F3, G2)),
        // rook - horizontally and vertically from G2
        new Move(G3, A3, Set.of(F3, E3, D3, C3, B3)),
        new Move(G3, B3, Set.of(F3, E3, D3, C3)),
        new Move(G3, C3, Set.of(F3, E3, D3)),
        new Move(G3, D3, Set.of(F3, E3)),
        new Move(G3, E3, Set.of(F3)),
        new Move(G3, F3, Set.of()),
        new Move(G3, H3, Set.of()),
        new Move(G3, G2, Set.of()),
        new Move(G3, G1, Set.of(G2)),
        new Move(G3, G4, Set.of()),
        new Move(G3, G5, Set.of(G4)),
        new Move(G3, G6, Set.of(G4, G5)),
        new Move(G3, G7, Set.of(G4, G5, G6)),
        new Move(G3, G8, Set.of(G4, G5, G6, G7))
      ),
      new Situation(
        WHITE,
        Map.of(
          B7, new Piece(WHITE, BISHOP),
          G3, new Piece(WHITE, ROOK)
        )
      ).getValidMoves()
    );
  }

  @Test
  public void getValidMoves_regularPiecesCannotMoveThroughOtherPiecesOfSameColor() {
    assertEquals(
      Set.of(
        new Move(B7, A8, Set.of()), new Move(B7, C8, Set.of()),
        new Move(B7, A6, Set.of()), new Move(B7, C6, Set.of())
      ),
      new Situation(
        WHITE,
        Map.of(
          B7, WHITE_BISHOP,
          D5, new Piece(WHITE, PAWN)
        )
      ).getValidMoves(WHITE_BISHOP, B7)
    );
  }

  @Test
  public void getValidMoves_regularPiecesCannotMoveThroughOtherPiecesOfOtherColor() {
    assertEquals(
      Set.of(
        new Move(B7, A8, Set.of()), new Move(B7, C8, Set.of()),
        new Move(B7, A6, Set.of()), new Move(B7, C6, Set.of()),
        new Move(B7, D5, Set.of(C6))
      ),
      new Situation(
        WHITE,
        Map.of(
          B7, new Piece(WHITE, BISHOP),
          D5, new Piece(BLACK, PAWN)
        )
      ).getValidMoves()
    );
  }

  @Test
  public void getValidMoves_knightCanMoveThroughPiecesOfBothColors() {
    assertEquals(
      Set.of(
        new Move(A5, B7, Set.of()), new Move(A5, C6, Set.of()),
        new Move(A5, C4, Set.of()), new Move(A5, B3, Set.of())
      ),
      new Situation(
        WHITE,
        Map.of(
          A5, WHITE_KNIGHT,
          A4, new Piece(BLACK, PAWN),
          B4, new Piece(BLACK, PAWN),
          B5, new Piece(BLACK, PAWN),
          A6, new Piece(WHITE, PAWN),
          B6, new Piece(WHITE, PAWN)
        )
      ).getValidMoves(WHITE_KNIGHT, A5)
    );
  }

  @Test
  public void getValidMoves_canCaptureOpponentsPieces() {
    assertEquals(
      Set.of(new Move(A1, B2, Set.of())),
      new Situation(
        WHITE,
        Map.of(
          A1, new Piece(WHITE, BISHOP),
          B2, new Piece(BLACK, QUEEN)
        )
      ).getValidMoves()
    );
  }

  @Test
  public void getValidMoves_cantCaptureOwnPieces() {
    var moves = new Situation(
      WHITE,
      Map.of(
        A1, WHITE_BISHOP,
        B2, new Piece(WHITE, QUEEN)
      )
    ).getValidMoves(WHITE_BISHOP, A1);
    assertEquals(0, moves.size());
  }

  @Test
  public void getValidMoves_thereAreTwentyValidMovesFromInitialPosition() {
    assertEquals(20, Situation.getInitial().getValidMoves().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getValidMoves_fromInvalidPosition_throws() {
    new Situation(WHITE, Map.of()).getValidMoves(WHITE_BISHOP, A1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getValidMoves_withInvalidPiece_throws() {
    new Situation(
      WHITE,
      Map.of(
        A1, WHITE_BISHOP
      )
    ).getValidMoves(WHITE_KNIGHT, A1);
  }

  @Test
  public void isCheck_kingUnderAttack_returnsTrue() {
    assertTrue(
      new Situation(
        WHITE,
        Map.of(
          F5, new Piece(WHITE, KING),
          C5, new Piece(BLACK, ROOK)
        )
      ).isCheck()
    );
  }

  @Test
  public void isCheck_kingNotUnderAttack_returnsFalse() {
    assertFalse(
      new Situation(
        WHITE,
        Map.of(
          F5, new Piece(WHITE, KING),
          C6, new Piece(BLACK, ROOK)
        )
      ).isCheck()
    );
  }

  @Test
  public void isCheck_kingCoveredByOwnPiece_returnsFalse() {
    assertFalse(
      new Situation(
        WHITE,
        Map.of(
          F5, new Piece(WHITE, KING),
          E5, new Piece(WHITE, PAWN),
          C5, new Piece(BLACK, ROOK)
        )
      ).isCheck()
    );
  }

  @Test
  public void isCheck_kingCoveredByEnemyPiece_returnsFalse() {
    assertFalse(
      new Situation(
        WHITE,
        Map.of(
          F5, new Piece(WHITE, KING),
          E5, new Piece(BLACK, PAWN),
          C5, new Piece(BLACK, ROOK)
        )
      ).isCheck()
    );
  }

  @Test
  public void isCheck_kingCoveredByOwnPieces_KnightMovesThrough() {
    assertTrue(
      new Situation(
        BLACK,
        Map.of(
          B8, new Piece(BLACK, KING),
          B7, new Piece(BLACK, PAWN),
          C7, new Piece(BLACK, PAWN),
          C6, new Piece(WHITE, KNIGHT)
        )
      ).isCheck()
    );
  }

  @Test
  public void equals_samePiecesLocatedAtSamePositionsAndSameCurrentPlayer_areEqual() {
    var first = new Situation(
      BLACK,
      Map.of(
        B8, BLACK_KING,
        D8, WHITE_KING
      )
    );
    var second = new Situation(
      BLACK,
      Map.of(
        B8, BLACK_KING,
        D8, WHITE_KING
      )
    );
    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
  }

  @Test
  public void equals_differentCurrentPlayer_areNotEqual() {
    assertNotEquals(
      new Situation(
        BLACK,
        Map.of(
          B8, BLACK_KING,
          D8, WHITE_KING
        )
      ),
      new Situation(
        WHITE,
        Map.of(
          B8, BLACK_KING,
          D8, WHITE_KING
        )
      )
    );
  }

  @Test
  public void equals_differentPieces_areNotEqual() {
    assertNotEquals(
      new Situation(
        BLACK,
        Map.of(
          B8, BLACK_KING,
          D8, WHITE_BISHOP
        )
      ),
      new Situation(
        BLACK,
        Map.of(
          B8, BLACK_KING,
          D8, WHITE_KING
        )
      )
    );
  }

  @Test
  public void apply_appliesTheGivenMove() {
    assertEquals(
      new Situation(
        BLACK,
        Map.of(
          F5, WHITE_KING,
          G7, WHITE_PAWN,
          G8, BLACK_KING
        )
      ),
      new Situation(
        WHITE,
        Map.of(
          F5, WHITE_KING,
          G6, WHITE_PAWN,
          G8, BLACK_KING
        )
      ).apply(new Move(G6, G7, Set.of()))
    );
  }
}
