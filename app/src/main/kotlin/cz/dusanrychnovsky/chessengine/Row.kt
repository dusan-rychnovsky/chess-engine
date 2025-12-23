package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.extensions.next
import cz.dusanrychnovsky.chessengine.extensions.prev

enum class Row : Navigable<Row> {
    R1, R2, R3, R4, R5, R6, R7, R8;

    override fun next(): Row? = this.next<Row>()
    override fun prev(): Row? = this.prev<Row>()
}
