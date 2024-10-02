package cz.dusanrychnovsky.chessengine.core;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.Direction.*;
import static java.util.stream.Collectors.toSet;

public enum PieceTypeV2 {

    ROOK {
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
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            var result = new HashSet<MoveV2>();
            result.addAll(ROOK.getMovesTemplate(situation, from));
            result.addAll(BISHOP.getMovesTemplate(situation, from));
            return result;
        }
    },

    KING {
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            return Position.getAllAdjacent(from)
                .map(to -> new MoveV2(from, to, Set.of()))
                .collect(toSet());
        }
    };

    /**
     * @return All moves a piece of the represented type can make on an empty
     * chessboard from the given position.
     */
    public abstract Set<MoveV2> getMovesTemplate(Situation situation, Position from);
}
