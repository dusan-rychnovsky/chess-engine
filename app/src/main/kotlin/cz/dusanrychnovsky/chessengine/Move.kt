package cz.dusanrychnovsky.chessengine

data class Move(val from: Square, val to: Square, val through: List<Square>) {
}