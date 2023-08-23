package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static org.junit.Assert.assertEquals;

public class ColorTests {

  @Test
  public void getOppositeGivenWhiteIsBlack() {
    assertEquals(BLACK, WHITE.getOpposite());
  }

  @Test
  public void getOppositeGivenBlackIsWhite() {
    assertEquals(WHITE, BLACK.getOpposite());
  }
}
