package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Column.*
import cz.dusanrychnovsky.chessengine.Directions.LEFT
import cz.dusanrychnovsky.chessengine.Directions.RIGHT
import cz.dusanrychnovsky.chessengine.Directions.UP
import cz.dusanrychnovsky.chessengine.Directions.DOWN
import cz.dusanrychnovsky.chessengine.Directions.UP_RIGHT
import cz.dusanrychnovsky.chessengine.Directions.UP_LEFT
import cz.dusanrychnovsky.chessengine.Directions.DOWN_RIGHT
import cz.dusanrychnovsky.chessengine.Directions.DOWN_LEFT
import cz.dusanrychnovsky.chessengine.Row.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DirectionTest {

    @Test
    fun left_returnsSquareToTheLeft() {
        assertEquals(
            Square(CD, R2),
            LEFT(Square(CE, R2))
        )
    }

    @Test
    fun left_atLeftBoardEdge_returnsNull() {
        assertNull(LEFT(Square(CA, R4)))
    }

    @Test
    fun right_returnsSquareToTheRight() {
        assertEquals(
            Square(CF, R2),
            RIGHT(Square(CE, R2))
        )
    }

    @Test
    fun right_atRightBoardEdge_returnsNull() {
        assertNull(RIGHT(Square(CH, R4)))
    }

    @Test
    fun up_returnsSquareAbove() {
        assertEquals(
            Square(CE, R3),
            UP(Square(CE, R2))
        )
    }

    @Test
    fun up_atTopBoardEdge_returnsNull() {
        assertNull(UP(Square(CE, R8)))
    }

    @Test
    fun down_returnsSquareBelow() {
        assertEquals(
            Square(CE, R1),
            DOWN(Square(CE, R2))
        )
    }

    @Test
    fun down_atBottomBoardEdge_returnsNull() {
        assertNull(DOWN(Square(CE, R1)))
    }

    @Test
    fun upRight_returnsSquareDiagonallyUpRight() {
        assertEquals(
            Square(CF, R3),
            UP_RIGHT(Square(CE, R2))
        )
    }

    @Test
    fun upRight_atTopEdge_returnsNull() {
        assertNull(UP_RIGHT(Square(CE, R8)))
    }

    @Test
    fun upRight_atRightEdge_returnsNull() {
        assertNull(UP_RIGHT(Square(CH, R2)))
    }

    @Test
    fun upRight_atTopRightCorner_returnsNull() {
        assertNull(UP_RIGHT(Square(CH, R8)))
    }

    @Test
    fun upLeft_returnsSquareDiagonallyUpLeft() {
        assertEquals(
            Square(CD, R3),
            UP_LEFT(Square(CE, R2))
        )
    }

    @Test
    fun upLeft_atTopEdge_returnsNull() {
        assertNull(UP_LEFT(Square(CE, R8)))
    }

    @Test
    fun upLeft_atLeftEdge_returnsNull() {
        assertNull(UP_LEFT(Square(CA, R2)))
    }

    @Test
    fun upLeft_atTopLeftCorner_returnsNull() {
        assertNull(UP_LEFT(Square(CA, R8)))
    }

    @Test
    fun downRight_returnsSquareDiagonallyDownRight() {
        assertEquals(
            Square(CF, R1),
            DOWN_RIGHT(Square(CE, R2))
        )
    }

    @Test
    fun downRight_atBottomEdge_returnsNull() {
        assertNull(DOWN_RIGHT(Square(CE, R1)))
    }

    @Test
    fun downRight_atRightEdge_returnsNull() {
        assertNull(DOWN_RIGHT(Square(CH, R2)))
    }

    @Test
    fun downRight_atBottomRightCorner_returnsNull() {
        assertNull(DOWN_RIGHT(Square(CH, R1)))
    }

    @Test
    fun downLeft_returnsSquareDiagonallyDownLeft() {
        assertEquals(
            Square(CD, R1),
            DOWN_LEFT(Square(CE, R2))
        )
    }

    @Test
    fun downLeft_atBottomEdge_returnsNull() {
        assertNull(DOWN_LEFT(Square(CE, R1)))
    }

    @Test
    fun downLeft_atLeftEdge_returnsNull() {
        assertNull(DOWN_LEFT(Square(CA, R2)))
    }

    @Test
    fun downLeft_atBottomLeftCorner_returnsNull() {
        assertNull(DOWN_LEFT(Square(CA, R1)))
    }
}