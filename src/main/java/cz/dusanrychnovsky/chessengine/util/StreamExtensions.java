package cz.dusanrychnovsky.chessengine.util;

import java.util.stream.Stream;

public class StreamExtensions {

  private StreamExtensions() {
    throw new UnsupportedOperationException("Static utility class.");
  }

  /**
   * @return {@link Stream#concat} applied iteratively over the given array
   * of streams.
   */
  public static <T> Stream<T> concat(Stream<T>... streams) {
    if (streams.length == 1) {
      return streams[0];
    }
    var acc = Stream.concat(streams[0], streams[1]);
    for (var i = 2; i < streams.length; i++) {
      acc = Stream.concat(acc, streams[i]);
    }
    return acc;
  }
}
