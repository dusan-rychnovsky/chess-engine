package cz.dusanrychnovsky.chessengine

import kotlin.math.abs

enum class PieceType {
    PAWN {
        override fun moves(from: Position): Set<Move> {
            return emptySet()
        }
    },
    KNIGHT {
        override fun moves(from: Position): Set<Move> {
            val offsets = listOf(
                2 to 1, // up-right
                2 to -1, // down-right
                -2 to 1, // up-left
                -2 to -1, // down-left
                1 to 2, // right-up
                1 to -2, // right-down
                -1 to 2, // left-up
                -1 to -2 // left-down
            )

            return offsets.mapNotNull { (colOffset, rowOffset) ->
                var col: Column? = from.column
                var row: Row? = from.row

                repeat(abs(colOffset)) {
                    col = if (colOffset > 0) col?.next() else col?.prev()
                }

                repeat(abs(rowOffset)) {
                    row = if (rowOffset > 0) row?.next() else row?.prev()
                }

                if (col != null && row != null) {
                    Move(from, Position(col, row))
                }
                else {
                    null
                }
            }.toSet()
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
            return (Directions.HORIZONTAL + Directions.VERTICAL + Directions.DIAGONAL)
                .mapNotNull({ direction -> direction(from) })
                .map({ to -> Move(from, to) })
                .toSet()
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
