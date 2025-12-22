package cz.dusanrychnovsky.chessengine

data class Square(val  column: Column, val row: Row) {
    fun next(nextColumn: (Column) -> Column?, nextRow: (Row) -> Row?): Square? {
        val col = nextColumn(column)
        val row = nextRow(row)
        if (col != null && row != null) {
            return Square(col, row)
        }
        return null
    }
}