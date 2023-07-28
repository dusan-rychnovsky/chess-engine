package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.Column.*;
import static cz.dusanrychnovsky.chessengine.core.Direction.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.core.Row.R3;
import static cz.dusanrychnovsky.chessengine.util.AssertExtensions.assertStreamSetEquals;
import static org.junit.Assert.assertEquals;

public class PositionTests {

  @Test
  public void getShouldReturnEnumValue() {
    assertEquals(C3, Position.get(CC, R3));
  }

  @Test
  public void getAllColumShouldReturnAllPositionsInGivenColumn() {
    assertStreamSetEquals(Set.of(C1, C2, C3, C4, C5, C6, C7, C8), Position.getAllColumn(CC));
  }

  @Test
  public void getAllRowShouldReturnAllPositionsInGivenRow() {
    assertStreamSetEquals(Set.of(A3, B3, C3, D3, E3, F3, G3, H3), Position.getAllRow(R3));
  }

  @Test
  public void getAllRightDiagonalShouldReturnAllPositionsInTheRightDiagonalWhichGoesThroughTheGivenPosition() {
    assertStreamSetEquals(Set.of(C1, D2, E3, F4, G5, H6), Position.getAllRightDiagonal(D2));
  }

  @Test
  public void getAllLeftDiagonalShouldReturnAllPositionsInTheLeftDiagonalWhichGOesThroughTheGivenPosition() {
    assertStreamSetEquals(Set.of(E1, D2, C3, B4, A5), Position.getAllLeftDiagonal(D2));
  }

  @Test
  public void getAllAdjacentFromChessboardCornerShouldReturnThreePositions() {
    assertStreamSetEquals(Set.of(A2, B2, B1), Position.getAllAdjacent(A1));
  }

  @Test
  public void getAllAdjacentFromChessboardSideShouldReturnFivePositions() {
    assertStreamSetEquals(Set.of(H3, G3, G4, G5, H5), Position.getAllAdjacent(H4));
  }

  @Test
  public void getAllAdjacentFromChessboardMiddleShouldReturnEightPositions() {
    assertStreamSetEquals(Set.of(B7, C7, D7, B6, D6, B5, C5, D5), Position.getAllAdjacent(C6));
  }

  @Test
  public void applyShouldReturnNextPositionInGivenDirection() {
    assertEquals(Optional.of(C3), C2.apply(top()));
  }

  @Test
  public void applyShouldReturnEmptyResultWhenNextPositionInGivenDirectionIsOutOfChessboard() {
    assertEquals(Optional.empty(), H1.apply(topRight()));
  }

  @Test
  public void applyShouldReturnFinalPositionWhenApplyingMultipleDirections() {
    assertEquals(Optional.of(G5), E2.apply(right(), top(), top(), topRight()));
  }
}
