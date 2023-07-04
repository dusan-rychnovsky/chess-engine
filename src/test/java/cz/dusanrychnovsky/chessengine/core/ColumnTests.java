package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import static cz.dusanrychnovsky.chessengine.core.Column.CD;
import static org.junit.Assert.assertEquals;

public class ColumnTests {

  @Test
  public void toStringShouldPrintColumnName() {
    assertEquals("D", CD.toString());
  }
}
