package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Directions.DOWN
import cz.dusanrychnovsky.chessengine.Directions.LEFT
import cz.dusanrychnovsky.chessengine.Directions.RIGHT
import cz.dusanrychnovsky.chessengine.Directions.UP

enum class PieceType {
    PAWN {
        override fun moves(from: Square): Set<Move> {
            return emptySet()
        }
    },
    KNIGHT {
        override fun moves(from: Square): Set<Move> {
            var directions = setOf(
                listOf(UP, UP, RIGHT),
                listOf(UP, RIGHT, RIGHT),
                listOf(DOWN, RIGHT, RIGHT),
                listOf(DOWN, DOWN, RIGHT),
                listOf(DOWN, DOWN, LEFT),
                listOf(DOWN, LEFT, LEFT),
                listOf(UP, LEFT, LEFT),
                listOf(UP, UP, LEFT)
            );
            return directions
                .mapNotNull { dirs ->
                    dirs.fold(from as Square?) { acc, direction ->
                        acc?.let { direction(it) }
                    }
                }
                .map { to -> Move(from, to, emptyList()) }
                .toSet()
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
                .map { to -> Move(from, to, emptyList()) }
                .toSet()
        }
    };

    abstract fun moves(from: Square): Set<Move>

    protected fun closure(from: Square, directions: Collection<Direction>): Set<Move> {
        val moves = mutableSetOf<Move>()
        for (direction in directions) {
            val through = mutableListOf<Square>();
            var next = direction(from)
            while (next != null) {
                moves.add(Move(from, next, through.toList()))
                through.add(next)
                next = direction(next)
            }
        }
        return moves;
    }
}
