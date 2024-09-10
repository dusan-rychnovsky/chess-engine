package cz.dusanrychnovsky.chessengine.core;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum PieceTypeV2 {

    ROOK {
        @Override
        public Set<MoveV2> getMovesTemplate(Situation situation, Position from) {
            var result = new HashSet<MoveV2>();
            var intermediaries = new HashSet<Position>();
            var columnTo = Optional.of(from.getColumn());
            while ((columnTo = columnTo.get().getPrevious()).isPresent()) {
                result.add(new MoveV2(from, Position.get(columnTo.get(), from.getRow()), new HashSet<>(intermediaries)));
                intermediaries.add(Position.get(columnTo.get(), from.getRow()));
            }
            intermediaries = new HashSet<>();
            columnTo = Optional.of(from.getColumn());
            while ((columnTo = columnTo.get().getNext()).isPresent()) {
                result.add(new MoveV2(from, Position.get(columnTo.get(), from.getRow()), new HashSet<>(intermediaries)));
                intermediaries.add(Position.get(columnTo.get(), from.getRow()));
            }
            intermediaries = new HashSet<>();
            var rowTo = Optional.of(from.getRow());
            while ((rowTo = rowTo.get().getNext()).isPresent()) {
                result.add(new MoveV2(from, Position.get(from.getColumn(), rowTo.get()), new HashSet<>(intermediaries)));
                intermediaries.add(Position.get(from.getColumn(), rowTo.get()));
            }
            intermediaries = new HashSet<>();
            rowTo = Optional.of(from.getRow());
            while ((rowTo = rowTo.get().getPrevious()).isPresent()) {
                result.add(new MoveV2(from, Position.get(from.getColumn(), rowTo.get()), new HashSet<>(intermediaries)));
                intermediaries.add(Position.get(from.getColumn(), rowTo.get()));
            }
            return result;
        }
    };

    /**
     * @return All moves a piece of the represented type can make on an empty
     * chessboard from the given position.
     */
    public abstract Set<MoveV2> getMovesTemplate(Situation situation, Position position);
}
