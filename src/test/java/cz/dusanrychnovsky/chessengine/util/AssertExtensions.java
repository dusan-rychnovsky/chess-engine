package cz.dusanrychnovsky.chessengine.util;

import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class AssertExtensions {

  public static <T> void assertStreamEquals(Stream<T> first, Stream<T> second) {
    assertArrayEquals(first.toArray(), second.toArray());
  }
}
