package cz.dusanrychnovsky.chessengine

enum class PieceType {
    PAWN {
        override fun moves(from: Square): Set<Move> {
            return emptySet()
        }
    },
    KNIGHT {
        override fun moves(from: Square): Set<Move> {
            fun offset(col: (Column) -> Column?, row: (Row) -> Row?) = Pair(col, row)
            val offsets = listOf(
                offset({ it.next()?.next() }, { it.next() }), // right-up
                offset({ it.next()?.next() }, { it.prev() }), // right-down
                offset({ it.prev()?.prev() }, { it.next() }), // left-up
                offset({ it.prev()?.prev() }, { it.prev() }), // left-down
                offset({ it.next() }, { it.next()?.next() }), // right-up
                offset({ it.next() }, { it.prev()?.prev() }), // right-down
                offset({ it.prev() }, { it.next()?.next() }), // left-up
                offset({ it.prev() }, { it.prev()?.prev() })  // left-down
            )
            return offsets.mapNotNull { (colOffset, rowOffset) ->
                val square = from.next(colOffset, rowOffset)
                if (square != null) {
                    Move(from, square)
                } else {
                    null
                }
            }.toSet()
        }
    },
    BISHOP {
        override fun moves(from: Square): Set<Move> {
            return closure(from, Directions.DIAGONAL)
        }
    },
    ROOK {
        override fun moves(from: Square): Set<Move> {
            return closure(from, Directions.HORIZONTAL + Directions.VERTICAL)
        }
    },
    QUEEN {
        override fun moves(from: Square): Set<Move> {
            return closure(from, Directions.HORIZONTAL + Directions.VERTICAL + Directions.DIAGONAL)
        }
    },
    KING {
        override fun moves(from: Square): Set<Move> {
            return (Directions.HORIZONTAL + Directions.VERTICAL + Directions.DIAGONAL)
                .mapNotNull { direction -> direction(from) }
                .map { to -> Move(from, to) }
                .toSet()
        }
    };

    abstract fun moves(from: Square): Set<Move>

    protected fun closure(from: Square, directions: Collection<Direction>): Set<Move> =
        directions.flatMap { direction ->
            val start = direction(from) // skip the 'from' position
            generateSequence(start) { direction(it) }
                .map { Move(from, it) }
        }.toSet()
}
