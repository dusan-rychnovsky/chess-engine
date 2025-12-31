package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Color.*
import cz.dusanrychnovsky.chessengine.Directions.DOWN
import cz.dusanrychnovsky.chessengine.Directions.DOWN_LEFT
import cz.dusanrychnovsky.chessengine.Directions.DOWN_RIGHT
import cz.dusanrychnovsky.chessengine.Directions.LEFT
import cz.dusanrychnovsky.chessengine.Directions.RIGHT
import cz.dusanrychnovsky.chessengine.Directions.UP
import cz.dusanrychnovsky.chessengine.Directions.UP_LEFT
import cz.dusanrychnovsky.chessengine.Directions.UP_RIGHT
import cz.dusanrychnovsky.chessengine.Row.*

enum class PieceType {
    PAWN {
        override fun moves(from: Square, position: Position): Set<Move> {
            val piece = position.pieces[from]?: return emptySet()
            val color = piece.color

            val moves = mutableSetOf<Move>()
            val (forward, left, right) = when (color) {
                WHITE -> Triple(UP, UP_LEFT, UP_RIGHT)
                BLACK -> Triple(DOWN, DOWN_LEFT, DOWN_RIGHT)
            }
            for (direction in setOf(left, right, forward)) {
                val to = direction(from) ?: continue
                moves.add(Move(from, to, emptyList()))
            }

            val startingRow = when (color) { WHITE -> R2; BLACK -> R7  }
            if (from.row == startingRow) {
                val firstStep = forward(from)
                val to = firstStep?.let { forward(it) }
                if (to != null) {
                    moves.add(Move(from, to, listOf(firstStep)))
                }
            }

            return moves
        }
    },
    KNIGHT {
        override fun moves(from: Square, position: Position): Set<Move> {
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
        override fun moves(from: Square, position: Position): Set<Move> {
            return closure(from, Directions.DIAGONAL)
        }
    },
    ROOK {
        override fun moves(from: Square, position: Position): Set<Move> {
            return closure(from, Directions.HORIZONTAL + Directions.VERTICAL)
        }
    },
    QUEEN {
        override fun moves(from: Square, position: Position): Set<Move> {
            return closure(from, Directions.HORIZONTAL + Directions.VERTICAL + Directions.DIAGONAL)
        }
    },
    KING {
        override fun moves(from: Square, position: Position): Set<Move> {
            return (Directions.HORIZONTAL + Directions.VERTICAL + Directions.DIAGONAL)
                .mapNotNull { direction -> direction(from) }
                .map { to -> Move(from, to, emptyList()) }
                .toSet()
        }
    };

    abstract fun moves(from: Square, position: Position): Set<Move>

    protected fun closure(from: Square, directions: Collection<Direction>): Set<Move> {
        val moves = mutableSetOf<Move>()
        for (direction in directions) {
            val through = mutableListOf<Square>()
            var next = direction(from)
            while (next != null) {
                moves.add(Move(from, next, through.toList()))
                through.add(next)
                next = direction(next)
            }
        }
        return moves
    }
}
