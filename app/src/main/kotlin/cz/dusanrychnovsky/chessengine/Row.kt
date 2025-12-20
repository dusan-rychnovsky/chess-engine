package cz.dusanrychnovsky.chessengine

enum class Row {
    R1, R2, R3, R4, R5, R6, R7, R8;

    fun next(): Row? {
        return if (ordinal < entries.size - 1) {
            entries[ordinal + 1]
        } else {
            null
        }
    }

    fun prev(): Row? {
        return if (ordinal > 0) {
            entries[ordinal - 1]
        } else {
            null
        }
    }
}