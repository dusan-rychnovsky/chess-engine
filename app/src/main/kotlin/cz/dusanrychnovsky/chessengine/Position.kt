package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Color.*
import cz.dusanrychnovsky.chessengine.PieceType.*
import cz.dusanrychnovsky.chessengine.Row.*

data class Position(val currentPlayer: Color, val pieces: Map<Square, Piece>) {

    fun isValid(move: Move): Boolean {
        // TODO: en passant
        // TODO: pawn promotions
        // TODO: castling

        val fromPiece = pieces[move.from] ?: return false
        if (fromPiece.color != currentPlayer) {
            return false
        }
        if (!fromPiece.type.moves(move.from, this).contains(move)) {
            return false
        }

        for (through in move.through) {
            if (pieces.containsKey(through)) {
                return false
            }
        }

        val toPiece = pieces[move.to]
        if (toPiece != null && toPiece.color == fromPiece.color) {
            return false
        }

        return true
    }

    fun apply(move: Move): Position {
        val piece = pieces[move.from] ?: throw IllegalArgumentException("No piece at ${move.from}.")
        return Position(
            currentPlayer.opposite(),
            pieces
                .minus(move.from)
                .plus(move.to to piece)
        )
    }

    fun validMoves(): Set<Move> {
        return pieces.entries
            .filter { it.value.color == currentPlayer }
            .flatMap { (square, piece) -> piece.type.moves(square, this) }
            .filter { isValid(it) }
            .toSet()
    }

    fun isCheck(): Boolean {
        val opponent = currentPlayer.opposite()
        val opponentsView = this.copy(currentPlayer = opponent)
        val kingSquare = opponentsView.getKingSquare(currentPlayer)
        return pieces
            .filter { (_, piece) -> piece.color == opponent }
            .flatMap { (square, piece) -> piece.type.moves(square, opponentsView) }
            .filter { it.to == kingSquare }
            .any { opponentsView.isValid(it) }
    }

    fun getKingSquare(color: Color) : Square {
        return pieces.entries
            .first { it.value.color == color && it.value.type == KING }
            .key
    }

    companion object {
        val INITIAL = Position(
            WHITE,
            buildMap {
                val pattern = listOf(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK)
                for ((idx, col) in Column.entries.withIndex()) {
                    put(Square(col, R1), Piece(WHITE, pattern[idx]))
                    put(Square(col, R2), Piece(WHITE, PAWN))
                    put(Square(col, R8), Piece(BLACK, pattern[idx]))
                    put(Square(col, R7), Piece(BLACK, PAWN))
                }
            }
        )
    }
}
