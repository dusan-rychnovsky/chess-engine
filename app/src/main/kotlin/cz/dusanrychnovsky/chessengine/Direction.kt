package cz.dusanrychnovsky.chessengine

typealias Direction = (Square) -> Square?

object Directions {
    val LEFT: Direction = { square -> square.column.prev()?.let { Square(it, square.row) } }
    val RIGHT: Direction = { square -> square.column.next()?.let { Square(it, square.row) } }
    val HORIZONTAL = setOf(LEFT, RIGHT)

    val DOWN: Direction = { square -> square.row.prev()?.let { Square(square.column, it) } }
    val UP: Direction = { square -> square.row.next()?.let { Square(square.column, it) } }
    val VERTICAL = setOf(DOWN, UP)

    val UP_RIGHT: Direction = { square ->
        square.column.next()?.let { col ->
            square.row.next()?.let { row ->
                Square(col, row)
            }
        }
    }
    val UP_LEFT: Direction = { square ->
        square.column.prev()?.let { col ->
            square.row.next()?.let { row ->
                Square(col, row)
            }
        }
    }
    val DOWN_RIGHT: Direction = { square ->
        square.column.next()?.let { col ->
            square.row.prev()?.let { row ->
                Square(col, row)
            }
        }
    }
    val DOWN_LEFT: Direction = { square ->
        square.column.prev()?.let { col ->
            square.row.prev()?.let { row ->
                Square(col, row)
            }
        }
    }
    val DIAGONAL = listOf(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT)
}

