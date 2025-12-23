package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.extensions.next
import cz.dusanrychnovsky.chessengine.extensions.prev

enum class Column : Navigable<Column> {
    CA, CB, CC, CD, CE, CF, CG, CH;

    override fun next(): Column? = this.next<Column>()
    override fun prev(): Column? = this.prev<Column>()
}
