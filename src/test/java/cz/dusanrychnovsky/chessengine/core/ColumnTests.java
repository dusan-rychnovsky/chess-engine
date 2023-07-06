package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.Optional;

import static cz.dusanrychnovsky.chessengine.core.Column.*;
import static org.junit.Assert.assertEquals;

public class ColumnTests {

  @Test
  public void getPreviousShouldReturnPreviousColumn() {
    assertEquals(Optional.empty(), CA.getPrevious());
    assertEquals(Optional.of(CA), CB.getPrevious());
    assertEquals(Optional.of(CB), CC.getPrevious());
    assertEquals(Optional.of(CC), CD.getPrevious());
    assertEquals(Optional.of(CD), CE.getPrevious());
    assertEquals(Optional.of(CE), CF.getPrevious());
    assertEquals(Optional.of(CF), CG.getPrevious());
    assertEquals(Optional.of(CG), CH.getPrevious());
  }

  @Test
  public void getNextShouldReturnNextColumn() {
    assertEquals(Optional.of(CB), CA.getNext());
    assertEquals(Optional.of(CC), CB.getNext());
    assertEquals(Optional.of(CD), CC.getNext());
    assertEquals(Optional.of(CE), CD.getNext());
    assertEquals(Optional.of(CF), CE.getNext());
    assertEquals(Optional.of(CG), CF.getNext());
    assertEquals(Optional.of(CH), CG.getNext());
    assertEquals(Optional.empty(), CH.getNext());
  }

  @Test
  public void getDistanceToShouldBeZeroForSameColumn() {
    assertEquals(0, CB.getDistanceTo(CB));
  }

  @Test
  public void getDistanceToShouldBeOneForAdjacentColumns() {
    assertEquals(1, CB.getDistanceTo(CC));
    assertEquals(1, CC.getDistanceTo(CB));
  }

  @Test
  public void getDistanceToShouldBeSevenForOppositeBorders() {
    assertEquals(7, CA.getDistanceTo(CH));
    assertEquals(7, CH.getDistanceTo(CA));
  }

  @Test
  public void toStringShouldPrintColumnName() {
    assertEquals("D", CD.toString());
  }
}
