package cz.dusanrychnovsky.chessengine

enum class Column {
    CA, CB, CC, CD, CE, CF, CG, CH;

    fun next(): Column? {
        return if (ordinal < entries.size - 1) {
            entries[ordinal + 1]
        } else {
            null
        }
    }

    fun prev(): Column? {
        return if (ordinal > 0) {
            entries[ordinal - 1]
        } else {
            null
        }
    }
}
