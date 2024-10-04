package cz.dusanrychnovsky.chessengine.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static cz.dusanrychnovsky.chessengine.core.Color.*;
import static cz.dusanrychnovsky.chessengine.core.PieceType.*;
import static cz.dusanrychnovsky.chessengine.core.Position.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PieceTypeTests {

    // TODO: temporary placeholder until more advanced features are implemented (see PieceType)
    private final Situation situation = new Situation(WHITE, new HashMap<>());

    @Test
    public void rook_getMovePatterns_canMoveHorizontallyAndVertically() {
        var moves = ROOK.getMovePatterns(situation, D3);
        assertEquals(14, moves.size());
        assertTrue(moves.contains(new Move(D3, E3, Set.of())));
        assertTrue(moves.contains(new Move(D3, D5, Set.of(D4))));
        assertTrue(moves.contains(new Move(D3, A3, Set.of(C3, B3))));
    }

    @Test
    public void bishop_getMovePatterns_canMoveDiagonally() {
        var moves = BISHOP.getMovePatterns(situation, D3);
        assertEquals(11, moves.size());
        assertTrue(moves.contains(new Move(D3, E4, Set.of())));
        assertTrue(moves.contains(new Move(D3, F1, Set.of(E2))));
        assertTrue(moves.contains(new Move(D3, A6, Set.of(C4, B5))));
    }

    @Test
    public void bishop_getMovePatterns_fromCorner_canMoveAlongSingleDiagonal() {
        var moves = BISHOP.getMovePatterns(situation, A1);
        assertEquals(7, moves.size());
        assertTrue(moves.contains(new Move(A1, B2, Set.of())));
        assertTrue(moves.contains(new Move(A1, D4, Set.of(B2, C3))));
    }

    @Test
    public void queen_getMovePatterns_canMoveHorizontallyVerticallyAndDiagonally() {
        var moves = QUEEN.getMovePatterns(situation, D3);
        assertEquals(25, moves.size());
        assertTrue(moves.contains(new Move(D3, E3, Set.of())));
        assertTrue(moves.contains(new Move(D3, D5, Set.of(D4))));
        assertTrue(moves.contains(new Move(D3, A3, Set.of(C3, B3))));
        assertTrue(moves.contains(new Move(D3, E4, Set.of())));
        assertTrue(moves.contains(new Move(D3, F1, Set.of(E2))));
        assertTrue(moves.contains(new Move(D3, A6, Set.of(C4, B5))));
    }

    @Test
    public void king_getMovePatterns_canMoveToAdjacentFields() {
        assertEquals(
            Set.of(
                new Move(D3, C4, Set.of()),
                new Move(D3, D4, Set.of()),
                new Move(D3, E4, Set.of()),
                new Move(D3, C3, Set.of()),
                new Move(D3, E3, Set.of()),
                new Move(D3, C2, Set.of()),
                new Move(D3, D2, Set.of()),
                new Move(D3, E2, Set.of())
            ),
            KING.getMovePatterns(situation, D3)
        );
    }

    @Test
    public void king_getMovePatterns_fromCorner_canOnlyMoveToThreeFields() {
        assertEquals(
            Set.of(
                new Move(A1, A2, Set.of()),
                new Move(A1, B2, Set.of()),
                new Move(A1, B1, Set.of())
            ),
            KING.getMovePatterns(situation, A1)
        );
    }

    @Test
    public void knight_getMovePatterns_fromCenter_canMoveToEightFields() {
        assertEquals(
            Set.of(
                new Move(D3, F2, Set.of()),
                new Move(D3, E1, Set.of()),
                new Move(D3, C1, Set.of()),
                new Move(D3, B2, Set.of()),
                new Move(D3, B4, Set.of()),
                new Move(D3, C5, Set.of()),
                new Move(D3, E5, Set.of()),
                new Move(D3, F4, Set.of())
            ),
            KNIGHT.getMovePatterns(situation, D3)
        );
    }

    @Test
    public void knight_getMovePatterns_fromCorner_canMoveToTwoFields() {
        assertEquals(
            Set.of(
                new Move(A1, C2, Set.of()),
                new Move(A1, B3, Set.of())
            ),
            KNIGHT.getMovePatterns(situation, A1)
        );
    }

    @Test
    public void knight_getMovePatterns_fromEdge_canMoveToFourFields() {
        assertEquals(
            Set.of(
                new Move(H5, G3, Set.of()),
                new Move(H5, F4, Set.of()),
                new Move(H5, F6, Set.of()),
                new Move(H5, G7, Set.of())
            ),
            KNIGHT.getMovePatterns(situation, H5)
        );
    }

    @Test
    public void knight_getMovePatterns_oneFieldAwayFromEdge_canMoveToSixFields() {
        assertEquals(
            Set.of(
                new Move(F7, H8, Set.of()),
                new Move(F7, H6, Set.of()),
                new Move(F7, G5, Set.of()),
                new Move(F7, E5, Set.of()),
                new Move(F7, D6, Set.of()),
                new Move(F7, D8, Set.of())
            ),
            KNIGHT.getMovePatterns(situation, F7)
        );
    }

    @Test
    public void pawn_getMovesTemplate_white_movesUp() {
        assertEquals(
            Set.of(
                new Move(B5, B6, Set.of())
            ),
            PAWN.getMovePatterns(
                new Situation(
                    WHITE,
                    Map.of(B5, new Piece(WHITE, PAWN))
                ),
                B5)
        );
    }

    @Test
    public void pawn_getMovesTemplate_black_movesDown() {
        assertEquals(
            Set.of(
                new Move(B5, B4, Set.of())
            ),
            PAWN.getMovePatterns(
                new Situation(
                    BLACK,
                    Map.of(B5, new Piece(BLACK, PAWN))
                ),
                B5)
        );
    }

    @Test
    public void pawn_getMovePatterns_white_fromInitialPosition_canMoveTwoFieldsUp() {
        assertEquals(
            Set.of(
                new Move(B2, B3, Set.of()),
                new Move(B2, B4, Set.of())

            ),
            PAWN.getMovePatterns(
                new Situation(
                    WHITE,
                    Map.of(B2, new Piece(WHITE, PAWN))
                ),
                B2)
        );
    }

    @Test
    public void pawn_getMovePatterns_black_fromInitialPosition_canMoveTwoFieldsDown() {
        assertEquals(
            Set.of(
                new Move(B7, B6, Set.of()),
                new Move(B7, B5, Set.of())
            ),
            PAWN.getMovePatterns(
                new Situation(
                    BLACK,
                    Map.of(B7, new Piece(BLACK, PAWN))
                ),
                B7)
        );
    }

    @Test
    public void pawn_getMovePatterns_canCapturePiecesWhichAreDirectlyAndDiagonallyInFrontOfThem() {
        assertEquals(
            Set.of(
                new Move(B5, B6, Set.of()),
                new Move(B5, C6, Set.of())
            ),
            PAWN.getMovePatterns(
                new Situation(
                    WHITE,
                    Map.of(
                        B5, new Piece(WHITE, PAWN),
                        C6, new Piece(BLACK, PAWN),
                        C4, new Piece(BLACK, PAWN))
                ),
                B5)
        );
    }
}
