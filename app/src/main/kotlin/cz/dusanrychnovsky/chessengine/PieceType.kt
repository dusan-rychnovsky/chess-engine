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
            return emptySet()
        }
    },
    ROOK {
        override fun moves(from: Position): Set<Move> {
            val moves = mutableSetOf<Move>()
            for (col in Column.entries) {
                if (col != from.column) {
                    moves.add(Move(from, Position(col, from.row)))
                }
            }
            for (row in Row.entries) {
                if (row != from.row) {
                    moves.add(Move(from, Position(from.column, row)))
                }
            }
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
