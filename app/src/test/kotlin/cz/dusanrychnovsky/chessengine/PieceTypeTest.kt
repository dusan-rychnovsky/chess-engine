package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Color.*
import cz.dusanrychnovsky.chessengine.PieceType.*
import cz.dusanrychnovsky.chessengine.Square.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PieceTypeTest {
    val EMPTY_POSITION = Position(WHITE, emptyMap())
    val WHITE_PAWN = Piece(WHITE, PAWN)
    val BLACK_PAWN = Piece(BLACK, PAWN)

    @Test
    fun rook_movesHorizontallyAndVertically() {
        val moves = ROOK.moves(C4, EMPTY_POSITION)
        assertEquals(
            setOf(
                // vertically
                move(from= C4, C3, C2, to=C1),
                move(from=C4, C3, to=C2),
                move(from=C4, to=C3),
                move(from=C4, to=C5),
                move(from=C4, C5, to=C6),
                move(from=C4, C5, C6, to=C7),
                move(from=C4, C5, C6, C7, to=C8),
                // horizontally
                move(from=C4, B4, to=A4),
                move(from=C4, to=B4),
                move(from=C4, to=D4),
                move(from=C4, D4, to=E4),
                move(from=C4, D4, E4, to=F4),
                move(from=C4, D4, E4, F4, to=G4),
                move(from=C4, D4, E4, F4, G4, to=H4)
            ),
            moves)
    }

    @Test
    fun bishop_fromCorner_movesDiagonally() {
        val moves = BISHOP.moves(A1, EMPTY_POSITION)
        assertEquals(
            setOf(
                // up-right
                move(from=A1, to=B2),
                move(from=A1, B2, to=C3),
                move(from=A1, B2, C3, to=D4),
                move(from=A1, B2, C3, D4, to=E5),
                move(from=A1, B2, C3, D4, E5, to=F6),
                move(from=A1, B2, C3, D4, E5, F6, to=G7),
                move(from=A1, B2, C3, D4, E5, F6, G7, to=H8)
            ),
            moves)
    }

    @Test
    fun bishop_fromCenter_movesDiagonally() {
        val moves = BISHOP.moves(D4, EMPTY_POSITION)
        assertEquals(
            setOf(
                // up-right
                move(from=D4, to=E5),
                move(from=D4, E5, to=F6),
                move(from=D4, E5, F6, to=G7),
                move(from=D4, E5, F6, G7, to=H8),
                // up-left
                move(from=D4, to=C5),
                move(from=D4, C5, to=B6),
                move(from=D4, C5, B6, to=A7),
                // down-right
                move(from=D4, to=E3),
                move(from=D4, E3, to=F2),
                move(from=D4, E3, F2, to=G1),
                // down-left
                move(from=D4, to=C3),
                move(from=D4, C3, to=B2),
                move(from=D4, C3, B2, to=A1)
            ),
            moves)
    }

    @Test
    fun queen_movesHorizontallyVerticallyAnCDiagonally() {
        val moves = QUEEN.moves(D4, EMPTY_POSITION)
        assertEquals(
            setOf(
                // vertically
                move(from=D4, D3, D2, to=D1),
                move(from=D4, D3, to=D2),
                move(from=D4, to=D3),
                move(from=D4, to=D5),
                move(from=D4, D5, to=D6),
                move(from=D4, D5, D6, to=D7),
                move(from=D4, D5, D6, D7, to=D8),
                // horizontally
                move(from=D4, C4, B4, to=A4),
                move(from=D4, C4, to=B4),
                move(from=D4, to=C4),
                move(from=D4, to=E4),
                move(from=D4, E4, to=F4),
                move(from=D4, E4, F4, to=G4),
                move(from=D4, E4, F4, G4, to=H4),
                // up-right
                move(from=D4, to=E5),
                move(from=D4, E5, to=F6),
                move(from=D4, E5, F6, to=G7),
                move(from=D4, E5, F6, G7, to=H8),
                // up-left
                move(from=D4, to=C5),
                move(from=D4, C5, to=B6),
                move(from=D4, C5, B6, to=A7),
                // down-right
                move(from=D4, to=E3),
                move(from=D4, E3, to=F2),
                move(from=D4, E3, F2, to=G1),
                // down-left
                move(from=D4, to=C3),
                move(from=D4, C3, to=B2),
                move(from=D4, C3, B2, to=A1)
            ),
            moves)
    }

    @Test
    fun king_fromCenter_movesOneSquareInAllDirections() {
        val moves = KING.moves(D4, EMPTY_POSITION)
        assertEquals(
            setOf(
                move(from=D4, to=C4), // left
                move(from=D4, to=E4), // right
                move(from=D4, to=D3), // down
                move(from=D4, to=D5), // up
                move(from=D4, to=C3), // down-left
                move(from=D4, to=E3), // down-right
                move(from=D4, to=C5), // up-left
                move(from=D4, to=E5)  // up-right
            ),
            moves
        )
    }

    @Test
    fun king_fromCorner_movesOnlyToValidSquares() {
        val moves = KING.moves(A1, EMPTY_POSITION)
        assertEquals(
            setOf(
                move(from=A1, to=B1), // right
                move(from=A1, to=A2), // up
                move(from=A1, to=B2)  // up-right
            ),
            moves
        )
    }

    @Test
    fun king_fromSide_movesOnlyToValidSquares() {
        val moves = KING.moves(A4, EMPTY_POSITION)
        assertEquals(
            setOf(
                move(from=A4, to=A3), // down
                move(from=A4, to=A5), // up
                move(from=A4, to=B3), // down-right
                move(from=A4, to=B4), // right
                move(from=A4, to=B5)  // up-right
            ),
            moves
        )
    }

    @Test
    fun knight_fromCenter_movesInLShape() {
        val moves = KNIGHT.moves(D4, EMPTY_POSITION)
        assertEquals(
            setOf(
                move(from=D4, to=E6), // up 2, right 1
                move(from=D4, to=C6), // up 2, left 1
                move(from=D4, to=E2), // down 2, right 1
                move(from=D4, to=C2), // down 2, left 1
                move(from=D4, to=F5), // right 2, up 1
                move(from=D4, to=F3), // right 2, down 1
                move(from=D4, to=B5), // left 2, up 1
                move(from=D4, to=B3)  // left 2, down 1
            ),
            moves
        )
    }

    @Test
    fun knight_fromCorner_movesOnlyToValidSquares() {
        val moves = KNIGHT.moves(A1, EMPTY_POSITION)
        assertEquals(
            setOf(
                move(from=A1, to=B3), // up 2, right 1
                move(from=A1, to=C2)  // right 2, up 1
            ),
            moves
        )
    }

    @Test
    fun knight_fromSide_movesOnlyToValidSquares() {
        val moves = KNIGHT.moves(A4, EMPTY_POSITION)
        assertEquals(
            setOf(
                move(from=A4, to=B6), // up 2, right 1
                move(from=A4, to=B2), // down 2, right 1
                move(from=A4, to=C5), // right 2, up 1
                move(from=A4, to=C3)  // right 2, down 1
            ),
            moves
        )
    }

    @Test
    fun pawn_white_movesUp() {
        val position = Position(WHITE, mapOf(D4 to WHITE_PAWN))
        val moves = PAWN.moves(D4, position)
        assertEquals(
            setOf(
                move(from=D4, to=D5)
            ),
            moves
        )
    }

    @Test
    fun pawn_white_cannotMoveUpWhenBlocked() {
        val position = Position(WHITE, mapOf(D4 to WHITE_PAWN, D5 to BLACK_PAWN))
        val moves = PAWN.moves(D4, position)
        assertTrue(moves.isEmpty())
    }

    @Test
    fun pawn_white_fromStartingPosition_movesOneOrTwoSquaresUp() {
        val position = Position(WHITE, mapOf(E2 to WHITE_PAWN))
        val moves = PAWN.moves(E2, position)
        assertEquals(
            setOf(
                move(from=E2, to=E3),
                move(from=E2, E3, to=E4)
            ),
            moves
        )
    }

    @Test
    fun pawn_white_fromStartingPosition_cannotMoveUpWhenFirstStepIsBlocked() {
        val position = Position(WHITE, mapOf(E2 to WHITE_PAWN, E3 to BLACK_PAWN))
        val moves = PAWN.moves(E2, position)
        assertTrue(moves.isEmpty())
    }

    @Test
    fun pawn_white_fromStartingPosition_cannotMoveTwoSquaresUpWhenSecondStepIsBlocked() {
        val position = Position(WHITE, mapOf(E2 to WHITE_PAWN, E4 to BLACK_PAWN))
        val moves = PAWN.moves(E2, position)
        assertEquals(
            setOf(
                move(from=E2, to=E3)
            ),
            moves
        )
    }

    @Test
    fun pawn_white_capturesDiagonally() {
        val position = Position(WHITE, mapOf(D4 to WHITE_PAWN, E5 to BLACK_PAWN))
        val moves = PAWN.moves(D4, position)
        assertEquals(
            setOf(
                move(from=D4, to=D5),
                move(from=D4, to=E5)
            ),
            moves
        )
    }

    @Test
    fun pawn_white_fromStartingPosition_canCapture() {
        val position = Position(WHITE, mapOf(E2 to WHITE_PAWN, D3 to BLACK_PAWN, F3 to BLACK_PAWN))
        val moves = PAWN.moves(E2, position)
        assertEquals(
            setOf(
                move(from=E2, to=E3),
                move(from=E2, E3, to=E4),
                move(from=E2, to=D3),
                move(from=E2, to=F3)
            ),
            moves
        )
    }

    @Test
    fun pawn_black_movesDown() {
        val position = Position(BLACK, mapOf(D5 to BLACK_PAWN))
        val moves = PAWN.moves(D5, position)
        assertEquals(
            setOf(
                move(from=D5, to=D4)
            ),
            moves
        )
    }

    @Test
    fun pawn_black_cannotMoveDownWhenBlocked() {
        val position = Position(BLACK, mapOf(D5 to BLACK_PAWN, D4 to WHITE_PAWN))
        val moves = PAWN.moves(D5, position)
        assertTrue(moves.isEmpty())
    }

    @Test
    fun pawn_black_fromStartingPosition_movesOneOrTwoSquaresDown() {
        val position = Position(BLACK, mapOf(E7 to BLACK_PAWN))
        val moves = PAWN.moves(E7, position)
        assertEquals(
            setOf(
                move(from=E7, to=E6),
                move(from=E7, E6, to=E5)
            ),
            moves
        )
    }

    @Test
    fun pawn_black_fromStartingPosition_cannotMoveDownWhenFirstStepIsBlocked() {
        val position = Position(BLACK, mapOf(E7 to BLACK_PAWN, E6 to WHITE_PAWN))
        val moves = PAWN.moves(E7, position)
        assertTrue(moves.isEmpty())
    }

    @Test
    fun pawn_black_fromStartingPosition_cannotMoveTwoSquaresDownWhenSecondStepIsBlocked() {
        val position = Position(BLACK, mapOf(E7 to BLACK_PAWN, E5 to WHITE_PAWN))
        val moves = PAWN.moves(E7, position)
        assertEquals(
            setOf(
                move(from=E7, to=E6)
            ),
            moves
        )
    }
    
    @Test
    fun pawn_black_capturesDiagonally() {
        val position = Position(BLACK, mapOf(D5 to BLACK_PAWN, E4 to WHITE_PAWN))
        val moves = PAWN.moves(D5, position)
        assertEquals(
            setOf(
                move(from=D5, to=D4),
                move(from=D5, to=E4)
            ),
            moves
        )
    }

    @Test
    fun pawn_black_fromStartingPosition_canCapture() {
        val position = Position(BLACK, mapOf(E7 to BLACK_PAWN, D6 to WHITE_PAWN, F6 to WHITE_PAWN))
        val moves = PAWN.moves(E7, position)
        assertEquals(
            setOf(
                move(from=E7, to=E6),
                move(from=E7, E6, to=E5),
                move(from=E7, to=D6),
                move(from=E7, to=F6)
            ),
            moves
        )
    }

    private fun move(from: Square, vararg through: Square, to: Square) =
        Move(from, to, through.toList())
}