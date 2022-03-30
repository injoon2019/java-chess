package chess.domain.game.state.running;

import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.domain.game.state.afterrunning.FinishedKing;
import chess.domain.game.state.State;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

public class RunningBlack extends Running {

    public RunningBlack(Board board) {
        super(board);
    }

    @Override
    public State move(Position from, Position to) {
        MoveResult moveResult = board.executeCommand(from, to, PieceColor.BLACK);

        if (moveResult == MoveResult.KILL_KING) {
            return new FinishedKing(board);
        }

        if (moveResult.isMoveSuccess()) {
            return new RunningWhite(board);
        }

        return this;
    }
}
