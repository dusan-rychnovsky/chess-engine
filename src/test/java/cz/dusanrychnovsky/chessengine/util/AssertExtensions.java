package cz.dusanrychnovsky.chessengine.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AssertExtensions {

  public static <T> void assertStreamEquals(Stream<T> expected, Stream<T> actual) {
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  public static <T> void assertStreamSetEquals(Set<T> expected, Stream<T> actual) {
    var actualList = actual.toList();
    assertEquals(expected.size(), actualList.size());
    assertEquals(expected, new HashSet<>(actualList));
  }
}
