package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Column.*
import cz.dusanrychnovsky.chessengine.PieceType.*
import cz.dusanrychnovsky.chessengine.Row.*
import kotlin.test.Test
import kotlin.test.assertEquals

class PieceTypeTest {

    @Test
    fun rook_movesHorizontallyAndVertically() {
        val moves = ROOK.moves(Position(CC, R4));
        assertEquals(
            setOf(
                // vertically
                move(CC, R4, CC, R1),
                move(CC, R4, CC, R2),
                move(CC, R4, CC, R3),
                move(CC, R4, CC, R5),
                move(CC, R4, CC, R6),
                move(CC, R4, CC, R7),
                move(CC, R4, CC, R8),
                // horizontally
                move(CC, R4, CA, R4),
                move(CC, R4, CB, R4),
                move(CC, R4, CD, R4),
                move(CC, R4, CE, R4),
                move(CC, R4, CF, R4),
                move(CC, R4, CG, R4),
                move(CC, R4, CH, R4)
            ),
            moves)
    }

    @Test
    fun bishop_fromCorner_movesDiagonally() {
        val moves = BISHOP.moves(Position(CA, R1));
        assertEquals(
            setOf(
                // up-right
                move(CA, R1, CB, R2),
                move(CA, R1, CC, R3),
                move(CA, R1, CD, R4),
                move(CA, R1, CE, R5),
                move(CA, R1, CF, R6),
                move(CA, R1, CG, R7),
                move(CA, R1, CH, R8)
            ),
            moves)
    }

    @Test
    fun bishop_fromCenter_movesDiagonally() {
        val moves = BISHOP.moves(Position(CD, R4));
        assertEquals(
            setOf(
                // up-right
                move(CD, R4, CE, R5),
                move(CD, R4, CF, R6),
                move(CD, R4, CG, R7),
                move(CD, R4, CH, R8),
                // up-left
                move(CD, R4, CC, R5),
                move(CD, R4, CB, R6),
                move(CD, R4, CA, R7),
                // down-right
                move(CD, R4, CE, R3),
                move(CD, R4, CF, R2),
                move(CD, R4, CG, R1),
                // down-left
                move(CD, R4, CC, R3),
                move(CD, R4, CB, R2),
                move(CD, R4, CA, R1)
            ),
            moves)
    }

    @Test
    fun queen_movesHorizontallyVerticallyAnCDiagonally() {
        val moves = QUEEN.moves(Position(CD, R4));
        assertEquals(
            setOf(
                // vertically
                move(CD, R4, CD, R1),
                move(CD, R4, CD, R2),
                move(CD, R4, CD, R3),
                move(CD, R4, CD, R5),
                move(CD, R4, CD, R6),
                move(CD, R4, CD, R7),
                move(CD, R4, CD, R8),
                // horizontally
                move(CD, R4, CA, R4),
                move(CD, R4, CB, R4),
                move(CD, R4, CC, R4),
                move(CD, R4, CE, R4),
                move(CD, R4, CF, R4),
                move(CD, R4, CG, R4),
                move(CD, R4, CH, R4),
                // up-right
                move(CD, R4, CE, R5),
                move(CD, R4, CF, R6),
                move(CD, R4, CG, R7),
                move(CD, R4, CH, R8),
                // up-left
                move(CD, R4, CC, R5),
                move(CD, R4, CB, R6),
                move(CD, R4, CA, R7),
                // down-right
                move(CD, R4, CE, R3),
                move(CD, R4, CF, R2),
                move(CD, R4, CG, R1),
                // down-left
                move(CD, R4, CC, R3),
                move(CD, R4, CB, R2),
                move(CD, R4,CA, R1)
            ),
            moves)
    }

    @Test
    fun king_fromCenter_movesOneSquareInAllDirections() {
        val moves = KING.moves(Position(CD, R4))
        assertEquals(
            setOf(
                move(CD, R4, CC, R4), // left
                move(CD, R4, CE, R4), // right
                move(CD, R4, CD, R3), // down
                move(CD, R4, CD, R5), // up
                move(CD, R4, CC, R3), // down-left
                move(CD, R4, CE, R3), // down-right
                move(CD, R4, CC, R5), // up-left
                move(CD, R4, CE, R5)  // up-right
            ),
            moves
        )
    }

    @Test
    fun king_fromCorner_movesOnlyToValidSquares() {
        val moves = KING.moves(Position(CA, R1))
        assertEquals(
            setOf(
                move(CA, R1, CB, R1), // right
                move(CA, R1, CA, R2), // up
                move(CA, R1, CB, R2)  // up-right
            ),
            moves
        )
    }

    @Test
    fun king_fromSide_movesOnlyToValidSquares() {
        val moves = KING.moves(Position(CA, R4))
        assertEquals(
            setOf(
                move(CA, R4, CA, R3), // down
                move(CA, R4, CA, R5), // up
                move(CA, R4, CB, R3), // down-right
                move(CA, R4, CB, R4), // right
                move(CA, R4, CB, R5)  // up-right
            ),
            moves
        )
    }

    @Test
    fun knight_fromCenter_movesInLShape() {
        val moves = KNIGHT.moves(Position(CD, R4))
        assertEquals(
            setOf(
                move(CD, R4, CE, R6), // up 2, right 1
                move(CD, R4, CC, R6), // up 2, left 1
                move(CD, R4, CE, R2), // down 2, right 1
                move(CD, R4, CC, R2), // down 2, left 1
                move(CD, R4, CF, R5), // right 2, up 1
                move(CD, R4, CF, R3), // right 2, down 1
                move(CD, R4, CB, R5), // left 2, up 1
                move(CD, R4, CB, R3)  // left 2, down 1
            ),
            moves
        )
    }

    @Test
    fun knight_fromCorner_movesOnlyToValidSquares() {
        val moves = KNIGHT.moves(Position(CA, R1))
        assertEquals(
            setOf(
                move(CA, R1, CB, R3), // up 2, right 1
                move(CA, R1, CC, R2)  // right 2, up 1
            ),
            moves
        )
    }

    @Test
    fun knight_fromSide_movesOnlyToValidSquares() {
        val moves = KNIGHT.moves(Position(CA, R4))
        assertEquals(
            setOf(
                move(CA, R4, CB, R6), // up 2, right 1
                move(CA, R4, CB, R2), // down 2, right 1
                move(CA, R4, CC, R5), // right 2, up 1
                move(CA, R4, CC, R3)  // right 2, down 1
            ),
            moves
        )
    }

    private fun move(fromColumn: Column, fromRow: Row, toColumn: Column, toRow: Row) =
        Move(Position(fromColumn, fromRow), Position(toColumn, toRow))
}