package cz.dusanrychnovsky.chessengine;

import cz.dusanrychnovsky.chessengine.Color.*
import cz.dusanrychnovsky.chessengine.Column.*
import cz.dusanrychnovsky.chessengine.PieceType.*
import cz.dusanrychnovsky.chessengine.Row.*
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
                Square(CB, R3) to WHITE_KING,
                Square(CF, R5) to BLACK_KING
            )
        )

        assertFalse(position.isValid(Move(Square(CC, R2), Square(CC, R4))))
    }

    @Test
    fun isValid_piecesInIsolation() {
        val position = Position(
            mapOf(
                Square(CB, R3) to WHITE_KING,
                Square(CC, R2) to WHITE_ROOK,
                Square(CF, R5) to BLACK_KING
            )
        )

        // white rook
        assertTrue(position.isValid(Move(Square(CC, R2), Square(CC, R5))))
        assertTrue(position.isValid(Move(Square(CC, R2), Square(CE, R2))))
        assertFalse(position.isValid(Move(Square(CC, R2), Square(CD, R1))))

        // black king
        assertTrue(position.isValid(Move(Square(CF, R5), Square(CE, R4))))
        assertTrue(position.isValid(Move(Square(CF, R5), Square(CG, R6))))
        assertFalse(position.isValid(Move(Square(CF, R5), Square(CF, R7))))
    }
}
