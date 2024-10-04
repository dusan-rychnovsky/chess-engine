package cz.dusanrychnovsky.chessengine.core;

import java.util.Set;

/**
 * Represents a move from a given to a given position on a chessboard.
 */
public record Move(Position from, Position to, Set<Position> intermediaries) {
}
