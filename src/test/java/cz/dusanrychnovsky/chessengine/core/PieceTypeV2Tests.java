package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceTypeV2.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PieceTypeV2Tests {

    // TODO: temporary placeholder until more advanced features are implemented (see PieceType)
    private final Situation situation = new Situation(WHITE, new HashMap<>());

    @Test
    public void rook_getMovesTemplate_canMoveHorizontallyAndVertically() {
        var moves = ROOK.getMovesTemplate(situation, D3);
        assertEquals(14, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, E3, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, D5, Set.of(D4))));
        assertTrue(moves.contains(new MoveV2(D3, A3, Set.of(C3, B3))));
    }

    @Test
    public void bishop_getMovesTemplate_canMoveDiagonally() {
        var moves = BISHOP.getMovesTemplate(situation, D3);
        assertEquals(11, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, E4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, F1, Set.of(E2))));
        assertTrue(moves.contains(new MoveV2(D3, A6, Set.of(C4, B5))));
    }

    @Test
    public void bishop_getMovesTemplate_fromCorner_canMoveAlongSingleDiagonal() {
        var moves = BISHOP.getMovesTemplate(situation, A1);
        assertEquals(7, moves.size());
        assertTrue(moves.contains(new MoveV2(A1, B2, Set.of())));
        assertTrue(moves.contains(new MoveV2(A1, D4, Set.of(B2, C3))));
    }

    @Test
    public void queen_getMovesTemplate_canMoveHorizontallyVerticallyAndDiagonally() {
        var moves = QUEEN.getMovesTemplate(situation, D3);
        assertEquals(25, moves.size());
        assertTrue(moves.contains(new MoveV2(D3, E3, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, D5, Set.of(D4))));
        assertTrue(moves.contains(new MoveV2(D3, A3, Set.of(C3, B3))));
        assertTrue(moves.contains(new MoveV2(D3, E4, Set.of())));
        assertTrue(moves.contains(new MoveV2(D3, F1, Set.of(E2))));
        assertTrue(moves.contains(new MoveV2(D3, A6, Set.of(C4, B5))));
    }

    @Test
    public void king_getMovesTemplate_canMoveToAdjacentFields() {
        var moves = KING.getMovesTemplate(situation, D3);
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
    public void king_getMovesTemplate_fromCorner_canOnlyMoveToThreeFields() {
        var moves = KING.getMovesTemplate(situation, A1);
        assertEquals(3, moves.size());
        assertTrue(moves.contains(new MoveV2(A1, A2, Set.of())));
        assertTrue(moves.contains(new MoveV2(A1, B2, Set.of())));
        assertTrue(moves.contains(new MoveV2(A1, B1, Set.of())));
    }
}
