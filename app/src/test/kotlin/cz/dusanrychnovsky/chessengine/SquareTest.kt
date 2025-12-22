package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Column.*
import cz.dusanrychnovsky.chessengine.Row.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SquareTest {
    @Test
    fun next_returnsNextSquare() {
        assertEquals(
            Square(CG, R3),
            Square(CF, R2).next({ it.next() }, { it.next() }))
    }

    @Test
    fun next_atRightBoardEdge_returnsNull() {
        assertNull(Square(CH, R5).next({ it.next() }, { it.next() }))
    }

    @Test
    fun next_atTopBoardEdge_returnsNull() {
        assertNull(Square(CE, R8).next({ it.next() }, { it.next() }))
    }
}
