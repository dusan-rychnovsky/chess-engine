package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Column.*
import cz.dusanrychnovsky.chessengine.Row.*
import cz.dusanrychnovsky.chessengine.Square.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SquareTest {
    @Test
    fun next_returnsNextSquare() {
        assertEquals(
            G3,
            F2.next({ it.next() }, { it.next() }))
    }

    @Test
    fun next_atRightBoardEdge_returnsNull() {
        assertNull(H5.next({ it.next() }, { it.next() }))
    }

    @Test
    fun next_atTopBoardEdge_returnsNull() {
        assertNull(E8.next({ it.next() }, { it.next() }))
    }

    @Test
    fun invoke_givenColumnAndRow_returnsSquare() {
        val square = Square(CD, R5)
        assertEquals(D5, square)
        assertEquals(CD, square.column)
        assertEquals(R5, square.row)
    }
}
