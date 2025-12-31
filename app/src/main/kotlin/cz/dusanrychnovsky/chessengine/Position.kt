package cz.dusanrychnovsky.chessengine

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
}
