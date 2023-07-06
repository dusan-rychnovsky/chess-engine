package cz.dusanrychnovsky.chessengine.util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AssertExtensions {

  public static <T> void assertStreamEquals(Stream<T> expected, Stream<T> actual) {
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  public static <T> void assertStreamSetEquals(Set<T> expected, Stream<T> actual) {
    assertEquals(expected, actual.collect(Collectors.toSet()));
  }
}
