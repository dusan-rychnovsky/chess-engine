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
            return closure(from, Directions.DIAGONAL)
        }
    },
    ROOK {
        override fun moves(from: Position): Set<Move> {
            return closure(from, Directions.HORIZONTAL + Directions.VERTICAL)
        }
    },
    QUEEN {
        override fun moves(from: Position): Set<Move> {
            return closure(from, Directions.HORIZONTAL + Directions.VERTICAL + Directions.DIAGONAL)
        }
    },
    KING {
        override fun moves(from: Position): Set<Move> {
            return emptySet()
        }
    };

    abstract fun moves(from: Position): Set<Move>

    protected fun closure(from: Position, directions: Collection<Direction>): Set<Move> =
        directions.flatMap { direction ->
            val start = direction(from) // skip the 'from' position
            generateSequence(start) { direction(it) }
                .map { Move(from, it) }
        }.toSet()
}
