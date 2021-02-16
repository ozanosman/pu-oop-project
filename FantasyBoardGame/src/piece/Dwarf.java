package piece;

import java.awt.*;

public class Dwarf extends Piece
{
    public Dwarf(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor, "D", 6, 2, 12, 2, 2);
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
