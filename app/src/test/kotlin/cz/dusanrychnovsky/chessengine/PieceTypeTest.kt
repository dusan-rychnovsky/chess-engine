package cz.dusanrychnovsky.chessengine

import cz.dusanrychnovsky.chessengine.Column.*
import cz.dusanrychnovsky.chessengine.PieceType.*
import cz.dusanrychnovsky.chessengine.Row.*
import kotlin.test.Test
import kotlin.test.assertEquals

class PieceTypeTest {

    @Test fun rook_movesHorizontallyAndVertically() {
        val moves = ROOK.moves(Position(CC, R4));
        assertEquals(
            setOf(
                move(CC, R4, CC, R1),
                move(CC, R4, CC, R2),
                move(CC, R4, CC, R3),
                move(CC, R4, CC, R5),
                move(CC, R4, CC, R6),
                move(CC, R4, CC, R7),
                move(CC, R4, CC, R8),
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

    private fun move(fromColumn: Column, fromRow: Row, toColumn: Column, toRow: Row) =
        Move(Position(fromColumn, fromRow), Position(toColumn, toRow))
}