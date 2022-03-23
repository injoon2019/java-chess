package chess.domain.piece;

import chess.domain.position.Position;

public abstract class AbstractPiece {

    private final PieceColor pieceColor;
    private final PieceType pieceType;

    public AbstractPiece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return pieceType.isMovable(from, to);
    }
}
