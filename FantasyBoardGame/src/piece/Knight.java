package piece;

import java.awt.*;

public class Knight extends Piece
{
    public Knight(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor, "K", 8, 3, 15, 1, 1);
    }

    @Override
    public boolean isMoveValid(int moveRow, int moveCol)
    {
        return false;
    }

    @Override
    public boolean isAttackValid(int attackRow, int attackCol)
    {
        return false;
    }
}