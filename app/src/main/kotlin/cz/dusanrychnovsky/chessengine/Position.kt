package cz.dusanrychnovsky.chessengine

data class Position(val pieces: Map<Square, Piece>) {
    fun isValid(move: Move): Boolean {
        val piece = pieces[move.from] ?: return false
        if (!piece.type.moves(move.from).contains(move)) {
            return false
        }

        // TODO
        return true
    }
}
