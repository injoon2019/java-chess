package domain.piece;

import domain.position.Position;
import domain.position.Row;

import java.util.List;

public class Pawn extends Division {
    public Pawn(Color color, Position position) {
        super(color, "p", position);
    }

    @Override
    public void move(Position to, Pieces pieces) {
        if (canMoveDouble(to)) {
            validateNoneBetween(to, pieces);
            position = to;
            return;
        }
        if (canMoveSingle(to)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    private boolean canMoveDouble(Position to) {
        return (getPawnMovementSize(to) == 2) && position.hasRow(initRow());
    }

    private int getPawnMovementSize(Position to) {
        if (position.diffColumn(to) != 0) {
            throw new IllegalArgumentException();
        }
        return position.diffRow(to) / color.moveUnit();
    }

    private Row initRow() {
        if (isBlack()) {
            return Row.SEVEN;
        }
        return Row.TWO;
    }

    private void validateNoneBetween(Position to, Pieces pieces) {
        List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean canMoveSingle(Position to) {
        return getPawnMovementSize(to) == 1;
    }

    @Override
    public void kill(Position to, Pieces pieces) {
        if (canKill(to)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    private boolean canKill(Position to) {
        return Math.abs(position.diffColumn(to)) == 1 && position.diffRow(to) == color.moveUnit();
    }
}
