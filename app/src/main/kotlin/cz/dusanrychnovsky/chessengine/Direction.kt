package cz.dusanrychnovsky.chessengine

typealias Direction = (Position) -> Position?

object Directions {
    val LEFT: Direction = { pos -> pos.column.prev()?.let { Position(it, pos.row) } }
    val RIGHT: Direction = { pos -> pos.column.next()?.let { Position(it, pos.row) } }
    val HORIZONTAL = setOf(LEFT, RIGHT)

    val DOWN: Direction = { pos -> pos.row.prev()?.let { Position(pos.column, it) } }
    val UP: Direction = { pos -> pos.row.next()?.let { Position(pos.column, it) } }
    val VERTICAL = setOf(DOWN, UP)

    val UP_RIGHT: Direction = { pos ->
        pos.column.next()?.let { col ->
            pos.row.next()?.let { row ->
                Position(col, row)
            }
        }
    }
    val UP_LEFT: Direction = { pos ->
        pos.column.prev()?.let { col ->
            pos.row.next()?.let { row ->
                Position(col, row)
            }
        }
    }
    val DOWN_RIGHT: Direction = { pos ->
        pos.column.next()?.let { col ->
            pos.row.prev()?.let { row ->
                Position(col, row)
            }
        }
    }
    val DOWN_LEFT: Direction = { pos ->
        pos.column.prev()?.let { col ->
            pos.row.prev()?.let { row ->
                Position(col, row)
            }
        }
    }
    val DIAGONAL = listOf(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT)
}

