package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceTypeV2.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PieceTypeV2Tests {

    // TODO: temporary placeholder until more advanced features are implemented (see PieceType)
    private final SituationV2 situation = new SituationV2(WHITE, new HashMap<>());

    @Test
    public void rook_getMovePatterns_canMoveHorizontallyAndVertically() {
        var moves = ROOK.getMovePatterns(situation, D3);
        assertEquals(14, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, E3, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, D5, Set.of(D4))));
        assertTrue(moves.contains(new MoveV2(D3, A3, Set.of(C3, B3))));
    }

    @Test
    public void bishop_getMovePatterns_canMoveDiagonally() {
        var moves = BISHOP.getMovePatterns(situation, D3);
        assertEquals(11, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, E4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, F1, Set.of(E2))));
        assertTrue(moves.contains(new MoveV2(D3, A6, Set.of(C4, B5))));
    }

    @Test
    public void bishop_getMovePatterns_fromCorner_canMoveAlongSingleDiagonal() {
        var moves = BISHOP.getMovePatterns(situation, A1);
        assertEquals(7, moves.size());
        assertTrue(moves.contains(new MoveV2(A1, B2, Set.of())));
        assertTrue(moves.contains(new MoveV2(A1, D4, Set.of(B2, C3))));
    }

    @Test
    public void queen_getMovePatterns_canMoveHorizontallyVerticallyAndDiagonally() {
        var moves = QUEEN.getMovePatterns(situation, D3);
        assertEquals(25, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, E3, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, D5, Set.of(D4))));
        assertTrue(moves.contains(new MoveV2(D3, A3, Set.of(C3, B3))));
        assertTrue(moves.contains(new MoveV2(D3, E4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, F1, Set.of(E2))));
        assertTrue(moves.contains(new MoveV2(D3, A6, Set.of(C4, B5))));
    }

    @Test
    public void king_getMovePatterns_canMoveToAdjacentFields() {
        var moves = KING.getMovePatterns(situation, D3);
        assertEquals(8, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, C4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, D4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, E4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, C3, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, E3, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, C2, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, D2, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, E2, Set.of())));
    }

    @Test
    public void king_getMovePatterns_fromCorner_canOnlyMoveToThreeFields() {
        var moves = KING.getMovePatterns(situation, A1);
        assertEquals(3, moves.size());
        assertTrue(moves.contains(new MoveV2(A1, A2, Set.of())));
        assertTrue(moves.contains(new MoveV2(A1, B2, Set.of())));
        assertTrue(moves.contains(new MoveV2(A1, B1, Set.of())));
    }

    @Test
    public void knight_getMovePatterns_fromCenter_canMoveToEightFields() {
        var moves = KNIGHT.getMovePatterns(situation, D3);
        assertEquals(8, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, F2, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, E1, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, C1, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, B2, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, B4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, C5, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, E5, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, F4, Set.of())));
    }

    @Test
    public void knight_getMovePatterns_fromCorner_canMoveToTwoFields() {
        var moves = KNIGHT.getMovePatterns(situation, A1);
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new MoveV2(A1, C2, Set.of())));
        assertTrue(moves.contains(new MoveV2(A1, B3, Set.of())));
    }

    @Test
    public void knight_getMovePatterns_fromEdge_canMoveToFourFields() {
        var moves = KNIGHT.getMovePatterns(situation, H5);
        assertEquals(4, moves.size());
        assertTrue(moves.contains(new MoveV2(H5, G3, Set.of())));
        assertTrue(moves.contains(new MoveV2(H5, F4, Set.of())));
        assertTrue(moves.contains(new MoveV2(H5, F6, Set.of())));
        assertTrue(moves.contains(new MoveV2(H5, G7, Set.of())));
    }

    @Test
    public void knight_getMovePatterns_oneFieldAwayFromEdge_canMoveToSixFields() {
        var moves = KNIGHT.getMovePatterns(situation, F7);
        assertEquals(6, moves.size());
        assertTrue(moves.contains(new MoveV2(F7, H8, Set.of())));
        assertTrue(moves.contains(new MoveV2(F7, H6, Set.of())));
        assertTrue(moves.contains(new MoveV2(F7, G5, Set.of())));
        assertTrue(moves.contains(new MoveV2(F7, E5, Set.of())));
        assertTrue(moves.contains(new MoveV2(F7, D6, Set.of())));
        assertTrue(moves.contains(new MoveV2(F7, D8, Set.of())));
    }

    @Test
    public void pawn_getMovesTemplate_white_movesUp() {
        var moves = PAWN.getMovePatterns(
            new SituationV2(
                WHITE,
                Map.of(B5, new PieceV2(WHITE, PAWN))
            ),
            B5);
        assertEquals(1, moves.size());
        assertTrue(moves.contains(new MoveV2(B5, B6, Set.of())));
    }

    @Test
    public void pawn_getMovesTemplate_black_movesDown() {
        var moves = PAWN.getMovePatterns(
            new SituationV2(
                BLACK,
                Map.of(B5, new PieceV2(BLACK, PAWN))
            ),
            B5);
        assertEquals(1, moves.size());
        assertTrue(moves.contains(new MoveV2(B5, B4, Set.of())));
    }

    @Test
    public void pawn_getMovePatterns_white_fromInitialPosition_canMoveTwoFieldsUp() {
        var moves = PAWN.getMovePatterns(
            new SituationV2(
                WHITE,
                Map.of(B2, new PieceV2(WHITE, PAWN))
            ),
            B2);
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new MoveV2(B2, B3, Set.of())));
        assertTrue(moves.contains(new MoveV2(B2, B4, Set.of())));
    }

    @Test
    public void pawn_getMovePatterns_black_fromInitialPosition_canMoveTwoFieldsDown() {
        var moves = PAWN.getMovePatterns(
            new SituationV2(
                BLACK,
                Map.of(B7, new PieceV2(BLACK, PAWN))
            ),
            B7);
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new MoveV2(B7, B6, Set.of())));
        assertTrue(moves.contains(new MoveV2(B7, B5, Set.of())));
    }

    @Test
    public void pawn_getMovePatterns_canCapturePiecesWhichAreDirectlyAndDiagonallyInFrontOfThem() {
        var moves = PAWN.getMovePatterns(
            new SituationV2(
                WHITE,
                Map.of(
                    B5, new PieceV2(WHITE, PAWN),
                    C6, new PieceV2(BLACK, PAWN),
                    C4, new PieceV2(BLACK, PAWN))
            ),
            B5);
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new MoveV2(B5, B6, Set.of())));
        assertTrue(moves.contains(new MoveV2(B5, C6, Set.of())));
    }
}
