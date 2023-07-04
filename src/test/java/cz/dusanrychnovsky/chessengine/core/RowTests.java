package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import static cz.dusanrychnovsky.chessengine.core.Row.R5;
import static org.junit.Assert.assertEquals;

public class RowTests {

  @Test
  public void toStringShouldReturnRowName() {
    assertEquals("5", R5.toString());
  }
}
