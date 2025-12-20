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

    private fun move(fromColumn: Column, fromRow: Row, toColumn: Column, toRow: Row) =
        Move(Position(fromColumn, fromRow), Position(toColumn, toRow))
}