package chess.domain.game.state.afterrunning;

import chess.domain.board.Board;
import chess.domain.game.state.State;
import chess.domain.position.Position;

public class FinishedKing extends AfterRunning {

    public FinishedKing(Board board) {
        super(board);
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 중이 아니어서 말을 움직일 수 없습니다.");
    }


    @Override
    public State endGame() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }
}
