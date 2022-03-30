package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BoardFactory;
import chess.domain.game.state.afterrunning.FinishedKing;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedKingTest {

    @DisplayName("endGame을 호출하면 예외가 발생한다.")
    @Test
    void endGame_throwsException() {
        // given
        State state = new FinishedKing(BoardFactory.createInitializedBoard());

        // when & then
        assertThatThrownBy(state::endGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @DisplayName("move를 호출하면 예외가 발생한다.")
    @Test
    void move_throwsException() {
        // given
        State state = new FinishedKing(BoardFactory.createInitializedBoard());

        // when & then
        assertThatThrownBy(() -> state.move(Position.from(XAxis.B, YAxis.THREE), Position.from(XAxis.A, YAxis.FIVE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 중이 아니어서 말을 움직일 수 없습니다.");
    }
}
