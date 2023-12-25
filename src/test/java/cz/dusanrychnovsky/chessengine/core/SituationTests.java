package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.core.Row.*;
import static cz.dusanrychnovsky.chessengine.core.Column.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SituationTests {

  @Test
  public void getPieceAtReturnsPieceStandingOnGivenPositionOnTheBoard() {
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
  public void getPieceAtReturnsEmptyResultWhenNoPieceExistsOnGivenPositionOnTheBoard() {
    assertEquals(
      Optional.empty(),
      new Situation(WHITE, new HashMap<>())
        .getPieceAt(D4));
  }

  @Test
  public void getInitialReturnsInitialChessSituation() {
    var situation = Situation.getInitial();
    // color
    assertEquals(WHITE, situation.getCurrentPlayer());
    // pawns
    for (var col : Column.values()) {
      assertPieceAt(situation, Position.get(col, R2), WHITE, PAWN);
      assertPieceAt(situation, Position.get(col, R7), BLACK, PAWN);
    }
    // white
    assertPieceAt(situation, Position.get(CA, R1), WHITE, ROOK);
    assertPieceAt(situation, Position.get(CB, R1), WHITE, KNIGHT);
    assertPieceAt(situation, Position.get(CC, R1), WHITE, BISHOP);
    assertPieceAt(situation, Position.get(CD, R1), WHITE, QUEEN);
    assertPieceAt(situation, Position.get(CE, R1), WHITE, KING);
    assertPieceAt(situation, Position.get(CF, R1), WHITE, BISHOP);
    assertPieceAt(situation, Position.get(CG, R1), WHITE, KNIGHT);
    assertPieceAt(situation, Position.get(CH, R1), WHITE, ROOK);
    // black
    assertPieceAt(situation, Position.get(CA, R8), BLACK, ROOK);
    assertPieceAt(situation, Position.get(CB, R8), BLACK, KNIGHT);
    assertPieceAt(situation, Position.get(CC, R8), BLACK, BISHOP);
    assertPieceAt(situation, Position.get(CD, R8), BLACK, QUEEN);
    assertPieceAt(situation, Position.get(CE, R8), BLACK, KING);
    assertPieceAt(situation, Position.get(CF, R8), BLACK, BISHOP);
    assertPieceAt(situation, Position.get(CG, R8), BLACK, KNIGHT);
    assertPieceAt(situation, Position.get(CH, R8), BLACK, ROOK);
  }

  private void assertPieceAt(Situation situation, Position position, Color color, PieceType pieceType) {
    var piece = situation.getPieceAt(position);
    assertTrue(piece.isPresent());
    assertEquals(
      new Piece(color, pieceType),
      piece.get());
  }
}
