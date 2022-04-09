package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.state.beforerunning.Ready;
import chess.domain.game.state.State;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void updateState(State state) {
        this.state = state;
    }

    public void startGame() {
        this.state = state.startGame();
    }

    public void movePiece(Position from, Position to) {
        this.state = state.move(from, to);
    }

    public void endGame() {
        this.state = state.endGame();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public List<Double> getScore() {
        return List.of(state.calculateWhiteScore(), state.calculateBlackScore());
    }
}
