package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.YAxis;
import chess.domain.position.direction.DiagonalDirection;
import chess.domain.position.direction.VerticalDirection;

public class Pawn extends AbstractPiece {

    private static final int DEFAULT_MOVE_RANGE = 1;
    private static final int INITIAL_MOVE_RANGE = 2;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor, PieceType.PAWN);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        if (isInitialPosition(from)) {
            return getMovableIfInitialPosition(from, to);
        }

        return getMovableIfNotInitialPosition(from, to);
    }

    @Override
    public boolean isAbleToAttack(Position from, Position to) {
        boolean isDiagonalOneDistance = DiagonalDirection.isOnDiagonal(from, to) && VerticalDirection.isInVerticalRange(from, to, 1);

        if (isPieceColor(PieceColor.BLACK)) {
            return isDiagonalOneDistance && from.isUpperThan(to);
        }

        return isDiagonalOneDistance && from.isLowerThan(to);
    }

    private boolean isInitialPosition(Position from) {
        return from.isSameYAxis(YAxis.TWO) || from.isSameYAxis(YAxis.SEVEN);
    }

    private boolean getMovableIfInitialPosition(Position from, Position to) {
        boolean inVerticalRange = from.isInVerticalRangeAndSameXAxis(to, INITIAL_MOVE_RANGE);

        if (isPieceColor(PieceColor.BLACK)) {
            return from.isUpperThan(to) && inVerticalRange;
        }

        return from.isLowerThan(to) && inVerticalRange;
    }

    private boolean getMovableIfNotInitialPosition(Position from, Position to) {
        boolean inVerticalRange = from.isInVerticalRangeAndSameXAxis(to, DEFAULT_MOVE_RANGE);

        if (isPieceColor(PieceColor.BLACK)) {
            return from.isUpperThan(to) && inVerticalRange;
        }

        return from.isLowerThan(to) && inVerticalRange;
    }
}
