package cz.dusanrychnovsky.chessengine

data class Position(val pieces: Map<Square, Piece>) {
    fun isValid(move: Move): Boolean {
        val fromPiece = pieces[move.from] ?: return false
        if (!fromPiece.type.moves(move.from).contains(move)) {
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
