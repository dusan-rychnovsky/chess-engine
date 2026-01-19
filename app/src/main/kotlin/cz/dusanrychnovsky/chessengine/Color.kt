package cz.dusanrychnovsky.chessengine

enum class Color {
    WHITE,
    BLACK;

    fun opposite(): Color = when (this) {
        WHITE -> BLACK
        BLACK -> WHITE
    }
}
