package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface State {
    State startGame();

    State endGame();

    State move(Position from, Position to);

    Board getBoard();

    boolean isEnd();

    double calculateBlackScore();

    double calculateWhiteScore();
}
