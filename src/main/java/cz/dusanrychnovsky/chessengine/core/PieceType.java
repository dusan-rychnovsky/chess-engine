package cz.dusanrychnovsky.chessengine.core;

import java.util.*;
import java.util.stream.Collectors;

import static cz.dusanrychnovsky.chessengine.core.Direction.*;
import static cz.dusanrychnovsky.chessengine.core.Row.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public enum PieceType {

    ROOK {
        /**
         * @return All moves a rook can make on an empty chessboard from
         * the given position. A rook can move to all positions in the same
         * column and in the same row, except the position on which it's
         * already standing.
         */
        @Override
        public Set<Move> getMovePatterns(Situation situation, Position from) {
            var result = new HashSet<Move>();
            var directions = Set.of(top(), bottom(), left(), right());
            for (var direction : directions) {
                var intermediaries = new HashSet<Position>();
                Optional<Position> to = Optional.of(from);
                while ((to = direction.apply(to.get())).isPresent()) {
                    result.add(new Move(from, to.get(), new HashSet<>(intermediaries)));
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
        public Set<Move> getMovePatterns(Situation situation, Position from) {
            var result = new HashSet<Move>();
            var directions = Set.of(topLeft(), topRight(), bottomLeft(), bottomRight());
            for (var direction : directions) {
                var intermediaries = new HashSet<Position>();
                Optional<Position> to = Optional.of(from);
                while ((to = direction.apply(to.get())).isPresent()) {
                    result.add(new Move(from, to.get(), new HashSet<>(intermediaries)));
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
        public Set<Move> getMovePatterns(Situation situation, Position from) {
            var result = new HashSet<Move>();
            result.addAll(ROOK.getMovePatterns(situation, from));
            result.addAll(BISHOP.getMovePatterns(situation, from));
            return result;
        }
    },

    KING {
        /**
         * @return All moves a king can make on an empty chessboard from
         * the given position. A king can move to all adjacent fields.
         */
        @Override
        public Set<Move> getMovePatterns(Situation situation, Position from) {
            return Position.getAllAdjacent(from)
                .map(to -> new Move(from, to, Set.of()))
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
        public Set<Move> getMovePatterns(Situation situation, Position from) {
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
                .map(to -> new Move(from, to.get(), Set.of()))
                .collect(Collectors.toSet());
        }
    },

    PAWN {
        /**
         * @return All moves a pawn can make from the given position, without
         * taking into account potential check implications. A pawn can
         * move vertically (white pawn up, black pawn down), exactly one
         * position, or two when starting from the pawn's initial position.
         * A pawn can additionally capture opponent's pieces which are located
         * exactly one position diagonally (white pawn top-right/-left, black
         * pawn bottom-right/-left).
         *
         * Unlike other piece types, for pawn we need to consider positioning
         * of other pieces on the board to identify moves patterns.
         *
         * TODO: support en passant
         */
        @Override
        public Set<Move> getMovePatterns(Situation situation, Position from) {
            var tos = new HashSet<Optional<Position>>();

            var piece = situation.getPieceAt(from).orElseThrow();
            var color = piece.color();

            var moveDirection = getMoveDirection(color);
            tos.add(from.apply(moveDirection));

            if (isInitialPosition(color, from)) {
                tos.add(from.apply(moveDirection, moveDirection));
            }

            var captureDirections = getCaptureDirections(color);
            for (var direction : captureDirections) {
                var to = from.apply(direction);
                if (to.isPresent()) {
                    var targetPiece = situation.getPieceAt(to.get());
                    if (targetPiece.isPresent() && targetPiece.get().color() == color.getOpposite()) {
                        tos.add(to);
                    }
                }
            }

            return tos.stream()
                .filter(Optional::isPresent)
                .map(to -> new Move(from, to.get(), Set.of()))
                .collect(toSet());
        }

        private Direction getMoveDirection(Color color) {
            return switch (color) {
                case WHITE -> top();
                case BLACK -> bottom();
                default -> throw new IllegalArgumentException("Unknown color: " + color);
            };
        }

        private Set<Direction> getCaptureDirections(Color color) {
            return switch (color) {
                case WHITE -> Set.of(topLeft(), topRight());
                case BLACK -> Set.of(bottomLeft(), bottomRight());
                default -> throw new IllegalArgumentException("Unknown color: " + color);
            };
        }

        private boolean isInitialPosition(Color color, Position position) {
            return position.getRow() == switch (color) {
                case WHITE -> R2;
                case BLACK -> R7;
                default -> throw new IllegalArgumentException("Unknown color: " + color);
            };
        }
    };

    /**
     * @return All moves a piece of the represented type can make on an empty
     * chessboard from the given position.
     */
    public abstract Set<Move> getMovePatterns(Situation situation, Position from);
}
