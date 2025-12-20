package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Row.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RowTest {

    @Test
    fun next_returnsNextRow() {
        assertEquals(R2, R1.next())
        assertEquals(R3, R2.next())
        assertEquals(R4, R3.next())
        assertEquals(R5, R4.next())
        assertEquals(R6, R5.next())
        assertEquals(R7, R6.next())
        assertEquals(R8, R7.next())
    }

    @Test
    fun next_lastRow_ReturnsNull() {
        assertNull(R8.next())
    }

    @Test
    fun prev_returnsPreviousRow() {
        assertEquals(R7, R8.prev())
        assertEquals(R6, R7.prev())
        assertEquals(R5, R6.prev())
        assertEquals(R4, R5.prev())
        assertEquals(R3, R4.prev())
        assertEquals(R2, R3.prev())
        assertEquals(R1, R2.prev())
    }

    @Test
    fun prev_firstRow_returnsNull() {
        assertNull(R1.prev())
    }
}

