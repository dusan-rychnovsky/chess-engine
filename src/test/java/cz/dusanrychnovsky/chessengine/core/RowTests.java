package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.Optional;

import static cz.dusanrychnovsky.chessengine.core.Row.*;
import static org.junit.Assert.assertEquals;

public class RowTests {

  @Test
  public void getPreviousShouldReturnPreviousRow() {
    assertEquals(Optional.empty(), R1.getPrevious());
    assertEquals(Optional.of(R1), R2.getPrevious());
    assertEquals(Optional.of(R2), R3.getPrevious());
    assertEquals(Optional.of(R3), R4.getPrevious());
    assertEquals(Optional.of(R4), R5.getPrevious());
    assertEquals(Optional.of(R5), R6.getPrevious());
    assertEquals(Optional.of(R6), R7.getPrevious());
    assertEquals(Optional.of(R7), R8.getPrevious());
  }

  @Test
  public void getNextShouldReturnNextRow() {
    assertEquals(Optional.of(R2), R1.getNext());
    assertEquals(Optional.of(R3), R2.getNext());
    assertEquals(Optional.of(R4), R3.getNext());
    assertEquals(Optional.of(R5), R4.getNext());
    assertEquals(Optional.of(R6), R5.getNext());
    assertEquals(Optional.of(R7), R6.getNext());
    assertEquals(Optional.of(R8), R7.getNext());
    assertEquals(Optional.empty(), R8.getNext());
  }

  @Test
  public void getDistanceToShouldBeZeroForSameRow() {
    assertEquals(0, R2.getDistanceTo(R2));
  }

  @Test
  public void getDistanceToShouldBeOneForAdjacentRows() {
    assertEquals(1, R2.getDistanceTo(R3));
    assertEquals(1, R3.getDistanceTo(R2));
  }

  @Test
  public void getDistanceToShouldBeSevenForOppositeBorders() {
    assertEquals(7, R1.getDistanceTo(R8));
    assertEquals(7, R8.getDistanceTo(R1));
  }

  @Test
  public void toStringShouldReturnRowName() {
    assertEquals("5", R5.toString());
  }
}
