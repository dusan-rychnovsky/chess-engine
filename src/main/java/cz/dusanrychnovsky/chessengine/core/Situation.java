package cz.dusanrychnovsky.chessengine.core;

import java.util.EnumMap;
import java.util.Map;

public class Situation {

  private final Map<Position, Piece> pieces;
  private final Color currentPlayer;

  public Situation(Color currentPlayer, Map<Position, Piece> pieces) {
    this.pieces = pieces; // TODO: use EnumMap
    this.currentPlayer = currentPlayer;
  }
}
