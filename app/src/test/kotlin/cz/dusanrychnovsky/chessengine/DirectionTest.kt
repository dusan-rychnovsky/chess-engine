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
    fun left_returnsPositionToTheLeft() {
        assertEquals(
            Position(CD, R2),
            LEFT(Position(CE, R2))
        )
    }

    @Test
    fun left_atLeftBoardEdge_returnsNull() {
        assertNull(LEFT(Position(CA, R4)))
    }

    @Test
    fun right_returnsPositionToTheRight() {
        assertEquals(
            Position(CF, R2),
            RIGHT(Position(CE, R2))
        )
    }

    @Test
    fun right_atRightBoardEdge_returnsNull() {
        assertNull(RIGHT(Position(CH, R4)))
    }

    @Test
    fun up_returnsPositionAbove() {
        assertEquals(
            Position(CE, R3),
            UP(Position(CE, R2))
        )
    }

    @Test
    fun up_atTopBoardEdge_returnsNull() {
        assertNull(UP(Position(CE, R8)))
    }

    @Test
    fun down_returnsPositionBelow() {
        assertEquals(
            Position(CE, R1),
            DOWN(Position(CE, R2))
        )
    }

    @Test
    fun down_atBottomBoardEdge_returnsNull() {
        assertNull(DOWN(Position(CE, R1)))
    }

    @Test
    fun upRight_returnsPositionDiagonallyUpRight() {
        assertEquals(
            Position(CF, R3),
            UP_RIGHT(Position(CE, R2))
        )
    }

    @Test
    fun upRight_atTopEdge_returnsNull() {
        assertNull(UP_RIGHT(Position(CE, R8)))
    }

    @Test
    fun upRight_atRightEdge_returnsNull() {
        assertNull(UP_RIGHT(Position(CH, R2)))
    }

    @Test
    fun upRight_atTopRightCorner_returnsNull() {
        assertNull(UP_RIGHT(Position(CH, R8)))
    }

    @Test
    fun upLeft_returnsPositionDiagonallyUpLeft() {
        assertEquals(
            Position(CD, R3),
            UP_LEFT(Position(CE, R2))
        )
    }

    @Test
    fun upLeft_atTopEdge_returnsNull() {
        assertNull(UP_LEFT(Position(CE, R8)))
    }

    @Test
    fun upLeft_atLeftEdge_returnsNull() {
        assertNull(UP_LEFT(Position(CA, R2)))
    }

    @Test
    fun upLeft_atTopLeftCorner_returnsNull() {
        assertNull(UP_LEFT(Position(CA, R8)))
    }

    @Test
    fun downRight_returnsPositionDiagonallyDownRight() {
        assertEquals(
            Position(CF, R1),
            DOWN_RIGHT(Position(CE, R2))
        )
    }

    @Test
    fun downRight_atBottomEdge_returnsNull() {
        assertNull(DOWN_RIGHT(Position(CE, R1)))
    }

    @Test
    fun downRight_atRightEdge_returnsNull() {
        assertNull(DOWN_RIGHT(Position(CH, R2)))
    }

    @Test
    fun downRight_atBottomRightCorner_returnsNull() {
        assertNull(DOWN_RIGHT(Position(CH, R1)))
    }

    @Test
    fun downLeft_returnsPositionDiagonallyDownLeft() {
        assertEquals(
            Position(CD, R1),
            DOWN_LEFT(Position(CE, R2))
        )
    }

    @Test
    fun downLeft_atBottomEdge_returnsNull() {
        assertNull(DOWN_LEFT(Position(CE, R1)))
    }

    @Test
    fun downLeft_atLeftEdge_returnsNull() {
        assertNull(DOWN_LEFT(Position(CA, R2)))
    }

    @Test
    fun downLeft_atBottomLeftCorner_returnsNull() {
        assertNull(DOWN_LEFT(Position(CA, R1)))
    }
}