package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static org.junit.Assert.assertEquals;

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
}
