package cz.dusanrychnovsky.chessengine.util;

import org.junit.Test;

import java.util.stream.Stream;

import static cz.dusanrychnovsky.chessengine.util.AssertExtensions.assertStreamEquals;
import static org.junit.Assert.assertEquals;

public class StreamExtensionsTests {

  @Test
  public void concatWhenGivenOneStreamShouldJustReturnTheStream() {
    var stream = Stream.of(1, 2, 3);
    assertEquals(stream, StreamExtensions.concat(stream));
  }

  @Test
  public void concatWhenGivenTwoStreamsShouldReturnThemConcatenated() {
    var first = Stream.of(1, 2);
    var second = Stream.of(3, 4);
    assertStreamEquals(Stream.of(1, 2, 3, 4), StreamExtensions.concat(first, second));
  }

  @Test
  public void concatWhenGivenThreeStreamsShouldReturnThemConcatenated() {
    var first = Stream.of(1, 2);
    var second = Stream.of(3, 4);
    var third = Stream.of(5, 6);
    assertStreamEquals(Stream.of(1, 2, 3, 4, 5, 6), StreamExtensions.concat(first, second, third));
  }
}
