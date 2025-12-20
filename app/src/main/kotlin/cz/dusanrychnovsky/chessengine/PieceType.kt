package cz.dusanrychnovsky.chessengine

enum class PieceType {
    PAWN {
        override fun moves(from: Position): Set<Move> {
            return emptySet()
        }
    },
    KNIGHT {
        override fun moves(from: Position): Set<Move> {
            return emptySet()
        }
    },
    BISHOP {
        override fun moves(from: Position): Set<Move> {
            val moves = mutableSetOf<Move>()
            for (direction in Directions.DIAGONAL) {
                generateSequence(from) { direction(it) }
                    .forEach { moves.add(Move(from, it)) }
            }
            moves.remove(Move(from, from))
            return moves
        }
    },
    ROOK {
        override fun moves(from: Position): Set<Move> {
            val moves = mutableSetOf<Move>()
            for (direction in (Directions.HORIZONTAL + Directions.VERTICAL)) {
                generateSequence(from) { direction(it) }
                    .forEach { moves.add(Move(from, it)) }
            }
            moves.remove(Move(from, from))
            return moves
        }
    },
    QUEEN {
        override fun moves(from: Position): Set<Move> {
            return emptySet()
        }
    },
    KING {
        override fun moves(from: Position): Set<Move> {
            return emptySet()
        }
    };

    abstract fun moves(from: Position): Set<Move>
}
