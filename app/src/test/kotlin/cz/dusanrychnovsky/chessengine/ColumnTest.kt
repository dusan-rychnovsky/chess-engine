package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Column.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ColumnTest {

    @Test
    fun next_returnsNextColumn() {
        assertEquals(CB, CA.next())
        assertEquals(CC, CB.next())
        assertEquals(CD, CC.next())
        assertEquals(CE, CD.next())
        assertEquals(CF, CE.next())
        assertEquals(CG, CF.next())
        assertEquals(CH, CG.next())
    }

    @Test
    fun next_lastColumn_ReturnsNull() {
        assertNull(CH.next())
    }

    @Test
    fun prev_returnsPreviousColumn() {
        assertEquals(CG, CH.prev())
        assertEquals(CF, CG.prev())
        assertEquals(CE, CF.prev())
        assertEquals(CD, CE.prev())
        assertEquals(CC, CD.prev())
        assertEquals(CB, CC.prev())
        assertEquals(CA, CB.prev())
    }

    @Test
    fun prev_firstColumn_returnsNull() {
        assertNull(CA.prev())
    }
}
