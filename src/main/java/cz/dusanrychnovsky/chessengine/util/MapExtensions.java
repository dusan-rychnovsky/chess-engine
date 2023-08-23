package cz.dusanrychnovsky.chessengine.util;

import java.util.Map;
import java.util.Optional;

public class MapExtensions {

  private MapExtensions() {
    throw new UnsupportedOperationException("Static utility class.");
  }

  /**
   * @return value which corresponds to the given key in the given map, if any.
   */
  public static <K, V> Optional<V> get(Map<K, V> map, K key) {
    return Optional.ofNullable(map.get(key));
  }
}
