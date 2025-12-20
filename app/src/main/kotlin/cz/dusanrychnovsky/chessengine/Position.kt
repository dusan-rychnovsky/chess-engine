package cz.dusanrychnovsky.chessengine

data class Position(val  column: Column, val row: Row) {
    fun next(nextColumn: (Column) -> Column?, nextRow: (Row) -> Row?): Position? {
        val col = nextColumn(column)
        val row = nextRow(row)
        if (col != null && row != null) {
            return Position(col, row)
        }
        return null
    }
}