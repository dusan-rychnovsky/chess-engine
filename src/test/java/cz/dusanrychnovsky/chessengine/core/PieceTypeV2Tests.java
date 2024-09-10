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
        assertTrue(moves.contains(new MoveV2(D3, D5, Set.of(D4))));
        assertTrue(moves.contains(new MoveV2(D3, A3, Set.of(C3, B3))));
    }
}
