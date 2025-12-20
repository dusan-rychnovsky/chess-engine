package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Column.*
import cz.dusanrychnovsky.chessengine.Row.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PositionTest {
    @Test
    fun next_returnsNextPosition() {
        assertEquals(
            Position(CG, R3),
            Position(CF, R2).next({ it.next() }, { it.next() }))
    }

    @Test
    fun next_atRightBoardEdge_returnsNull() {
        assertNull(Position(CH, R5).next({ it.next() }, { it.next() }))
    }

    @Test
    fun next_atTopBoardEdge_returnsNull() {
        assertNull(Position(CE, R8).next({ it.next() }, { it.next() }))
    }
}
