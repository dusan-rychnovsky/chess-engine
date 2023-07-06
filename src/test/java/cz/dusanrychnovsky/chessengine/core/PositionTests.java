package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static cz.dusanrychnovsky.chessengine.core.Column.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static cz.dusanrychnovsky.chessengine.core.Row.R3;
import static org.junit.Assert.assertEquals;

public class PositionTests {

  @Test
  public void getShouldReturnEnumValue() {
    assertEquals(C3, Position.get(CC, R3));
  }

  @Test
  public void getAllColumShouldReturnAllPositionsInGivenColumn() {
    var result = Position.getAllColumn(CC).collect(Collectors.toSet());
    assertEquals(Set.of(C1, C2, C3, C4, C5, C6, C7, C8), result);
  }

  @Test
  public void getAllRowShouldReturnAllPositionsInGivenRow() {
    var result = Position.getAllRow(R3).collect(Collectors.toSet());
    assertEquals(Set.of(A3, B3, C3, D3, E3, F3, G3, H3), result);
  }

  @Test
  public void getAllRightDiagonalShouldReturnAllPositionsInTheRightDiagonalWhichGoesThroughTheGivenPosition() {
    var result = Position.getAllRightDiagonal(D2).collect(Collectors.toSet());
    assertEquals(Set.of(C1, D2, E3, F4, G5, H6), result);
  }

  @Test
  public void getAllLeftDiagonalShouldReturnAllPositionsInTheLeftDiagonalWhichGOesThroughTheGivenPosition() {
    var result = Position.getAllLeftDiagonal(D2).collect(Collectors.toSet());
    assertEquals(Set.of(E1, D2, C3, B4, A5), result);
  }
}
