package cz.dusanrychnovsky.chessengine.core;

import java.util.*;
import java.util.stream.Collectors;

import static cz.dusanrychnovsky.chessengine.core.Direction.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public enum PieceTypeV2 {

    ROOK {
        /**
         * @return All moves a rook can make on an empty chessboard from
         * the given position. A rook can move to all positions in the same
         * column and in the same row, except the position on which it's
         * already standing.
         */
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            var result = new HashSet<MoveV2>();
            var directions = Set.of(top(), bottom(), left(), right());
            for (var direction : directions) {
                var intermediaries = new HashSet<Position>();
                Optional<Position> to = Optional.of(from);
                while ((to = direction.apply(to.get())).isPresent()) {
                    result.add(new MoveV2(from, to.get(), new HashSet<>(intermediaries)));
                    intermediaries.add(to.get());
                }
            }
            return result;
        }
    },

    BISHOP {
        /**
         * @return All moves a bishop can make on an empty chessboard
         * from the given position. A bishop can move to all positions
         * diagonally, except the position on which it's already standing.
         */
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            var result = new HashSet<MoveV2>();
            var directions = Set.of(topLeft(), topRight(), bottomLeft(), bottomRight());
            for (var direction : directions) {
                var intermediaries = new HashSet<Position>();
                Optional<Position> to = Optional.of(from);
                while ((to = direction.apply(to.get())).isPresent()) {
                    result.add(new MoveV2(from, to.get(), new HashSet<>(intermediaries)));
                    intermediaries.add(to.get());
                }
            }
            return result;
        }
    },

    QUEEN {
        /**
         * @return All moves a queen can make on an empty chessboard
         * from the given position. A queen can move to all positions in the
         * same column and the same row, as well as diagonally, except the
         * position on which it's already standing.
         */
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            var result = new HashSet<MoveV2>();
            result.addAll(ROOK.getMovesTemplate(situation, from));
            result.addAll(BISHOP.getMovesTemplate(situation, from));
            return result;
        }
    },

    KING {
        /**
         * @return All moves a king can make on an empty chessboard from
         * the given position. A king can move to all adjacent fields.
         */
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            return Position.getAllAdjacent(from)
                .map(to -> new MoveV2(from, to, Set.of()))
                .collect(toSet());
        }
    },

    KNIGHT {
        /**
         * @return All moves a knight can make on an empty chessboard
         * from the given position. A knight can move in L-shaped pattern.
         *
         * Knight moves always have no intermediaries - knights can jump
         * over other pieces on the board.
         */
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            var directions = new ArrayList<List<Direction>>();
            for (var horizontal : new Direction[] { left(), right() }) {
                for (var vertical : new Direction[] { top(), bottom() }) {
                    directions.add(asList(horizontal, horizontal, vertical));
                    directions.add(asList(vertical, vertical, horizontal));
                }
            }
            return directions.stream()
                .map(from::apply)
                .filter(Optional::isPresent)
                .map(to -> new MoveV2(from, to.get(), Set.of()))
                .collect(Collectors.toSet());
        }
    };

    /**
     * @return All moves a piece of the represented type can make on an empty
     * chessboard from the given position.
     */
    public abstract Set<MoveV2> getMovesTemplate(Situation situation, Position from);
}
