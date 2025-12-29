package cz.dusanrychnovsky.chessengine;

import cz.dusanrychnovsky.chessengine.Color.*
import cz.dusanrychnovsky.chessengine.PieceType.*
import cz.dusanrychnovsky.chessengine.Square.*
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PositionTest {

    val WHITE_KING = Piece(WHITE, KING)
    val WHITE_ROOK = Piece(WHITE, ROOK)
    val BLACK_KING = Piece(BLACK, KING)

    @Test
    fun isValid_noPieceAtSource_returnsFalse() {
        val position = Position(
            mapOf(
                B3 to WHITE_KING,
                F5 to BLACK_KING
            )
        )

        assertFalse(position.isValid(Move(C2, C4, listOf(C3))))
    }

    @Test
    fun isValid_piecesInIsolation() {
        val position = Position(
            mapOf(
                B3 to WHITE_KING,
                C2 to WHITE_ROOK,
                F5 to BLACK_KING
            )
        )

        // white rook
        assertTrue(position.isValid(Move(C2, C5, listOf(C3, C4))))
        assertTrue(position.isValid(Move(C2, E2, listOf(D2))))
        assertFalse(position.isValid(Move(C2, D1, emptyList())))

        // black king
        assertTrue(position.isValid(Move(F5, E4, emptyList())))
        assertTrue(position.isValid(Move(F5, G6, emptyList())))
        assertFalse(position.isValid(Move(F5, F7, listOf(F6))))
    }
}
